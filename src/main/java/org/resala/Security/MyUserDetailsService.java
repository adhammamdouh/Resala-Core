package org.resala.Security;


import org.resala.Models.MyUserDetails;
import org.resala.Models.Volunteer.User;
import org.resala.Repository.UserRepository;
import org.resala.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{

        User user = userRepository.findByUserName(userName);
        if(user==null){
            throw new UsernameNotFoundException("User Not Fount: "+userName);
        }
        return new MyUserDetails(user);


    }
}
