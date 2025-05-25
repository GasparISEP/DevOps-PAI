package PAI.service.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear;

import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
    void getTotalEnrolledStudentsInProgrammesByDepartmentAndYear() {
        // Arrange
        IDepartmentRepository depRepo = mock(IDepartmentRepository.class);
        ISchoolYearRepository sYRepo = mock(ISchoolYearRepository.class);
        IProgrammeRepository progRepos = mock(IProgrammeRepository.class);
        IProgrammeEditionRepository progERepo = mock(IProgrammeEditionRepository.class);
        IProgrammeEditionEnrolmentRepository progEERepo = mock(IProgrammeEditionEnrolmentRepository.class);

        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService service = new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl(
                depRepo, sYRepo, progRepos, progERepo, progEERepo);

        TotalEnrolledStudentsCommand command = mock(TotalEnrolledStudentsCommand.class);

        // Act
        int result = service.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);

        // Assert
        assertEquals(0, result);
    }
}