package org.resala.Controllers.Volunteer.Volunteer;

import org.resala.Security.Jwt.JwtUtil;
import org.resala.Models.Auth.Request;
import org.resala.Models.Auth.Response;
import org.resala.Models.MyUserDetails;
import org.resala.Models.Volunteer.User;
import org.resala.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/loginTest", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody Request auth) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        } catch (UsernameNotFoundException u) {
            throw new Exception("invalid input", u);
        } catch (BadCredentialsException e) {
            throw new Exception("invalid input", e);
        }
        System.out.println("111111111111111111111111111111111111111");
        final MyUserDetails myUser =
                (MyUserDetails) userDetailsService.loadUserByUsername(auth.getUsername());

        String token = jwtUtil.generateToken(myUser);
        User loggedUser = userService.getUser(auth.getUsername());
        return ResponseEntity.ok(new Response(token, loggedUser.getVolunteer()));
    }
}
