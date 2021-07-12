package org.resala.Security.Filter;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.resala.Models.Auth.MyJacksonObjMapper;
import org.resala.Models.Auth.Response;
import org.resala.Security.Jwt.JwtUtil;
import org.resala.Security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authorizationHeader;

        String header = request.getHeader("Authorization");
        try {
            authorizationHeader = request.getHeader("Authorization");

        } catch (Exception e) {
            logger.error("Request header should be Authorization");
            return;
        }

        String username = null;
        String id = null;
        String jwt;
        List<String> actions;
        Collection<GrantedAuthority> grantedAuth = new ArrayList<>();

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                jwtUtil.validateToken(jwt);
            } catch (Exception e) {
                System.out.println(e.getCause().toString());
                logger.error(e.getMessage());
                response.setContentType("application/json");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                Response responseObj = new Response(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
                ObjectMapper mapper = new MyJacksonObjMapper();
                response.getWriter().write(mapper.writeValueAsString(responseObj));
                return;
            }
            username = jwtUtil.extractUserName(jwt);
            id = jwtUtil.extractId(jwt);
            actions = jwtUtil.extractAuthorities(jwt);

            for (String action : actions) {
                grantedAuth.add(new SimpleGrantedAuthority(action.trim()));
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            ///move to login
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            username, id, grantedAuth);

            usernamePasswordAuthenticationToken
                    .setDetails(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        chain.doFilter(request, response);
    }


}
