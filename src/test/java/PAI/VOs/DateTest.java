package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    @Test
    void DateWithLocalDateShouldReturnAValidDate() {
        // Arrange
        LocalDate expectedDate = LocalDate.now();
        Date date= new Date(expectedDate);

        // Act
        LocalDate actualDate = date.getLocalDate();

        // Assert
        assertNotNull(date);
        assertEquals(expectedDate, actualDate, "The date should be the one passed in the constructor.");
    }

    @Test
    void DateWithNullLocalDateShouldThrowException() {
        //arrange
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Date(null);
        });
        assertEquals("Date cannot be null", exception.getMessage());
    }

    @Test
    void testGetLocalDate() {
        // Arrange
        LocalDate expectedDate = LocalDate.now();
        Date date = new Date(expectedDate);

        // Act
        LocalDate actualDate = date.getLocalDate();

        // Assert
        assertEquals(expectedDate, actualDate, "The local date should match the one passed in the constructor.");
    }
}