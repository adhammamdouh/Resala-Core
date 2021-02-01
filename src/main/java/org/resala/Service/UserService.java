package org.resala.Service;

import org.resala.Models.Volunteer.User;
import org.resala.Repository.UserRepository;
import org.resala.dto.Volunteer.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserService implements CommonService<UserDTO> {
    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<Object> create(UserDTO userDTO) {
        if (userNameExist(userDTO.getUserName()))
            return ResponseEntity.badRequest().body("UserName found before");
        User user = convertToModel(userDTO);
        userRepository.save(user);

        return ResponseEntity.ok("Created successfully");
    }

    @Override
    public ResponseEntity<Object> delete(UserDTO userDTO) {
        if (!userNameExist(userDTO.getUserName()))
            return ResponseEntity.badRequest().body("UserName not found");
        User user = convertToModel(userDTO);
        userRepository.delete(user);
        return ResponseEntity.ok("Deleted successfully");
    }

    @Override
    public ResponseEntity<Object> update(UserDTO oldObj, UserDTO newObj) {
        User user1=getUser(oldObj.getUserName());
        /*if (!userNameExist(oldObj.getUserName()))
            return ResponseEntity.badRequest().body("UserName: "+oldObj.getUserName()+" not found");*/
        if(!oldObj.getUserName().equals(newObj.getUserName())&&userNameExist(newObj.getUserName()))
            return ResponseEntity.badRequest().body("UserName: "+newObj.getUserName()+" found before");
        //User user1 = convertToModel(oldObj);
        //User user2 = convertToModel(newObj);
        user1.setUserName(newObj.getUserName());
        user1.setPassword(newObj.getPassword());
        //userRepository.updateUserByUserName(user1.getUserName(),user2.getUserName(),user2.getPassword());
        userRepository.save(user1);
        return null;
    }

    @Override
    public ResponseEntity<Object> get(int id) {
        return null;
    }

    public User getUser(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + userName));
        return user.get();
    }

    public boolean userNameExist(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }

    private User convertToModel(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
