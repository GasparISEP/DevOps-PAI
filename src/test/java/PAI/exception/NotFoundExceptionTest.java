package PAI.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {

    @Test
    void shouldCreateExceptionWithCorrectMessageAndEntity() {
        //arrange
        String message = "Teacher Category was not found";

        //act
        NotFoundException result = new NotFoundException(message);

        //assert
        assertEquals(message, result.getMessage());
    }

}