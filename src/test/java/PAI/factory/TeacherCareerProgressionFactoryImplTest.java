package PAI.factory;

import PAI.VOs.*;
import PAI.domain.TeacherCareerProgression;
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

        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);

        try (
                MockedConstruction <TeacherCareerProgression> tcpConstructorMock = mockConstruction(TeacherCareerProgression.class)){

            // Act
            TeacherCareerProgression careerProgression = tcpFactory.createTeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, teacherIDDouble);

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

        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);

        try (MockedConstruction<TeacherCareerProgression> tcpConstructionMock = mockConstruction(TeacherCareerProgression.class, (mock, context) -> {
            throw new RuntimeException((new InstantiationException("Date must be valid")));
        })) {
            try {  // Act
                tcpFactory.createTeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, teacherIDDouble);
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

        Date dateDouble = mock(Date.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);

        try (
                MockedConstruction <TeacherCareerProgression> tcpConstructorMock = mockConstruction(TeacherCareerProgression.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Teacher Category cannot be null."));
                })
            ) {
            //Act
            try {
                tcpFactory.createTeacherCareerProgression(dateDouble, null, wpDouble, teacherIDDouble);
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

        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);

        try (
                MockedConstruction <TeacherCareerProgression> tcpConstructorMock = mockConstruction(TeacherCareerProgression.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Working Percentage must be between 0 and 100"));
                })
        ){
            try {   // Act
                tcpFactory.createTeacherCareerProgression(dateDouble, tcIDDouble, wpDouble, teacherIDDouble);
                fail("Expected exception not thrown");
            }       // Assert
            catch (Exception e) {
                assertTrue(e.getCause().getMessage().contains("Working Percentage must be between 0 and 100"));
            }
        }
    }

    @Test
    void shouldReturnTeacherCareerProgressionWhenCreateTeacherCareerProgressionFromDataModelIsCalled(){
        // Arrange
        TeacherCareerProgressionFactoryImpl factory = new TeacherCareerProgressionFactoryImpl();
        TeacherCareerProgressionID idDouble = mock(TeacherCareerProgressionID.class);
        Date dateDouble = mock(Date.class);
        TeacherCategoryID tcIDDouble = mock(TeacherCategoryID.class);
        WorkingPercentage wpDouble = mock(WorkingPercentage.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);

        try (MockedConstruction<TeacherCareerProgression> constructorDouble = mockConstruction(TeacherCareerProgression.class,
                (mock, context) -> {

            when(mock.getID()).thenReturn(idDouble);
        })) {

        // Act
            TeacherCareerProgression result = factory.createTeacherCareerProgressionFromDataModel(
                                            idDouble, dateDouble, tcIDDouble, wpDouble, teacherIDDouble);

            // Assert
            assertEquals(1, constructorDouble.constructed().size());
            assertSame(constructorDouble.constructed().get(0), result);
            assertEquals(idDouble, result.getID());
        }
    }

    static Stream<Arguments> testInvalidInputs() {
        return Stream.of(
                Arguments.of(null, mock(Date.class), mock(TeacherCategoryID.class), mock(WorkingPercentage.class), mock(TeacherID.class), "Teacher Career Progression Id cannot be null!"),
                Arguments.of(mock(TeacherCareerProgressionID.class), null, mock(TeacherCategoryID.class), mock(WorkingPercentage.class), mock(TeacherID.class), "Date cannot be null!"),
                Arguments.of(mock(TeacherCareerProgressionID.class), mock(Date.class), null, mock(WorkingPercentage.class), mock(TeacherID.class), "Teacher Category cannot be null!"),
                Arguments.of(mock(TeacherCareerProgressionID.class), mock(Date.class), mock(TeacherCategoryID.class), null, mock(TeacherID.class), "Working Percentage cannot be null!"),
                Arguments.of(mock(TeacherCareerProgressionID.class), mock(Date.class), mock(TeacherCategoryID.class), mock(WorkingPercentage.class), null, "Teacher ID cannot be null!")
        );
    }
    @ParameterizedTest
    @MethodSource("testInvalidInputs")
    void shouldPropagateExceptionIfInputsAreInvalidWhenCreateTeacherCareerProgressionFromDataModelIsCalled(
            TeacherCareerProgressionID idDouble, Date dateDouble, TeacherCategoryID tcIDDouble,
            WorkingPercentage wpDouble, TeacherID teacherIDDouble, String expectedException){

        // Arrange
        TeacherCareerProgressionFactoryImpl factory = new TeacherCareerProgressionFactoryImpl();

        try (MockedConstruction<TeacherCareerProgression> constructorDouble = mockConstruction(TeacherCareerProgression.class,
                (invocation) -> {

                    throw new IllegalArgumentException(expectedException);
                })) {

            // Act & Assert
            Throwable result = assertThrows(IllegalArgumentException.class, () -> factory.createTeacherCareerProgressionFromDataModel(
                    idDouble, dateDouble, tcIDDouble, wpDouble, teacherIDDouble));

            assertEquals(result.getMessage(), expectedException);
        }
    }
}