package org.resala.dto.Call;

import lombok.Getter;
import lombok.Setter;
import org.resala.Exceptions.NullException;
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

    public void checkNullForGetAssigned() {
        if (event == null ) {
            throw new NullException("Event");
        }
        if (volunteer == null)
            throw new NullException("Volunteer");
        if (callType == null)
            throw new NullException("CallType");
    }
}
