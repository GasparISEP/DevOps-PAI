package PAI.domain;

import PAI.VOs.Description;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class SchoolYearTest {

    // -------------------------------------------------------------
    // ------------ TESTS FOR VALID PARAMETERS -------------------
    // -------------------------------------------------------------

    @Test
    void validArgumentsCreateSchoolYear() {
        // Arrange
        Description description = new Description("School Year 23/24");
        // Act
        SchoolYear sy1 = new SchoolYear(description, "01-09-2023", "31-08-2024");
        // Assert
        assertNotNull(sy1);
    }


    @Test
    void sameYearStartAndEndDate() {
        // Arrange
        Description description = new Description("School Year 24/25");
        // Act
        SchoolYear sy1 = new SchoolYear(description, "01-01-2024", "31-10-2024");

        // Assert
        assertNotNull(sy1);
    }

    @Test
    void oneDayIntervalIsValid() {
        // Arrange
        Description description = new Description("School Year 24/25");
        // Act
        SchoolYear sy1 = new SchoolYear(description, "01-09-2024", "02-09-2024");

        // Assert
        assertNotNull(sy1);
    }

    // -------------------------------------------------------------
    // ------------ TESTS FOR INVALID PARAMETERS -------------------
    // -------------------------------------------------------------

    @Test
    void endDateBeforeStartDateThrowsException() {
        // Arrange
        Description description = new Description("School Year 24/25");
        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(description, "01-09-2024", "31-08-2023"); // endDate is before startDate
        });
    }

    @Test
    void endDateSameAsStartDateThrowsException() {
        // Arrange
        Description description = new Description("School Year 24/25");
        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(description, "24-09-2024", "24-09-2024"); // endDate is the same as startDate
        });
    }

    @Test
    void twoDigitYearThrowsException() {
        // Arrange
        Description description = new Description("School Year 24/25");
        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(description, "24-09-24", "24-09-25"); // Invalid two-digit year
        });
    }

    @Test
    void nullDescriptionThrowsException() {

        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(new Description(null), "10-09-2024", "09-07-2025");
        });
    }

    @Test
    void blankDescriptionThrowsException() {

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new SchoolYear(new Description(" "), "10-09-2024", "09-06-2025");
        });
    }

    @Test
    void nullStartDateThrowsException() {
        // Arrange
        Description description = new Description("School Year 24/25");
        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(description, null, "24-06-2025");
        });
    }

    @Test
    void blankStartDateThrowsException() {
        // Arrange
        Description description = new Description("School Year 24/25");
        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(description, "", "13-06-2025");
        });
    }

    @Test
    void nullEndDateThrowsException() {
        // Arrange
        Description description = new Description("School Year 24/25");
        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(description, "13-09-2024", null);
        });
    }

    @Test
    void blankEndDateThrowsException() {
        // Arrange
        Description description = new Description("School Year 24/25");
        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(description, "13-09-2024", "");
        });
    }

    @Test
    void invalidDateFormatThrowsException() {
        // Arrange
        Description description = new Description("School Year 24/25");
        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(description, "2024-09-24", "2025-06-24"); // Incorrect format
        });
    }

    @Test
    void dateWrittenInWordsThrowsException() {
        // Arrange
        Description description = new Description("School Year 24/25");
        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(description, "24 de setembro de 2024", "24 de junho de 2025");
        });
    }

    @Test
    void shouldReturnTrueIfTwoSchoolYearsHaveSameStartDateAndEndDate() {
        // Arrange
        Description description = new Description("School Year 23/24");
        SchoolYear sy1 = new SchoolYear(description, "01-09-2023", "31-08-2024");
        SchoolYear sy2 = new SchoolYear(description, "01-09-2023", "31-08-2024");

        // Act
        boolean result = (sy1.isSameSchoolYear(sy2));

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoSchoolYearsDoNotHaveSameEndDate()  {
        // Arrange
        Description description = new Description("School Year 24/25");
        SchoolYear sy1 = new SchoolYear(description, "23-11-2024", "09-12-2025");
        SchoolYear sy2 = new SchoolYear(description, "23-11-2024", "09-10-2025");

        // Act
        boolean result = (sy1.isSameSchoolYear(sy2));

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTwoSchoolYearsDoNotHaveSameStartDate() {
        // Arrange
        Description description = new Description("School Year 24/25");
        SchoolYear sy1 = new SchoolYear(description, "23-11-2024", "09-12-2025");
        SchoolYear sy2 = new SchoolYear(description, "23-10-2024", "09-12-2025");

        // Act
        boolean result = (sy1.isSameSchoolYear(sy2));

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnEndDateFromSchoolYear() {
        // Arrange
        Description description = new Description("School Year 24/25");
        SchoolYear sy1 = new SchoolYear(description, "23-11-2024", "09-12-2025");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate lc = LocalDate.parse("09-12-2025", formatter);

        // Act
        LocalDate endDate = sy1.getEndDate();

        // Assert
        assertEquals(endDate, lc);
    }

    @Test
    void shouldReturnStartDateFromSchoolYear() {
        // Arrange
        Description description = new Description("School Year 24/25");
        SchoolYear sy1 = new SchoolYear(description, "23-11-2024", "09-12-2025");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate lc = LocalDate.parse("23-11-2024", formatter);

        // Act
        LocalDate startDate = sy1.getStartDate();

        // Assert
        assertEquals(startDate, lc);
    }

    //US17
    @Test
    void shouldReturnTrueForEqualSchoolYears() {
        // Arrange
        Description description = new Description("School Year 24/25");
        SchoolYear sy1 = new SchoolYear(  description, "14-10-2024", "30-06-2025");
        SchoolYear sy2 = new SchoolYear(  description, "14-10-2024", "30-06-2025");

        // Act & Assert
        assertTrue(sy1.equals(sy2), "Two identical school years should be equal.");
    }

    //US17
    @Test
    void shouldReturnFalseForDifferentDescriptions() {
        // Arrange
        Description description1 = new Description("School Year 23/24");
        Description description2 = new Description("School Year 24/25");
        SchoolYear sy1 = new SchoolYear(  description1, "14-10-2024", "30-06-2025");
        SchoolYear sy2 = new SchoolYear(  description2, "14-10-2025", "30-06-2026");

        // Act & Assert
        assertFalse(sy1.equals(sy2), "School years with different descriptions should not be equal.");
    }

    //US17
    @Test
    void shouldReturnFalseForDifferentStartDates() {
        // Arrange
        Description description = new Description("School Year 24/25");
        SchoolYear sy1 = new SchoolYear(  description, "14-10-2024", "30-06-2025");
        SchoolYear sy2 = new SchoolYear(  description, "14-11-2024", "30-06-2025");

        // Act & Assert
        assertFalse(sy1.equals(sy2), "School years with different start dates should not be equal.");
    }

    //US17
    @Test
    void shouldReturnFalseForDifferentEndDates() {
        // Arrange
        Description description = new Description("School Year 24/25");
        SchoolYear sy1 = new SchoolYear(  description, "14-10-2024", "30-06-2025");
        SchoolYear sy2 = new SchoolYear(  description, "14-10-2024", "30-07-2025");

        // Act & Assert
        assertFalse(sy1.equals(sy2), "School years with different end dates should not be equal.");
    }

    //US17
    @Test
    void shouldReturnFalseWhenComparedWithNull() {
        // Arrange
        Description description = new Description("School Year 24/25");
        SchoolYear sy1 = new SchoolYear(  description, "14-10-2024", "30-06-2025");

        // Act & Assert
        assertFalse(sy1.equals(null), "A school year should not be equal to null.");
    }

    //US17
    @Test
    void shouldReturnFalseWhenComparedWithDifferentClass() {
        // Arrange
        Description description = new Description("School Year 24/25");
        SchoolYear sy1 = new SchoolYear(  description, "14-10-2024", "30-06-2025");
        String notASchoolYear = "Not a school year";

        // Act & Assert
        assertFalse(sy1.equals(notASchoolYear), "A school year should not be equal to an object of a different class.");
    }

    //US17
    @Test
    void shouldReturnTrueWhenComparedWithItself() {
        // Arrange
        Description description = new Description("School Year 24/25");
        SchoolYear sy1 = new SchoolYear(  description, "14-10-2024", "30-06-2025");

        // Act & Assert
        assertTrue(sy1.equals(sy1), "A school year should be equal to itself.");
    }

    //US17
    @Test
    void shouldReturnFalseForCompletelyDifferentSchoolYears() {
        // Arrange
        Description description1 = new Description("School Year 24/25");
        Description description2 = new Description("School Year 23/24");
        SchoolYear sy1 = new SchoolYear(  description1, "14-10-2024", "30-06-2025");
        SchoolYear sy2 = new SchoolYear(  description2, "01-09-2023", "15-06-2024");

        // Act & Assert
        assertFalse(sy1.equals(sy2), "Completely different school years should not be equal.");
    }
}