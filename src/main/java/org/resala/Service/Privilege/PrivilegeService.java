package org.resala.Service.Privilege;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Privilege.Privilege;
import org.resala.Repository.Privilege.PrivilegeRepo;
import org.resala.Service.CommonCRUDService;
import org.resala.Service.CommonService;
import org.resala.dto.Privilege.PrivilegeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrivilegeService implements CommonCRUDService<PrivilegeDTO>, CommonService<Privilege> {
    @Autowired
    PrivilegeRepo privilegeRepo;
    @Override
    public ResponseEntity<Object> create(List<PrivilegeDTO> dto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> archive(PrivilegeDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(PrivilegeDTO newDto) {
        return null;
    }

    @Override
    public Privilege getById(int id) {
        return null;
    }

    @Override
    public List<Privilege> getAll() {
        return null;
    }

    public Privilege getPrivilegeByName(String name ){
        Optional<Privilege> optionalPrivilege = privilegeRepo.findByName(name);
        if (!optionalPrivilege.isPresent())
            throw new MyEntityNotFoundException(name+" Privilege Not Found");
        return optionalPrivilege.get();
    }
}
