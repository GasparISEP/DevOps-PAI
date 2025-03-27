package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    @Test
    void DateWithLocalDateShouldReturnAValidDate() {
        //arrange
        Date date1 = new Date(LocalDate.now());
        //Act+Assert
        assertNotNull(date1);
    }

    @Test
    void DateWithNullLocalDateShouldReturnException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Date(null);
        });
        assertEquals("Date cannot be null", exception.getMessage());
    }

}