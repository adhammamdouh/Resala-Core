package org.resala.resala.Service;

import org.resala.Models.Volunteer.User;
import org.resala.Repository.UserRepository;
import org.resala.Service.CommonService;
import org.resala.dto.Volunteer.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService implements CommonService<UserDTO> {
    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<Object> create(UserDTO userDTO) {


        return ResponseEntity.ok("Created successfully");
    }

    @Override
    public ResponseEntity<Object> delete(UserDTO userDTO) {

        return ResponseEntity.ok("Deleted successfully");
    }

    @Override
    public ResponseEntity<Object> update(UserDTO newObj) {

        return null;
    }

    @Override
    public ResponseEntity<Object> get(int id) {
        return null;
    }
    private User convertToModel(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public User getUser(String username) {
        return userRepository.findByUserName(username);
    }

    public int getBranchId(){
        return 1;
    }
}
