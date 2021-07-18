package org.resala.Service;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.ActiveStateException;
import org.resala.Exceptions.ConstraintViolationException;
import org.resala.Exceptions.MyEntityFoundBeforeException;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Auth.Response;
import org.resala.Models.Organization;
import org.resala.Models.Privilege.Privilege;
import org.resala.Models.Volunteer.*;
import org.resala.Pair;
import org.resala.Repository.UserRepository;
import org.resala.Security.Jwt.JwtUtil;
import org.resala.Service.Privilege.PrivilegeService;
import org.resala.Service.Volunteer.*;
import org.resala.StaticNames;
import org.resala.dto.BranchDTO;
import org.resala.dto.Privilege.PrivilegeDTO;
import org.resala.dto.UserLoginDTO;
import org.resala.dto.Volunteer.UserDTO;
import org.resala.dto.Volunteer.VolunteerDTO;
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
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Autowired
    CloudService cloudService;
    @Autowired
    PrivilegeService privilegeService;

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

    public User getUser(String username) {
        return userRepository.findByUserName(username);
    }
//    public bo

    public ResponseEntity<Object> createVolunteerUser(List<VolunteerDTO> volunteerDTOS) {
        BCryptPasswordEncoder encoder = getPwEncoder();
        ArrayList<Pair<Integer, String>> failed = new ArrayList<>();
        for (int i = 0; i < volunteerDTOS.size(); i++) {
            try {
                VolunteerDTO dto = volunteerDTOS.get(i);
                Volunteer volunteer=volunteerService.getById(dto.getId());
                User user = modelMapper().map(dto.getUser(), User.class);
                user.setUserType(userTypeService.getByName(StaticNames.volunteerType));
                checkConstraintViolations(user);
                String domain = getDomain(user.getUserName());
                Organization organization = organizationService.getByDomainName(domain);
                if (organization.getId() != IssTokenService.getOrganizationId())
                    throw new BadCredentialsException("Can't create user for this organization");
                if (getUser(user.getUserName()) != null)
                    throw new MyEntityFoundBeforeException("User Name is already exist");
                user.setPassword(encoder.encode(user.getPassword()));
                user.setId(0);

                userRepository.save(user);
                volunteer.setUser(user);
                volunteerService.savaVol(volunteer);
            } catch (Exception e) {
                failed.add(new Pair<>(i, e.getMessage()));
            }
        }
        if (failed.size() == 0)
            return ResponseEntity.ok(new Response(StaticNames.addedSuccessfully, HttpStatus.OK.value()));
        else
            return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), failed), HttpStatus.BAD_REQUEST);
    }

    public String getDomain(String name) {
        int index = name.lastIndexOf('@');
        if (index == -1) {
            throw new ConstraintViolationException("This field isn't domain");
        }
        return name.substring(index);
    }

    public Object login(UserLoginDTO auth) {
        checkConstraintViolations(auth);
        String domain = getDomain(auth.getUserName());
        Organization organization = organizationService.getByDomainName(domain);
        User user = getUser(auth.getUserName());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(auth.getUserName(), auth.getPassword());
        usernamePasswordAuthenticationToken.setDetails(user);
        Authentication authentication = authenticationManager.authenticate(
                usernamePasswordAuthenticationToken);
        String token;
        UserStatus userStatus;
        Map<String, Object> map = new HashMap<>();

        switch (user.getUserType().getName()) {
            case StaticNames.volunteerType:
                Volunteer volunteer = volunteerService.getVolunteerByUserId(user.getId());
                try {
                    LeadVolunteer leadVolunteer = leadVolunteerService.getByVolunteer(volunteer);
                    map.put("info", leadVolunteer);
                } catch (MyEntityNotFoundException e) {
                    map.put("info", volunteer);
                }
                token = jwtUtil.generateToken(volunteer.getOrganization().getId(), volunteer.getBranch().getId(), authentication);
                userStatus = volunteer.getVolunteerStatus();
                break;
            case StaticNames.cloudType:
                token = jwtUtil.generateToken(organization.getId(), 0, authentication);
                Cloud cloud = cloudService.findById(user.getId());
                userStatus = cloud.getUserStatus();
                map.put("info", cloud);
                break;
            default:

                throw new BadCredentialsException("Wrong User Name Or Password");
        }
        if (userStatus.getName().equals(StaticNames.archivedState))
            throw new ActiveStateException("This Volunteer State is " + userStatus.getName());

        map.put("token", token);
        map.put("user", user);
        return map;
    }

    public void checkConstraintViolations(User user) {
        CheckConstraintService.checkConstraintViolations(user, User.class);
    }

    public void checkConstraintViolations(UserLoginDTO user) {
        CheckConstraintService.checkConstraintViolations(user, UserLoginDTO.class);
    }

    public User getById(int id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent())
            throw new MyEntityNotFoundException("User " + StaticNames.notFound);
        return optional.get();
    }


    public ResponseEntity<Object> addUserPrivileges(List<UserDTO> userDTOS) {
        ArrayList<Pair<Integer, String>> failed = new ArrayList<>();
        for (int i = 0; i < userDTOS.size(); i++) {
            try {
                UserDTO dto = userDTOS.get(i);
                dto.checkNullForPrivilege();
                User user = getById(dto.getId());
                String userDomain = getDomain(user.getUserName());
                Organization organization = organizationService.getByDomainName(userDomain);
                if (organization.getId() != IssTokenService.getOrganizationId())
                    throw new BadCredentialsException("Can't Assign Privileges for this user");
                List<Integer> privilegesIds = dto.getPrivileges().stream().map(PrivilegeDTO::getId).collect(Collectors.toList());
                List<Privilege> privileges = privilegeService.findByIds(privilegesIds);
                List<Privilege> userNewPrivileges = new ArrayList<>(Stream.of(user.getPrivileges(), privileges).flatMap(List::stream)
                        .collect(Collectors.toMap(Privilege::getName, d -> d, (Privilege x, Privilege y) -> x)).values());
                user.setPrivileges(userNewPrivileges);
                userRepository.save(user);
            } catch (Exception e) {
                failed.add(new Pair<>(i, e.getMessage()));
            }
        }
        if (failed.size() == 0)
            return ResponseEntity.ok(new Response(StaticNames.assignedSuccessfully, HttpStatus.OK.value()));
        else
            return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), failed), HttpStatus.BAD_REQUEST);
    }
}
