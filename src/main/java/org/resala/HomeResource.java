package org.resala;

import org.resala.Security.Jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController()
public class HomeResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("myUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/")
    public String home() {
        return ("<h1>3ok4 sa32 streamer 404 hhlol</h1>");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }

    /*@RequestMapping(value = "/authenticate" , method = RequestMethod.POST)
    public ResponseEntity<?> CreateAuthenticationToken(@RequestBody Request auth) throws Exception{
        try {
            Authentication authentication =authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        }
        catch (BadCredentialsException e ){
            throw new Exception("invalid input",e);
        }
        final MyUserDetails myUser =
                (MyUserDetails) userDetailsService.loadUserByUsername(auth.getUsername());

        String token = jwtUtil.generateToken(myUser);
        System.out.println(token);

        return ResponseEntity.ok(new Response(token));
    }*/
}

