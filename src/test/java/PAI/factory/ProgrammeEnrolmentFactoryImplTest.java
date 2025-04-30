package PAI.factory;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.repository.IProgrammeEnrolmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeEnrolmentFactoryImplTest {

    private IProgrammeEnrolmentRepository _peRepositoryDouble;
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
        createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

        try (
                MockedConstruction<ProgrammeEnrolment> programmeEnrolmentDouble = mockConstruction(ProgrammeEnrolment.class, (mock, context) -> {
                })) {

        //Act
        ProgrammeEnrolment result = peFactory.createProgrammeEnrolment(_studentIDDouble, _accessMethodIDDouble, _programmeIDDouble, _dateDouble);

        //Assert
        List<ProgrammeEnrolment> constructed = programmeEnrolmentDouble.constructed();
        ProgrammeEnrolment created = (constructed).get(0);
        assertEquals(created, result);
        }
    }

    @Test
    void shouldThrowExceptionAndNotCreateObjectIfStudentIDNullWithIsolation() {
        //arrange
        createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

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
    void shouldThrowExceptionAndNotCreateObjectIfAccessMethodIDNullWithIsolation() {
        //arrange
        createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

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
    void shouldThrowExceptionAndNotCreateObjectIfProgrammeIDNullWithIsolation() {
        //arrange
        createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

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
    void shouldThrowExceptionAndNotCreateObjectIfDateNullWithIsolation() {
        //Arrange
        createDoublesForTestsWithIsolation();
        ProgrammeEnrolmentFactoryImpl peFactory = new ProgrammeEnrolmentFactoryImpl();

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
}