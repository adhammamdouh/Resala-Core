package org.resala.Service;

import org.resala.Exceptions.ActiveStateException;
import org.resala.dto.UserLoginDTO;
import org.resala.Models.Volunteer.User;
import org.resala.Models.Volunteer.VolunteerStatus;
import org.resala.Repository.UserRepository;
import org.resala.Security.Jwt.JwtUtil;
import org.resala.Service.Volunteer.RoleService;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.Service.Volunteer.VolunteerStatusService;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BranchService branchService;
    @Autowired
    RoleService roleService;
    @Autowired
    VolunteerService volunteerService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    VolunteerStatusService volunteerStatusService;
    public User getUser(String username) {
        return userRepository.findByUserName(username);
    }

   /* public int getBranchId(String username){
        return branchService.getBranchByUserName(username).getId();
    }*/
    //@Transactional
    public Object login(UserLoginDTO auth){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        User user=getUser(auth.getUsername());
        String token = jwtUtil.generateToken(user.getVolunteer().getOrganization().getId(),user.getVolunteer().getBranch().getId(),authentication);
        VolunteerStatus volunteerStatus=volunteerStatusService.getVolunteerStatusByUserName(auth.getUsername());
        if(volunteerStatus.getName().equals(StaticNames.archivedState))
            throw new ActiveStateException("This Volunteer State is "+volunteerStatus.getName());
        //User loggedUser = getUser(auth.getUsername());
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
        map.put("volunteer",volunteerService.getByUserName(auth.getUsername()));
        return map;
    }
}
