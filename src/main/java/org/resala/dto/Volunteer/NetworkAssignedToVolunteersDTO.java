package org.resala.dto.Volunteer;

import lombok.Getter;
import lombok.Setter;
import org.resala.Exceptions.NullException;
import org.resala.dto.BranchDTO;
import org.resala.dto.Call.NetworkTypeDTO;
import org.resala.dto.Event.EventDTO;

@Setter
@Getter
public class NetworkAssignedToVolunteersDTO {
    VolunteerDTO volunteer;
    NetworkTypeDTO networkType;
    EventDTO event;
    BranchDTO branch;

    public NetworkAssignedToVolunteersDTO(VolunteerDTO volunteer, NetworkTypeDTO networkType, EventDTO eventDTO, BranchDTO branchDTO) {
        this.volunteer = volunteer;
        this.networkType = networkType;
        this.event = eventDTO;
        this.branch = branchDTO;
    }


    public NetworkAssignedToVolunteersDTO() {
    }

    public void checkNull(){
        if (networkType == null)
            throw new NullException("network type");
        if(volunteer == null)
            throw new NullException("volunteer");
        if(event == null)
            throw new NullException("event");
    }
}
