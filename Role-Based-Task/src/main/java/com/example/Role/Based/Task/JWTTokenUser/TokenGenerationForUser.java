package com.example.Role.Based.Task.JWTTokenUser;

import com.example.Role.Based.Task.Entity.Admin;
import com.example.Role.Based.Task.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerationForUser {
    private  static  final String  privateKey= "This Private Key is User ";
    Long duration = 604800L;
    Long takenTime = System.currentTimeMillis();
    Long expiredTime = duration + takenTime * 1000L;
    Date takenAt = new Date(takenTime);
    Date expiredAt = new Date(expiredTime);

    public String generateTokens(User user){
        Claims claims = Jwts.claims().setExpiration(expiredAt).setIssuedAt(takenAt).setIssuer(user.getUserName());
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS384,privateKey).compact();
    }
    public Claims verifyToken(String authorization) throws Exception {
        try {
            Claims claims = Jwts.parser().setSigningKey(privateKey).parseClaimsJws(authorization).getBody();
            return claims;
        }catch (IllegalArgumentException e){
            throw  new IllegalArgumentException("UnAuthorized Access");
        }
    }
}
