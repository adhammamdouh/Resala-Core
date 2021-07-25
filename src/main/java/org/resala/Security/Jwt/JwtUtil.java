package org.resala.Security.Jwt;

import io.jsonwebtoken.*;
import org.resala.Exceptions.JwtTokenMalformedException;
import org.resala.Exceptions.JwtTokenMissingException;
import org.resala.Models.MyUserDetails;
import org.resala.Service.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;

import static java.util.Arrays.asList;


@Service
public class JwtUtil {
    private String SECRET_CODE = "DevOutLoudTeam111";
    /// feb ,aug

    public String generateToken(Map<String, Object> claims, Authentication authentication) {
        return createToken(claims, authentication);
    }

    public String createTokenForTest(Map<String, Object> claims, List<SimpleGrantedAuthority> authorities) {
        int year = (LocalDate.now().getMonthValue() >= Calendar.AUGUST) ?
                LocalDate.now().getYear() + 1 : LocalDate.now().getYear();
        int month = (LocalDate.now().getMonthValue() >= Calendar.FEBRUARY && LocalDate.now().getMonthValue() < Calendar.AUGUST) ?
                Calendar.AUGUST : Calendar.FEBRUARY;
        return Jwts.builder().setClaims(claims)
                .setSubject("test")
                .setAudience(authorities.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(year, month, 1))
                .signWith(SignatureAlgorithm.HS256, SECRET_CODE).compact();
    }

    private String createToken(Map<String, Object> claims, Authentication authentication) {
        int year = (LocalDate.now().getMonthValue() >= Calendar.AUGUST) ?
                LocalDate.now().getYear() + 1 : LocalDate.now().getYear();
        int month = (LocalDate.now().getMonthValue() >= Calendar.FEBRUARY && LocalDate.now().getMonthValue() < Calendar.AUGUST) ?
                Calendar.AUGUST : Calendar.FEBRUARY;

        return Jwts.builder().setClaims(claims)
                .setSubject(authentication.getName())
                .setAudience(authentication.getAuthorities().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(year, month, 1))
                .signWith(SignatureAlgorithm.HS256, SECRET_CODE).compact();
    }

    public void validateToken(String token) throws JwtTokenMalformedException, JwtTokenMissingException {
        try {
            String userName = extractUserName(token);
            List<String> authorities = extractAuthorities(token);
            Claims claims = getClaims(token);
            Date expirationDate = extractExpirationDate(token);
            if (userName == null || claims.get(TokenService.myOrganizationId) == null || authorities == null || expirationDate.before(new Date()))
                throw new JwtTokenMalformedException("Invalid JWT signature or JWT Token has expired");
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


    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractId(String token) {
        return extractClaim(token, Claims::getIssuer);
    }

    public Claims getClaims(String token) {
        return extractAllClaims(token);
    }


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
