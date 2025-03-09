package PAI.factory;

import PAI.domain.TeacherCareerProgression;
import PAI.domain.TeacherCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

class TeacherCareerProgressionFactoryTest {

    @Test
    void shouldCreateTeacherCareerProgression () throws IllegalArgumentException {

        // Arrange
        String date = "01-10-2022";
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        int workingPercentage = 100;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        TeacherCareerProgressionFactory TCPfactory = new TeacherCareerProgressionFactory();

        // Mock the construction of TeacherCareerProgression objects
        try (MockedConstruction<TeacherCareerProgression> mockedConstruction = Mockito.mockConstruction(TeacherCareerProgression.class, (mock, context) -> {
            // Extracting the arguments
            String strActualDate = (String) context.arguments().get(0);
            TeacherCategory actualTC = (TeacherCategory) context.arguments().get(1);
            int actualWorkingPercentage = (int) context.arguments().get(2);
            // Configuring the mock's returns
            when(mock.getDate()).thenReturn(LocalDate.parse(strActualDate, formatter));
            when(mock.getCategory()).thenReturn(actualTC);
            when(mock.getWorkingPercentage()).thenReturn(actualWorkingPercentage);
        })) {

            // Act
            TeacherCareerProgression tcpObject = TCPfactory.createTeacherCareerProgression(date, tcDouble, workingPercentage);

        // Assert
            List<TeacherCareerProgression> tcpList = mockedConstruction.constructed();
            assertEquals(1, tcpList.size());

            assertEquals(LocalDate.parse(date, formatter), tcpList.get(0).getDate());
            assertEquals(tcDouble, tcpList.get(0).getCategory());
            assertEquals(workingPercentage, tcpList.get(0).getWorkingPercentage());
            //assertSame(tcpList.get(0), tcpObject);
        }
    }

    public static Stream<Arguments> provideInvalidDate() {
        return Stream.of(
                arguments(null, "Date cannot be empty!"),      // Null Date
                arguments("", "Date cannot be empty!"),        // Empty Date
                arguments(" ", "Date cannot be empty!")       // Blank Date
        );
    }

    @ParameterizedTest
    @MethodSource ("provideInvalidDate")
    void shouldThrowExceptionIfDateProvidedIsInvalid (String date, String expectedException) {

        // Arrange
        TeacherCareerProgressionFactory factory = new TeacherCareerProgressionFactory();
        TeacherCategory tcDouble = mock(TeacherCategory.class);

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> factory.createTeacherCareerProgression(date, tcDouble, 100));
        assertEquals(expectedException, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCategoryIsNull () throws IllegalArgumentException {

        // Arrange
        TeacherCareerProgressionFactory factory = new TeacherCareerProgressionFactory();

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> factory.createTeacherCareerProgression("10-12-2022", null, 100));
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
        String date = "01-10-2022";
        TeacherCategory tcDouble = mock(TeacherCategory.class);

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> factory.createTeacherCareerProgression(date, tcDouble, workingPercentage));
        assertEquals(expectedException, exception.getMessage());
    }
}