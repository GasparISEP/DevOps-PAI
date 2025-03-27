package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    @Test
    public void testValidDateString() {
        Date date = new Date("15-08-2023");
        assertEquals(LocalDate.of(2023, 8, 15), date.getLocalDate());
    }
    @Test
    public void testValidDate() {
        Date date = new Date(LocalDate.now());
        assertEquals(LocalDate.now(), date.getLocalDate());
    }

    @Test
    public void testInvalidDateFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Date("2023-08-15");
        });
        assertEquals("Invalid date format. Please use dd-MM-yyyy.", exception.getMessage());
    }

    @Test
    public void testInvalidDateValues() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Date("32-12-2023");
        });
        assertEquals("Invalid date format. Please use dd-MM-yyyy.", exception.getMessage());
    }

    @Test
    public void testNullDateString() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Date((String) null);
        });
        assertEquals("Date cannot be empty!", exception.getMessage());
    }

    @Test
    public void testEmptyDateString() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Date("");
        });
        assertEquals("Date cannot be empty!", exception.getMessage());
    }

    @Test
    public void testValidLocalDateConstructor() {
        LocalDate localDate = LocalDate.now();
        Date date = new Date(localDate);
        assertEquals(localDate, date.getLocalDate());
    }

    @Test
    public void testNullLocalDate() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Date((LocalDate) null);
        });
        assertEquals("Date cannot be null!", exception.getMessage());
    }

    @Test
    public void testEqualsMethod() {
        Date date1 = new Date("15-08-2023");
        Date date2 = new Date("15-08-2023");
        assertEquals(date1, date2);
    }
    @Test
    public void testEqualsMethodLocalDateNow() {
        Date date1 = new Date(LocalDate.now());
        Date date2 = new Date(LocalDate.now());
        assertEquals(date1, date2);
    }

    @Test
    public void testNotEqualsMethod() {
        Date date1 = new Date("15-08-2023");
        Date date2 = new Date("16-08-2023");
        assertNotEquals(date1, date2);
    }

    @Test
    public void testHashCodeMethod() {
        Date date1 = new Date("15-08-2023");
        Date date2 = new Date("15-08-2023");
        assertEquals(date1.hashCode(), date2.hashCode());
    }
    @Test
    public void testHashCodeMethodLocalDateNow() {
        Date date1 = new Date(LocalDate.now());
        Date date2 = new Date(LocalDate.now());
        assertEquals(date1.hashCode(), date2.hashCode());
    }

}