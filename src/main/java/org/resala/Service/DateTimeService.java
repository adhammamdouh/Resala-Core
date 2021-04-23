package org.resala.Service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

public class DateTimeService {
    public static LocalDateTime getNow(){
            return LocalDateTime.now();
    }
}
