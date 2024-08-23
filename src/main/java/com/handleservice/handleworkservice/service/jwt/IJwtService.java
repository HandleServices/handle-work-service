package com.handleservice.handleworkservice.service.jwt;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.function.Function;

public interface IJwtService {

    String extractAuthId(String token);

    String extractClaim(String token, String claimName);
}
