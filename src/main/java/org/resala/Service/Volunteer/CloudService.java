package org.resala.Service.Volunteer;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Auth.Response;
import org.resala.Models.Organization;
import org.resala.Models.Privilege.Privilege;
import org.resala.Models.Volunteer.Cloud;
import org.resala.Models.Volunteer.UserStatus;
import org.resala.Repository.Volunteer.CloudRepo;
import org.resala.Service.TokenService;
import org.resala.Service.OrganizationService;
import org.resala.Service.Privilege.PrivilegeService;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class CloudService {
    @Autowired
    private CloudRepo cloudRepo;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private UserStatusService userStatusService;
    @Autowired
    private PrivilegeService privilegeService;

    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    public ResponseEntity<Object> create() {
        UserStatus userStatus = userStatusService.getByName(StaticNames.activeState);
        Privilege privilege = privilegeService.getPrivilegeByName(StaticNames.cloud);
        Cloud cloud = new Cloud();
        cloud.setPrivileges(Stream.of(privilege).collect(toList()));
        cloud.setUserStatus(userStatus);
        cloudRepo.save(cloud);
        return ResponseEntity.ok(new Response(StaticNames.addedSuccessfully, HttpStatus.OK.value()));

    }

    public Cloud findById(int id) {
        Optional<Cloud> optional = cloudRepo.findById(id);
        if (!optional.isPresent()) throw new MyEntityNotFoundException("Cloud" + StaticNames.notFound);
        return optional.get();
    }
}
