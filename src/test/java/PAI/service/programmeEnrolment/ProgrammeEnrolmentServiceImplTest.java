package PAI.service.programmeEnrolment;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.domain.programmeEnrolment.IProgrammeEnrolmentFactory;
import PAI.domain.repositoryInterfaces.programmeEnrolment.IProgrammeEnrolmentRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProgrammeEnrolmentServiceImplTest {

    private IProgrammeEnrolmentFactory _peFactoryDouble;
    private IProgrammeEnrolmentRepository _peRepositoryDouble;
    private StudentID _studentIDDouble;
    private AccessMethodID _amIDDouble;
    private ProgrammeID _programmeIDDouble;
    private Date _dateDouble;
    private ProgrammeEnrolment _peDouble;
    private ProgrammeEnrolmentID _peIDDouble;

    private void createDoubles() {
        _peFactoryDouble = mock(IProgrammeEnrolmentFactory.class);
        _peRepositoryDouble = mock(IProgrammeEnrolmentRepository.class);
        _studentIDDouble = mock(StudentID.class);
        _amIDDouble = mock(AccessMethodID.class);
        _programmeIDDouble = mock(ProgrammeID.class);
        _dateDouble = mock(Date.class);
        _peDouble = mock(ProgrammeEnrolment.class);
        _peIDDouble = mock(ProgrammeEnrolmentID.class);
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
    void shouldCreateNewProgrammeEnrolmentAndPersist() throws Exception {
        //Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble);

        when(_peFactoryDouble.createProgrammeEnrolment(_studentIDDouble, _amIDDouble, _programmeIDDouble, _dateDouble)).thenReturn(_peDouble);
        when(_peDouble.getProgrammeEnrolmentID()).thenReturn(_peIDDouble);
        when(_peRepositoryDouble.containsOfIdentity(_peIDDouble)).thenReturn(false);
        when(_peRepositoryDouble.save(_peDouble)).thenReturn(_peDouble);

        //Act
        ProgrammeEnrolment result = peService.enrolStudentInProgramme(_studentIDDouble, _amIDDouble, _programmeIDDouble, _dateDouble);

        //Assert
        assertEquals(_peDouble, result);    }

    @Test
    void shouldThrowExceptionAndNotCreateProgrammeEnrolmentIfStudentIDNull() {
        //Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble);

        // Act & Assert:
        Exception ex = assertThrows(Exception.class, () -> peService.enrolStudentInProgramme(null, _amIDDouble, _programmeIDDouble, _dateDouble));
    }

    @Test
    void shouldThrowExceptionAndNotCreateProgrammeEnrolmentIfAccessMethodIDNull() {
        //Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble);

        //Act + assert
        Exception ex = assertThrows(
                Exception.class,
                () -> peService.enrolStudentInProgramme(
                        _studentIDDouble,
                        null,
                        _programmeIDDouble,
                        _dateDouble
                )
        );
    }

    @Test
    void shouldThrowExceptionAndNotCreateProgrammeEnrolmentIfProgrammeIDNull() {
        //Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble);

        //Act + Assert
        Exception ex = assertThrows(
                Exception.class,
                () -> peService.enrolStudentInProgramme(
                        _studentIDDouble,
                        _amIDDouble,
                        null,
                        _dateDouble
                )
        );
    }

    @Test
    void shouldThrowExceptionAndNotCreateProgrammeEnrolmentIfDateIDNull() {
        //Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble);

        //Act + Assert
        Exception ex = assertThrows(
                Exception.class,
                () -> peService.enrolStudentInProgramme(
                        _studentIDDouble,
                        _amIDDouble,
                        _programmeIDDouble,
                        null
                )
        );
    }

    @Test
    void shouldThrowExceptionAndNotPersistProgrammeEnrolment(){
        // Arrange
        createDoubles();

        when(_peFactoryDouble.createProgrammeEnrolment(
                any(StudentID.class),
                any(AccessMethodID.class),
                any(ProgrammeID.class),
                any(Date.class)
        )).thenReturn(_peDouble);
        when(_peDouble.getProgrammeEnrolmentID()).thenReturn(_peIDDouble);
        when(_peRepositoryDouble.containsOfIdentity(_peIDDouble)).thenReturn(true);


        ProgrammeEnrolmentServiceImpl peService = new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble);

        // Act & Assert:
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> peService.enrolStudentInProgramme(_studentIDDouble, _amIDDouble, _programmeIDDouble, _dateDouble));
        assertEquals("Programme Enrolment already exists.", ex.getMessage());
    }

    @Test
    void shouldReturnEnrolmentWhenFound() {
        // Arrange
        createDoubles();

        ProgrammeEnrolmentServiceImpl peService =
                new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble);


        when(_peRepositoryDouble.findByStudentIDAndProgrammeID(_studentIDDouble, _programmeIDDouble))
                .thenReturn(Optional.of(_peDouble));

        // Act
        ProgrammeEnrolment result =
                peService.findEnrolmentByStudentAndProgramme(_studentIDDouble, _programmeIDDouble);

        // Assert
        assertNotNull(result, "Deveria retornar o object de domínio quando existe");
        assertSame(_peDouble, result, "Deveria ser exatamente a instância mockada");
    }

    @Test
    void shouldReturnNullWhenNotFound() {
        // Arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService =
                new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble);


        when(_peRepositoryDouble.findByStudentIDAndProgrammeID(_studentIDDouble, _programmeIDDouble))
                .thenReturn(Optional.empty());

        // Act
        ProgrammeEnrolment result =
                peService.findEnrolmentByStudentAndProgramme(_studentIDDouble, _programmeIDDouble);

        // Assert
        assertNull(result, "Deveria retornar null quando não existe enrolment");
    }

    @Test
    void shouldReturnListOfProgrammes() {
        //arrange
        createDoubles();
        ProgrammeEnrolmentServiceImpl peService =
                new ProgrammeEnrolmentServiceImpl(_peFactoryDouble, _peRepositoryDouble);

        List<ProgrammeEnrolment> expectedProgrammeIDs = List.of(mock(ProgrammeEnrolment.class), mock(ProgrammeEnrolment.class));
        when(_peRepositoryDouble.listOfProgrammesStudentIsEnrolledIn(_studentIDDouble))
                .thenReturn(expectedProgrammeIDs);

        // act
        List<ProgrammeEnrolment> result = peService.listOfProgrammesStudentIsEnrolledIn(_studentIDDouble);

        // assert
        assertEquals(expectedProgrammeIDs, result);
    }

}