package org.resala.Service;

import org.resala.Exceptions.DeActivateException;
import org.resala.Models.Auth.Request;
import org.resala.Models.Volunteer.User;
import org.resala.Models.Volunteer.VolunteerStatus;
import org.resala.Repository.BranchRepo;
import org.resala.Repository.UserRepository;
import org.resala.Security.Jwt.JwtUtil;
import org.resala.Service.Volunteer.VolunteerStatusService;
import org.resala.StaticNames;
import org.resala.dto.Volunteer.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BranchService branchService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    VolunteerStatusService volunteerStatusService;
    public User getUser(String username) {
        return userRepository.findByUserName(username);
    }

    public int getBranchId(String username){
        return branchService.getBranchByUserName(username).getId();
    }
    public Object login(Request auth){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        String token = jwtUtil.generateToken(getBranchId(auth.getUsername()),authentication);
        VolunteerStatus volunteerStatus=volunteerStatusService.getVolunteerStatusByUserName(auth.getUsername());
        if(!volunteerStatus.getName().equals(StaticNames.activeState))
            throw new DeActivateException("This Volunteer State is "+volunteerStatus.getName());
        User loggedUser = getUser(auth.getUsername());
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
        map.put("volunteer",loggedUser.getVolunteer());
        return map;
    }
}
