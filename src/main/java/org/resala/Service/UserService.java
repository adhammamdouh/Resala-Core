package org.resala.Service;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.resala.Exceptions.ActiveStateException;
import org.resala.Exceptions.ConstraintViolationException;
import org.resala.Exceptions.MyEntityFoundBeforeException;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Auth.Response;
import org.resala.Models.Volunteer.User;
import org.resala.Models.Volunteer.UserStatus;
import org.resala.Models.Volunteer.UserType;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Pair;
import org.resala.Repository.UserRepository;
import org.resala.Security.Jwt.JwtUtil;
import org.resala.Service.Volunteer.*;
import org.resala.StaticNames;
import org.resala.dto.UserLoginDTO;
import org.resala.dto.Volunteer.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    LeadVolunteerService leadVolunteerService;
    @Autowired
    UserTypeService userTypeService;
    @Autowired
    OrganizationService organizationService;

    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    private BCryptPasswordEncoder getPwEncoder() {
        return new BCryptPasswordEncoder();
    }

    public boolean checkPw(String pw, String encodedPw) {
        if (getPwEncoder().matches(pw, encodedPw))
            return true;
        else return false;
    }

    public User getUser(String username, int orgId) {
        return userRepository.findByUserNameAndOrganization_Id(username, orgId);
    }
//    public bo

    public ResponseEntity<Object> createUser(List<UserDTO> userDTOS) {
        BCryptPasswordEncoder encoder = getPwEncoder();
        ArrayList<Pair<Integer, String>> failed = new ArrayList<>();
        int count = 0;
        for (UserDTO dto : userDTOS) {
            try {
                User user = modelMapper().map(dto, User.class);
                user.setOrganization(organizationService.getById(IssTokenService.getOrganizationId()));
                checkConstraintViolations(user);
                if (getUser(dto.getUserName(), IssTokenService.getOrganizationId()) != null)
                    throw new MyEntityFoundBeforeException("User Name is already exist");
                UserType userType = userTypeService.getById(user.getUserType().getId());
                user.setPassword(encoder.encode(user.getPassword()));
                user.setUserType(userType);
                user.setId(0);
                userRepository.save(user);
                count++;
            } catch (Exception e) {
                failed.add(new Pair<>(count, e.getMessage()));
                count++;
            }
        }
        if (failed.size() == 0)
            return ResponseEntity.ok(new Response(StaticNames.addedSuccessfully, HttpStatus.OK.value()));
        else
            return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), failed), HttpStatus.BAD_REQUEST);
    }


    public Object login(UserLoginDTO auth) {
        User user = getUser(auth.getUsername(), auth.getOrganizationId());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword());
        usernamePasswordAuthenticationToken.setDetails(user);
        Authentication authentication = authenticationManager.authenticate(
                usernamePasswordAuthenticationToken);
        String token;
        UserStatus userStatus;
        if (user.getVolunteer() != null) {
            token = jwtUtil.generateToken(user.getVolunteer().getOrganization().getId(), user.getVolunteer().getBranch().getId(), authentication);
            userStatus = user.getVolunteer().getVolunteerStatus();
        } else if (user.getCloud() != null) {
            token = jwtUtil.generateToken(user.getCloud().getOrganization().getId(), 0, authentication);
            userStatus = user.getCloud().getCloudStatus();
        } else if (user.getAdmin() != null) {
            token = jwtUtil.generateToken(user.getAdmin().getOrganization().getId(), 0, authentication);
            userStatus = user.getAdmin().getAdminStatus();
        } else
            throw new BadCredentialsException("Wrong User Name Or Password");
        if (userStatus.getName().equals(StaticNames.archivedState))
            throw new ActiveStateException("This Volunteer State is " + userStatus.getName());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("user", user);
        return map;
    }

    public void checkConstraintViolations(User user) {
        CheckConstraintService.checkConstraintViolations(user, User.class);
    }

    public User getById(int id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent())
            throw new MyEntityNotFoundException("User " + StaticNames.notFound);
        return optional.get();
    }
}
