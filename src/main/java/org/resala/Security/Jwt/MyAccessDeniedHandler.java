package org.resala.Security.Jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.resala.Models.Auth.MyJacksonObjMapper;
import org.resala.Models.Auth.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyAccessDeniedHandler.class);
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        logger.error("Forbidden error: {}", e.getMessage());
        response.setContentType("application/json");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        Response responseObj = new Response(HttpStatus.FORBIDDEN.value(), "Forbidden");
        ObjectMapper mapper = new MyJacksonObjMapper();
        response.getWriter().write(mapper.writeValueAsString(responseObj));
    }
}
