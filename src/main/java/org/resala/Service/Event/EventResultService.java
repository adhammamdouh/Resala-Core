package org.resala.Service.Event;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Branch;
import org.resala.Models.Call.CallResult;
import org.resala.Models.Event.Attendance.AttendanceStatus;
import org.resala.Models.Event.Attendance.EventAttendance;
import org.resala.Models.Event.Event;
import org.resala.Models.Event.EventResult;
import org.resala.Repository.Event.EventResultRepo;
import org.resala.Service.Call.CallResultService;
import org.resala.Service.Call.CallsService;
import org.resala.Service.Event.Attendance.AttendanceStatusService;
import org.resala.Service.Event.Attendance.EventAttendanceService;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventResultService {
    @Autowired
    EventResultRepo eventResultRepo;
    @Autowired
    EventService eventService;
    @Autowired
    EventAttendanceService eventAttendanceService;
    @Autowired
    CallsService callsService;
    @Autowired
    CallResultService callResultService;
    @Autowired
    AttendanceStatusService attendanceStatusService;
    public void generateKPIsForAll() {
        List<Event>events=eventService.getAll();
        for (Event event:events){
            update(event);
        }
    }
    public void update(Event event) {
        for (Branch branch:event.getBranches()){
            EventResult eventResult;
            try {
                eventResult=getByEventAndBranch(event,branch);
                continue;
            }catch (MyEntityNotFoundException ex){
                eventResult=new EventResult();
            }
            double responsePercentage=callsService.getResponsePercentageByEventAndBranch(event,branch);
            double attendancePercentage=eventAttendanceService.getAttendancePercentageByEventAndBranch(event,branch);///////
            AttendanceStatus attendanceStatus=attendanceStatusService.getByName(StaticNames.attendedTheEvent);
            int percentCount=eventAttendanceService.countAllByEventAndBranch(event,branch,attendanceStatus);
            ///need attracting
            int callsCount=callsService.countAllCalledByEventAndBranch(event,branch);
            CallResult callResult=callResultService.getByName(StaticNames.callEnsure);
            double ensurePercentage=callsService.countAllByEventAndBranchAndCallResult(event,branch,callResult);

            eventResult.setResponsePercentage(responsePercentage);
            eventResult.setAttendancePercentage(attendancePercentage);
            eventResult.setPresentCount(percentCount);
            eventResult.setCallsCount(callsCount);
            eventResult.setEnsurePercentage(ensurePercentage);
            eventResultRepo.save(eventResult);
        }
    }
    public EventResult getByEventAndBranch(Event event,Branch branch){
        Optional<EventResult>eventResultOptional=eventResultRepo.getByEventAndBranch(event,branch);
        if(!eventResultOptional.isPresent())
            throw new MyEntityNotFoundException("EventResult "+StaticNames.notFound);
        return eventResultOptional.get();
    }

}
