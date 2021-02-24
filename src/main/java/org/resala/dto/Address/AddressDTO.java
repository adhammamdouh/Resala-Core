package org.resala.dto.Address;

import lombok.Getter;
import lombok.Setter;
import org.resala.Exceptions.NullException;

@Getter
@Setter
public class AddressDTO {
    int id;
    CapitalDTO capital;
    String regionName;
    String additionalInfo;
    String apartmentNumber;
    String buildingNumber;
    String streetName;
    public void checkNull() {
        if (capital == null) {
            throw new NullException("Capital");
        }
    }
}
