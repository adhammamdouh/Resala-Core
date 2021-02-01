package org.resala.Security.Jwt;

import io.jsonwebtoken.*;
import org.resala.Models.MyUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

import static java.util.Arrays.asList;

@Service
public class JwtUtil {
    private String SECRET_CODE = "DevOutLoudTeam111";

    public String generateToken(MyUserDetails user) {
        Map<String, Object> claims = new HashMap<>();
        List<GrantedAuthority> authorities = new ArrayList<>(user.getAuthorities());
        return createToken(claims, user.getUsername(), "user.getRole()", authorities);
    }

    private String createToken(Map<String, Object> claims, String userDetails, String role, List<GrantedAuthority> authorities) {
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails)
                .setAudience(authorities.toString())
                .setIssuer(role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 182))
                .signWith(SignatureAlgorithm.HS256, SECRET_CODE).compact();
    }

    public boolean validateToken(String token) {
        String userName = extractUserName(token);
        List<String> authorities = extractAuthorities(token);
        String role = extractRole(token);
        Date expirationDate = extractExpirationDate(token);
        if (userName == null || role == null || authorities == null || expirationDate.before(new Date()))
            return false;
        return true;
    }

    /*public String extractUserName(String token) {
        String details=extractUserDetails(token);
        return details;
    }*/
    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        return extractClaim(token, Claims::getIssuer);
    }


    /*private String extractAuthorities(String token) {
        return
    }*/

    public List<String> extractAuthorities(String token) {
        String details = extractClaim(token, Claims::getAudience);
        details = details.substring(1);
        details = details.substring(0, details.length() - 1);
        return asList(details.split(","));
    }


    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_CODE).parseClaimsJws(token).getBody();
    }

}
