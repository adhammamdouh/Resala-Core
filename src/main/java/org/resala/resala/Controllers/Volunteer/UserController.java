package org.resala.resala.Controllers.Volunteer;

import org.resala.Models.Auth.Request;
import org.resala.Models.Auth.Response;
import org.resala.Models.Volunteer.User;
import org.resala.Security.Jwt.JwtUtil;
import org.resala.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController()
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    @Qualifier("myUserDetailsService")
    UserDetailsService userDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody Request auth) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        String token = jwtUtil.generateToken(userService.getBranchId(),authentication);
        User loggedUser = userService.getUser(auth.getUsername());
        Map<String,Object>map=new HashMap<>();
        map.put("token",token);
        map.put("volunteer",loggedUser.getVolunteer());
        return ResponseEntity.ok(new Response(map, HttpStatus.OK.value()));
    }
}
