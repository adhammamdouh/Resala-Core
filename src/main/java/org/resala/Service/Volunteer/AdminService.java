package org.resala.Service.Volunteer;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.AssignedBeforeException;
import org.resala.Exceptions.BadAssignException;
import org.resala.Exceptions.MyEntityFoundBeforeException;
import org.resala.Exceptions.NullException;
import org.resala.Models.Auth.Response;
import org.resala.Models.Volunteer.User;
import org.resala.Models.Volunteer.UserType;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Pair;
import org.resala.Repository.Volunteer.AdminRepo;
import org.resala.Service.OrganizationService;
import org.resala.Service.Privilege.PrivilegeService;
import org.resala.Service.UserService;
import org.resala.StaticNames;
import org.resala.dto.Privilege.PrivilegeDTO;
import org.resala.dto.Volunteer.UserDTO;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private UserStatusService userStatusService;
    @Autowired
    private PrivilegeService privilegeService;
    @Autowired
    private UserService userService;

    @Autowired
    private VolunteerService volunteerService;

    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }


    public ResponseEntity<Object> createUser(List<UserDTO> userDTOS) {
        return userService.createUser(userDTOS);
    }

    public ResponseEntity<Object> assignVolunteerUser(List<VolunteerDTO> volunteerDTOS) {
        return volunteerService.assignVolunteerUser(volunteerDTOS);

    }

    public ResponseEntity<Object> addPrivileges(List<PrivilegeDTO> privilegeDTOS) {
        return privilegeService.create(privilegeDTOS);
    }

    public ResponseEntity<Object> addPrivilegesActions(List<PrivilegeDTO> privilegeDTOS) {
        return  privilegeService.addPrivilegesActions(privilegeDTOS);
    }
}
