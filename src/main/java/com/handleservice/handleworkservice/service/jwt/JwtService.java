package com.handleservice.handleworkservice.service.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.regex.Pattern;

@Service
public class JwtService implements IJwtService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Pattern JWT_PATTERN = Pattern.compile("^[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*$");

    @Override
    public String extractAuthId(String token) {
        return extractClaim(token, "user_id");
    }

    @Override
    public String extractClaim(String token, String claimName) throws IllegalArgumentException {
        JsonNode claims = extractAllClaims(token);
        JsonNode claimNode = claims.get(claimName);
        if (claimNode == null) {
            throw new IllegalArgumentException("Claim not found: " + claimName);
        }
        return claimNode.asText();
    }

    public JsonNode extractAllClaims(String token) throws IllegalArgumentException {
        if (!JWT_PATTERN.matcher(token).matches()) {
            throw new IllegalArgumentException("Invalid JWT token");
        }
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        try {
            return objectMapper.readTree(payload);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to parse JWT payload", e);
        }
    }

}
