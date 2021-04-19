package org.resala.dto.Volunteer;

import lombok.Getter;
import lombok.Setter;
import org.resala.Exceptions.NullException;
import org.resala.dto.Call.NetworkTypeDTO;
import org.resala.dto.Event.EventDTO;

import java.util.List;

@Setter
@Getter
public class VolunteerAssignedCallsToEventDTO {
    List<NetworkTypeDTO> networkTypeDTO;
    VolunteerDTO volunteerDTO;
    EventDTO eventDTO;

    public VolunteerAssignedCallsToEventDTO(List<NetworkTypeDTO> networkTypeDTO, VolunteerDTO volunteerDTO, EventDTO eventDTO) {
        this.networkTypeDTO = networkTypeDTO;
        this.volunteerDTO = volunteerDTO;
        this.eventDTO = eventDTO;
    }

    public void checkNull(){
        if (networkTypeDTO == null)
            throw new NullException("network type");
        if(volunteerDTO == null)
            throw new NullException("volunteer");
        if(eventDTO == null)
            throw new NullException("event");
    }
}
