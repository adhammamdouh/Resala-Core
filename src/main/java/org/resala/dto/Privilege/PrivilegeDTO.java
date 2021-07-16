package org.resala.dto.Privilege;

import lombok.Getter;
import lombok.Setter;
import org.resala.Models.Privilege.Action;
import org.resala.dto.OrganizationDTO;

import java.util.List;

@Getter
@Setter
public class PrivilegeDTO {
    int id;
    String name;
    OrganizationDTO organization;
    List<ActionDTO> actions;
}
