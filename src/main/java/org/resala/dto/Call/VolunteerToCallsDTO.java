package org.resala.dto.Call;

import lombok.Getter;
import lombok.Setter;
import org.resala.dto.Call.CallTypeDTO;
import org.resala.dto.Call.NetworkTypeDTO;
import org.resala.dto.Volunteer.VolunteerDTO;

import java.util.List;

@Setter
@Getter
public class VolunteerToCallsDTO {
    List<VolunteerDTO> volunteerIds;
    CallTypeDTO callTypeId;
    List<NetworkTypeDTO> networkTypeIds;

}
