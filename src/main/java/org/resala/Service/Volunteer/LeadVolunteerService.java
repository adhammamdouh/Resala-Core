package org.resala.Service.Volunteer;

import org.resala.Projections.LeadVolunteerProjection;
import org.resala.Projections.LeadVolunteerPublicInfoProjection;
import org.resala.Repository.Volunteer.LeadVolunteerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadVolunteerService {
    @Autowired
    LeadVolunteerRepo leadVolunteerRepo;

    public <T>List<T> getAll(Class<T> projection) {
        return leadVolunteerRepo.findAllBy(projection);
    }

    public List<LeadVolunteerPublicInfoProjection> getAllPublicInfo() {
        return leadVolunteerRepo.findAllBy(LeadVolunteerPublicInfoProjection.class);
    }

    public List<LeadVolunteerProjection> getAllByBranchAndState(String stateName,int branchId) {
        return leadVolunteerRepo.findAllByBranchAndState(branchId,stateName,LeadVolunteerProjection.class);
    }

    public List<LeadVolunteerPublicInfoProjection> getAllPublicInfoByBranchAndState(String stateName,int branchId) {
        return leadVolunteerRepo.findAllByBranchAndState(branchId,stateName,LeadVolunteerPublicInfoProjection.class);
    }

    public List<LeadVolunteerProjection> getAllByState(String activeState) {
        return leadVolunteerRepo.findByMyVolunteerInfo_VolunteerStatus_Name(activeState,LeadVolunteerProjection.class);
    }

    public List<LeadVolunteerPublicInfoProjection> getAllByStatePublicInfo(String activeState) {
        return leadVolunteerRepo.findByMyVolunteerInfo_VolunteerStatus_Name(activeState,LeadVolunteerPublicInfoProjection.class);
    }

    public List<LeadVolunteerProjection> getLeadVolunteersProjectionByBranch(int branchId) {
        return leadVolunteerRepo.findByMyVolunteerInfo_Branch_Id(branchId,LeadVolunteerProjection.class);
    }
    public List<LeadVolunteerPublicInfoProjection> getLeadVolunteersPublicInfoByBranch(int branchId) {
        return leadVolunteerRepo.findByMyVolunteerInfo_Branch_Id(branchId,LeadVolunteerPublicInfoProjection.class);
    }
}
