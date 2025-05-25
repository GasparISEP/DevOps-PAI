package PAI.service.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear;

import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImplTest {

    @Test
    void getTotalEnrolledStudentsInProgrammesByDepartmentAndYear() {
        // Arrange
        ITotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearService service = new TotalEnrolledStudentsInProgrammesByDepartmentAndSchoolYearServiceImpl();
        TotalEnrolledStudentsCommand command = mock(TotalEnrolledStudentsCommand.class);

        // Act
        int result = service.getTotalEnrolledStudentsInProgrammesByDepartmentAndYear(command);

        // Assert
        assertEquals(0, result);
    }
}