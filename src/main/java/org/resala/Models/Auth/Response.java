package org.resala.Models.Auth;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.resala.Models.Volunteer.User;
import org.resala.Models.Volunteer.Volunteer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

public class Response implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private int statues;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String error;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Object message;

    public Response(Object message, int statues) {
        timestamp = LocalDateTime.now();
        this.statues = statues;
        this.message = message;
    }
    public Response(int statues,String error) {
        timestamp = LocalDateTime.now();
        this.statues = statues;
        this.error=error;
    }
}
