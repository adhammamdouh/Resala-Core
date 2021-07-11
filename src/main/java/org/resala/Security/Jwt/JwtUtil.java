package org.resala.Security.Jwt;

import io.jsonwebtoken.*;
import org.resala.Exceptions.JwtTokenMalformedException;
import org.resala.Exceptions.JwtTokenMissingException;
import org.resala.Models.MyUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;

import static java.util.Arrays.asList;


@Service
public class JwtUtil {
    private String SECRET_CODE = "DevOutLoudTeam111";
    /// feb ,aug

    public String generateToken(int branchId, Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, branchId, authentication);
    }

    private String createToken(Map<String, Object> claims, int branchId, Authentication authentication) {
        int year = (LocalDate.now().getMonthValue() >= Calendar.AUGUST) ?
                LocalDate.now().getYear() + 1 : LocalDate.now().getYear();
        int month = (LocalDate.now().getMonthValue() >= Calendar.FEBRUARY && LocalDate.now().getMonthValue() < Calendar.AUGUST) ?
                Calendar.AUGUST : Calendar.FEBRUARY;
       /* TokenInfo tokenInfo= new TokenInfo(
                branchId,authentication.getName(),authentication.getAuthorities(),
                new Date(year, month, 1),new Date(System.currentTimeMillis()));*/
        return Jwts.builder().setClaims(claims)
                .setSubject(authentication.getName())
                .setAudience(authentication.getAuthorities().toString())
                .setIssuer(String.valueOf(branchId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(year, month, 1))
                .signWith(SignatureAlgorithm.HS256, SECRET_CODE).compact();
    }

    public void validateToken(String token) throws JwtTokenMalformedException, JwtTokenMissingException {
        try {
            String userName = extractUserName(token);
            List<String> authorities = extractAuthorities(token);
            String role = extractId(token);
            Date expirationDate = extractExpirationDate(token);
            if (userName == null || role == null || authorities == null || expirationDate.before(new Date()))
                throw new JwtTokenMalformedException("JWT UserName or Roles can't be null or JWT Token has expired");

        } catch (SignatureException e) {
            throw new JwtTokenMalformedException("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            throw new JwtTokenMalformedException("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new JwtTokenMalformedException("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            throw new JwtTokenMalformedException("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            throw new JwtTokenMissingException("JWT claims string is empty.");
        }
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

    public String extractId(String token) {
        return extractClaim(token, Claims::getIssuer);
    }


    /*private String extractAuthorities(String token) {
        return
    }*/

    public List<String> extractAuthorities(String token) {
        String details = extractClaim(token, Claims::getAudience);
        details = details.substring(1);
        details = details.substring(0, details.length() - 1);
        if (details.length() == 0) return null;
        return asList(details.split(","));
    }


    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_CODE).parseClaimsJws(token).getBody();
    }

}
