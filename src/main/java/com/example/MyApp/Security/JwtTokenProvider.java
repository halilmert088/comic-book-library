package com.example.MyApp.Security;

import com.example.MyApp.Repository.UserRepository;
import com.example.MyApp.Service.ServiceImpl.UserDetailsServiceImpl;
import com.example.MyApp.Service.ServiceImpl.UserServiceImpl;
import io.jsonwebtoken.*;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private UserDetailsServiceImpl userDetailsService;
    @Value("${MyApp.app.secret}")
    private String app_secret;

    @Value("${MyApp.expires.in}")
    private long expires_in;

    public String generateJwtToken(@NotNull Authentication auth)
    {
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal(); //??????

        Date expireDate = new Date(new Date().getTime() + expires_in);
        return Jwts.builder().setSubject(Integer.toString(userDetails.getId()))
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, app_secret).compact();
    }

    int getUserIdFromJwt(String token)
    {
        Claims claims = Jwts.parser().setSigningKey(app_secret).parseClaimsJws(token).getBody();
        return Integer.parseInt(claims.getSubject());
    }

    boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(app_secret).parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (SignatureException e) {
            return false;
        } catch (MalformedJwtException e){
            return false;
        } catch (ExpiredJwtException e){
            return false;
        } catch (UnsupportedJwtException e){
            return false;
        } catch(IllegalArgumentException e){
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(app_secret).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}