package PAI.VOs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {

    @Test
    void shouldCreateDescriptionWhenValidStringIsProvided() {
        // Arrange & Act
        Description description = new Description("School year 2025/2026");

        // Assert
        assertNotNull(description);
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Description(null));
        assertEquals("Description cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {
        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Description(""));
        assertEquals("Description cannot be null or empty", exception.getMessage());
    }

    @Test
    void testGetDescription() {
        // Arrange
        String expectedDescription = "School Year 2023/2024";
        Description description = new Description(expectedDescription);

        // Act
        String actualDescription = description.getDescription();

        // Assert
        assertEquals(expectedDescription, actualDescription);
    }
}
