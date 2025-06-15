package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {
    @Test
    public void testDateCreationFromString() {
        Date date = new Date("31-03-2025");
        assertEquals(LocalDate.of(2025, 3, 31), date.getLocalDate());
    }

    @Test
    public void testDateCreationFromLocalDate() {
        LocalDate localDate = LocalDate.of(2025, 3, 31);
        Date date = new Date(localDate);
        assertEquals(localDate, date.getLocalDate());
    }

    @Test
    public void testInvalidDateFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Date("2025-03-31");
        });
        assertEquals("Invalid date format. Please use dd-MM-yyyy.", exception.getMessage());
    }

    @Test
    public void testIsBefore() {
        Date date1 = new Date("30-03-2025");
        Date date2 = new Date("31-03-2025");
        assertTrue(date1.isBefore(date2));
        assertFalse(date2.isBefore(date1));
    }

    @Test
    public void testIsAfter() {
        Date date1 = new Date("30-03-2025");
        Date date2 = new Date("31-03-2025");
        assertTrue(date2.isAfter(date1));
        assertFalse(date1.isAfter(date2));
    }

    @Test
    public void testEquals() {
        Date date1 = new Date("31-03-2025");
        Date date2 = new Date("31-03-2025");
        Date date3 = new Date("30-03-2025");
        //act + assert
        assertEquals(date1, date2);
        assertNotEquals(date1, date3);
    }
    @Test
    public void testEqualsDiferentClasses() {
        Date date1 = new Date("31-03-2025");
        String date2 = "31-03-2025";
        //act + assert
        assertNotEquals(date2, date1);
    }


    @Test
    public void testEqualsWithDifferentClass() {
        Date date1 = new Date("31-03-2025");
        Object differentObject = "31-03-2025";
        assertFalse(date1.equals(differentObject));
    }
    @Test
    public void testNow() {
        Date today = Date.now();
        assertEquals(LocalDate.now(), today.getLocalDate());
    }

    @Test
    void testToString() {
        Date date = new Date(LocalDate.of(2025, 6, 15));
        assertEquals("15-06-2025", date.toString());
    }

    @Test
    void testHashCodeSameDate() {
        LocalDate localDate = LocalDate.of(2025, 6, 15);
        Date date1 = new Date(localDate);
        Date date2 = new Date(localDate);

        assertEquals(date1.hashCode(), date2.hashCode());
    }

    @Test
    void testHashCodeDifferentDates() {
        Date date1 = new Date(LocalDate.of(2025, 6, 15));
        Date date2 = new Date(LocalDate.of(2024, 6, 15));

        assertNotEquals(date1.hashCode(), date2.hashCode());
    }
}
