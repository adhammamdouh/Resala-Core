package org.resala.dto.Committee;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommitteeDTO {
    int Id;
    int responsibleVolunteerId;
    String name;
}
