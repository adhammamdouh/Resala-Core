package org.resala.dto.Volunteer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Exceptions.NullException;
import org.resala.Models.Organization;
import org.resala.Models.Privilege.Privilege;
import org.resala.Models.Volunteer.User;
import org.resala.Models.Volunteer.UserStatus;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
public class CloudDTO {
    int id;
    User user;
    Organization organization;
    UserStatus cloudStatus;
    private List<Privilege> privileges;
}
