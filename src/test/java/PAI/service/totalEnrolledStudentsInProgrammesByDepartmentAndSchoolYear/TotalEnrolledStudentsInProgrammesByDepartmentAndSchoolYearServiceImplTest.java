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
import java.util.Optional;

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

    //business logic tests
    @Test
    void shouldReturnTheSumOfStudentsEnrolInProgrammesByDepartmentAndSchoolYear() throws Exception{
        // Arrange
        IDepartmentRepository depRepo = mock(IDepartmentRepository.class);
        ISchoolYearRepository sYRepo = mock(ISchoolYearRepository.class);
        IProgrammeRepository progRepo = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository progERepo = mock(IProgrammeEditionRepository.class);
        IProgrammeEditionEnrolmentRepository progEERepo = mock(IProgrammeEditionEnrolmentRepository.class);

        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService service = new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(
                depRepo, sYRepo, progRepo, progERepo, progEERepo);

        TotalEnrolledStudentsCommand command = mock(TotalEnrolledStudentsCommand.class);

        DepartmentID departmentID = mock(DepartmentID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        when(command.schoolYearID()).thenReturn(schoolYearID);
        when(command.departmentID()).thenReturn(departmentID);
        when(depRepo.containsOfIdentity(departmentID)).thenReturn(true);
        when(sYRepo.containsOfIdentity(schoolYearID)).thenReturn(true);


        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        ProgrammeID programmeID3 = mock(ProgrammeID.class);
        ProgrammeID programmeID4 = mock(ProgrammeID.class);
        ProgrammeID programmeID5 = mock(ProgrammeID.class);
        when(progRepo.findProgrammeByDepartment(departmentID)).thenReturn(List.of(programmeID1, programmeID2, programmeID3, programmeID4, programmeID5));


        Optional<ProgrammeEditionID> opt1 = mock(Optional.class);
        ProgrammeEditionID pE1 = mock(ProgrammeEditionID.class);
        Optional<ProgrammeEditionID> opt2 = mock(Optional.class);
        ProgrammeEditionID pE2 = mock(ProgrammeEditionID.class);
        Optional<ProgrammeEditionID> opt3 = mock(Optional.class);
        ProgrammeEditionID pE3 = mock(ProgrammeEditionID.class);
        Optional<ProgrammeEditionID> opt4 = mock(Optional.class);
        ProgrammeEditionID pE4 = mock(ProgrammeEditionID.class);
        Optional<ProgrammeEditionID> opt5 = mock(Optional.class);
        when(progERepo.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeID1, schoolYearID)).thenReturn(opt1);
        when(progERepo.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeID2, schoolYearID)).thenReturn(opt2);
        when(progERepo.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeID3, schoolYearID)).thenReturn(opt3);
        when(progERepo.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeID4, schoolYearID)).thenReturn(opt4);
        when(progERepo.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeID5, schoolYearID)).thenReturn(opt5);

        when(opt1.isPresent()).thenReturn(true);
        when(opt1.get()).thenReturn(pE1);
        when(opt2.isPresent()).thenReturn(true);
        when(opt2.get()).thenReturn(pE2);
        when(opt3.isPresent()).thenReturn(true);
        when(opt3.get()).thenReturn(pE3);
        when(opt4.isPresent()).thenReturn(true);
        when(opt4.get()).thenReturn(pE4);
        when(opt5.isPresent()).thenReturn(false);

        ProgrammeEditionEnrolment Enrolment1 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment Enrolment2 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment Enrolment3 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment Enrolment4 = mock(ProgrammeEditionEnrolment.class);
        ProgrammeEditionEnrolment Enrolment5 = mock(ProgrammeEditionEnrolment.class);
        when(progEERepo.findAll()).thenReturn(List.of(Enrolment1, Enrolment2, Enrolment3, Enrolment4, Enrolment5));

        when(Enrolment1.hasSameProgrammeEdition(pE1)).thenReturn(false);
        when(Enrolment1.hasSameProgrammeEdition(pE2)).thenReturn(true);

        when(Enrolment2.hasSameProgrammeEdition(pE1)).thenReturn(true);

        when(Enrolment3.hasSameProgrammeEdition(pE1)).thenReturn(false);
        when(Enrolment3.hasSameProgrammeEdition(pE2)).thenReturn(false);
        when(Enrolment3.hasSameProgrammeEdition(pE3)).thenReturn(true);

        when(Enrolment4.hasSameProgrammeEdition(pE1)).thenReturn(false);
        when(Enrolment4.hasSameProgrammeEdition(pE2)).thenReturn(false);
        when(Enrolment4.hasSameProgrammeEdition(pE3)).thenReturn(false);
        when(Enrolment4.hasSameProgrammeEdition(pE4)).thenReturn(false);

        when(Enrolment5.hasSameProgrammeEdition(pE1)).thenReturn(false);
        when(Enrolment5.hasSameProgrammeEdition(pE2)).thenReturn(false);
        when(Enrolment5.hasSameProgrammeEdition(pE3)).thenReturn(false);
        when(Enrolment5.hasSameProgrammeEdition(pE4)).thenReturn(true);

        // Act
        int result = service.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);

        // Assert
        assertEquals(4, result);

        verify(Enrolment1, times(2)).hasSameProgrammeEdition(any());
        verify(Enrolment2, times(1)).hasSameProgrammeEdition(any());
        verify(Enrolment3, times(3)).hasSameProgrammeEdition(any());
        verify(Enrolment4, times(4)).hasSameProgrammeEdition(any());
        verify(Enrolment5, times(4)).hasSameProgrammeEdition(any());

        InOrder inOrder = inOrder(depRepo, sYRepo, progRepo, progERepo, progEERepo);
        inOrder.verify(depRepo).containsOfIdentity(departmentID);
        inOrder.verify(sYRepo).containsOfIdentity(schoolYearID);
        inOrder.verify(progRepo).findProgrammeByDepartment(departmentID);
        inOrder.verify(progERepo).findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeID1, schoolYearID);
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

        when(progRepo.findProgrammeByDepartment(departmentID)).thenReturn(List.of());

        // Act
        int result = service.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);

        // Assert
        assertEquals(0, result);

        verify(depRepo).containsOfIdentity(departmentID);
        verify(sYRepo).containsOfIdentity(schoolYearID);
        verify(progRepo).findProgrammeByDepartment(departmentID);

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
        when(progRepo.findProgrammeByDepartment(departmentID)).thenReturn(List.of(programmeID1, programmeID2));

        when(progERepo.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeID1, schoolYearID))
                .thenReturn(Optional.empty());
        when(progERepo.findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeID2, schoolYearID))
                .thenReturn(Optional.empty());

        // Act
        int result = service.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);

        // Assert
        assertEquals(0, result);

        verify(depRepo).containsOfIdentity(departmentID);
        verify(sYRepo).containsOfIdentity(schoolYearID);
        verify(progRepo).findProgrammeByDepartment(departmentID);
        verify(progERepo).findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeID1, schoolYearID);
        verify(progERepo).findProgrammeEditionIDByProgrammeIDAndSchoolYearID(programmeID2, schoolYearID);

        verifyNoInteractions(progEERepo);
    }



}