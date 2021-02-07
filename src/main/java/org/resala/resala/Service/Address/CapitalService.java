package org.resala.resala.Service.Address;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Address.Capital;
import org.resala.Repository.Address.CapitalRepo;
import org.resala.Service.CommonService;
import org.resala.dto.Address.CapitalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CapitalService implements CommonService<CapitalDTO> {
    @Autowired
    CapitalRepo capitalRepo;

    @Override
    public ResponseEntity<Object> create(CapitalDTO obj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(CapitalDTO obj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(CapitalDTO newObj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> get(int id) {
        return null;
    }

    public Capital getCapitalId(int id) {
        Optional<Capital> optionalCapital = capitalRepo.findById(id);
        if (!optionalCapital.isPresent())
            throw new MyEntityNotFoundException("Capital Not Found");
        return optionalCapital.get();
    }

}
