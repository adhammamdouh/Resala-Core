package org.resala.dto.Call;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.resala.dto.Event.EventDTO;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter
@Setter
public class CallsDTO {
    int id;
    boolean respond;
    EventDTO eventDTO;
    int answerId;
    VolunteerDTO callerDTO;
    VolunteerDTO receiverDTO;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    Date time;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    Date timeUneditableBefore;
    CallTypeDTO callTypeDTO;
    CallResultDTO callResultDTO;
    String comment;

}
