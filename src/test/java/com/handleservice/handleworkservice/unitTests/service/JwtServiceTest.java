package com.handleservice.handleworkservice.unitTests.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.handleservice.handleworkservice.service.jwt.IJwtService;
import com.handleservice.handleworkservice.service.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {

    @InjectMocks
    private final IJwtService _jwtService = new JwtService();

    @Mock
    private ObjectMapper OBJECT_MAPPER;

    @Test
    public void testExtractAuthId_givenValidTokenWithUserIdField_shouldReturnAuthId() {
        // Preparation
        String validToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiMDAwNjFlMWQtYWRiMC00ZGJjLThhZGQtMWRhYmMyNmY5MjJiIiwiZXhwIjoxNzI0Mzg2MjExLjc4MjYxMTF9.x1SCgnNdubiLI0UZygUcsvP_r-LzTQGALIPY5UQ4qTc";

        // Act
        String authId = _jwtService.extractAuthId(validToken);

        // Assert
        Assertions.assertEquals("00061e1d-adb0-4dbc-8add-1dabc26f922b", authId);
    }

    @Test
    public void testExtractAuthId_givenInvalidToken_shouldThrowIllegalArgumentException() {
        // Preparation
        String invalidToken = "Invalid token";

        // Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> _jwtService.extractAuthId(invalidToken));
    }

    @Test
    public void testExtractAuthId_givenValidTokenWithoutUserIdField_shouldThrowIllegalArgumentException() {
        // Preparation
        String validTokenWithoutUserIdField = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZXN0IjoidGVzdCJ9.9EQaLsDRKDVXLUVLR9JgDTjEULaT2-OMbHayQAzgZH8";

        // Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> _jwtService.extractAuthId(validTokenWithoutUserIdField));
    }

    @Test
    public void testExtractClaim_givenInvalidToken_shouldThrowIllegalArgumentException() {
        // Preparation
        String invalidTokenWithUserIdField = "apparently.valid.token";
        String claim = "user_id";

        // Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> _jwtService.extractClaim(invalidTokenWithUserIdField,claim));
    }
}
