package org.resala.Service.Call;

import org.aspectj.weaver.ast.Call;
import org.resala.Models.Branch;
import org.resala.Models.Call.CallResult;
import org.resala.Models.Call.CallType;
import org.resala.Models.Call.Calls;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Pair;
import org.resala.Repository.Call.CallsRepo;
import org.resala.Service.BranchService;
import org.resala.Service.Event.EventService;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.StaticNames;
import org.resala.dto.BranchDTO;
import org.resala.dto.Call.CallTypeDTO;
import org.resala.dto.Call.NetworkTypeDTO;
import org.resala.dto.Call.VolunteerToCallsDTO;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
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


    public ResponseEntity<Object> assignCalls(int branchId, boolean equality, VolunteerToCallsDTO data) {

        List<VolunteerDTO> volunteerDtos = data.getVolunteers();
        List<NetworkTypeDTO> networkTypeDtos = data.getNetworkTypes();

        List<Volunteer> volunteers = new ArrayList<>();
        List<NetworkType> networkType = new ArrayList<>();
        List<Calls> calls = callsRepo.findAllByBranch_IdAndEvent_id(branchId, data.getEventId());

        for (VolunteerDTO volunteerDTO : volunteerDtos)
            volunteers.add(
                    volunteerService.get(volunteerDTO.getId()));

        for (NetworkTypeDTO networkTypeDTO : networkTypeDtos)
            networkType.add(networkTypeService.getNetworkTypeById(networkTypeDTO.getId()));

        List<Pair<Volunteer, Integer>> counts = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); ++i) counts.add(i, new Pair(volunteers.get(i), 0));

        int callsCounter = calls.size() / volunteers.size();

        for (int i = 0; i < calls.size(); ++i) {
            for (int idx = 0; idx < networkType.size(); ++idx) {
                if (calls.get(i).getCallType().equals(networkType.get(idx))) {
                    calls.get(i).setCaller(volunteers.get(idx));
                    counts.set(idx, new Pair(counts.get(idx).getKey(), counts.get(idx).getValue() + 1));
                }
            }
        }

        if (equality) {
            Collections.sort(counts, new Comparator<Pair<Volunteer, Integer>>() {
                @Override
                public int compare(Pair<Volunteer, Integer> o1, Pair<Volunteer, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });     //Descending sort


            for (var i : counts) {
                System.out.println("volunteer " + i.getKey().getId());
                System.out.println("count " + i.getValue());
            }

            Pair<Volunteer, Integer> pair = new Pair<>();
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
                            call.setCaller(volunteers.get(idx));
//                            System.out.println("call "+call.getId() +" caller setted to "+volunteers.get(idx).getId());
                            counts.set(idx, new Pair<>(pair.getKey(), pair.getValue() + 1));
                            break;
                        }
                    }
                }
            }
        }

        for (Calls call : calls) {
            callsRepo.save(call);
        }

        return ResponseEntity.ok(StaticNames.callAssignedSuccessfully);
    }


    public void createCalls(List<BranchDTO> branches, Event event) {
        List<Volunteer> volunteers = new ArrayList<>();
        for (BranchDTO branch : branches) {
            volunteers = volunteerService.getVolunteersByBranch(branch.getId());
            fillCallData(volunteers, event, branch);
        }
    }

    private void fillCallData(List<Volunteer> volunteers, Event event, BranchDTO branchDTO) {
        Branch branch = branchService.modelMapper().map(branchDTO, Branch.class);
        for (Volunteer volunteer : volunteers) {
            Calls call = new Calls();
            call.setBranch(branch);
            call.setReceiver(volunteer);
            call.setTimeUnEditableBefore(event.getCallsStartTime());
            call.setNetworkType(networkTypeService.
                    getNetworkTypeBasedOnVolunteerNumber(volunteer.getPhoneNumber()));
            call.setEvent(event);

            call.setCallType(callTypeService.getCallTypeByName(StaticNames.invitation));
            callsRepo.save(call);
        }

    }

    public List<Calls> getAssignedCalls(VolunteerDTO volunteerDTO, CallTypeDTO CallTypeDTO) {
        Volunteer volunteer = volunteerService.get(volunteerDTO.getId());
        CallType callType = callTypeService.getCallTypeByName(CallTypeDTO.getName());
        return callsRepo.findByCallerAndCallType(volunteer, callType);
    }


    public void submitAssignedCalls(int callsId, CallTypeDTO callTypeDTO, String comment, CallResult callResult) {
        Calls call = callsRepo.findById(callsId);
        CallType callType = callTypeService.getCallTypeById(callTypeDTO.getId());
        switch (callType.getName()) {
            case StaticNames.invitation:
                call.setInvitationComment(comment);
                call.setCallResult(callResult);
                call.setInvitationTime(new Date(System.currentTimeMillis()));
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

    }


}
