package org.resala.Service.Call;

import org.modelmapper.ModelMapper;
import org.resala.Models.Auth.Response;
import org.resala.Models.Branch;
import org.resala.Models.Call.CallResult;
import org.resala.Models.Call.CallType;
import org.resala.Models.Call.Calls;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Models.Volunteer.NetworkTypeAssignedToVolunteersToEvent;
import org.resala.Pair;
import org.resala.Projections.Calls.CallsPublicInfoProjection;
import org.resala.Repository.Call.CallsRepo;
import org.resala.Service.BranchService;
import org.resala.Service.Event.EventService;
import org.resala.Service.Volunteer.NetworkTypeAssignedToVolunteersToEventService;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.StaticNames;
import org.resala.dto.Call.*;
import org.resala.dto.Event.EventDTO;
import org.resala.dto.Volunteer.NetworkTypeAssignedToVolunteersToEventDTO;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

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
    NetworkTypeAssignedToVolunteersToEventService networkTypeAssignedToVolunteersToEventService;

    @Autowired
    CallResultService callResultService;


    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }


    public ResponseEntity<Object> assignCalls(VolunteerToCallsDTO volunteerToCallsDTO) {

        EventDTO eventDTO = volunteerToCallsDTO.getEvent();
        for (NetworkTypeAssignedToVolunteersToEventDTO networkTypeAssignedToVolunteersToEventDTO
                : volunteerToCallsDTO.getNetworkTypeAssignedToVolunteersToEvents()) {
            List<VolunteerDTO> volunteerDTO = networkTypeAssignedToVolunteersToEventDTO.getVolunteers();
            NetworkTypeDTO networkTypeDTO = networkTypeAssignedToVolunteersToEventDTO.getNetworkType();


            networkTypeAssignedToVolunteersToEventService.update(volunteerDTO, eventDTO, networkTypeDTO);
        }
        return ResponseEntity.ok(new Response(StaticNames.updatedSuccessfully, HttpStatus.OK.value()));
    }

    public ResponseEntity<Object> confirmAssignedCalls(boolean balanced, EventDTO eventDTO) {

        Event event = eventService.getById(eventDTO.getId());
        int branchId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        Branch branch = branchService.getById(branchId);

        List<NetworkTypeAssignedToVolunteersToEvent> networkTypeAssignedToVolunteersToEvents =
                networkTypeAssignedToVolunteersToEventService.getByEventId(event.getId());


        List<Pair<Volunteer, Integer>> counts = new ArrayList<>();
        List<Calls> calls = new ArrayList<>();
        Map<Volunteer, Integer> map = new HashMap<>();

        int callersCount = 0, callsCount = 0;


        for (NetworkTypeAssignedToVolunteersToEvent networkTypeAssignedToVolunteersToEvent :
                networkTypeAssignedToVolunteersToEvents) {
            List<Volunteer> caller = networkTypeAssignedToVolunteersToEvent.getVolunteers();

//            System.out.println( "callers size is " +  caller.size());
            NetworkType networkType = networkTypeAssignedToVolunteersToEvent.getNetworkType();

//            System.out.println("network type id is " + networkType.getId());

            List<Volunteer> receivers = volunteerService.
                    getVolunteersByBranchAndNetworkType(branch, networkType);

//            System.out.println("bte5");
            calls.addAll(fillCallData(caller, receivers, event, map));

            callersCount += caller.size();
            callsCount += receivers.size();
        }


        int callsPerCaller = (callsCount + (callersCount - 1)) / callersCount;
//        System.out.println("calls count is " + callsCount);
//        System.out.println("callers count is " + callersCount);
//        System.out.println("calls per caller is " + callsPerCaller);

        if (balanced) {
//            System.out.println("bte5");
            assignBalancedCalls(map, calls, callsPerCaller);
        }

        callsRepo.saveAll(calls);

        return ResponseEntity.ok(new Response(StaticNames.assignedSuccessfully, HttpStatus.OK.value()));
    }

    public void assignBalancedCalls(Map<Volunteer, Integer> map, List<Calls> calls, int callsPerCaller) {

//        System.out.println("size " + map.size());
        Map<Volunteer, Integer> sortedMap = new HashMap<>();
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        Iterator<Map.Entry<Volunteer, Integer>> itr = sortedMap.entrySet().iterator();
        Map.Entry<Volunteer, Integer> entry = itr.next();

        for (Calls call : calls) {
            System.out.println(entry.getKey().getId() + " " + entry.getValue());
            while (entry.getValue() >= callsPerCaller) {
                if (itr.hasNext())
                    entry = itr.next();
                else {
                    entry = null;
                    break;
                }
            }
            if (entry == null) break;

            if (map.get(call.getCaller()) > callsPerCaller) {
                entry.setValue(entry.getValue() + 1);
//                System.out.println("map "+map.get(entry.getKey()));
                map.replace(call.getCaller(), map.get(call.getCaller()) - 1);
                call.setCaller(entry.getKey());
            }


        }


    }


    private List<Calls> fillCallData(List<Volunteer> callers, List<Volunteer> receivers, Event event,
                                     Map<Volunteer, Integer> map) {
        List<Calls> calls = new ArrayList<>();
        int receiverCount = receivers.size();
        int callsPerCaller = (receiverCount + (callers.size() - 1)) / callers.size();

        for (int receiverIdx = 0, callerIdx = 0; receiverIdx < receiverCount; ++receiverIdx) {

            if (receiverIdx != 0 && receiverIdx % callsPerCaller == 0) callerIdx++;

            Calls call = new Calls();
            Volunteer receiver = receivers.get(receiverIdx), caller = callers.get(callerIdx);

//            System.out.println(receiver.getId());
//            System.out.println(receiver.getPhoneNumber());
//            System.out.println(receiver.getNetworkType().getId());

            call.setBranch(receiver.getBranch());
            call.setReceiver(receiver);
            call.setTimeUnEditableBefore(event.getCallsStartTime());
            call.setNetworkType(receiver.getNetworkType());
            call.setCaller(caller);
            call.setEvent(event);
//            System.out.println("bte5 tany");
            call.setCallType(callTypeService.getCallTypeByName(StaticNames.invitation));
//            System.out.println("map contains the caller  " + caller.getId());
            map.put(caller, map.getOrDefault(caller, 0) + 1);

            calls.add(call);
        }
        return calls;
    }

    public List<CallsPublicInfoProjection> getAssignedCalls(VolunteerToCallsDTO volunteerToCallsDTO) {
        volunteerToCallsDTO.checkNullForGetAssigned();
        Volunteer volunteer = volunteerService.getById(1);//volunteerToCallsDTO.getVolunteer().getId());
        CallType callType = callTypeService.getCallTypeById(volunteerToCallsDTO.getCallType().getId());
        Event event = eventService.getById(volunteerToCallsDTO.getEvent().getId());

//        if(event.isEnded()) throw new MyEntityNotFoundException("this event "+StaticNames.notFound);

        return callsRepo.findAllByCaller_IdAndCallType_IdAndEvent_Id(
                volunteer.getId(), callType.getId(), event.getId(), CallsPublicInfoProjection.class);
    }


    public ResponseEntity<Object> submitAssignedCalls(SubmitCallDTO submitCallDTO) {

        int callId = submitCallDTO.getCallId();
        CallTypeDTO callTypeDTO = submitCallDTO.getCallType();
        String comment = submitCallDTO.getComment();
        CallResultDTO callResultDto = submitCallDTO.getCallResult();

        CallResult callResult = callResultService.getById(callResultDto.getId());
        Calls call = callsRepo.findById(callId);
        CallType callType = callTypeService.getCallTypeById(callTypeDTO.getId());
        switch (callType.getName()) {
            case StaticNames.invitation:
                call.setInvitationComment(comment);
                call.setCallResult(callResult);
                call.setInvitationTime(new Date(System.currentTimeMillis()));
                //call.setCallType(callTypeService.getCallTypeByName(StaticNames.feedBack));
                break;
            case StaticNames.feedBack:
                call.setFeedBackComment(comment);
                call.setFeedBackTime(new Date(System.currentTimeMillis()));
                break;
            case StaticNames.notAttend:
                call.setNotAttendComment(comment);
                call.setNotAttendTime(new Date(System.currentTimeMillis()));
                break;
        }

        callsRepo.save(call);

        return ResponseEntity.ok(new Response(StaticNames.submittedSuccessfully, HttpStatus.OK.value()));
    }
    public int countByReceiverAndDoesNotCalled(Volunteer receiver){
        CallResult callResult=callResultService.getByName(StaticNames.doesNotCalled);
        return callsRepo.countAllByReceiverAndCallResultNot(receiver,callResult);
    }
    public int countByReceiverAndCallResult(Volunteer receiver,String callResultName){
        CallResult callResult=callResultService.getByName(callResultName);
        return callsRepo.countAllByReceiverAndCallResult(receiver,callResult);
    }
    public int countAllResponseByReceiver(Volunteer receiver){
        CallResult callResult=callResultService.getByName(StaticNames.didNotAnswer);
        CallResult callResult2=callResultService.getByName(StaticNames.doesNotCalled);
        return callsRepo.countAllByReceiverAndCallResultNotAndCallResultNot(receiver,callResult,callResult2);
    }

}
