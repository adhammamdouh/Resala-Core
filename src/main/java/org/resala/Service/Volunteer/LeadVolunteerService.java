package org.resala.Service.Volunteer;

import org.modelmapper.ModelMapper;
import org.resala.Exceptions.MyEntityFoundBeforeException;
import org.resala.Models.Auth.Response;
import org.resala.Models.Volunteer.LeadVolunteer;
import org.resala.Models.Volunteer.Volunteer;
import org.resala.Projections.LeadVolunteerProjection;
import org.resala.Projections.LeadVolunteerPublicInfoProjection;
import org.resala.Repository.Volunteer.LeadVolunteerRepo;
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


    public List<LeadVolunteerProjection> getAllByBranchAndState(String stateName, int branchId) {
        return leadVolunteerRepo.findAllByBranchAndState(branchId, stateName, LeadVolunteerProjection.class);
    }

    public List<LeadVolunteerPublicInfoProjection> getAllPublicInfoByBranchAndState(String stateName, int branchId) {
        return leadVolunteerRepo.findAllByBranchAndState(branchId, stateName, LeadVolunteerPublicInfoProjection.class);
    }

    public List<LeadVolunteerProjection> getAllByState(String activeState) {
        return leadVolunteerRepo.findByMyVolunteerInfo_VolunteerStatus_Name(activeState, LeadVolunteerProjection.class);
    }

    public List<LeadVolunteerPublicInfoProjection> getAllByStatePublicInfo(String activeState) {
        return leadVolunteerRepo.findByMyVolunteerInfo_VolunteerStatus_Name(activeState, LeadVolunteerPublicInfoProjection.class);
    }

    public List<LeadVolunteerProjection> getLeadVolunteersProjectionByBranch(int branchId) {
        return leadVolunteerRepo.findByMyVolunteerInfo_Branch_Id(branchId, LeadVolunteerProjection.class);
    }

    public List<LeadVolunteerPublicInfoProjection> getLeadVolunteersPublicInfoByBranch(int branchId) {
        return leadVolunteerRepo.findByMyVolunteerInfo_Branch_Id(branchId, LeadVolunteerPublicInfoProjection.class);
    }

    public boolean checkFound(Volunteer volunteer){
        Optional<LeadVolunteer>leadVolunteerOptional=leadVolunteerRepo.findAllByMyVolunteerInfo(volunteer);
        return leadVolunteerOptional.isPresent();
    }


}
