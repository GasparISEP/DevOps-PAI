package PAI.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class ErrorResponseTest {

    @Test
    void shouldCreateErrorResponseWithCorrectFields() {
        // Arrange
        String code = "FATAL_ERROR";
        String message = "Error message.";

        // Act
        ErrorResponse errorResponse = new ErrorResponse(code, message);

        // Assert
        assertEquals(code, errorResponse.getCode());
        assertEquals(message, errorResponse.getMessage());
        assertNotNull(errorResponse.getTimestamp());
        assertTrue(errorResponse.getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    @Test
    void shouldReturnCorrectCodeFromGetter() {
        // Arrange
        String code = "FATAL_ERROR";
        String message = "Error message.";

        // Act
        ErrorResponse error = new ErrorResponse(code, message);

        // Assert
        assertEquals(code, error.getCode());
    }

    @Test
    void shouldReturnCorrectMessageFromGetter() {
        // Arrange
        String code = "FATAL_ERROR";
        String message = "Error message.";

        // Act
        ErrorResponse error = new ErrorResponse(code, message);

        // Assert
        assertEquals(message, error.getMessage());
    }

    @Test
    void shouldReturnCorrectTimestampFromGetter() {
        // Arrange
        String code = "FATAL_ERROR";
        String message = "Error message.";

        // Act
        ErrorResponse error = new ErrorResponse(message, code);

        // Assert
        assertNotNull(error.getTimestamp());
    }
}
