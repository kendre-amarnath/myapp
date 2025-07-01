package com.contact.contactmanager.Util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setup() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secret", "12345678901234567890123456789012");
        ReflectionTestUtils.setField(jwtUtil, "expiration", 3600000L);
    }

    @Test
    void generateAndValidateToken() {
        String token = jwtUtil.generateToken("testuser");
        assertNotNull(token);
        String username = jwtUtil.extractUsername(token);
        assertEquals("testuser", username);
        assertTrue(jwtUtil.validateToken(token, "testuser"));
    }

    @Test
    void isTokenExpired_false() {
        String token = jwtUtil.generateToken("testuser");
        assertFalse(jwtUtil.isTokenExpired(token));
    }
}
