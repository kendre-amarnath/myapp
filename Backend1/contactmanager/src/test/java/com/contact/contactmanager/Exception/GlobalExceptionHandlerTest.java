package com.contact.contactmanager.Exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleNotFound() {
        ResponseEntity<Object> response = handler.handleNotFound(new ResourceNotFoundException("Not found"));
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void handleDuplicateKey() {
        ResponseEntity<Object> response = handler.handleDuplicateKey(new org.springframework.dao.DuplicateKeyException("Duplicate"));
        assertEquals(409, response.getStatusCodeValue());
    }

    @Test
    void handleGenericException() {
        ResponseEntity<Object> response = handler.handleGenericException(new RuntimeException("Error"));
        assertEquals(500, response.getStatusCodeValue());
    }
}
