package org.resala.Service.KPI;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.KPI.LeadVolunteerKPI;
import org.resala.Models.Volunteer.LeadVolunteer;
import org.resala.Repository.KPI.LeadVolunteerKPIRepo;
import org.resala.Service.Call.CallsService;
import org.resala.Service.Event.Attendance.EventAttendanceService;
import org.resala.Service.Volunteer.LeadVolunteerService;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadVolunteerKPIService {
    @Autowired
    LeadVolunteerKPIRepo leadVolunteerKPIRepo;
    @Autowired
    EventAttendanceService eventAttendanceService;
    @Autowired
    LeadVolunteerService leadVolunteerService;
    @Autowired
    CallsService callsService;


    public void generateKPIsForAll() {
        List<LeadVolunteer> leadVolunteers = leadVolunteerService.getAll(LeadVolunteer.class);
        for (LeadVolunteer leadVolunteer : leadVolunteers) {
            update(leadVolunteer);
        }
    }

    public void update(LeadVolunteer leadVolunteer) {
        LeadVolunteerKPI leadVolunteerKPI;
        try {
            leadVolunteerKPI = getByLeadVolunteer(leadVolunteer);
        } catch (MyEntityNotFoundException e) {
            leadVolunteerKPI = new LeadVolunteerKPI();
        }
        int presentCount = eventAttendanceService.countPresentForCaller(leadVolunteer, StaticNames.attendedTheEvent);
        int callCount = callsService.countByCallerAndCalled(leadVolunteer);
        int callEnsureCount = callsService.countByCallerAndCallResult(leadVolunteer, StaticNames.callEnsure);
        /*System.out.println(presentCount);
        System.out.println(callCount);
        System.out.println(callEnsureCount);*/
        leadVolunteerKPI.setLeadVolunteer(leadVolunteer);
        leadVolunteerKPI.setPresentCount(presentCount);
        leadVolunteerKPI.setCallsCount(callCount);
        leadVolunteerKPI.setEnsureCount(callEnsureCount);
        leadVolunteerKPIRepo.save(leadVolunteerKPI);
        if (leadVolunteer.getLeadVolunteerKPI()==null){
            leadVolunteerService.setNewKPI(leadVolunteer,getByLeadVolunteer(leadVolunteer));
        }
    }

    public LeadVolunteerKPI getByLeadVolunteer(LeadVolunteer leadVolunteer) {
        Optional<LeadVolunteerKPI> leadVolunteerKPIOptional = leadVolunteerKPIRepo.findByLeadVolunteer(leadVolunteer);
        if (leadVolunteerKPIOptional.isEmpty())
            throw new MyEntityNotFoundException("LeadVolunteerKPI " + StaticNames.notFound);
        return leadVolunteerKPIOptional.get();
    }
}
