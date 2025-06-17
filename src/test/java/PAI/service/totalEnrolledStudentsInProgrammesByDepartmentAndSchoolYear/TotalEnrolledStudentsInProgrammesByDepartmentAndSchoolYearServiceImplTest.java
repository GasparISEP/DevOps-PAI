package PAI.service.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear;

import PAI.VOs.DepartmentID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImplTest {

    @Test
    void shouldThrowExceptionIfDepartmentRepositoryIsNull() {
        // Arrange
        IDepartmentRepository depRepo = null;
        ISchoolYearRepository sYRepo = mock(ISchoolYearRepository.class);
        IProgrammeRepository progRepos = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository progERepo = mock(IProgrammeEditionRepository.class);
        IProgrammeEditionEnrolmentRepository progEERepo = mock(IProgrammeEditionEnrolmentRepository.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(depRepo, sYRepo, progRepos, progERepo, progEERepo);});

        // Assert
        assertEquals("Department Repository cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfSchoolYearRepositoryIsNull() {
        // Arrange
        IDepartmentRepository depRepo = mock(IDepartmentRepository.class);
        ISchoolYearRepository sYRepo = null;
        IProgrammeRepository progRepos = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository progERepo = mock(IProgrammeEditionRepository.class);
        IProgrammeEditionEnrolmentRepository progEERepo = mock(IProgrammeEditionEnrolmentRepository.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(depRepo, sYRepo, progRepos, progERepo, progEERepo);});

        // Assert
        assertEquals("School Year Repository cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfProgrammeRepositoryIsNull() {
        // Arrange
        IDepartmentRepository depRepo = mock(IDepartmentRepository.class);
        ISchoolYearRepository sYRepo = mock(ISchoolYearRepository.class);
        IProgrammeRepository progRepos = null;
        IProgrammeEditionRepository progERepo = mock(IProgrammeEditionRepository.class);
        IProgrammeEditionEnrolmentRepository progEERepo = mock(IProgrammeEditionEnrolmentRepository.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(depRepo, sYRepo, progRepos, progERepo, progEERepo);});

        // Assert
        assertEquals("Programme Repository cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionRepositoryIsNull() {
        // Arrange
        IDepartmentRepository depRepo = mock(IDepartmentRepository.class);
        ISchoolYearRepository sYRepo = mock(ISchoolYearRepository.class);
        IProgrammeRepository progRepos = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository progERepo = null;
        IProgrammeEditionEnrolmentRepository progEERepo = mock(IProgrammeEditionEnrolmentRepository.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(depRepo, sYRepo, progRepos, progERepo, progEERepo);});

        // Assert
        assertEquals("Programme Edition Repository cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionEnrolmentRepositoryIsNull() {
        // Arrange
        IDepartmentRepository depRepo = mock(IDepartmentRepository.class);
        ISchoolYearRepository sYRepo = mock(ISchoolYearRepository.class);
        IProgrammeRepository progRepos = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository progERepo = mock(IProgrammeEditionRepository.class);
        IProgrammeEditionEnrolmentRepository progEERepo = null;

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(depRepo, sYRepo, progRepos, progERepo, progEERepo);});

        // Assert
        assertEquals("Programme Edition Enrolment Repository cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCommandReceivedIsNull() {
        // Arrange
        IDepartmentRepository depRepo = mock(IDepartmentRepository.class);
        ISchoolYearRepository sYRepo = mock(ISchoolYearRepository.class);
        IProgrammeRepository progRepos = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository progERepo = mock(IProgrammeEditionRepository.class);
        IProgrammeEditionEnrolmentRepository progEERepo = mock(IProgrammeEditionEnrolmentRepository.class);

        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService service = new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(depRepo, sYRepo, progRepos, progERepo, progEERepo);

        TotalEnrolledStudentsCommand command = null;

        // Act
        Exception exception = assertThrows(Exception.class, () -> {service.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);});

        // Assert
        assertEquals("TotalEnrolledStudentsCommand must not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDepartmentIDReceivedIsNull() {
        // Arrange
        IDepartmentRepository depRepo = mock(IDepartmentRepository.class);
        ISchoolYearRepository sYRepo = mock(ISchoolYearRepository.class);
        IProgrammeRepository progRepos = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository progERepo = mock(IProgrammeEditionRepository.class);
        IProgrammeEditionEnrolmentRepository progEERepo = mock(IProgrammeEditionEnrolmentRepository.class);

        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService service = new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(depRepo, sYRepo, progRepos, progERepo, progEERepo);

        TotalEnrolledStudentsCommand command = mock(TotalEnrolledStudentsCommand.class);

        when(command.departmentID()).thenReturn(null);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {service.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);});

        // Assert
        assertEquals("Department ID cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearIDReceivedIsNull() {
        // Arrange
        IDepartmentRepository depRepo = mock(IDepartmentRepository.class);
        ISchoolYearRepository sYRepo = mock(ISchoolYearRepository.class);
        IProgrammeRepository progRepos = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository progERepo = mock(IProgrammeEditionRepository.class);
        IProgrammeEditionEnrolmentRepository progEERepo = mock(IProgrammeEditionEnrolmentRepository.class);

        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService service = new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(depRepo, sYRepo, progRepos, progERepo, progEERepo);

        TotalEnrolledStudentsCommand command = mock(TotalEnrolledStudentsCommand.class);

        when(command.departmentID()).thenReturn(mock(DepartmentID.class));
        when(command.schoolYearID()).thenReturn(null);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {service.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);});

        // Assert
        assertEquals("School Year ID cannot be null", exception.getMessage());
    }

    @Test
    void shouldReturnZeroWhenDepartmentGivenIsNotInTheRepository() throws Exception {
        // Arrange
        IDepartmentRepository depRepo = mock(IDepartmentRepository.class);
        ISchoolYearRepository sYRepo = mock(ISchoolYearRepository.class);
        IProgrammeRepository progRepo = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository progERepo = mock(IProgrammeEditionRepository.class);
        IProgrammeEditionEnrolmentRepository progEERepo = mock(IProgrammeEditionEnrolmentRepository.class);

        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService service = new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(depRepo, sYRepo, progRepo, progERepo, progEERepo);

        TotalEnrolledStudentsCommand command = mock(TotalEnrolledStudentsCommand.class);

        DepartmentID departmentID = mock(DepartmentID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(command.departmentID()).thenReturn(departmentID);
        when(command.schoolYearID()).thenReturn(schoolYearID);
        when(depRepo.containsOfIdentity(departmentID)).thenReturn(false);
        when(sYRepo.containsOfIdentity(schoolYearID)).thenReturn(true);

        // Act
        int result = service.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);

        // Assert
        assertEquals(0, result);
        assertNotEquals(1, result);
        verify(depRepo, times(1)).containsOfIdentity(departmentID);
        verifyNoInteractions(sYRepo, progERepo, progEERepo);
        verify(depRepo).containsOfIdentity(departmentID);
    }

    @Test
    void shouldReturnZeroWhenSchoolYearGivenIsNotInTheRepository() throws Exception {
        // Arrange
        IDepartmentRepository depRepo = mock(IDepartmentRepository.class);
        ISchoolYearRepository sYRepo = mock(ISchoolYearRepository.class);
        IProgrammeRepository progRepo = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository progERepo = mock(IProgrammeEditionRepository.class);
        IProgrammeEditionEnrolmentRepository progEERepo = mock(IProgrammeEditionEnrolmentRepository.class);

        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService service = new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(depRepo, sYRepo, progRepo, progERepo, progEERepo);

        TotalEnrolledStudentsCommand command = mock(TotalEnrolledStudentsCommand.class);

        DepartmentID departmentID = mock(DepartmentID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(command.departmentID()).thenReturn(departmentID);
        when(command.schoolYearID()).thenReturn(schoolYearID);
        when(depRepo.containsOfIdentity(departmentID)).thenReturn(true);
        when(sYRepo.containsOfIdentity(schoolYearID)).thenReturn(false);

        // Act
        int result = service.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);

        // Assert
        assertEquals(0, result);
        assertNotEquals(1, result);
        verify(depRepo).containsOfIdentity(departmentID);
        verify(sYRepo).containsOfIdentity(schoolYearID);
        verify(depRepo, times(1)).containsOfIdentity(departmentID);
        verify(sYRepo, times(1)).containsOfIdentity(schoolYearID);
        verifyNoInteractions(progRepo, progERepo, progEERepo);
    }

    @Test
    void shouldReturnZeroWhenSchoolYearAndDepartmentGivenAreNotInTheRepository() throws Exception {
        // Arrange
        IDepartmentRepository depRepo = mock(IDepartmentRepository.class);
        ISchoolYearRepository sYRepo = mock(ISchoolYearRepository.class);
        IProgrammeRepository progRepo = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository progERepo = mock(IProgrammeEditionRepository.class);
        IProgrammeEditionEnrolmentRepository progEERepo = mock(IProgrammeEditionEnrolmentRepository.class);

        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService service = new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(depRepo, sYRepo, progRepo, progERepo, progEERepo);

        TotalEnrolledStudentsCommand command = mock(TotalEnrolledStudentsCommand.class);

        DepartmentID departmentID = mock(DepartmentID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(command.departmentID()).thenReturn(departmentID);
        when(command.schoolYearID()).thenReturn(schoolYearID);
        when(depRepo.containsOfIdentity(departmentID)).thenReturn(false);
        when(sYRepo.containsOfIdentity(schoolYearID)).thenReturn(false);

        // Act
        int result = service.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);

        // Assert
        assertEquals(0, result);
        assertNotEquals(1, result);
        verify(depRepo).containsOfIdentity(departmentID);
        verify(depRepo, times(1)).containsOfIdentity(departmentID);
        verify(sYRepo, times(0)).containsOfIdentity(schoolYearID);
        verifyNoInteractions(sYRepo, progERepo, progEERepo);
    }

    //BUSINESS LOGIC TESTS
    @Test
    void shouldReturnTheSumOfStudentsEnrolInProgrammesByDepartmentAndSchoolYear() throws Exception{
        // Arrange
        IDepartmentRepository depRepo = mock(IDepartmentRepository.class);
        ISchoolYearRepository sYRepo = mock(ISchoolYearRepository.class);
        IProgrammeRepository progRepo = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository progERepo = mock(IProgrammeEditionRepository.class);
        IProgrammeEditionEnrolmentRepository progEERepo = mock(IProgrammeEditionEnrolmentRepository.class);

        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService service = new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(depRepo, sYRepo, progRepo, progERepo, progEERepo);

        TotalEnrolledStudentsCommand command = mock(TotalEnrolledStudentsCommand.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        when(command.schoolYearID()).thenReturn(schoolYearID);
        when(command.departmentID()).thenReturn(departmentID);
        when(depRepo.containsOfIdentity(departmentID)).thenReturn(true);
        when(sYRepo.containsOfIdentity(schoolYearID)).thenReturn(true);

        List<ProgrammeID> programmeIDs = List.of(mock(ProgrammeID.class), mock(ProgrammeID.class), mock(ProgrammeID.class), mock(ProgrammeID.class), mock(ProgrammeID.class));
        when(progRepo.findProgrammesIdByDepartmentId(departmentID)).thenReturn(programmeIDs);

        List<ProgrammeEditionID> programmeEditionIDs = List.of(mock(ProgrammeEditionID.class), mock(ProgrammeEditionID.class), mock(ProgrammeEditionID.class), mock(ProgrammeEditionID.class));
        when(progERepo.findProgrammeEditionIDsBySchoolYearIDAndProgrammeIDs(schoolYearID, programmeIDs)).thenReturn(programmeEditionIDs);

        when(progEERepo.countEnrolledStudentsByProgrammeEditionIds(programmeEditionIDs)).thenReturn(4);

        // Act
        int result = service.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);

        // Assert
        assertEquals(4, result);
        verify(depRepo).containsOfIdentity(departmentID);
        verify(sYRepo).containsOfIdentity(schoolYearID);
        verify(progRepo).findProgrammesIdByDepartmentId(departmentID);
        verify(progERepo).findProgrammeEditionIDsBySchoolYearIDAndProgrammeIDs(schoolYearID, programmeIDs);
        verify(progEERepo).countEnrolledStudentsByProgrammeEditionIds(programmeEditionIDs);
    }

    @Test
    void shouldReturnZeroWhenNoProgrammeBelongsToDepartment() throws Exception {
        // Arrange
        IDepartmentRepository depRepo = mock(IDepartmentRepository.class);
        ISchoolYearRepository sYRepo = mock(ISchoolYearRepository.class);
        IProgrammeRepository progRepo = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository progERepo = mock(IProgrammeEditionRepository.class);
        IProgrammeEditionEnrolmentRepository progEERepo = mock(IProgrammeEditionEnrolmentRepository.class);

        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService service =
                new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(
                        depRepo, sYRepo, progRepo, progERepo, progEERepo);

        TotalEnrolledStudentsCommand command = mock(TotalEnrolledStudentsCommand.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        when(command.departmentID()).thenReturn(departmentID);
        when(command.schoolYearID()).thenReturn(schoolYearID);

        when(depRepo.containsOfIdentity(departmentID)).thenReturn(true);
        when(sYRepo.containsOfIdentity(schoolYearID)).thenReturn(true);

        when(progRepo.findProgrammesIdByDepartmentId(departmentID)).thenReturn(List.of());

        // Act
        int result = service.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);

        // Assert
        assertEquals(0, result);
        assertNotEquals(1, result);

        verify(depRepo).containsOfIdentity(departmentID);
        verify(sYRepo).containsOfIdentity(schoolYearID);
        verify(progRepo).findProgrammesIdByDepartmentId(departmentID);

        verifyNoInteractions(progERepo, progEERepo);
    }

    @Test
    void shouldReturnsZeroWhenNoProgrammeEditionExistsForProgrammeAndSchoolYear() throws Exception {
        // Arrange
        IDepartmentRepository depRepo = mock(IDepartmentRepository.class);
        ISchoolYearRepository sYRepo = mock(ISchoolYearRepository.class);
        IProgrammeRepository progRepo = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository progERepo = mock(IProgrammeEditionRepository.class);
        IProgrammeEditionEnrolmentRepository progEERepo = mock(IProgrammeEditionEnrolmentRepository.class);

        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService service =
                new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(
                        depRepo, sYRepo, progRepo, progERepo, progEERepo);

        TotalEnrolledStudentsCommand command = mock(TotalEnrolledStudentsCommand.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        when(command.departmentID()).thenReturn(departmentID);
        when(command.schoolYearID()).thenReturn(schoolYearID);

        when(depRepo.containsOfIdentity(departmentID)).thenReturn(true);
        when(sYRepo.containsOfIdentity(schoolYearID)).thenReturn(true);

        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        List<ProgrammeID> programmeIDList = List.of(programmeID1, programmeID2);
        when(progRepo.findProgrammesIdByDepartmentId(departmentID)).thenReturn(programmeIDList);

        when(progERepo.findProgrammeEditionIDsBySchoolYearIDAndProgrammeIDs(schoolYearID, programmeIDList)).thenReturn(List.of());

        // Act
        int result = service.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);

        // Assert
        assertEquals(0, result);
        assertNotEquals(1, result);

        verify(depRepo).containsOfIdentity(departmentID);
        verify(sYRepo).containsOfIdentity(schoolYearID);
        verify(progRepo).findProgrammesIdByDepartmentId(departmentID);
        verify(progERepo).findProgrammeEditionIDsBySchoolYearIDAndProgrammeIDs(schoolYearID, programmeIDList);
        verifyNoInteractions(progEERepo);
    }
}