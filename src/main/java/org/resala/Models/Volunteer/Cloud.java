package org.resala.Models.Volunteer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Models.Organization;
import org.resala.Models.Privilege.Privilege;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "user_id")
public class Cloud extends User{

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    UserStatus userStatus;

}
