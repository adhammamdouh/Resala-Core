package org.resala.Service.Address;

import org.resala.Models.Address.Address;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Service.CheckConstraintService;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    public void checkConstraintViolations(Address address){
        CheckConstraintService.checkConstraintViolations(address,Address.class);
    }
}
