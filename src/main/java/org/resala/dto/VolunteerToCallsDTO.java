package org.resala.dto;

import lombok.Getter;
import lombok.Setter;
import org.resala.dto.Call.CallTypeDTO;
import org.resala.dto.Volunteer.VolunteerDTO;

import java.util.List;

@Setter
@Getter
public class VolunteerToCallsDTO {
    List<VolunteerDTO> volunteerIds;
    List<CallTypeDTO> callTypeIds;

}
