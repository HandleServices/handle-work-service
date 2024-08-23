package com.handleservice.handleworkservice.service.jwt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class JwtService implements IJwtService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public String extractAuthId(String token) {
        return extractClaim(token, "user_id");
    }

    @Override
    public String extractClaim(String token, String claimName) {
        JsonNode claims = extractAllClaims(token);
        JsonNode claimNode = claims.get(claimName);
        if (claimNode == null) {
            throw new IllegalArgumentException("Claim not found: " + claimName);
        }
        return claimNode.asText();
    }


    private JsonNode extractAllClaims(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        try {
            return OBJECT_MAPPER.readTree(payload);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JWT token", e);
        }
    }

}
