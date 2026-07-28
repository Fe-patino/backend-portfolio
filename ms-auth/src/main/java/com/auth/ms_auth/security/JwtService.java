package com.auth.ms_auth.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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

   

}
