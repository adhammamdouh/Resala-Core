package org.resala.dto.Event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.resala.dto.BranchDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EventDTO {
    int id;
    String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date fromDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date toDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    Date callsStartTime;
    String script;
    String description;
    boolean hasCalls;
    boolean shareable;
    List<BranchDTO>branches;
}
