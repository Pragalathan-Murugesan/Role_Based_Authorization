package com.example.Role.Based.Task.JWTTokenUser;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserInterceptor implements HandlerInterceptor{
    @Autowired
    private TokenGenerationForUser tokenGenerationForUser;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String jwts =null;
        String token = request.getHeader("Authorization");
        if(token!=null & token.startsWith("Bearer")){
            jwts =token.substring(7,token.length());
        }
        if (request.getRequestURI().contains("/userapi") & !request.getRequestURI().contains("/adduser")){
            try {
                Claims claims = this.tokenGenerationForUser.verifyToken(jwts);
            }catch (IllegalArgumentException e){
                throw new IllegalArgumentException("Unauthorized Access");
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
