package org.resala.Service.KPI;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.KPI.VolunteerKPI;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Repository.KPI.VolunteerKPIRepo;
import org.resala.Service.Call.CallsService;
import org.resala.Service.Event.Attendance.EventAttendanceService;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class VolunteerKPIService {
    @Autowired
    VolunteerKPIRepo volunteerKPIRepo;
    @Autowired
    EventAttendanceService eventAttendanceService;
    @Autowired
    VolunteerService volunteerService;
    @Autowired
    CallsService callsService;
    public void generateKPIsForAll(){
        List<Volunteer>volunteers=volunteerService.getAllNormal();
        for (Volunteer volunteer : volunteers){
            update(volunteer);
        }
    }
    public void update(Volunteer volunteer){
        VolunteerKPI volunteerKPI;
        try {
            volunteerKPI=getByVolunteer(volunteer);
        }catch (MyEntityNotFoundException e){
            volunteerKPI=new VolunteerKPI();
        }
        int presentCount=eventAttendanceService.countVolunteerAttendance(volunteer,StaticNames.attendedTheEvent);
        int callCount=callsService.countHowManyTimedBeReceiver(volunteer);
        int callEnsureCount=callsService.countByReceiverAndCallResult(volunteer,StaticNames.callEnsure);
        int callResponseCount=callsService.countAllResponseByReceiver(volunteer);
        volunteerKPI.setVolunteer(volunteer);
        volunteerKPI.setPresentCount(presentCount);
        volunteerKPI.setCallsCount(callCount);
        volunteerKPI.setEnsureCount(callEnsureCount);
        volunteerKPI.setResponseCount(callResponseCount);
        volunteerKPIRepo.save(volunteerKPI);
        if (volunteer.getVolunteerKPI()==null){
            volunteerService.setNewKPI(volunteer,getByVolunteer(volunteer));
        }

    }
    public VolunteerKPI getByVolunteer(Volunteer volunteer){
        Optional<VolunteerKPI> volunteerKPIOptional=volunteerKPIRepo.findByVolunteer(volunteer);
        if(volunteerKPIOptional.isEmpty())
            throw new MyEntityNotFoundException("VolunteerKPI "+ StaticNames.notFound);
        return volunteerKPIOptional.get();
    }
}