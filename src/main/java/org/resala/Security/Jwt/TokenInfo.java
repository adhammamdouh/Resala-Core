package org.resala.Security.Jwt;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TokenInfo {
    int branchId;
    String uesrname;
    String authorities;
    Date expirationDate;
    Date creationDate;

    public TokenInfo(int branchId, String uesrname, String authorities, Date expirationDate, Date creationDate) {
        this.branchId = branchId;
        this.uesrname = uesrname;
        this.authorities = authorities;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;
    }

}
