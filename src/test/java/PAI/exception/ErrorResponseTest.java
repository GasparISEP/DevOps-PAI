package PAI.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class ErrorResponseTest {

    @Test
    void shouldCreateErrorResponseWithCorrectValues() {
        String message = "Error";
        int status = 400;
        ErrorResponse errorResponse = new ErrorResponse(message, status);

        assertEquals(message, errorResponse.getMessage());
        assertEquals(status, errorResponse.getStatus());
        assertNotNull(errorResponse.getTimestamp());
        assertTrue(errorResponse.getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1)));
    }
}
