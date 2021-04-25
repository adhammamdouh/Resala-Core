package org.resala.Service.Event.Attendance;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.MyEntityFoundBeforeException;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Exceptions.NeedToConfirmException;
import org.resala.Models.Auth.Response;
import org.resala.Models.Branch;
import org.resala.Models.Call.CallResult;
import org.resala.Models.Event.Attendance.AttendanceStatus;
import org.resala.Models.Event.Attendance.EventAttendance;
import org.resala.Models.Event.Event;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Repository.Event.Attendance.EventAttendanceRepo;
import org.resala.Service.Call.CallResultService;
import org.resala.Service.Call.CallsService;
import org.resala.Service.DateTimeService;
import org.resala.Service.Event.EventService;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.StaticNames;
import org.resala.dto.Event.EventStatus.EventAttendanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventAttendanceService {
    @Autowired
    EventAttendanceRepo eventAttendanceRepo;
    @Autowired
    EventService eventService;
    @Autowired
    VolunteerService volunteerService;
    @Autowired
    AttendanceStatusService attendanceStatusService;
    @Autowired
    CallResultService callResultService;
    @Autowired
    CallsService callsService;
    private ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    public ResponseEntity<Object> makeAttendanceToVolunteer(EventAttendanceDTO dto) {
        dto.checkNullForAttendance();
        Event event = eventService.getById(dto.getEvent().getId());
        Volunteer volunteer = volunteerService.getById(dto.getVolunteer().getId());
        AttendanceStatus attendanceStatus = attendanceStatusService.getById(dto.getAttendanceStatus().getId());
        EventAttendance eventAttendance;
        try {
            eventAttendance = getByEventAndVolunteer(event.getId(), volunteer.getId());
            if (eventAttendance.getAttendanceStatus().getId() == dto.getAttendanceStatus().getId()) {
                throw new MyEntityFoundBeforeException("Already " + attendanceStatus.getName());
            } else {//need to confirm
                throw new NeedToConfirmException("Need To Confirm");
            }
        } catch (MyEntityNotFoundException e) {
            eventAttendance = modelMapper().map(dto, EventAttendance.class);
        }
        eventAttendance.setEvent(event);
        eventAttendance.setVolunteer(volunteer);
        eventAttendance.setAttendanceStatus(attendanceStatus);
        eventAttendance.setDateTime(DateTimeService.getNow());
        eventAttendanceRepo.save(eventAttendance);
        return ResponseEntity.ok(new Response("Attendance Made Successfully", HttpStatus.OK.value()));
    }

    public ResponseEntity<Object> confirmMakeAttendanceToVolunteer(EventAttendanceDTO dto) {
        dto.checkNullForAttendance();
        Event event = eventService.getById(dto.getEvent().getId());
        Volunteer volunteer = volunteerService.getById(dto.getVolunteer().getId());
        AttendanceStatus attendanceStatus = attendanceStatusService.getById(dto.getAttendanceStatus().getId());
        EventAttendance eventAttendance;
        try {
            eventAttendance = getByEventAndVolunteer(event.getId(), volunteer.getId());
            if (eventAttendance.getAttendanceStatus().getId() == dto.getAttendanceStatus().getId()) {
                throw new MyEntityFoundBeforeException("Already " + attendanceStatus.getName());
            }
        } catch (MyEntityNotFoundException e) {
            eventAttendance = modelMapper().map(dto, EventAttendance.class);
        }
        eventAttendance.setEvent(event);
        eventAttendance.setVolunteer(volunteer);
        eventAttendance.setAttendanceStatus(attendanceStatus);
        eventAttendance.setDateTime(DateTimeService.getNow());
        eventAttendanceRepo.save(eventAttendance);
        return ResponseEntity.ok(new Response("Attendance Made Successfully", HttpStatus.OK.value()));
    }

    public EventAttendance getByEventAndVolunteer(int eventId, int volunteerId) {
        Optional<EventAttendance> eventAttendanceOptional = eventAttendanceRepo.findAllByEvent_IdAndAndVolunteer_Id(eventId, eventId);
        if (!eventAttendanceOptional.isPresent())
            throw new MyEntityNotFoundException("EventAttendance " + StaticNames.notFound);
        return eventAttendanceOptional.get();
    }

    public int countVolunteerAttendance(Volunteer volunteer, String attendanceStatusName) {
        AttendanceStatus attendanceStatus = attendanceStatusService.getByName(attendanceStatusName);
        return eventAttendanceRepo.countAllByVolunteerAndAttendanceStatus(volunteer, attendanceStatus);
    }

    public int countPresentForCaller(Volunteer caller, String attendanceStatusName) {
        AttendanceStatus attendanceStatus = attendanceStatusService.getByName(attendanceStatusName);
        CallResult callResult1=callResultService.getByName(StaticNames.didNotAnswer);
        CallResult callResult2=callResultService.getByName(StaticNames.didNotAnswer);
        return eventAttendanceRepo.countPresentForLead(caller, attendanceStatus,callResult1,callResult2);
    }


    public double getAttendancePercentageByEventAndBranch(Event event, Branch branch) {
        AttendanceStatus attendanceStatus=attendanceStatusService.getByName(StaticNames.attendedTheEvent);
        return countAllByEventAndBranch(event,branch,attendanceStatus)/(double)callsService.countAllCalledByEventAndBranch(event,branch);///////////
    }
    public int countAllByEventAndBranch(Event event,Branch branch,AttendanceStatus attendanceStatus){
        return eventAttendanceRepo.countAllByEventAndEvent_BranchesAndAttendanceStatus(event,branch,attendanceStatus);
    }


    public int countAllByEventAndBranchAndCallResult(Event event ,Branch branch,CallResult callResult){
        return countAllByEventAndBranchAndCallResult(event,branch,callResult);
    }
}
