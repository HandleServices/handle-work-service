package com.handleservice.handleworkservice.config.interceptor;

import com.handleservice.handleworkservice.exception.custom.DomainUnauthorizedException;
import com.handleservice.handleworkservice.service.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private final JwtService jwtService;

    @Autowired
    public JwtInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws DomainUnauthorizedException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ") ||
                authorizationHeader.replace("Bearer ", "").trim().isEmpty()
        ) {
            throw new DomainUnauthorizedException("Authorization header is missing or incorrect");
        }


        try{
            String jwtToken = authorizationHeader.replace("Bearer", "").trim();
            UUID workerId = UUID.fromString(jwtService.extractAuthId(jwtToken));
            request.setAttribute("workerId", workerId);
        } catch (IllegalArgumentException e){
            throw new DomainUnauthorizedException("Authorization header is incorrect");
        }

        return true;
    }
}
