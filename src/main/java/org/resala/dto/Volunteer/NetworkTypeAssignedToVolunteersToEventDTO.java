package org.resala.dto.Volunteer;

import lombok.Getter;
import lombok.Setter;
import org.resala.Exceptions.NullException;
import org.resala.dto.BranchDTO;
import org.resala.dto.Call.CallTypeDTO;
import org.resala.dto.Call.NetworkTypeDTO;
import org.resala.dto.Event.EventDTO;

import java.util.List;

@Setter
@Getter
public class NetworkTypeAssignedToVolunteersToEventDTO {
    List<VolunteerDTO> volunteers;
    NetworkTypeDTO networkType;
    EventDTO eventDTO;
    CallTypeDTO callTypeDTO;
    BranchDTO branchDTO;

    public NetworkTypeAssignedToVolunteersToEventDTO(List<VolunteerDTO> volunteers, NetworkTypeDTO networkType, EventDTO eventDTO, CallTypeDTO callTypeDTO, BranchDTO branchDTO) {
        this.volunteers = volunteers;
        this.networkType = networkType;
        this.eventDTO = eventDTO;
        this.callTypeDTO = callTypeDTO;
        this.branchDTO = branchDTO;
    }


    public NetworkTypeAssignedToVolunteersToEventDTO() {
    }


    public NetworkTypeAssignedToVolunteersToEventDTO(List<VolunteerDTO> volunteers, NetworkTypeDTO networkType, EventDTO eventDTO) {
        this.networkType = networkType;
        this.volunteers = volunteers;
        this.eventDTO = eventDTO;
    }

    public void checkNull(){
        if (networkType == null)
            throw new NullException("network type");
        if(volunteers == null)
            throw new NullException("volunteer");
        if(eventDTO == null)
            throw new NullException("event");
    }
}
