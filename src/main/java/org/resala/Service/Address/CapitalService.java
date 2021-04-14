package org.resala.Service.Address;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Address.Capital;
import org.resala.Repository.Address.CapitalRepo;
import org.resala.Service.CommonCRUDService;
import org.resala.Service.CommonService;
import org.resala.StaticNames;
import org.resala.dto.Address.CapitalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CapitalService implements CommonCRUDService<CapitalDTO>, CommonService<Capital> {
    @Autowired
    CapitalRepo capitalRepo;

    @Override
    public ResponseEntity<Object> create(CapitalDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> archive(CapitalDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(CapitalDTO newDto) {
        return null;
    }

    @Override
    public Capital getById(int id) {
        Optional<Capital> optionalCapital = capitalRepo.findById(id);
        if (!optionalCapital.isPresent())
            throw new MyEntityNotFoundException("Capital "+ StaticNames.notFound);
        return optionalCapital.get();
    }

    @Override
    public List<Capital> getAll() {
        return null;
    }

}
