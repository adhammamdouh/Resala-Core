package org.resala.Security.Filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.resala.Security.Jwt.JwtUtil;
import org.resala.Security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
                if (!jwtUtil.validateToken(jwt)) {
                    logger.error("Unable to get JWT Token or JWT Token has expired");
                    return;
                }
            } catch (IllegalArgumentException | MalformedJwtException | ExpiredJwtException e) {
                logger.error("Unable to get JWT Token or JWT Token has expired");
                return;
            }
            username = jwtUtil.extractUserName(jwt);
            id = jwtUtil.extractId(jwt);
            actions = jwtUtil.extractAuthorities(jwt);
            for (String action : actions) {
                //System.out.println(action);
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
