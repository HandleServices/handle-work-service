package com.handleservice.handleworkservice.unitTests.service;
import com.handleservice.handleworkservice.service.jwt.IJwtService;
import com.handleservice.handleworkservice.service.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class JwtServiceTest {

    private final IJwtService _jwtService = new JwtService();

    @Test
    void shouldReturnAuthIdWhenExtractJwtToken() {

        // Arrange
        String TEST_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiMDAwNjFlMWQtYWRiMC00ZGJjLThhZGQtMWRhYmMyNmY5MjJiIiwiZXhwIjoxNzI0Mzg2MjExLjc4MjYxMTF9.x1SCgnNdubiLI0UZygUcsvP_r-LzTQGALIPY5UQ4qTc";

        // Act
        String authId = _jwtService.extractAuthId(TEST_TOKEN);

        // Assert
        Assertions.assertEquals("00061e1d-adb0-4dbc-8add-1dabc26f922b", authId);
    }

}
