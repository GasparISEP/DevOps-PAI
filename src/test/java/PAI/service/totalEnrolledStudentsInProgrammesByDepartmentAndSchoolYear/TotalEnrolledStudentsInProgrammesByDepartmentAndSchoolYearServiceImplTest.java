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