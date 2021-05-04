package org.resala.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;

public  class AuthorizeController {
    public static Collection<? extends GrantedAuthority> getAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities();
    }

    public static boolean contain(String auth, Collection<? extends GrantedAuthority> authorities) {
        for (GrantedAuthority g : authorities) {
            if (g.getAuthority().equals(auth))
                return true;
        }
        return false;
    }
}
