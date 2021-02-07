package org.resala.resala.Service.Volunteer;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Volunteer.Role;
import org.resala.Repository.Volunteer.RoleRepo;
import org.resala.Service.CommonService;
import org.resala.dto.Volunteer.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements CommonService<RoleDTO> {
    @Autowired
    RoleRepo roleRepo;
    @Override
    public ResponseEntity<Object> create(RoleDTO obj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(RoleDTO obj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(RoleDTO newObj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> get(int id) {
        return null;
    }
    public Role getRoleByName(String name){
        Optional<Role> roleOptional=roleRepo.findByName(name);
        if(!roleOptional.isPresent())
            throw  new MyEntityNotFoundException(name+" Role Not Found");
        return roleOptional.get();
    }
}
