package PAI.factory;

import PAI.VOs.*;
import PAI.domain.*;
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

class ProgrammeEnrolmentFactoryImplTest {

    private StudentID _studentIDDouble;
    private AccessMethodID _accessMethodIDDouble;
    private ProgrammeID _programmeIDDouble;
    private Date _dateDouble;

    // Method to create doubles for tests with isolation
    private void createDoublesForTestsWithIsolation() {
        _studentIDDouble = mock(StudentID.class);
        _accessMethodIDDouble = mock(AccessMethodID.class);
        _programmeIDDouble = mock(ProgrammeID.class);
        _dateDouble = mock(Date.class);
    }


    @Test
    void shouldCreateProgrammeEnrolmentWithIsolation() throws IllegalArgumentException {
        //Arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();
        createDoublesForTestsWithIsolation();

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {

                })) {

        //Act
            ProgrammeEnrolment result = peFactory.createProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        //Assert
            //
            List<ProgrammeEnrolment> constructed = programmeEnrolmentDouble.constructed();
            ProgrammeEnrolment created = (constructed).get(0);
            assertEquals(created, result);
        }
    }

    @Test
    void nullStudentDoesNotCreateObjectAndThrowsExceptionWithIsolation() {
        //arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();
        createDoublesForTestsWithIsolation();

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Argument cannot be null"));
                })) {
            //Act
            try {
                peFactory.createProgrammeEnrolment(null, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
                //Assert
                assertTrue(e.getCause().getMessage().contains("Argument cannot be null"));
            }
        }
    }

    @Test
    void nullAccessMethodDoesNotCreateObjectAndThrowsExceptionWithIsolation() {
        //arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        createDoublesForTestsWithIsolation();

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Argument cannot be null"));
                })) {
            //Act
            try {
                peFactory.createProgrammeEnrolment(_studentIDDouble, null, _programmeIDDouble, _dateDouble);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
                //Assert
                assertTrue(e.getCause().getMessage().contains("Argument cannot be null"));
            }
        }
    }

    @Test
    void nullProgrammeDoesNotCreateObjectAndThrowsExceptionWithIsolation() {
        //arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();
        createDoublesForTestsWithIsolation();

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Argument cannot be null"));
                })) {
            //Act
            try {
                peFactory.createProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, null, _dateDouble);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
                //Assert
                assertTrue(e.getCause().getMessage().contains("Argument cannot be null"));
            }
        }
    }

    @Test
    void shouldReturnExceptionIfDateIsNullOrBlankWithIsolation() {
        //Arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();
        createDoublesForTestsWithIsolation();

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Date cannot be empty!"));
                })) {
            //Act
            try {
                peFactory.createProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
            //Assert
                assertTrue(e.getCause().getMessage().contains("Date cannot be empty!"));
            }
        }
    }

    public static Stream<Arguments> provideInvalidDateForTestWithIsolation() {
        return Stream.of(
                arguments("2024-12-10"),
                arguments("10/12/2024"),
                arguments("10 de dezembro de 2024"),
                arguments("32-01-2024"),
                arguments("30-100-2024"),
                arguments("340-100-2024")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDateForTestWithIsolation")
    void invalidDateDoesNotCreateObjectAndThrowsExceptionWithIsolation(String date) {
        //arrange
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();
        createDoublesForTestsWithIsolation();

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                    throw new RuntimeException(new InstantiationException("Invalid date format. Please use dd-MM-yyyy."));
                })) {
            //Act
            try {
                peFactory.createProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);
                fail("Expected exception not thrown");
            }
            catch (Exception e)
            {
                //Assert
                assertTrue(e.getCause().getMessage().contains("Invalid date format. Please use dd-MM-yyyy."));
            }
        }
    }
}