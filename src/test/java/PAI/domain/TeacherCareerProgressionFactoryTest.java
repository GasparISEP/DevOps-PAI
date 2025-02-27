package PAI.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

class TeacherCareerProgressionFactoryTest {

    @Test
    void shouldCreateTeacherCareerProgression () throws IllegalArgumentException {

        // Arrange
        TeacherCareerProgressionFactory factory = new TeacherCareerProgressionFactory();
        TeacherCategory tcDouble = mock(TeacherCategory.class);

        // Act
        TeacherCareerProgression TCP = factory.createTeacherCareerProgression("01-10-2022", tcDouble, 100);

        // Assert
        assertNotNull(TCP);
    }

    public static Stream<Arguments> provideInvalidDate() {
        return Stream.of(
                arguments(null, "Date cannot be empty!"),      // Null Date
                arguments("", "Date cannot be empty!"),        // Empty Date
                arguments(" ", "Date cannot be empty!"),       // Blank Date
                arguments("32-01-2022", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),            // Invalid Date
                arguments("01-13-2022", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct."),            // Invalid Date
                arguments("1 de Outubro de 2022", "Invalid date. Please check whether the day, month, year or date format (dd-MM-yyyy) are correct.")   // Invalid Date
        );
    }

    @ParameterizedTest
    @MethodSource ("provideInvalidDate")
    void shouldThrowExceptionIfDateProvidedIsInvalid (String date, String expectedException) {

        // Arrange
        TeacherCareerProgressionFactory factory = new TeacherCareerProgressionFactory();
        TeacherCategory tcDouble = mock(TeacherCategory.class);

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> factory.createTeacherCareerProgression(date, tcDouble, 100));
        assertEquals(expectedException, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCategoryIsNull () throws IllegalArgumentException {

        // Arrange
        TeacherCareerProgressionFactory factory = new TeacherCareerProgressionFactory();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> factory.createTeacherCareerProgression("01-10-2022", null, 100));
        assertEquals("Teacher Category cannot be null", exception.getMessage());
    }

    public static Stream<Arguments> provideInvalidWorkingPercentage () {
        return Stream.of(
                arguments(101, "Working Percentage must be a value between 0 and 100."),
                arguments(-1, "Working Percentage must be a value between 0 and 100.")
        );
    }

    @ParameterizedTest
    @MethodSource ("provideInvalidWorkingPercentage")
    void shouldThrowExceptionWhenWorkingPercentageIsInvalid (int workingPercentage, String expectedException) {

        // Arrange
        TeacherCareerProgressionFactory factory = new TeacherCareerProgressionFactory();
        TeacherCategory tcDouble = mock(TeacherCategory.class);

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> factory.createTeacherCareerProgression("01-10-2022", tcDouble, workingPercentage));
        assertEquals(expectedException, exception.getMessage());
    }
}