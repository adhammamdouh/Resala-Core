package org.resala.resala.dto.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    int id;
    int capitalId;
    String regionName;
    String additionalInfo;
    String apartmentNumber;
    String buildingNumber;
    String streetName;

}
