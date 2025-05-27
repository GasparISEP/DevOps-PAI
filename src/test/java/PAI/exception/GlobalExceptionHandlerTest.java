package PAI.exception;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.List;

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


    @Test
    void handleValidationErrors_shouldReturnFirstFieldErrorMessage() {
        // Arrange
        FieldError fieldError = mock (FieldError.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        GlobalExceptionHandler handler = new GlobalExceptionHandler();

        // Act
        ResponseEntity<ErrorResponse> response = handler.handleValidationErrors(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid input", response.getBody().getMessage());
        assertEquals("ARGUMENT_INVALID", response.getBody().getCode());
    }

    @Test
    void handleValidationErrors_shouldReturnDefaultMessageWhenNoFieldErrors() {
        // Arrange
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.emptyList());

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        GlobalExceptionHandler handler = new GlobalExceptionHandler();

        // Act
        ResponseEntity<ErrorResponse> response = handler.handleValidationErrors(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid input", response.getBody().getMessage());
        assertEquals("ARGUMENT_INVALID", response.getBody().getCode());
    }

    @Test
    void handleNotFound_ShouldReturn404AndErrorResponse() {
        // Arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        String expectedMessage = "Teacher Category not found with ID: abc";
        NotFoundException ex = mock (NotFoundException.class);
        when(ex.getMessage()).thenReturn(expectedMessage);

        // Act
        ResponseEntity<ErrorResponse> response = handler.handleNotFound(ex);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }
}
