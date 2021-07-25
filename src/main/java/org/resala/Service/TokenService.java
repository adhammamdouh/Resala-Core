package org.resala.Service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

public class TokenService {
    public static final String myCommitteeId="myCommittee";
    public static final String type="type";
    public static final String myBranchId="myBranch";
    public static final String myOrganizationId="myOrganization";
    public static final String userId="user_id";

    public static final String firstName="first_name";
    public static final String midName="mid_name";
    public static final String lastName="last_name";

    public static final String roleName="role_name";


    public static int getBranchId() {
        try {
            return getIntFromClaims(myBranchId);
        }catch (NullPointerException e){
            return 0;
        }
    }

    public static int getOrganizationId() {

        try {
            return getIntFromClaims(myOrganizationId);
        }catch (NullPointerException e){
            return 0;
        }
    }
    public static int getMyCommitteeId() {
        try {
            return getIntFromClaims(myCommitteeId);
        }catch (NullPointerException e){
            return 0;
        }
    }
    public static int getMyUserId() {
        try {
            return getIntFromClaims(userId);
        }catch (NullPointerException e){
            return 0;
        }
    }

    private static String getStringFromClaims(String key) {
        Claims claims = (Claims) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return claims.get(key, String.class);
    }

    private static Integer getIntFromClaims(String key) {
        Claims claims = (Claims) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return claims.get(key, Integer.class);
    }
}
