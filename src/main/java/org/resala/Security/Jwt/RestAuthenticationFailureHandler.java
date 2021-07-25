package org.resala.Security.Jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.resala.Models.Auth.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler
{
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException ex) throws IOException, ServletException
    {
        Response response1=new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        response.setStatus(HttpStatus.BAD_REQUEST.value());


        OutputStream out = response.getOutputStream();
        com.fasterxml.jackson.databind.ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, response1);
        out.flush();
    }
}