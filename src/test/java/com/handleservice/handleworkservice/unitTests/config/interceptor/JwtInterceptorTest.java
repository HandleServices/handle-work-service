package com.handleservice.handleworkservice.unitTests.config.interceptor;


import com.handleservice.handleworkservice.config.interceptor.JwtInterceptor;
import com.handleservice.handleworkservice.exception.custom.UnauthorizedException;
import com.handleservice.handleworkservice.service.jwt.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JwtInterceptorTest {

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private JwtInterceptor jwtInterceptor;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private Object handler;

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        handler = new Object();
    }

    @Test
    public void testPreHandle_givenValidAuthorizationHeader_shouldSetWorkerIdAttributeAndReturnTrue() throws Exception {
        // Preparation
        String token = "valid.jwt.token";
        String authorizationHeader = "Bearer " + token;
        UUID workerId = UUID.fromString("a3a389d9-909e-43dc-9df1-152ee945312c");

        request.addHeader("Authorization", authorizationHeader);

        when(jwtService.extractAuthId(token)).thenReturn(workerId.toString());

        // Act
        boolean result = jwtInterceptor.preHandle(request, response, new Object());

        // Assert
        assertTrue(result);
        assertEquals(workerId, request.getAttribute("workerId"));
        verify(jwtService, times(1)).extractAuthId(token);
    }

    @Test
    public void testPreHandle_givenValidAuthorizationHeader_shouldCallJwtServiceWithCorrectParams() throws Exception {
        // Preparation
        String token = "valid.jwt.token";
        String authorizationHeader = "Bearer " + token;
        UUID workerId = UUID.randomUUID();

        request.addHeader("Authorization", authorizationHeader);

        when(jwtService.extractAuthId(argThat(tokenArgument -> tokenArgument.equals(token))))
                .thenReturn(workerId.toString());

        // Act
        jwtInterceptor.preHandle(request, response, handler);

        // Assert
        verify(jwtService, times(1)).extractAuthId(argThat(tokenArgument -> tokenArgument.equals(token)));
    }

    @Test
    public void testPreHandle_givenMissingAuthorizationHeader_shouldThrowUnauthorizedException() {
        // Act and Assert
        assertThrows(UnauthorizedException.class, () -> jwtInterceptor.preHandle(request, response, handler));
    }

    @Test
    public void testPreHandle_givenInvalidAuthorizationHeader_shouldThrowUnauthorizedException() {
        // Preparation
        String authorizationHeader = "InvalidToken";
        request.addHeader("Authorization", authorizationHeader);

        // Act and Assert
        assertThrows(UnauthorizedException.class, () -> jwtInterceptor.preHandle(request, response, handler));
    }

    @Test
    public void testPreHandle_givenEmptyAuthorizationHeader_shouldThrowUnauthorizedException() {
        // Preparation
        String authorizationHeader = "Bearer ";
        request.addHeader("Authorization", authorizationHeader);

        // Act and Assert
        assertThrows(UnauthorizedException.class, () -> jwtInterceptor.preHandle(request, response, handler));
    }
}
