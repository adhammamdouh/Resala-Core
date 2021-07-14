package org.resala.Service;

import org.resala.Exceptions.ActiveStateException;
import org.resala.Exceptions.NullException;
import org.resala.dto.UserLoginDTO;
import org.resala.Models.Volunteer.User;
import org.resala.Models.Volunteer.UserStatus;
import org.resala.Repository.UserRepository;
import org.resala.Security.Jwt.JwtUtil;
import org.resala.Service.Volunteer.RoleService;
import org.resala.Service.Volunteer.VolunteerService;
import org.resala.Service.Volunteer.UserStatusService;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
    UserStatusService volunteerStatusService;

    public User getUser(String username) {
        return userRepository.findByUserName(username);
    }

    /* public int getBranchId(String username){
         return branchService.getBranchByUserName(username).getId();
     }*/
    //@Transactional
    public Object login(UserLoginDTO auth) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        User user = getUser(auth.getUsername());
        String token;
        UserStatus userStatus;
        if (user.getVolunteer() != null){
            token = jwtUtil.generateToken(user.getVolunteer().getOrganization().getId(), user.getVolunteer().getBranch().getId(), authentication);
            userStatus = user.getVolunteer().getVolunteerStatus();
        }
        else if(user.getCloud() != null)
        {
            token = jwtUtil.generateToken(user.getCloud().getOrganization().getId(), 0, authentication);
            userStatus = user.getCloud().getCloudStatus();
        }
        else
            throw new ActiveStateException("Wrong User Name Or Password");
        if (userStatus.getName().equals(StaticNames.archivedState))
            throw new ActiveStateException("This Volunteer State is " + userStatus.getName());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        if (user.getVolunteer() != null)
            map.put("user", user.getVolunteer());
        if (user.getCloud() != null)
            map.put("user", user.getCloud());
        return map;
    }
}
