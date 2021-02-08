package org.resala.Service;

import org.resala.Models.Volunteer.User;
import org.resala.Repository.BranchRepo;
import org.resala.Repository.UserRepository;
import org.resala.dto.Volunteer.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BranchService branchService;
    public User getUser(String username) {
        return userRepository.findByUserName(username);
    }

    public int getBranchId(String username){
        return branchService.getBranchByUserName(username).getId();
    }
}
