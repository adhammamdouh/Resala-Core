package org.resala.Security;


import org.resala.Models.MyUserDetails;
import org.resala.Models.Volunteer.User;
import org.resala.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService  implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("asdasdasdasdasdasdasd");
        User user=userService.getUser(userName);
        return new MyUserDetails(user);
    }
}
