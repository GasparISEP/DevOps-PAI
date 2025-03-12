package PAI.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;

class TeacherCareerProgressionTest {

    public static Stream<Arguments> provideValidAttributes() {
        return Stream.of(
                arguments("10-12-2024", 100),
                arguments("10-12-2024", 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidAttributes")
    void createsObjectWithValidAttributes(String date, int workingPercentage) throws Exception {
        // Arrange
        TeacherCategory tc1Double = mock(TeacherCategory.class);

        //act + assert
        TeacherCareerProgression tcp1 = new TeacherCareerProgression(date, tc1Double, workingPercentage);
    }

    @Test
    void nullTeacherCategoryDoesNotCreateObject() {
        // Arrange

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new TeacherCareerProgression("10-12-2024", null, 50));

        // Assert
        assertEquals("Teacher Category cannot be null", exception.getMessage());
    }

    public static Stream<Arguments> provideInvalidAttributes() {
        return Stream.of(
                arguments("10-12-2024", -1, "Working Percentage must be a value between 0 and 100."),
                arguments("10-12-2024", 101, "Working Percentage must be a value between 0 and 100."),
                arguments("", 50, "Date cannot be empty!"),
                arguments(" ", 50, "Date cannot be empty!"),
                arguments(null, 50, "Date cannot be empty!"),
                arguments("2024-12-10", 50, "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),
                arguments("10/12/2024", 50, "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),
                arguments("10 de dezembro de 2024", 50, "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),
                arguments("32-01-2024", 50, "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct.")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidAttributes")
    void invalidAttributesDoNotCreateObject(String date, int workingPercentage, String expectedMessage) throws Exception {
        //arrange
        TeacherCategory tc1Double = mock(TeacherCategory.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new TeacherCareerProgression(date, tc1Double, workingPercentage));

        //assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    public static Stream<Arguments> provideValidCategories() {
        return Stream.of(
                arguments("10-12-2024", 0),
                arguments("20-05-2022", 100)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidCategories")
    void getCategoryReturnsCorrectCategory(String date, int workingPercentage) throws Exception {
        //arrange
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        TeacherCareerProgression TCP = new TeacherCareerProgression(date, tcDouble, workingPercentage);

        //act
        TeacherCategory result = TCP.getCategory();

        //assert
        assertEquals(tcDouble, result);
    }

    public static Stream<Arguments> provideValidWorkingPercentages() {
        return Stream.of(
                arguments("02-02-2024", 0, 0),
                arguments("20-05-2022", 100, 100)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidWorkingPercentages")
    void getWorkingPercentageReturnsWorkingPercentage(String date, int workingPercentage, int expectedWorkingPercentage) throws Exception {

        //arrange
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        TeacherCareerProgression TCP = new TeacherCareerProgression(date, tcDouble, workingPercentage);

        //act
        int result = TCP.getWorkingPercentage();

        //assert
        assertEquals(expectedWorkingPercentage, result);
    }


    public static Stream<Arguments> provideValidDate () {
        return Stream.of(
                arguments("01-01-2010", "01-01-2010"),
                arguments("31-12-2010", "31-12-2010")
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidDate")
    void getDateReturnsDate (String date, String expectedDate) {
        //Arrange
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        TeacherCareerProgression tcp = new TeacherCareerProgression(date,tcDouble,100);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Act
        LocalDate result = tcp.getDate();

        // Assert
        assertEquals(LocalDate.parse(expectedDate, formatter), result);
    }

    public static Stream<Arguments> provideDates() {
        return Stream.of(
                arguments("16-04-2024", "17-04-2024", "Professor Adjunto", true),
                arguments("15-04-2024", "15-04-2024", "Professor Adjunto", false),
                arguments("15-04-2024", "14-04-2024", "Professor Adjunto", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDates")
    void shouldReturnTrueIfGivenDateIsAfterLastDate(String date1, String date2, String tc, boolean expectedResult) throws Exception {
        // Arrange
        TeacherCategory tc1Double = mock(TeacherCategory.class);

        TeacherCareerProgression TCP1 = new TeacherCareerProgression(date1, tc1Double, 50);
        TeacherCareerProgression TCP2 = new TeacherCareerProgression(date2, tc1Double, 60);

        // Act
        boolean result = TCP2.isDateAfter(TCP1);

        // Assert
        assertEquals(expectedResult, result);
    }
}