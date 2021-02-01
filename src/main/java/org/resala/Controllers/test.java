package org.resala.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
@PreAuthorize("isAuthenticated()")
public class test {
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String test(){
        return "asdasdasd";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String test2(){
        return "qqqqqq";
    }
}
