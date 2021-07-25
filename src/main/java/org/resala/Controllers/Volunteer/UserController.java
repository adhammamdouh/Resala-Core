package org.resala.Controllers.Volunteer;

import org.resala.dto.UserLoginDTO;
import org.resala.Models.Auth.Response;
import org.resala.Service.UserService;
import org.resala.dto.Volunteer.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class UserController {


    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO auth) throws Exception {

        return ResponseEntity.ok(new Response(userService.login(auth), HttpStatus.OK.value()));
    }
}
