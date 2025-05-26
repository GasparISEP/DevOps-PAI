package PAI.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlreadyRegisteredExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessageAndEntity() {
        // Arrange
        String expectedEntity = "Programme";
        String expectedMessage = "Programme is already registered.";

        // Act
        AlreadyRegisteredException ex = new AlreadyRegisteredException(expectedEntity);

        // Assert
        assertEquals(expectedMessage, ex.getMessage());
    }
}
