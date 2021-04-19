package org.resala.dto.Call;

import lombok.Getter;
import lombok.Setter;
import org.resala.dto.Call.CallTypeDTO;
import org.resala.dto.Call.NetworkTypeDTO;
import org.resala.dto.Event.EventDTO;
import org.resala.dto.Volunteer.VolunteerDTO;

import java.util.List;

@Setter
@Getter
public class VolunteerToCallsDTO {
    EventDTO event;
    VolunteerDTO volunteer;
    CallTypeDTO callType;
    List<NetworkTypeDTO> networkType;

}
