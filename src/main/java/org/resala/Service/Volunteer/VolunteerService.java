package org.resala.Service.Volunteer;

import org.resala.Repository.Volunteer.VolunteerRepo;
import org.resala.Service.CommonService;
import org.resala.dto.Volunteer.VolunteerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VolunteerService implements CommonService<VolunteerDTO> {
    @Autowired
    VolunteerRepo volunteerRepo;

    @Override
    public ResponseEntity<Object> create(VolunteerDTO obj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(VolunteerDTO obj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(VolunteerDTO oldObj, VolunteerDTO newObj) {
        return null;
    }

    @Override
    public ResponseEntity<Object> get(int id) {
        return ResponseEntity.ok(volunteerRepo.findById(id));
    }

    public ResponseEntity<Object> getAllVolunteers() {
        return ResponseEntity.ok().body(volunteerRepo.findAll());

    }
    public ResponseEntity<Object> getVolunteersByBranch(int branchId) {
        return ResponseEntity.ok().body(volunteerRepo.findByBranch_id(branchId));
    }


}
