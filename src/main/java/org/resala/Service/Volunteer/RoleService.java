package org.resala.Service.Volunteer;

import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Volunteer.Role;
import org.resala.Repository.Volunteer.RoleRepo;
import org.resala.Service.CommonCRUDService;
import org.resala.Service.CommonService;
import org.resala.StaticNames;
import org.resala.dto.Volunteer.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoleService implements CommonCRUDService<RoleDTO>, CommonService<Role> {
    @Autowired
    RoleRepo roleRepo;
    @Override
    public ResponseEntity<Object> create(List<RoleDTO> dto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> archive(RoleDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(RoleDTO newDto) {
        return null;
    }

    @Override
    public Role getById(int id) {
        return null;
    }

    @Override
    public List<Role> getAll() {
        return null;
    }

    public Role getRoleByName(String name){
        Optional<Role> roleOptional=roleRepo.findByName(name);
        if(!roleOptional.isPresent())
            throw  new MyEntityNotFoundException(name+" Role "+ StaticNames.notFound);
        return roleOptional.get();
    }
    /*public Role getRoleByUserName(String userName){
        Optional<Role> roleOptional=roleRepo.findByVolunteers_User_userName(userName);
        if(!roleOptional.isPresent())
            throw  new MyEntityNotFoundException("User Name "+ StaticNames.notFound);
        return roleOptional.get();
    }*/

    public Role findByVolunteers_User_userName(String userName){
        return roleRepo.findByVolunteers_User_userName(userName);
        /*if(!roleOptional.isPresent())
            throw new MyEntityNotFoundException("user name " + StaticNames.notFound);
        return roleOptional.get();*/
    }
}
