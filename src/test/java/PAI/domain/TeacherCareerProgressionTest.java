package PAI.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

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
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");

        //act + assert
        TeacherCareerProgression tcp1 = new TeacherCareerProgression(date, tc1, workingPercentage);
    }

    public static Stream<Arguments> provideInvalidAttributes() {
        return Stream.of(
                arguments("10-12-2024", -1, "Working Percentage must be a value between 0 and 100."),
                arguments("10-12-2024", 101, "Working Percentage must be a value between 0 and 100."),
                arguments("", 50, "Date cannot be empty!"),
                arguments(" ", 50, "Date cannot be empty!"),
                arguments(null, 50, "Date cannot be empty!"),
                arguments("2024-12-10", 50, "Invalid date format. Use the following format: dd-MM-yyyy."),
                arguments("10/12/2024", 50, "Invalid date format. Use the following format: dd-MM-yyyy."),
                arguments("10 de dezembro de 2024", 50, "Invalid date format. Use the following format: dd-MM-yyyy.")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidAttributes")
    void invalidAttributesDoNotCreateObject(String date, int workingPercentage, String expectedMessage) throws Exception {
        //arrange
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new TeacherCareerProgression(date, tc1, workingPercentage));

        //assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    public static Stream<Arguments> provideValidCategories() {
        return Stream.of(
                arguments("10-12-2024", "Professor Adjunto", 0),
                arguments("20-05-2022", "Professor Catedrático", 100)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidCategories")
    void getCategoryReturnsCorrectCategory(String date, String categoryName, int workingPercentage) throws Exception {
        //arrange
        TeacherCategory tc = new TeacherCategory(categoryName);
        TeacherCareerProgression TCP = new TeacherCareerProgression(date, tc, workingPercentage);

        //act
        TeacherCategory result = TCP.getCategory();

        //assert
        assertEquals(tc, result);
    }

    public static Stream<Arguments> provideValidDates() {
        return Stream.of(
                arguments("12-12-2024", 0, "12-12-2024"),
                arguments("20-05-2022", 100, "20-05-2022")
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidDates")
    void getDateReturnsCorrectDate(String inputDate, int workingPercentage, String expectedDate) throws Exception {
        //arrange
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate expectedLocalDate = LocalDate.parse(expectedDate, formatter);
        TeacherCategory tc = new TeacherCategory("Professor Adjunto");
        TeacherCareerProgression TCP = new TeacherCareerProgression(inputDate, tc, workingPercentage);

        //act
        LocalDate result = TCP.getDate();

        //assert
        assertEquals(expectedLocalDate, result);
    }

    public static Stream<Arguments> provideValidWorkingPercentages() {
        return Stream.of(
                arguments("02-02-2024", 0, "Assitente", 0),
                arguments("20-05-2022", 100, "Adjunto", 100)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidWorkingPercentages")
    void getWorkingPercentageReturnsWorkingPercentage(String date, int workingPercentage, String categoryName, int expectedWorkingPercentage) throws Exception {

        //arrange
        TeacherCategory tc = new TeacherCategory(categoryName);
        TeacherCareerProgression TCP = new TeacherCareerProgression(date, tc, workingPercentage);

        //act
        int result = TCP.getWorkingPercentage();

        //assert
        assertEquals(expectedWorkingPercentage, result);
    }

}
