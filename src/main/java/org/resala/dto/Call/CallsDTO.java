package org.resala.dto.Call;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CallsDTO {
    int id;
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
