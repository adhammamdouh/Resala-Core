package org.resala.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

//@Service
public class IssTokenService {
    public static int getBranchId(){
        String iss = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return Integer.parseInt(iss.split(",")[1]);
    }
    public static int getOrganizationId(){
        String iss = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return Integer.parseInt(iss.split(",")[0]);
    }
}
