package org.resala.Service.Volunteer;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.MyEntityFoundBeforeException;
import org.resala.Exceptions.MyEntityNotFoundException;
import org.resala.Models.Auth.Response;
import org.resala.Models.Branch;
import org.resala.Models.Committee.Committee;
import org.resala.Models.KPI.LeadVolunteerKPI;
import org.resala.Models.Volunteer.LeadVolunteer;
import org.resala.Models.Volunteer.Role;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Models.Volunteer.UserStatus;
import org.resala.Pair;
import org.resala.Projections.LeadVolunteer.LeadVolunteerProjection;
import org.resala.Projections.LeadVolunteer.LeadVolunteerPublicInfoProjection;
import org.resala.Repository.Volunteer.LeadVolunteerRepo;
import org.resala.Service.BranchService;
import org.resala.Service.Commiittee.CommitteeService;
import org.resala.Service.TokenService;
import org.resala.Service.Privilege.PrivilegeService;
import org.resala.Service.UserService;
import org.resala.StaticNames;
import org.resala.dto.Privilege.PrivilegeDTO;
import org.resala.dto.Volunteer.LeadVolunteerDTO;
import org.resala.dto.Volunteer.UserDTO;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeadVolunteerService {
    @Autowired
    LeadVolunteerRepo leadVolunteerRepo;
    @Autowired
    VolunteerService volunteerService;
    @Autowired
    UserStatusService userStatusService;
    @Autowired
    BranchService branchService;
    @Autowired
    CommitteeService committeeService;
    @Autowired
    RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;
    @Autowired
    private UserService userService;

    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    public ResponseEntity<Object> create(List<LeadVolunteerDTO> dtos) {
        ArrayList<Pair<Integer, String>> failed = new ArrayList<>();
        for (int i = 0; i < dtos.size(); i++) {
            try {
                LeadVolunteerDTO dto = dtos.get(i);
                dto.checkNullForCreation();
                Volunteer volunteer = volunteerService.getVolForCreation(dto, roleService.getById(dto.getRole().getId()));
                Committee committee = committeeService.getById(dto.getCommittee().getId());
                if (checkFound(volunteer))
                    throw new MyEntityFoundBeforeException("This Volunteer is already lead");
                LeadVolunteer leadVolunteer = modelMapper().map(dto, LeadVolunteer.class);
                leadVolunteer.setVolunteer(volunteer);
                leadVolunteer.setCommittee(committee);
                leadVolunteerRepo.save(leadVolunteer);
            } catch (Exception e) {
                failed.add(new Pair<>(i, e.getMessage()));
            }
        }
        if (failed.size() == 0)
            return ResponseEntity.ok(new Response(StaticNames.addedSuccessfully, HttpStatus.OK.value()));
        else
            return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), failed), HttpStatus.BAD_REQUEST);
    }

    public <T> List<T> getAll(Class<T> projection) {
        return leadVolunteerRepo.findAllByOrganization_Id(projection, TokenService.getOrganizationId());
    }

    public List<LeadVolunteer> getAllForKPI() {
        return leadVolunteerRepo.findAllBy();
    }


    public List<LeadVolunteerProjection> getAllByStateAndBranch(int stateId, int branchId) {
        Branch branch = branchService.getById(branchId);
        UserStatus volunteerStatus = userStatusService.getById(stateId);
        return leadVolunteerRepo.findAllByBranchAndState(branch, volunteerStatus, LeadVolunteerProjection.class, TokenService.getOrganizationId());
    }

    public List<LeadVolunteerPublicInfoProjection> getAllPublicInfoByStateAndBranch(int stateId, int branchId) {
        Branch branch = branchService.getById(branchId);
        UserStatus volunteerStatus = userStatusService.getById(stateId);
        return leadVolunteerRepo.findAllByBranchAndState(branch, volunteerStatus, LeadVolunteerPublicInfoProjection.class, TokenService.getOrganizationId());
    }

    public List<LeadVolunteerProjection> getAllByState(int stateId) {
        UserStatus volunteerStatus = userStatusService.getById(stateId);
        return leadVolunteerRepo.findByVolunteerStatusAndOrganization_Id(volunteerStatus, LeadVolunteerProjection.class, TokenService.getOrganizationId());
    }

    public List<LeadVolunteerPublicInfoProjection> getAllByStatePublicInfo(int stateId) {
        UserStatus volunteerStatus = userStatusService.getById(stateId);
        return leadVolunteerRepo.findByVolunteerStatusAndOrganization_Id(volunteerStatus, LeadVolunteerPublicInfoProjection.class, TokenService.getOrganizationId());
    }

    public List<LeadVolunteerProjection> getLeadVolunteersProjectionByBranch(int branchId) {
        return leadVolunteerRepo.findAllByBranch_IdAndOrganization_Id(branchId, LeadVolunteerProjection.class, TokenService.getOrganizationId());
    }

    public List<LeadVolunteerPublicInfoProjection> getLeadVolunteersPublicInfoByBranch(int branchId) {
        return leadVolunteerRepo.findAllByBranch_IdAndOrganization_Id(branchId, LeadVolunteerPublicInfoProjection.class, TokenService.getOrganizationId());
    }

    public boolean checkFound(Volunteer volunteer) {
        if (volunteer == null) return true;
        Optional<LeadVolunteer> leadVolunteerOptional = leadVolunteerRepo.findById(volunteer.getId());
        return leadVolunteerOptional.isPresent();
    }

    public LeadVolunteer getByVolunteer(Volunteer volunteer) {
        if (volunteer == null) throw new MyEntityNotFoundException("lead volunteer " + StaticNames.notFound);
        Optional<LeadVolunteer> leadVolunteerOptional = leadVolunteerRepo.findById(volunteer.getId());
        if (leadVolunteerOptional.isPresent()) return leadVolunteerOptional.get();
        throw new MyEntityNotFoundException("lead volunteer " + StaticNames.notFound);
    }

    public void setNewKPI(LeadVolunteer leadVolunteer, LeadVolunteerKPI kpi) {
        leadVolunteer.setLeadVolunteerKPI(kpi);
        leadVolunteerRepo.save(leadVolunteer);
    }

    public List<LeadVolunteerPublicInfoProjection> getCommitteeTeam(int branchId, int committeeId) {
        Branch branch = branchService.getById(branchId);
        Committee committee = committeeService.getById(committeeId);
        Role role1 = roleService.getRoleByName(StaticNames.TeamLeader);
        Role role2 = roleService.getRoleByName(StaticNames.TeamMember);
        UserStatus userStatus=userStatusService.getByName(StaticNames.activeState);
        return leadVolunteerRepo.findMyCommitteeTeam(LeadVolunteerPublicInfoProjection.class, branch, committee, TokenService.getOrganizationId(),userStatus, role1, role2);
    }


    public ResponseEntity<Object> createVolunteerUser(List<VolunteerDTO> volunteerDTOS) {
        return userService.createVolunteerUser(volunteerDTOS);
    }

    public ResponseEntity<Object> addPrivileges(List<PrivilegeDTO> privilegeDTOS) {
        return privilegeService.create(privilegeDTOS);
    }

    public ResponseEntity<Object> addPrivilegesActions(List<PrivilegeDTO> privilegeDTOS) {
        return privilegeService.addPrivilegesActions(privilegeDTOS);
    }

    public ResponseEntity<Object> addUserPrivileges(List<UserDTO> userDTOS) {
        return userService.addUserPrivileges(userDTOS);
    }


}
