package org.resala.Security.Jwt;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TokenInfo {
    int branchId;
    String username;
    List<String> authorities;
    Date expirationDate;
    Date creationDate;

    public TokenInfo(int branchId, String username, List<String> authorities, Date expirationDate, Date creationDate) {
        this.branchId = branchId;
        this.username = username;
        this.authorities = authorities;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;
    }

}
