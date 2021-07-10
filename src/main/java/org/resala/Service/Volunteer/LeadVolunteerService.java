package org.resala.Service.Volunteer;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.MyEntityFoundBeforeException;
import org.resala.Models.Auth.Response;
import org.resala.Models.Branch;
import org.resala.Models.KPI.LeadVolunteerKPI;
import org.resala.Models.Volunteer.LeadVolunteer;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Models.Volunteer.VolunteerStatus;
import org.resala.Projections.LeadVolunteerProjection;
import org.resala.Projections.LeadVolunteerPublicInfoProjection;
import org.resala.Repository.Volunteer.LeadVolunteerRepo;
import org.resala.Service.BranchService;
import org.resala.StaticNames;
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
        return leadVolunteerRepo.findAllBy(projection);
    }


    public List<LeadVolunteerProjection> getAllByStateAndBranch(int stateId, int branchId) {
        Branch branch=branchService.getById(branchId);
        VolunteerStatus volunteerStatus=volunteerStatusService.getById(stateId);
        return leadVolunteerRepo.findAllByBranchAndState(branch, volunteerStatus, LeadVolunteerProjection.class);
    }

    public List<LeadVolunteerPublicInfoProjection> getAllPublicInfoByStateAndBranch(int stateId, int branchId) {
        Branch branch=branchService.getById(branchId);
        VolunteerStatus volunteerStatus=volunteerStatusService.getById(stateId);
        return leadVolunteerRepo.findAllByBranchAndState(branch, volunteerStatus, LeadVolunteerPublicInfoProjection.class);
    }

    public List<LeadVolunteerProjection> getAllByState(int stateId) {
        VolunteerStatus volunteerStatus=volunteerStatusService.getById(stateId);
        return leadVolunteerRepo.findByMyVolunteerInfo_VolunteerStatus(volunteerStatus, LeadVolunteerProjection.class);
    }

    public List<LeadVolunteerPublicInfoProjection> getAllByStatePublicInfo(int stateId) {
        VolunteerStatus volunteerStatus=volunteerStatusService.getById(stateId);
        return leadVolunteerRepo.findByMyVolunteerInfo_VolunteerStatus(volunteerStatus, LeadVolunteerPublicInfoProjection.class);
    }

    public List<LeadVolunteerProjection> getLeadVolunteersProjectionByBranch(int branchId) {
        return leadVolunteerRepo.findByMyVolunteerInfo_Branch_Id(branchId, LeadVolunteerProjection.class);
    }

    public List<LeadVolunteerPublicInfoProjection> getLeadVolunteersPublicInfoByBranch(int branchId) {
        return leadVolunteerRepo.findByMyVolunteerInfo_Branch_Id(branchId, LeadVolunteerPublicInfoProjection.class);
    }

    public boolean checkFound(Volunteer volunteer) {
        Optional<LeadVolunteer> leadVolunteerOptional = leadVolunteerRepo.findAllByMyVolunteerInfo(volunteer);
        return leadVolunteerOptional.isPresent();
    }

    public void setNewKPI(LeadVolunteer leadVolunteer, LeadVolunteerKPI kpi) {
        leadVolunteer.setLeadVolunteerKPI(kpi);
        leadVolunteerRepo.save(leadVolunteer);
    }
}
