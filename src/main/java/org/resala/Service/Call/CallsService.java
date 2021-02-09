package org.resala.Service.Call;

import org.resala.Models.Branch;
import org.resala.Models.Call.CallType;
import org.resala.Models.Call.Calls;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Pair;
import org.resala.Repository.Call.CallsRepo;
import org.resala.Service.BranchService;
import org.resala.Service.Event.EventService;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.StaticNames;
import org.resala.dto.BranchDTO;
import org.resala.dto.Event.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CallsService{
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


    public List<CallType> getCallType(List<Integer> ids){
        return callTypeService.getCallTypeByNames(ids);
    }

    public List<Volunteer> getVolunteerById(List<Integer> ids){
        return volunteerService.getVolunteerByIds(ids);

    }

    public ResponseEntity<Object> assignCalls(List<Integer> volunteerId ,List<Integer> ids ,int branchId ,boolean equality){
        List<CallType> callType=getCallType(ids);
        List<Volunteer> volunteers =  getVolunteerById(volunteerId);
        List<Calls> calls=callsRepo.findAllByBranch_Id(branchId);


        List<Pair<Volunteer,Integer>> counts = new ArrayList<Pair<Volunteer,Integer>>();
        for(int i=0;i<volunteers.size();++i) counts.add(i,new Pair(volunteers.get(i),0));

        int callsCounter=calls.size()/volunteers.size();

        for(int i=0;i<calls.size();++i){
            for(int idx =0;idx<callType.size();++idx){
                if(calls.get(i).getCallType().equals(callType.get(idx))){
                    calls.get(i).setCaller(volunteers.get(idx));
                    counts.set(idx,new Pair(counts.get(idx).getKey(),counts.get(idx).getValue()+1));
                }
            }
        }

        if(equality) {
            Collections.sort(counts, new Comparator<Pair<Volunteer, Integer>>() {
                @Override
                public int compare(Pair<Volunteer, Integer> o1, Pair<Volunteer, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });     //Descending sort




            Pair<Volunteer, Integer> pair= new Pair<>();
            for (Calls call : calls) {
                boolean changeCaller = false;
                for (int idx = 0; idx < counts.size(); ++idx) {
                    pair = counts.get(idx);
                    if (call.getCaller().equals(pair.getKey())) {
                        if (pair.getValue() > callsCounter ) {
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
                        }
                    }
                }
            }
        }
        for(Calls call : calls){
            callsRepo.save(call);
        }

        return ResponseEntity.ok(StaticNames.callAssignedSuccessfully);
    }


    public void createCalls(List<BranchDTO> branches, Event event) {
        List<Volunteer> volunteers=new ArrayList<>();
        Calls calls = new Calls();
        for(BranchDTO branch : branches){
            volunteers = volunteerService.getVolunteersByBranch(branch.getId());
            fillCallData(volunteers,event,branch);
        }
    }

    public void fillCallData(List<Volunteer> volunteers , Event event ,BranchDTO branchDTO) {
        Calls call = new Calls();
        Branch branch = branchService.modelMapper().map(branchDTO,Branch.class);
        for(Volunteer volunteer : volunteers){
            call.setBranch(branch);
            call.setReceiver(volunteer);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                call.setTimeUnEditableBefore(sdf.parse(event.getCallsStartTime().toString()));
            }
            catch (ParseException p){

            }
            call.setCallType(callTypeService.getCallTypeByName(StaticNames.etisalat));  //HARD CODDED
            call.setEvent(event);
        }
        System.out.println("branch "+call.getBranch().getId());
        System.out.println("volunteer "+call.getReceiver().getId());
        System.out.println("time "+call.getTimeUnEditableBefore());
        System.out.println("type "+call.getCallType().getId());
        System.out.println("event "+call.getEvent().getId());
        callsRepo.save(call);

    }
}
