package org.resala.Service.Call;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Auth.Response;
import org.resala.Models.Branch;
import org.resala.Models.Call.CallResult;
import org.resala.Models.Call.CallType;
import org.resala.Models.Call.Calls;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Models.Volunteer.VolunteerAssignedCallsToEvent;
import org.resala.Pair;
import org.resala.Projections.Calls.CallsPublicInfoProjection;
import org.resala.Repository.Call.CallsRepo;
import org.resala.Service.BranchService;
import org.resala.Service.Event.EventService;
import org.resala.Service.Volunteer.VolunteerAssignedCallsToEventService;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.StaticNames;
import org.resala.dto.Call.*;
import org.resala.dto.Event.EventDTO;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    VolunteerAssignedCallsToEventService volunteerAssignedCallsToEventService;

    @Autowired
    CallResultService callResultService;


    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }


    public ResponseEntity<Object> assignCalls(List<VolunteerToCallsDTO> volunteerToCallsDTO) {

        EventDTO eventDTO = volunteerToCallsDTO.get(0).getEvent();
        for (VolunteerToCallsDTO volunteerDtos : volunteerToCallsDTO) {
            VolunteerDTO volunteerDTO = volunteerDtos.getVolunteer();
            List<NetworkTypeDTO> networkTypeDTO = volunteerDtos.getNetworkType();


            volunteerAssignedCallsToEventService.update(volunteerDTO, eventDTO, networkTypeDTO);
        }
        return ResponseEntity.ok(new Response(StaticNames.updatedSuccessfully, HttpStatus.OK.value()));
    }

    public ResponseEntity<Object> confirmAssignedCalls(boolean balanced ,EventDTO eventDTO) {

        Event event = eventService.getById(eventDTO.getId());
        List<Branch> branches=event.getBranches();

        List<VolunteerAssignedCallsToEvent> volunteerAssignedCallsToEvents =
                    volunteerAssignedCallsToEventService.getByEventId(event.getId());

//        System.out.println("volunteerAssignedCallsToEvents size is " + volunteerAssignedCallsToEvents.size());
//        System.out.println("branch size is "+branches.size());
        List<Pair<Volunteer, Integer>> counts = new ArrayList<>();
        List<Calls> calls = new ArrayList<>();

        int callersCount=0,callsCount=0;

        for(VolunteerAssignedCallsToEvent volunteerAssignedCallsToEvent : volunteerAssignedCallsToEvents){
            Volunteer caller=volunteerAssignedCallsToEvent.getVolunteer();
//            System.out.println("caller id is " + caller.getId());
            List<NetworkType> networkType = volunteerAssignedCallsToEvent.getNetworkTypeList();
//            System.out.println("network type size is " + networkType.size());
            List<Volunteer> invitedVolunteers = volunteerService.
                    getVolunteersByBranchAndNetworkType(branches,networkType);
//            System.out.println("invited volunteers size is " + invitedVolunteers.size());
            calls.addAll(fillCallData(caller,invitedVolunteers,event));

            callersCount++;
            callsCount+=invitedVolunteers.size();
            counts.add(new Pair<>(caller,invitedVolunteers.size()));
        }


        int callsCounter = callsCount / callersCount + callersCount;

        if(balanced){
            Collections.sort(counts, new Comparator<Pair<Volunteer, Integer>>() {
                @Override
                public int compare(Pair<Volunteer, Integer> o1, Pair<Volunteer, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });     //Descending sort


            Pair<Volunteer, Integer> pair;
            for (Calls call : calls) {
                boolean changeCaller = false;
                for (int idx = 0; idx < counts.size(); ++idx) {
                    pair = counts.get(idx);
                    if (call.getCaller().equals(pair.getKey())) {
                        if (pair.getValue() > callsCounter) {
                            changeCaller = true;
                            counts.set(idx, new Pair<>(pair.getKey(), pair.getValue() - 1));
                        } else {
                            break;
                        }
                    } else if (changeCaller) {
                        if (pair.getValue() < callsCounter) {
                            call.setCaller(pair.getKey());
//                            System.out.println("call "+call.getId() +" caller setted to "+volunteers.get(idx).getId());
                            counts.set(idx, new Pair<>(pair.getKey(), pair.getValue() + 1));
                            break;
                        }
                    }
                }
            }
        }
//        System.out.println("size is " + calls.size());
        for(Calls call:calls)
            callsRepo.save(call);

        return ResponseEntity.ok(new Response(StaticNames.assignedSuccessfully, HttpStatus.OK.value()));
    }



    private List<Calls> fillCallData(Volunteer caller , List<Volunteer> receivers, Event event) {
        List<Calls> calls=new ArrayList<>();
        for(Volunteer receiver:receivers) {
            Calls call = new Calls();
            call.setBranch(receiver.getBranch());
            call.setReceiver(receiver);
            call.setTimeUnEditableBefore(event.getCallsStartTime());
            call.setNetworkType(networkTypeService.
                    getNetworkTypeBasedOnVolunteerNumber(receiver.getPhoneNumber()));
            call.setCaller(caller);
            call.setEvent(event);
            call.setCallType(callTypeService.getCallTypeByName(StaticNames.invitation));
            calls.add(call);
        }
        return calls;
    }

    public List<CallsPublicInfoProjection> getAssignedCalls(VolunteerToCallsDTO volunteerToCallsDTO) {
        Volunteer volunteer = volunteerService.getById(volunteerToCallsDTO.getVolunteer().getId());
        CallType callType = callTypeService.getCallTypeById(volunteerToCallsDTO.getCallType().getId());
        Event event = eventService.getById(volunteerToCallsDTO.getEvent().getId());

//        if(event.isEnded()) throw new MyEntityNotFoundException("this event "+StaticNames.notFound);

        return callsRepo.findAllByCaller_IdAndCallType_IdAndEvent_Id(
                volunteer.getId(), callType.getId(),event.getId(), CallsPublicInfoProjection.class);
    }


    public ResponseEntity<Object> submitAssignedCalls(SubmitCallDTO submitCallDTO) {

        int callId=submitCallDTO.getCallId();
        CallTypeDTO callTypeDTO=submitCallDTO.getCallTypeDTO();
        String comment = submitCallDTO.getComment();
        CallResultDTO callResultDto = submitCallDTO.getCallResultDTO();

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


}
