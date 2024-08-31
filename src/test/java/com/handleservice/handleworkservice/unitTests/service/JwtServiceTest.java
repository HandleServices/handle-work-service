package com.handleservice.handleworkservice.unitTests.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.handleservice.handleworkservice.service.jwt.IJwtService;
import com.handleservice.handleworkservice.service.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {
    private final JwtService jwtService = new JwtService();

    @Test
    public void testExtractAuthId_shouldReturnRightAuthIdWhenValidTokenWithUserIdField() {
        // Arrange
        String validToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiMDAwNjFlMWQtYWRiMC00ZGJjLThhZGQtMWRhYmMyNmY5MjJiIiwiZXhwIjoxNzI0Mzg2MjExLjc4MjYxMTF9.x1SCgnNdubiLI0UZygUcsvP_r-LzTQGALIPY5UQ4qTc";

        // Act
        String authId = jwtService.extractAuthId(validToken);

        // Assert
        Assertions.assertEquals("00061e1d-adb0-4dbc-8add-1dabc26f922b", authId);
    }

    @Test
    public void testExtractAuthId_shouldThrowIllegalArgumentExceptionWhenInvalidToken() {
        // Arrange
        String invalidToken = "Invalid token";

        // Act and Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> jwtService.extractAuthId(invalidToken));
        Assertions.assertEquals("Invalid JWT token", exception.getMessage());
    }

    @Test
    public void testExtractAuthId_shouldThrowIllegalArgumentExceptionWhenValidTokenWithoutUserIdField() {
        // Arrange
        String validTokenWithoutUserIdField = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZXN0IjoidGVzdCJ9.9EQaLsDRKDVXLUVLR9JgDTjEULaT2-OMbHayQAzgZH8";

        // Act and Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> jwtService.extractAuthId(validTokenWithoutUserIdField));
        Assertions.assertEquals("Claim not found: user_id", exception.getMessage());
    }

    @Test
    public void testExtractAllClaims_shouldThrowIllegalArgumentExceptionWhenInvalidJsonPayload() {
        // Arrange
        String invalidJsonToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.INVALID_JSON_PAYLOAD.eyJ1c2VyX2lkIjoiMDAwNjFlMWQtYWRiMC00ZGJjLThhZGQtMWRhYmMyNmY5MjJiIiwiZXhwIjoxNzI0Mzg2MjExLjc4MjYxMTF9";

        // Act and Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            jwtService.extractAllClaims(invalidJsonToken);
        });

        Assertions.assertEquals("Failed to parse JWT payload", exception.getMessage());
    }


}
