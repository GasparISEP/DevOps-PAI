package PAI.factory;

import PAI.domain.TeacherCareerProgression;
import PAI.domain.TeacherCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedConstruction;

import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.*;

class TeacherCareerProgressionFactoryImplTest {

    @Test
    void shouldCreateTeacherCareerProgression() {
        // Arrange
        ITeacherCareerProgressionFactory tcpFactory = new TeacherCareerProgressionFactoryImpl();

        String date = "13-11-2002";
        TeacherCategory tcDouble = mock(TeacherCategory.class);
        int workingPercentage = 100;


        try (
                MockedConstruction <TeacherCareerProgression> tcpConstructorMock = mockConstruction(TeacherCareerProgression.class)){

            // Act
            TeacherCareerProgression careerProgression = tcpFactory.createTeacherCareerProgression(date, tcDouble, workingPercentage);

            // Assert
            List<TeacherCareerProgression> constructed = tcpConstructorMock.constructed();  // Puts in this list all the objects that were constructed
            TeacherCareerProgression created = (constructed).get(0);    // Retrieves the first position from that list
            assertEquals(created, careerProgression);
        }
    }

    public static Stream<Arguments> provideInvalidDate() {
        return Stream.of(
                arguments("13/11/2002"),
                arguments("13-11-02"),
                arguments(" "),
                arguments(""),
                arguments("13 do 11 de 2002"),
                arguments("131-122-2002")
        );
    }

    @ParameterizedTest
    @MethodSource ("provideInvalidDate")
    void invalidDateDoesNotCreateTeacherCareerProgression (String date) {
        // Arrange
        ITeacherCareerProgressionFactory tcpFactory = new TeacherCareerProgressionFactoryImpl();

        TeacherCategory tcDouble = mock(TeacherCategory.class);
        int workingPercentage = 100;

        try (MockedConstruction<TeacherCareerProgression> tcpConstructionMock = mockConstruction(TeacherCareerProgression.class, (mock, context) -> {
            throw new RuntimeException((new InstantiationException("Date must be valid")));
        })) {
            try {  // Act
                tcpFactory.createTeacherCareerProgression(date, tcDouble, workingPercentage);
                fail("Expected exception not thrown");
            }     // Assert
            catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Date must be valid"));
            }
        }
    }

    @Test
    void nullTeacherCategoryDoesNotCreateTeacherCareerProgression () {
        // Arrange
        ITeacherCareerProgressionFactory tcpFactory = new TeacherCareerProgressionFactoryImpl();

        String date = "13-11-2002";
        int workingPercentage = 100;

        try (
                MockedConstruction <TeacherCareerProgression> tcpConstructorMock = mockConstruction(TeacherCareerProgression.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Teacher Category cannot be null."));
                })
            ) {
            //Act
            try {
                tcpFactory.createTeacherCareerProgression(date, null, workingPercentage);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
                //Assert
                assertTrue(e.getCause().getMessage().contains("Teacher Category cannot be null."));
            }
        }
    }

    public static Stream<Arguments> provideInvalidWorkingPercentage () {
        return Stream.of(
                arguments(101),
                arguments(-1)
        );
    }

    @ParameterizedTest
    @MethodSource ("provideInvalidWorkingPercentage")
    void shouldThrowExceptionWhenWorkingPercentageIsInvalid (int workingPercentage) {

        // Arrange
        ITeacherCareerProgressionFactory tcpFactory = new TeacherCareerProgressionFactoryImpl();

        String date = "01-10-2022";
        TeacherCategory tcDouble = mock(TeacherCategory.class);

        try (
                MockedConstruction <TeacherCareerProgression> tcpConstructorMock = mockConstruction(TeacherCareerProgression.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Working Percentage must be between 0 and 100"));
                })
        ){
            try {   // Act
                tcpFactory.createTeacherCareerProgression(date, tcDouble, workingPercentage);
                fail("Expected exception not thrown");
            }       // Assert
            catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Working Percentage must be between 0 and 100"));
            }
        }
    }
}