package org.resala.dto.Call;

import lombok.Getter;
import lombok.Setter;
import org.resala.Exceptions.NullException;
import org.resala.dto.Event.EventDTO;
import org.resala.dto.Volunteer.NetworkTypeAssignedToVolunteersToEventDTO;

import java.util.List;

@Setter
@Getter
public class VolunteerToCallsDTO {
    EventDTO event;
    CallTypeDTO callType;
    List<NetworkTypeAssignedToVolunteersToEventDTO> networkTypeAssignedToVolunteersToEvents;

    public void checkNullForGetAssigned() {
        if (event == null ) {
            throw new NullException("Event");
        }
        if (networkTypeAssignedToVolunteersToEvents == null)
            throw new NullException("networkTypeAssignedToVolunteersToEventDTO");

        if (callType == null)
            throw new NullException("CallType");
    }
}
