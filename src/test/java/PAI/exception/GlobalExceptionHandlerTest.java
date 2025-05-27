package PAI.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class GlobalExceptionHandlerTest {

    @Test
    void shouldHandleAlreadyRegisteredException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        AlreadyRegisteredException ex = new AlreadyRegisteredException("Student");

        ResponseEntity<ErrorResponse> response = handler.handleAlreadyRegistered(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Student is already registered.", response.getBody().getMessage());
        assertEquals("ALREADY_REGISTERED", response.getBody().getCode());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    void shouldHandleIllegalArgumentException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        IllegalArgumentException ex = new IllegalArgumentException("Invalid argument");

        ResponseEntity<ErrorResponse> response = handler.handleIllegalArgument(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid argument", response.getBody().getMessage());
        assertEquals("ARGUMENT_INVALID", response.getBody().getCode());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    void shouldHandleBusinessRuleViolationException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        BusinessRuleViolationException ex = new BusinessRuleViolationException("Number os semesters can't be 0 or negative.");

        ResponseEntity<ErrorResponse> response = handler.handleBusinessRuleViolation(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Number os semesters can't be 0 or negative.", response.getBody().getMessage());
        assertEquals("BUSINESS_RULE_VIOLATED", response.getBody().getCode());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    void shouldHandleGenericException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        Exception ex = new Exception("Entity can't be null.");

        ResponseEntity<ErrorResponse> response = handler.handleGeneric(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Entity can't be null.", response.getBody().getMessage());
        assertEquals("INTERNAL_ERROR", response.getBody().getCode());
        assertNotNull(response.getBody().getTimestamp());
    }

}
