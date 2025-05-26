package PAI.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceResponseTest {

    @Test
    void successShouldReturnObject() {
        String expected = "TestObject";

        ServiceResponse<String> response = ServiceResponse.success(expected);

        assertTrue(response.isSuccess());
        assertEquals(expected, response.getObject());

    }

    @Test
    void failureShouldReturnNullObjectAndMessage() {
        String errorMessage = "Something went wrong";

        ServiceResponse<String> response = ServiceResponse.failure(errorMessage);


        assertNull(response.getObject());
        assertEquals(List.of(errorMessage), response.getMessages());
    }

    @Test
    void messagesShouldSupportMultipleEntries() {
        ServiceResponse<String> response = ServiceResponse.failure("First");
        response.getMessages().add("Second");

        assertEquals(2, response.getMessages().size());
        assertEquals("First", response.getMessages().get(0));
        assertEquals("Second", response.getMessages().get(1));
    }
}
