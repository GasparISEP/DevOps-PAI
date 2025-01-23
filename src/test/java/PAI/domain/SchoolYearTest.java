package PAI.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class SchoolYearTest {

    // -------------------------------------------------------------
    // ------------ TESTS FOR VALID PARAMETERS -------------------
    // -------------------------------------------------------------

    @Test
    void validArgumentsCreateSchoolYear() throws Exception {
        // Arrange & Act
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        // Assert
        assertNotNull(sy1);
    }


    @Test
    void sameYearStartAndEndDate() throws Exception {
        // Arrange & Act
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "01-09-2024", "31-12-2024");

        // Assert
        assertNotNull(sy1);
    }

    @Test
    void oneDayIntervalIsValid() throws Exception {
        // Arrange & Act
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "01-09-2024", "02-09-2024");

        // Assert
        assertNotNull(sy1);
    }

    // -------------------------------------------------------------
    // ------------ TESTS FOR INVALID PARAMETERS -------------------
    // -------------------------------------------------------------

    @Test
    void endDateBeforeStartDateThrowsException() {
        // Arrange, Act, Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear("Ano letivo de", "24-09-2024", "23-09-2024"); // endDate is before startDate
        });
    }

    @Test
    void endDateSameAsStartDateThrowsException() {
        // Arrange, Act, Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear("Ano letivo de", "24-09-2024", "24-09-2024"); // endDate is the same as startDate
        });
    }

    @Test
    void twoDigitYearThrowsException() {
        // Arrange, Act, Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear("Ano letivo de", "24-09-24", "24-09-25"); // Invalid two-digit year
        });
    }

    @Test
    void nullDescriptionThrowsException() {
        // Arrange, Act, Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(null, "10-09-2024", "09-07-2025");
        });
    }

    @Test
    void blankDescriptionThrowsException() {
        // Arrange, Act, Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear("", "10-09-2024", "09-06-2025");
        });
    }

    @Test
    void nullStartDateThrowsException() {
        // Arrange, Act, Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear("Ano letivo de", null, "24-06-2025");
        });
    }

    @Test
    void blankStartDateThrowsException() {
        // Arrange, Act, Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear("Ano letivo de", "", "13-06-2025");
        });
    }

    @Test
    void nullEndDateThrowsException() {
        // Arrange, Act, Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear("Ano letivo de", "13-09-2024", null);
        });
    }

    @Test
    void blankEndDateThrowsException() {
        // Arrange, Act, Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear("Ano letivo de", "13-09-2024", "");
        });
    }

    @Test
    void invalidDateFormatThrowsException() {
        // Arrange, Act, Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear("Ano letivo de", "2024-09-24", "2025-06-24"); // Incorrect format
        });
    }

    @Test
    void dateWrittenInWordsThrowsException() {
        // Arrange, Act, Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear("Ano letivo de", "24 de setembro de 2024", "24 de junho de 2025");
        });
    }

    @Test
    void shouldReturnTrueIfTwoSchoolYearsHaveSameStartDateAndEndDate() throws Exception {
        // Arrange
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        SchoolYear sy2 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");

        // Act
        boolean result = (sy1.isSameSchoolYear(sy2));

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoSchoolYearsDoNotHaveSameEndDate() throws Exception {
        // Arrange
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        SchoolYear sy2 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2026");

        // Act
        boolean result = (sy1.isSameSchoolYear(sy2));

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTwoSchoolYearsDoNotHaveSameStartDate() throws Exception {
        // Arrange
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        SchoolYear sy2 = new SchoolYear("Ano letivo de", "23-11-2025", "09-12-2025");

        // Act
        boolean result = (sy1.isSameSchoolYear(sy2));

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnEndDateFromSchoolYear() throws Exception {
        // Arrange
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate lc = LocalDate.parse("09-12-2025", formatter);

        // Act
        LocalDate endDate = sy1.getEndDate();

        // Assert
        assertEquals(endDate, lc);
    }
}