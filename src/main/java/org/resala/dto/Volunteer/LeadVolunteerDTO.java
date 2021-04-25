package org.resala.dto.Volunteer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.resala.Exceptions.NullException;
import org.resala.Models.Committe.Committee;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.dto.Committe.CommitteeDTO;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class LeadVolunteerDTO {

    int id;
    VolunteerDTO myVolunteerInfo;
    String personalImageUrl;
    String resalaObjective;
    String personalObjective;
    String selfSkills;
    String dreams;
    String nationalIdUrl;
    boolean doctorMeeting;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date graduationDate;
    int graduationNumber;
    int camp48;
    boolean graduated;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date omra;
    CommitteeDTO committee;
    public void checkNullForCreation(){
        if (myVolunteerInfo == null)
            throw new NullException("MyVolunteerInfo");
    }
}
