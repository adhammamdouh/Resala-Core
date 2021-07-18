package org.resala.Security;


import org.resala.Exceptions.ActiveStateException;
import org.resala.Exceptions.MyEntityFoundBeforeException;
import org.resala.Models.Privilege.Action;
import org.resala.Models.Privilege.Privilege;
import org.resala.Models.Volunteer.User;
import org.resala.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = (User) authentication.getDetails();
        if (user == null) {
            throw new BadCredentialsException("Wrong User Name Or Password");
        }
        if (!checkPw(password, user.getPassword())) {
            throw new BadCredentialsException("Wrong User Name Or Password");
        }
        if(user.getUserType()==null)
            throw new BadCredentialsException("Wrong User Name Or Password");

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<Privilege> privileges = user.getPrivileges();
        for (Privilege privilege : privileges) {
            List<Action> actions = privilege.getActions();
            for (Action action : actions)
                authorities.add(new SimpleGrantedAuthority(action.getName()));
        }
        return new UsernamePasswordAuthenticationToken(
                name, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }

    private BCryptPasswordEncoder getPwEncoder() {
        return new BCryptPasswordEncoder();
    }

    public boolean checkPw(String pw, String encodedPw) {
        if (getPwEncoder().matches(pw, encodedPw))
            return true;
        else return false;
    }
}