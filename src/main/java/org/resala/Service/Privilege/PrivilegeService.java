package org.resala.Service.Privilege;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Privilege.Privilege;
import org.resala.Models.Volunteer.Role;
import org.resala.Repository.Privilege.PrivilegeRepo;
import org.resala.Service.CommonService;
import org.resala.StaticNames;
import org.resala.dto.Privilege.PrivilegeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrivilegeService implements CommonService<PrivilegeDTO> {
    @Autowired
    PrivilegeRepo privilegeRepo;
    @Override
    public ResponseEntity<Object> create(PrivilegeDTO obj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(PrivilegeDTO obj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(PrivilegeDTO newObj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> get(int id) {
        return null;
    }
    public Privilege getPrivilegeByName(String name ){
        Optional<Privilege> optionalPrivilege = privilegeRepo.findByName(name);
        if (!optionalPrivilege.isPresent())
            throw new MyEntityNotFoundException(name+" Privilege Not Found");
        return optionalPrivilege.get();
    }
}
