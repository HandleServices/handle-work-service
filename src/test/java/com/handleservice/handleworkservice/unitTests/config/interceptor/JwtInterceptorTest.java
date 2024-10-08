package com.handleservice.handleworkservice.unitTests.config.interceptor;


import com.handleservice.handleworkservice.config.interceptor.JwtInterceptor;
import com.handleservice.handleworkservice.exception.custom.DomainUnauthorizedException;
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

    @InjectMocks
    private JwtInterceptor jwtInterceptor;

    @Mock
    private JwtService jwtService;

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
    public void testPreHandle_shouldSetWorkerIdAttributeAndReturnTrueWhenValidAuthorizationHeader() throws DomainUnauthorizedException {
        // Arrange
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
    public void testPreHandle_shouldCallJwtServiceWithCorrectParamsWhenValidAuthorizationHeader() throws DomainUnauthorizedException {
        // Arrange
        String token = "valid.jwt.token";
        String authorizationHeader = "Bearer " + token;
        UUID workerId = UUID.randomUUID();

        when(jwtService.extractAuthId(argThat(tokenArgument -> tokenArgument.equals(token))))
                .thenReturn(workerId.toString());

        // Act
        request.addHeader("Authorization", authorizationHeader);
        jwtInterceptor.preHandle(request, response, handler);

        // Assert
        verify(jwtService, times(1)).extractAuthId(argThat(tokenArgument -> tokenArgument.equals(token)));
    }

    @Test
    public void testPreHandle_shouldThrowDomainUnauthorizedExceptionWhenMissingAuthorizationHeader() {
        // Act and Assert
        DomainUnauthorizedException exception = assertThrows(DomainUnauthorizedException.class, () -> jwtInterceptor.preHandle(request, response, handler));
        assertEquals("Authorization header is missing or incorrect", exception.getMessage());
    }

    @Test
    public void testPreHandle_shouldThrowDomainUnauthorizedExceptionWhenInvalidAuthorizationHeader() {
        // Arrange
        String authorizationHeader = "NoBearer";

        // Act
        request.addHeader("Authorization", authorizationHeader);

        // Assert
        DomainUnauthorizedException exception = assertThrows(DomainUnauthorizedException.class, () -> jwtInterceptor.preHandle(request, response, handler));
        assertEquals("Authorization header is missing or incorrect", exception.getMessage());
    }

    @Test
    public void testPreHandle_shouldThrowDomainUnauthorizedExceptionWhenGivenEmptyAuthorizationHeader() {
        // Arrange
        String authorizationHeader = "Bearer ";

        // Act
        request.addHeader("Authorization", authorizationHeader);

        // Act and Assert
        DomainUnauthorizedException exception = assertThrows(DomainUnauthorizedException.class, () -> jwtInterceptor.preHandle(request, response, handler));
        assertEquals("Authorization header is missing or incorrect", exception.getMessage());
    }

}
