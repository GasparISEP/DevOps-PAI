package PAI.domain;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SchoolYearTest {


    @Test
    void validArgumentsCreateSchoolYear() {
        // Arrange
        Description description = new Description("School Year 23/24");
        Date startDate = new Date ("01-09-2023");
        Date endDate = new Date ("31-08-2024");
        // Act
        SchoolYear sy1 = new SchoolYear(description,startDate,endDate);
        // Assert
        assertNotNull(sy1);
    }

    @Test
    void sameYearStartAndEndDate() {
        // Arrange
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("01-01-2024");
        Date endDate = new Date ("31-10-2024");
        // Act
        SchoolYear sy1 = new SchoolYear(description, startDate,endDate);

        // Assert
        assertNotNull(sy1);
    }

    @Test
    void oneDayIntervalIsValid() {
        // Arrange
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("01-09-2024");
        Date endDate = new Date ("02-09-2024");
        // Act
        SchoolYear sy1 = new SchoolYear(description, startDate,endDate);

        // Assert
        assertNotNull(sy1);
    }

    @Test
    void endDateBeforeStartDateThrowsException() {
        // Arrange
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("01-09-2024");
        Date endDate = new Date ("31-08-2023");
        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(description, startDate, endDate);
        });
    }

    @Test
    void endDateSameAsStartDateThrowsException() {
        // Arrange
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("24-09-2024");
        Date endDate = new Date ("24-09-2024");
        // Act + Assert
        assertThrows(Exception.class, () -> {
            new SchoolYear(description, startDate, endDate);
        });
    }

    @Test
    void shouldReturnTrueIfTwoSchoolYearsHaveSameStartDateAndEndDate() {
        // Arrange
        Description description = new Description("School Year 23/24");
        Date startDate1 = new Date ("01-09-2023");
        Date endDate1 = new Date ("31-08-2024");
        Date startDate2 = new Date ("01-09-2023");
        Date endDate2 = new Date ("31-08-2024");
        SchoolYear sy1 = new SchoolYear(description,startDate1,endDate1);
        SchoolYear sy2 = new SchoolYear(description,startDate2, endDate2);

        // Act
        boolean result = (sy1.isSameSchoolYear(sy2));

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoSchoolYearsDoNotHaveSameEndDate()  {
        // Arrange
        Description description = new Description("School Year 24/25");
        Date startDate1 = new Date ("23-11-2024");
        Date endDate1 = new Date ("09-12-2025");
        Date startDate2 = new Date ("23-11-2024");
        Date endDate2 = new Date ("09-10-2025");
        SchoolYear sy1 = new SchoolYear(description, startDate1, endDate1);
        SchoolYear sy2 = new SchoolYear(description, startDate2, endDate2);

        // Act
        boolean result = (sy1.isSameSchoolYear(sy2));

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTwoSchoolYearsDoNotHaveSameStartDate() {
        // Arrange
        Description description = new Description("School Year 24/25");
        Date startDate1 = new Date ("23-11-2024");
        Date endDate1 = new Date ("09-12-2025");
        Date startDate2 = new Date ("23-10-2024");
        Date endDate2 = new Date ("09-12-2025");
        SchoolYear sy1 = new SchoolYear(description, startDate1,endDate1);
        SchoolYear sy2 = new SchoolYear(description, startDate2, endDate2);

        // Act
        boolean result = (sy1.isSameSchoolYear(sy2));

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnEndDateFromSchoolYear() {
        // Arrange
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("23-11-2024");
        Date endDate = new Date ("09-12-2025");
        SchoolYear sy1 = new SchoolYear(description,startDate,endDate);

        // Act
        Date actualEndDate = sy1.getEndDate();

        // Assert
        assertEquals(endDate, actualEndDate);
    }

    @Test
    void shouldReturnStartDateFromSchoolYear() {
        // Arrange
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("23-11-2024");
        Date endDate = new Date ("09-12-2025");
        SchoolYear sy1 = new SchoolYear(description, startDate,endDate);

        // Act
        Date actualStartDate = sy1.getStartDate();

        // Assert
        assertEquals(startDate, actualStartDate);
    }

    //US17
    @Test
    void shouldReturnTrueForEqualSchoolYears() {
        // Arrange
        Date startDate = new Date("14-10-2024");
        Date endDate = new Date("30-06-2025");

        SchoolYear sy1 = new SchoolYear(mock(Description.class), startDate, endDate);
        SchoolYear sy2 = new SchoolYear(mock(Description.class), startDate, endDate);

        // Act & Assert
        assertTrue(sy1.sameAs(sy2), "Two school years with the same start and end dates should be equal.");
    }

    //US17
    @Test
    void shouldReturnFalseForDifferentDescriptions() {
        // Arrange
        Description description1 = new Description("School Year 23/24");
        Description description2 = new Description("School Year 24/25");
        Date startDate1 = new Date ("14-10-2024");
        Date endDate1 = new Date ("30-06-2025");
        Date startDate2 = new Date ("14-10-2025");
        Date endDate2 = new Date ("30-06-2026");
        SchoolYear sy1 = new SchoolYear(description1,startDate1,endDate1);
        SchoolYear sy2 = new SchoolYear(description2, startDate2, endDate2);

        // Act & Assert
        assertFalse(sy1.sameAs(sy2), "School years with different descriptions should not be equal.");
    }

    //US17
    @Test
    void shouldReturnFalseForDifferentStartDates() {
        // Arrange
        Description description = new Description("School Year 24/25");
        Date startDate1 = new Date ("14-10-2024");
        Date endDate1 = new Date ("30-06-2025");
        Date startDate2 = new Date ("14-11-2024");
        Date endDate2 = new Date ("30-06-2025");
        SchoolYear sy1 = new SchoolYear(description, startDate1, endDate1);
        SchoolYear sy2 = new SchoolYear(description, startDate2, endDate2);

        // Act & Assert
        assertFalse(sy1.sameAs(sy2), "School years with different start dates should not be equal.");
    }

    //US17
    @Test
    void shouldReturnFalseForDifferentEndDates() {
        // Arrange
        Description description = new Description("School Year 24/25");
        Date startDate1 = new Date ("14-10-2024");
        Date endDate1 = new Date ("30-06-2025");
        Date startDate2 = new Date ("14-10-2024");
        Date endDate2 = new Date ("30-07-2025");
        SchoolYear sy1 = new SchoolYear(description, startDate1, endDate1);
        SchoolYear sy2 = new SchoolYear(description, startDate2, endDate2);

        // Act & Assert
        assertFalse(sy1.sameAs(sy2), "School years with different end dates should not be equal.");
    }

    //US17
    @Test
    void shouldReturnFalseWhenComparedWithNull() {
        // Arrange
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("14-10-2024");
        Date endDate = new Date ("30-06-2025");
        SchoolYear sy1 = new SchoolYear(description,startDate,endDate);

        // Act & Assert
        assertFalse(sy1.sameAs(null), "A school year should not be equal to null.");
    }

    //US17
    @Test
    void shouldReturnFalseWhenComparedWithDifferentClass() {
        // Arrange
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("14-10-2024");
        Date endDate = new Date ("30-06-2025");
        SchoolYear sy1 = new SchoolYear(description,startDate, endDate);
        String notASchoolYear = "Not a school year";

        // Act & Assert
        assertFalse(sy1.sameAs(notASchoolYear), "A school year should not be equal to an object of a different class.");
    }

    //US17
    @Test
    void shouldReturnTrueWhenComparedWithItself() {
        // Arrange
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("14-10-2024");
        Date endDate = new Date ("30-06-2025");
        SchoolYear sy1 = new SchoolYear(description,startDate, endDate);

        // Act & Assert
        assertTrue(sy1.sameAs(sy1), "A school year should be equal to itself.");
    }

    //US17
    @Test
    void shouldReturnFalseForCompletelyDifferentSchoolYears() {
        // Arrange
        Description description1 = new Description("School Year 24/25");
        Description description2 = new Description("School Year 23/24");
        Date startDate1 = new Date ("14-10-2024");
        Date endDate1 = new Date ("30-06-2025");
        Date startDate2 = new Date ("01-09-2023");
        Date endDate2 = new Date ("15-06-2024");
        SchoolYear sy1 = new SchoolYear(description1,startDate1, endDate1);
        SchoolYear sy2 = new SchoolYear(description2, startDate2, endDate2);

        // Act & Assert
        assertFalse(sy1.sameAs(sy2), "Completely different school years should not be equal.");
    }
    @Test
    void shouldReturnSchoolYearID() {
        // Arrange
        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);

        SchoolYear schoolYear = new SchoolYear(description, startDate, endDate);

        // Act
        SchoolYearID result = schoolYear.identity();

        // Assert
        assertNotNull(result);
    }
    @Test
    void shouldMatchAssignedSchoolYearID() {
        // Arrange
        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);

        SchoolYear schoolYear = new SchoolYear(description, startDate, endDate);
        SchoolYearID expectedID = schoolYear.identity();

        // Act
        SchoolYearID result = schoolYear.identity();

        // Assert
        assertEquals(expectedID, result);
    }

    // Equals Test
    @Test
    void shouldReturnTrueIfSchoolYearIsComparedToItSelf() {
        // Arrange
        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);
        SchoolYear schoolYear = new SchoolYear(description, startDate, endDate);

        // Act
        boolean result = schoolYear.equals(schoolYear);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfSchoolYearIsComparedToNull() {
        // Arrange
        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);
        SchoolYear schoolYear = new SchoolYear(description, startDate, endDate);

        // Act
        boolean result = schoolYear.equals(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfSchoolYearIsComparedToADifferentInstance() {
        // Arrange
        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);
        SchoolYear schoolYear = new SchoolYear(description, startDate, endDate);

        // Act
        boolean result = schoolYear.equals(description);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfSchoolYearIsComparedToADifferentSchoolYear() {
        // Arrange
        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date startDate1 = mock(Date.class);
        Date endDate = mock(Date.class);
        SchoolYear schoolYear = new SchoolYear(description, startDate, endDate);
        SchoolYear schoolYear2 = new SchoolYear(description, startDate1, endDate);


        // Act
        boolean result = schoolYear.equals(schoolYear2);

        // Assert
        assertFalse(result);
    }
}