package org.resala.Controllers.Security;

import org.resala.Exceptions.TokenException;
import org.resala.StaticNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/validateToken")
public class ValidateToken {

    @RequestMapping(value = "/validate",method = RequestMethod.GET)
    @PermitAll
    public void validateToken(){
        throw new TokenException(StaticNames.invalidToken);
    }
}
