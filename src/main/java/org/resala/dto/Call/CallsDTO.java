package org.resala.dto.Call;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter
@Setter
public class CallsDTO {
    int id;
    boolean respond;
    int eventId;
    int answerId;
    int callerId;
    int receiverId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    Date time;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    Date timeUneditableBefore;
    String callResult;

}
