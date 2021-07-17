package org.resala.dto.Event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.resala.Exceptions.NullException;
import org.resala.dto.BranchDTO;
import org.resala.dto.OrganizationDTO;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EventDTO {
    int id;
    String name;
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date fromDate;
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date toDate;
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date invitationStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date feedBackStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date notAttendStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date invitationEndTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date feedBackEndTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date notAttendEndTime;
    String script;
    String description;
    boolean hasCalls;
    boolean shareable;
    List<BranchDTO>branches;
    OrganizationDTO organization;
    public void checkNull(){
        if (branches==null||branches.isEmpty())
            throw new NullException("Branches");
    }
}
