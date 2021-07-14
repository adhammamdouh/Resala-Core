package org.resala.Service.Volunteer;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.MyEntityFoundBeforeException;
import org.resala.Models.Auth.Response;
import org.resala.Models.Branch;
import org.resala.Models.Committe.Committee;
import org.resala.Models.KPI.LeadVolunteerKPI;
import org.resala.Models.Volunteer.LeadVolunteer;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Models.Volunteer.VolunteerStatus;
import org.resala.Projections.LeadVolunteer.LeadVolunteerProjection;
import org.resala.Projections.LeadVolunteer.LeadVolunteerPublicInfoProjection;
import org.resala.Repository.Volunteer.LeadVolunteerRepo;
import org.resala.Service.BranchService;
import org.resala.Service.Commiittee.CommitteeService;
import org.resala.Service.IssTokenService;
import org.resala.StaticNames;
import org.resala.dto.BranchDTO;
import org.resala.dto.Committe.CommitteeDTO;
import org.resala.dto.Volunteer.LeadVolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadVolunteerService {
    @Autowired
    LeadVolunteerRepo leadVolunteerRepo;
    @Autowired
    VolunteerService volunteerService;
    @Autowired
    VolunteerStatusService volunteerStatusService;
    @Autowired
    BranchService branchService;
    @Autowired
    CommitteeService committeeService;

    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    public ResponseEntity<Object> create(LeadVolunteerDTO dto) {
        dto.checkNullForCreation();
        Volunteer myVolunteerInfo = volunteerService.getById(dto.getMyVolunteerInfo().getId());
        if (checkFound(myVolunteerInfo))
            throw new MyEntityFoundBeforeException("This Volunteer is already lead");
        LeadVolunteer leadVolunteer = modelMapper().map(dto, LeadVolunteer.class);
        leadVolunteer.setMyVolunteerInfo(myVolunteerInfo);
        leadVolunteerRepo.save(leadVolunteer);
        return ResponseEntity.ok(new Response(StaticNames.addedSuccessfully, HttpStatus.OK.value()));
    }

    public <T> List<T> getAll(Class<T> projection) {
        return leadVolunteerRepo.findAllByAndMyVolunteerInfo_Organization_Id(projection, IssTokenService.getOrganizationId());
    }

    public List<LeadVolunteer> getAllForKPI() {
        return leadVolunteerRepo.findAllBy();
    }


    public List<LeadVolunteerProjection> getAllByStateAndBranch(int stateId, int branchId) {
        Branch branch = branchService.getById(branchId);
        VolunteerStatus volunteerStatus = volunteerStatusService.getById(stateId);
        return leadVolunteerRepo.findAllByBranchAndState(branch, volunteerStatus, LeadVolunteerProjection.class,IssTokenService.getOrganizationId());
    }

    public List<LeadVolunteerPublicInfoProjection> getAllPublicInfoByStateAndBranch(int stateId, int branchId) {
        Branch branch = branchService.getById(branchId);
        VolunteerStatus volunteerStatus = volunteerStatusService.getById(stateId);
        return leadVolunteerRepo.findAllByBranchAndState(branch, volunteerStatus, LeadVolunteerPublicInfoProjection.class,IssTokenService.getOrganizationId());
    }

    public List<LeadVolunteerProjection> getAllByState(int stateId) {
        VolunteerStatus volunteerStatus = volunteerStatusService.getById(stateId);
        return leadVolunteerRepo.findByMyVolunteerInfo_VolunteerStatusAndMyVolunteerInfo_Organization_Id(volunteerStatus, LeadVolunteerProjection.class,IssTokenService.getOrganizationId());
    }

    public List<LeadVolunteerPublicInfoProjection> getAllByStatePublicInfo(int stateId) {
        VolunteerStatus volunteerStatus = volunteerStatusService.getById(stateId);
        return leadVolunteerRepo.findByMyVolunteerInfo_VolunteerStatusAndMyVolunteerInfo_Organization_Id(volunteerStatus, LeadVolunteerPublicInfoProjection.class,IssTokenService.getOrganizationId());
    }

    public List<LeadVolunteerProjection> getLeadVolunteersProjectionByBranch(int branchId) {
        return leadVolunteerRepo.findByMyVolunteerInfo_Branch_IdAndMyVolunteerInfo_Organization_Id(branchId, LeadVolunteerProjection.class,IssTokenService.getOrganizationId());
    }

    public List<LeadVolunteerPublicInfoProjection> getLeadVolunteersPublicInfoByBranch(int branchId) {
        return leadVolunteerRepo.findByMyVolunteerInfo_Branch_IdAndMyVolunteerInfo_Organization_Id(branchId, LeadVolunteerPublicInfoProjection.class,IssTokenService.getOrganizationId());
    }

    public boolean checkFound(Volunteer volunteer) {
        Optional<LeadVolunteer> leadVolunteerOptional = leadVolunteerRepo.findAllByMyVolunteerInfo(volunteer);
        return leadVolunteerOptional.isPresent();
    }

    public void setNewKPI(LeadVolunteer leadVolunteer, LeadVolunteerKPI kpi) {
        leadVolunteer.setLeadVolunteerKPI(kpi);
        leadVolunteerRepo.save(leadVolunteer);
    }

    public List<LeadVolunteerPublicInfoProjection> getCommitteeTeam(int branchId, int committeeId) {
        Branch branch = branchService.getById(branchId);
        Committee committee = committeeService.getById(committeeId);
        return leadVolunteerRepo.findAllByMyVolunteerInfo_Branch_IdAndCommittee_IdAndMyVolunteerInfo_Organization_Id(branch.getId(), committee.getId(), LeadVolunteerPublicInfoProjection.class,IssTokenService.getOrganizationId());
    }
}
