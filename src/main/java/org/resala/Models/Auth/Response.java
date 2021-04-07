package org.resala.Models.Auth;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Response implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private int status;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String error;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Object message;

    public Response(Object message, int status) {
        timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }
    public Response(int status, String error) {
        timestamp = LocalDateTime.now();
        this.status = status;
        this.error=error;
    }
}
