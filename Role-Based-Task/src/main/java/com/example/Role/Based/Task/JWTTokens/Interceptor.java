package com.example.Role.Based.Task.JWTTokens;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class Interceptor implements HandlerInterceptor {
    @Autowired
    private GenerateToken generateToken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String jwts =null;
        String token = request.getHeader("Authorization");
        if(token!=null & token.startsWith("Bearer")){
            jwts =token.substring(7,token.length());
        }
        if (request.getRequestURI().contains("/adminapi") & !request.getRequestURI().contains("/addadmin")){
            try {
            Claims claims = this.generateToken.verifyToken(jwts);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Unauthorized Access");
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
