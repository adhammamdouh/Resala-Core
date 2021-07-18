package org.resala.Models;

import org.resala.Models.Privilege.Action;
import org.resala.Models.Privilege.Privilege;

import org.resala.Models.Volunteer.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class MyUserDetails implements UserDetails {
    private final User user;


    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<Privilege> privileges=user.getPrivileges();

        for (Privilege privilege : privileges) {
            List<Action> actions = privilege.getActions();
            for (Action action : actions)
                authorities.add(new SimpleGrantedAuthority(action.getName()));
        }
        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}