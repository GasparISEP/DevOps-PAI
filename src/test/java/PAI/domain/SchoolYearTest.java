package PAI.domain;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SchoolYearTest {

    // -------------------------------------------------------------
    // ------------ TESTS FOR VALID PARAMETERS -------------------
    // -------------------------------------------------------------

    @Test
    void validArgumentsCreateSchoolYear() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 23/24");
        Date startDate = new Date ("01-09-2023");
        Date endDate = new Date ("31-08-2024");
        // Act
        SchoolYear sy1 = new SchoolYear(schoolYearID,description,startDate,endDate);
        // Assert
        assertNotNull(sy1);
    }


    @Test
    void sameYearStartAndEndDate() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("01-01-2024");
        Date endDate = new Date ("31-10-2024");
        // Act
        SchoolYear sy1 = new SchoolYear(schoolYearID,description, startDate,endDate);

        // Assert
        assertNotNull(sy1);
    }

    @Test
    void oneDayIntervalIsValid() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("01-09-2024");
        Date endDate = new Date ("02-09-2024");
        // Act
        SchoolYear sy1 = new SchoolYear(schoolYearID, description, startDate,endDate);

        // Assert
        assertNotNull(sy1);
    }

    // -------------------------------------------------------------
    // ------------ TESTS FOR INVALID PARAMETERS -------------------
    // -------------------------------------------------------------

    @Test
    void endDateBeforeStartDateThrowsException() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("01-09-2024");
        Date endDate = new Date ("31-08-2023");
        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(schoolYearID,description, startDate, endDate); // endDate is before startDate
        });
    }

    @Test
    void endDateSameAsStartDateThrowsException() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("24-09-2024");
        Date endDate = new Date ("24-09-2024");
        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(schoolYearID, description, startDate, endDate); // endDate is the same as startDate
        });
    }

    @Test
    void nullDescriptionThrowsException() {
        //Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Date startDate = new Date ("24-09-2024");
        Date endDate = new Date ("24-09-2025");

        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(schoolYearID, new Description(null), startDate, endDate);
        });
    }

    @Test
    void blankDescriptionThrowsException() {
        //Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Date startDate = new Date ("24-09-2024");
        Date endDate = new Date ("24-09-2025");

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new SchoolYear(schoolYearID, new Description(" "), startDate, endDate);
        });
    }

    @Test
    void nullStartDateThrowsException() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date endDate = new Date ("24-09-2025");
        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(schoolYearID, description, null, endDate);
        });
    }

    @Test
    void blankStartDateThrowsException() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            Date startDate = new Date("");
            Date endDate = new Date("24-09-2025");
            new SchoolYear(schoolYearID,description, startDate, endDate);
        });
    }


    @Test
    void nullEndDateThrowsException() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("24-09-2024");
        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(schoolYearID, description, startDate, null);
        });
    }

    @Test
    void blankEndDateThrowsException() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            Date startDate = new Date("24-09-2023"); // Invalid input
            Date endDate = new Date("");
            new SchoolYear(schoolYearID, description, startDate, endDate);
        });
    }


    @Test
    void invalidDateFormatThrowsExceptionInSchoolYear() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            Date startDate = new Date("2024-09-24");
            Date endDate = new Date("2025-06-24");
            new SchoolYear(schoolYearID, description, startDate, endDate);
        });
    }


    @Test
    void dateWrittenInWordsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Date("24 de setembro de 2024"));
    }


    @Test
    void shouldReturnTrueIfTwoSchoolYearsHaveSameStartDateAndEndDate() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 23/24");
        Date startDate1 = new Date ("01-09-2023");
        Date endDate1 = new Date ("31-08-2024");
        Date startDate2 = new Date ("01-09-2023");
        Date endDate2 = new Date ("31-08-2024");
        SchoolYear sy1 = new SchoolYear(schoolYearID, description,startDate1,endDate1);
        SchoolYear sy2 = new SchoolYear(schoolYearID, description,startDate2, endDate2);

        // Act
        boolean result = (sy1.isSameSchoolYear(sy2));

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoSchoolYearsDoNotHaveSameEndDate()  {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate1 = new Date ("23-11-2024");
        Date endDate1 = new Date ("09-12-2025");
        Date startDate2 = new Date ("23-11-2024");
        Date endDate2 = new Date ("09-10-2025");
        SchoolYear sy1 = new SchoolYear(schoolYearID, description, startDate1, endDate1);
        SchoolYear sy2 = new SchoolYear(schoolYearID, description, startDate2, endDate2);

        // Act
        boolean result = (sy1.isSameSchoolYear(sy2));

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTwoSchoolYearsDoNotHaveSameStartDate() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate1 = new Date ("23-11-2024");
        Date endDate1 = new Date ("09-12-2025");
        Date startDate2 = new Date ("23-10-2024");
        Date endDate2 = new Date ("09-12-2025");
        SchoolYear sy1 = new SchoolYear(schoolYearID, description, startDate1,endDate1);
        SchoolYear sy2 = new SchoolYear(schoolYearID, description, startDate2, endDate2);

        // Act
        boolean result = (sy1.isSameSchoolYear(sy2));

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnEndDateFromSchoolYear() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("23-11-2024");
        Date endDate = new Date ("09-12-2025");
        SchoolYear sy1 = new SchoolYear(schoolYearID, description,startDate,endDate);

        // Act
        Date actualEndDate = sy1.getEndDate();

        // Assert
        assertEquals(endDate, actualEndDate);
    }

    @Test
    void shouldReturnStartDateFromSchoolYear() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("23-11-2024");
        Date endDate = new Date ("09-12-2025");
        SchoolYear sy1 = new SchoolYear(schoolYearID, description, startDate,endDate);

        // Act
        Date actualStartDate = sy1.getStartDate();

        // Assert
        assertEquals(startDate, actualStartDate);
    }

    //US17
    @Test
    void shouldReturnTrueForEqualSchoolYears() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate1 = new Date ("14-10-2024");
        Date endDate1 = new Date ("30-06-2025");
        Date startDate2 = new Date ("14-10-2024");
        Date endDate2 = new Date ("30-06-2025");
        SchoolYear sy1 = new SchoolYear(schoolYearID, description, startDate1,endDate1);
        SchoolYear sy2 = new SchoolYear(schoolYearID, description, startDate2,endDate2);

        // Act & Assert
        assertTrue(sy1.equals(sy2), "Two identical school years should be equal.");
    }

    //US17
    @Test
    void shouldReturnFalseForDifferentDescriptions() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description1 = new Description("School Year 23/24");
        Description description2 = new Description("School Year 24/25");
        Date startDate1 = new Date ("14-10-2024");
        Date endDate1 = new Date ("30-06-2025");
        Date startDate2 = new Date ("14-10-2025");
        Date endDate2 = new Date ("30-06-2026");
        SchoolYear sy1 = new SchoolYear(schoolYearID, description1,startDate1,endDate1);
        SchoolYear sy2 = new SchoolYear(schoolYearID, description2, startDate2, endDate2);

        // Act & Assert
        assertFalse(sy1.equals(sy2), "School years with different descriptions should not be equal.");
    }

    //US17
    @Test
    void shouldReturnFalseForDifferentStartDates() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate1 = new Date ("14-10-2024");
        Date endDate1 = new Date ("30-06-2025");
        Date startDate2 = new Date ("14-11-2024");
        Date endDate2 = new Date ("30-06-2025");
        SchoolYear sy1 = new SchoolYear(schoolYearID, description, startDate1, endDate1);
        SchoolYear sy2 = new SchoolYear(schoolYearID, description, startDate2, endDate2);

        // Act & Assert
        assertFalse(sy1.equals(sy2), "School years with different start dates should not be equal.");
    }

    //US17
    @Test
    void shouldReturnFalseForDifferentEndDates() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate1 = new Date ("14-10-2024");
        Date endDate1 = new Date ("30-06-2025");
        Date startDate2 = new Date ("14-10-2024");
        Date endDate2 = new Date ("30-07-2025");
        SchoolYear sy1 = new SchoolYear(schoolYearID, description, startDate1, endDate1);
        SchoolYear sy2 = new SchoolYear(schoolYearID, description, startDate2, endDate2);

        // Act & Assert
        assertFalse(sy1.equals(sy2), "School years with different end dates should not be equal.");
    }

    //US17
    @Test
    void shouldReturnFalseWhenComparedWithNull() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("14-10-2024");
        Date endDate = new Date ("30-06-2025");
        SchoolYear sy1 = new SchoolYear(schoolYearID, description,startDate,endDate);

        // Act & Assert
        assertFalse(sy1.equals(null), "A school year should not be equal to null.");
    }

    //US17
    @Test
    void shouldReturnFalseWhenComparedWithDifferentClass() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("14-10-2024");
        Date endDate = new Date ("30-06-2025");
        SchoolYear sy1 = new SchoolYear(schoolYearID, description,startDate, endDate);
        String notASchoolYear = "Not a school year";

        // Act & Assert
        assertFalse(sy1.equals(notASchoolYear), "A school year should not be equal to an object of a different class.");
    }

    //US17
    @Test
    void shouldReturnTrueWhenComparedWithItself() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("14-10-2024");
        Date endDate = new Date ("30-06-2025");
        SchoolYear sy1 = new SchoolYear(schoolYearID, description,startDate, endDate);

        // Act & Assert
        assertTrue(sy1.equals(sy1), "A school year should be equal to itself.");
    }

    //US17
    @Test
    void shouldReturnFalseForCompletelyDifferentSchoolYears() {
        // Arrange
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description1 = new Description("School Year 24/25");
        Description description2 = new Description("School Year 23/24");
        Date startDate1 = new Date ("14-10-2024");
        Date endDate1 = new Date ("30-06-2025");
        Date startDate2 = new Date ("01-09-2023");
        Date endDate2 = new Date ("15-06-2024");
        SchoolYear sy1 = new SchoolYear(schoolYearID, description1,startDate1, endDate1);
        SchoolYear sy2 = new SchoolYear(schoolYearID, description2, startDate2, endDate2);

        // Act & Assert
        assertFalse(sy1.equals(sy2), "Completely different school years should not be equal.");
    }
}