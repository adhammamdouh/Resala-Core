package org.resala.Service.Call;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.ConstraintViolationException;
import org.resala.Exceptions.MyEntityFoundBeforeException;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Auth.Response;
import org.resala.Models.Branch;
import org.resala.Models.Call.CallResult;
import org.resala.Models.Call.Calls;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Event.Attendance.AttendanceStatus;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.NetworkAssignedToVolunteers;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Projections.Calls.CallsPublicInfoProjection;
import org.resala.Projections.Calls.CallsPublicInfoProjectionWithCaller;
import org.resala.Repository.Call.CallsRepo;
import org.resala.Service.BranchService;
import org.resala.Service.Event.Attendance.AttendanceStatusService;
import org.resala.Service.Event.EventService;
import org.resala.Service.IssTokenService;
import org.resala.Service.Volunteer.NetworkAssignedToVolunteersService;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.StaticNames;
import org.resala.dto.Call.CallResultDTO;
import org.resala.dto.Call.SubmitCallDTO;
import org.resala.dto.Volunteer.NetworkAssignedToVolunteersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CallsService {
    @Autowired
    NetworkTypeService networkTypeService;

    @Autowired
    CallTypeService callTypeService;

    @Autowired
    CallsRepo callsRepo;

    @Autowired
    VolunteerService volunteerService;

    @Autowired
    EventService eventService;

    @Autowired
    BranchService branchService;

    @Autowired
    NetworkAssignedToVolunteersService networkAssignedToVolunteersService;

    @Autowired
    CallResultService callResultService;

    @Autowired
    AttendanceStatusService attendanceStatusService;


    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }
    public  <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> modelMapper().map(entity, outCLass))
                .collect(Collectors.toList());
    }


    public ResponseEntity<Object> confirmAssignedCalls(boolean balanced, NetworkAssignedToVolunteersDTO dto) {

        int branchId = IssTokenService.getBranchId();

        Event event = eventService.getById(dto.getEvent().getId());
        Branch branch = branchService.getById(branchId);

        if(!eventService.checkEventStatus(event))
            throw new ConstraintViolationException(StaticNames.eventIsNotActive);

        if (callsRepo.countAllByEventAndBranch(event, branch) > 0) {
            throw new MyEntityFoundBeforeException(StaticNames.callsHasBeenCreatedBefore);
        }

        List<NetworkAssignedToVolunteers> networkAssignedToVolunteers =
                networkAssignedToVolunteersService.getByEventIdAndBranchId(event.getId(), branch.getId());

        if(networkAssignedToVolunteers == null || networkAssignedToVolunteers.size()==0){
            throw new MyEntityFoundBeforeException(StaticNames.thereIsNoAssignedCallsToConfirm);
        }

        List<Calls> calls = new ArrayList<>();
        //map between caller and number of his calls
        Map<Volunteer, Integer> map = new HashMap<>();

        int callersCount = 0, callsCount = 0;


        for (NetworkAssignedToVolunteers networkAssignedToVolunteer :
                networkAssignedToVolunteers) {

            Volunteer caller = networkAssignedToVolunteer.getVolunteer();

            NetworkType networkType = networkAssignedToVolunteer.getNetworkType();

            List<Volunteer> receivers = volunteerService.
                    getVolunteersByBranchAndNetworkType(branch, networkType);


            calls.addAll(fillCallData(caller, receivers, event, map));

            callersCount += networkAssignedToVolunteers.size();
            callsCount += receivers.size();
        }

        //ceil(callsCount/callersCount)
        int callsPerCaller = (callsCount + (callersCount - 1)) / callersCount;

        if (balanced) {
            assignBalancedCalls(map, calls, callsPerCaller);
        }

        callsRepo.saveAll(calls);

        return ResponseEntity.ok(new Response(StaticNames.assignedSuccessfully, HttpStatus.OK.value()));
    }

    public void assignBalancedCalls(Map<Volunteer, Integer> map, List<Calls> calls, int callsPerCaller) {

        //sort by value (each call for caller) in ascending order
        Map<Volunteer, Integer> sortedCallers = new HashMap<>();
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedCallers.put(x.getKey(), x.getValue()));

        Iterator<Map.Entry<Volunteer, Integer>> itr = sortedCallers.entrySet().iterator();
        Map.Entry<Volunteer, Integer> currentCaller;
        try {
            currentCaller = itr.next();
        } catch (Exception e) {
            throw new MyEntityNotFoundException(StaticNames.thereIsNoCallsToBalance);
        }

        for (Calls call : calls) {
            while (currentCaller.getValue() >= callsPerCaller) {
                if (itr.hasNext())
                    currentCaller = itr.next();
                else {
                    currentCaller = null;
                    break;
                }
            }
            if (currentCaller == null) break;

            //transfer this caller call to the current caller if his calls is bigger than the calls per caller cap
            if (map.get(call.getCaller()) > callsPerCaller) {
                //increase current caller calls by 1
                currentCaller.setValue(currentCaller.getValue() + 1);
                //decrease the changed caller calls by 1
                map.replace(call.getCaller(), map.get(call.getCaller()) - 1);
                //change the call caller
                call.setCaller(currentCaller.getKey());
            }
        }
    }


    private List<Calls> fillCallData(Volunteer caller, List<Volunteer> receivers, Event event,
                                     Map<Volunteer, Integer> map) {
        List<Calls> calls = new ArrayList<>();
        int receiverCount = receivers.size();
//        int callsPerCaller = (receiverCount + (callers.size() - 1)) / callers.size();
//        CallResult callResult = callResultService.getByName(StaticNames.doesNotCalled);

        for (int receiverIdx = 0; receiverIdx < receiverCount; ++receiverIdx) {

            Calls call = new Calls();
            Volunteer receiver = receivers.get(receiverIdx);


            call.setBranch(receiver.getBranch());
            call.setReceiver(receiver);
            call.setInvitationUnEditableBefore(event.getInvitationStartTime());
            call.setFeedBackUnEditableBefore(event.getFeedBackStartTime());
            call.setNotAttendUnEditableBefore(event.getNotAttendStartTime());
            call.setNetworkType(receiver.getNetworkType());
            call.setCaller(caller);
            call.setEvent(event);

            call.setCallType(callTypeService.getCallTypeByName(StaticNames.invitation));

            //relation between caller and number of calls he make
            map.put(caller, map.getOrDefault(caller, 0) + 1);

            calls.add(call);
        }
        return calls;
    }

    public ResponseEntity<Object> getAssignedCalls(NetworkAssignedToVolunteersDTO networkAssignedToVolunteersDTO) {
        Volunteer volunteer = volunteerService.getById(networkAssignedToVolunteersDTO.getVolunteer().getId());
        Event event = eventService.getById(networkAssignedToVolunteersDTO.getEvent().getId());

        if(!eventService.checkEventStatus(event)) {
            if (event.getEventStatus().equals(StaticNames.completedState))
                return ResponseEntity.ok(new Response(mapAll(callsRepo.findAllByBranch_IdAndEvent_id(
                        IssTokenService.getBranchId(), event.getId()),CallsPublicInfoProjectionWithCaller.class),HttpStatus.OK.value()));
            else throw new RuntimeException(StaticNames.cantGetEventCalls);
        }

        Date currentDate = new Date(System.currentTimeMillis());

        if (currentDate.after(event.getFeedBackStartTime()) && currentDate.before((event.getFeedBackEndTime()))) {
            if (!volunteer.getRole().getName().equals(StaticNames.TeamLeader)) {
                throw new RuntimeException(StaticNames.cantGetFeedBackCalls);
            }
            AttendanceStatus attendanceStatus = attendanceStatusService.getByName(StaticNames.attendedTheEvent);
            List<Calls>calls= callsRepo.findAllByAttendanceStatusAndEvent_Id(attendanceStatus,event.getId());
            for(Calls call:calls) call.setCallType(callTypeService.getCallTypeByName(StaticNames.feedBack));
            callsRepo.saveAll(calls);
            return ResponseEntity.ok(new Response(mapAll(calls,CallsPublicInfoProjection.class),HttpStatus.OK.value()));
        }

        if (currentDate.after(event.getInvitationStartTime()) && currentDate.before((event.getInvitationEndTime()))) {
            return ResponseEntity.ok(new Response(callsRepo.findAllByCaller_IdAndEvent_Id(volunteer.getId(), event.getId(), CallsPublicInfoProjection.class),HttpStatus.OK.value()));
        }

        if (currentDate.after(event.getNotAttendStartTime()) && currentDate.before((event.getNotAttendEndTime()))) {
            AttendanceStatus attendanceStatus = attendanceStatusService.getByName(StaticNames.notAttendedTheEvent);
            List<Calls>calls= callsRepo.findAllByAttendanceStatusAndEvent_Id(attendanceStatus,event.getId());
            for(Calls call:calls) call.setCallType(callTypeService.getCallTypeByName(StaticNames.notAttend));
            callsRepo.saveAll(calls);
            return ResponseEntity.ok(new Response(mapAll(calls,CallsPublicInfoProjection.class),HttpStatus.OK.value()));
        }
        throw new RuntimeException(StaticNames.cantGetEventCalls);
    }


    public ResponseEntity<Object> submitAssignedCalls(SubmitCallDTO submitCallDTO) {

        int callId = submitCallDTO.getCallId();
        String comment = submitCallDTO.getComment();
        CallResultDTO callResultDto = submitCallDTO.getCallResult();

        CallResult callResult = callResultService.getById(callResultDto.getId());
        Calls call = callsRepo.findById(callId);

        Event event = call.getEvent();

        if(!eventService.checkEventStatus(event))
            throw new ConstraintViolationException(StaticNames.eventIsNotActive);

        Date currentDate = new Date(System.currentTimeMillis());


        if (currentDate.after(event.getInvitationStartTime()) && currentDate.before((event.getInvitationEndTime()))) {
            call.setInvitationComment(comment);
            call.setInvitationTime(currentDate);
            call.setInvitationCallResult(callResult);
        }

        else if (currentDate.after(event.getFeedBackStartTime()) && currentDate.before((event.getFeedBackEndTime()))) {
            call.setFeedBackComment(comment);
            call.setFeedBackTime(currentDate);
            call.setFeedBackCallResult(callResult);
        }

        else if (currentDate.after(event.getNotAttendStartTime()) && currentDate.before((event.getNotAttendEndTime()))) {
            call.setNotAttendComment(comment);
            call.setNotAttendTime(currentDate);
            call.setNotAttendCallResult(callResult);
        }

        else {
            throw new RuntimeException(StaticNames.cantSubmitNow);
        }

        callsRepo.save(call);

        return ResponseEntity.ok(new Response(StaticNames.submittedSuccessfully, HttpStatus.OK.value()));
    }

    public int countByReceiverAndCalled(Volunteer receiver) {
        CallResult callResult = callResultService.getByName(StaticNames.doesNotCalled);
        return callsRepo.countAllByReceiverAndInvitationCallResultNot(receiver, callResult);
    }

    public int countByReceiverAndCallResult(Volunteer receiver, String callResultName) {
        CallResult callResult = callResultService.getByName(callResultName);
        return callsRepo.countAllByReceiverAndInvitationCallResult(receiver, callResult);
    }

    public int countAllResponseByReceiver(Volunteer receiver) {
        CallResult callResult = callResultService.getByName(StaticNames.didNotAnswer);
        CallResult callResult2 = callResultService.getByName(StaticNames.doesNotCalled);
        return callsRepo.countAllByReceiverAndInvitationCallResultNotAndInvitationCallResultNot(receiver, callResult, callResult2);
    }


    public int countByCallerAndCalled(Volunteer caller) {
        CallResult callResult = callResultService.getByName(StaticNames.doesNotCalled);
        return callsRepo.countAllByCallerAndInvitationCallResultNot(caller, callResult);
    }

    public int countByCallerAndCallResult(Volunteer caller, String callResultName) {
        CallResult callResult = callResultService.getByName(callResultName);
        return callsRepo.countAllByCallerAndInvitationCallResult(caller, callResult);
    }


    public double getResponsePercentageByEventAndBranch(Event event, Branch branch) {

        return countAllCalledByEventAndBranch(event, branch) /
                ((double) countAllByEventAndBranch(event, branch) + 1.0);
    }

    public int countAllByEventAndBranch(Event event, Branch branch) {
        return callsRepo.countAllByEventAndBranch(event, branch);
    }

    public int countAllCalledByEventAndBranch(Event event, Branch branch) {
        CallResult callResult1 = callResultService.getByName(StaticNames.didNotAnswer);
        CallResult callResult2 = callResultService.getByName(StaticNames.doesNotCalled);
        return callsRepo.countAllByEventAndBranchAndInvitationCallResultNotAndInvitationCallResultNot(event, branch, callResult1, callResult2);
    }

    public int countAllByEventAndBranchAndCallResult(Event event, Branch branch, CallResult callResult) {

        return callsRepo.countAllByEventAndBranchAndInvitationCallResult(event, branch, callResult);
    }

    public double getAttractingPercentage(Event event, Branch branch) {
        CallResult callResult = callResultService.getByName(StaticNames.firstTimeCall);
        return countAllByEventAndBranchAndCallResult(event, branch, callResult) /
                ((double) countAllByEventAndBranch(event, branch) + 1.0);
    }
}
