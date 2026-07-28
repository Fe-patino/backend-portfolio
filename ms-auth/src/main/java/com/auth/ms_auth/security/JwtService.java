package com.auth.ms_auth.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;



@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generarToken(UserDetails userDetails,String rol){
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", rol);
        return Jwts.builder()
                 .claims(claims)
                 .subject(userDetails.getUsername())
                 .issuedAt(new Date())
                 .expiration(new Date(System.currentTimeMillis()+ expiration))
                 .signWith(getSecretKey())
                 .compact();
    

    }            

    public String extraerEmail(String token){
        return extraerClaim(token, Claims::getSubject);

    }

    public boolean esTokenValido(String token, UserDetails userDetails){
        String email = extraerEmail(token);
        return email.equals(userDetails.getUsername())&& !esTokenExpirado(token);
    }

    private boolean esTokenExpirado(String token){
        return extraerExpiracion(token).before(new Date());

    }

    private Date extraerExpiracion(String token){
        return extraerClaim(token,Claims::getExpiration);
    }

   private <T> T extraerClaim(String token,Function<Claims,T>resolver){
        Claims claims = extraerTodosLosClaims(token);
        return resolver.apply(claims);



   }
   
   private Claims extraerTodosLosClaims(String token){
        return Jwts.parser()
                   .verifyWith(getSecretKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
    } 
    
    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

}
