package org.resala.dto.Event;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class EventStatuesDTO {
    int id;
    String name;
}
