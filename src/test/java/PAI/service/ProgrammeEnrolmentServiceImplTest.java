package PAI.service;

import PAI.VOs.AccessMethodID;
import PAI.VOs.Date;
import PAI.VOs.ProgrammeID;
import PAI.VOs.StudentID;
import PAI.domain.ProgrammeEnrolment;
import PAI.factory.IProgrammeEnrolmentFactory;
import PAI.repository.IProgrammeEnrolmentRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEnrolmentServiceImplTest {

    private IProgrammeEnrolmentFactory _peFactoryDouble;
    private IProgrammeEnrolmentRepository _peRepositoryDouble;
    private StudentID _studentIDDouble;
    private AccessMethodID _amIDDouble;
    private ProgrammeID _programmeIDDouble;
    private Date _dateDouble;
    private ProgrammeEnrolment _peDouble;

    private void createDoubles() {
        _peFactoryDouble = mock(IProgrammeEnrolmentFactory.class);
        _peRepositoryDouble = mock(IProgrammeEnrolmentRepository.class);
        _studentIDDouble = mock(StudentID.class);
        _amIDDouble = mock(AccessMethodID.class);
        _programmeIDDouble = mock(ProgrammeID.class);
        _dateDouble = mock(Date.class);
        _peDouble = mock(ProgrammeEnrolment.class);
    }

    @Test
    void shouldCreateProgrammeEnrolmentServiceIfParametersValid() {
        //Arrange
        createDoubles();

        //Act
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble);
        //Assert
        assertNotNull(peService);

    }

    @Test
    void shouldThrowExceptionAndNotCreateProgrammeEnrolmentServiceIfFactoryNull() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentServiceImpl(null, _peRepositoryDouble));
    }

    @Test
    void shouldThrowExceptionAndNotCreateProgrammeEnrolmentServiceIfRepositoryNull() {
        //Arrange
        createDoubles();

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, null));
    }

    @Test
    void shouldCreateNewProgrammeEnrolmentAndPersist(){
        //Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble);

        when(_peFactoryDouble.createProgrammeEnrolment(_studentIDDouble, _amIDDouble, _programmeIDDouble, _dateDouble)).thenReturn(_peDouble);
        when(_peRepositoryDouble.save(_peDouble)).thenReturn(_peDouble);

        //Act
        boolean result = peService.enrolStudentInProgramme(_studentIDDouble, _amIDDouble, _programmeIDDouble, _dateDouble);

        //Arrange
        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionAndNotSaveProgrammeEnrolmentIfStudentIDNull() {
        //Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble);

        //Act
        boolean result = peService.enrolStudentInProgramme(null, _amIDDouble, _programmeIDDouble, _dateDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldThrowExceptionAndNotSaveProgrammeEnrolmentIfAccessMethodIDNull() {
        //Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble);

        //Act
        boolean result = peService.enrolStudentInProgramme(_studentIDDouble, null, _programmeIDDouble, _dateDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldThrowExceptionAndNotSaveProgrammeEnrolmentIfProgrammeIDNull() {
        //Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble);

        //Act
        boolean result = peService.enrolStudentInProgramme(_studentIDDouble, _amIDDouble, null, _dateDouble);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldThrowExceptionAndNotSaveProgrammeEnrolmentIfDateIDNull() {
        //Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble);

        //Act
        boolean result = peService.enrolStudentInProgramme(_studentIDDouble, _amIDDouble, _programmeIDDouble, null);

        //Assert
        assertFalse(result);
    }
}