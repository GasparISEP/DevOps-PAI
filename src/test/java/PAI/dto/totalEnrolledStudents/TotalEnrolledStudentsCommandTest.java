package PAI.dto.totalEnrolledStudents;

import PAI.VOs.DepartmentID;
import PAI.VOs.SchoolYearID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TotalEnrolledStudentsCommandTest {

    @Test
    void shouldObtainDepartmentID() {
        // Arrange
        DepartmentID departmentID = mock(DepartmentID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        TotalEnrolledStudentsCommand command = new TotalEnrolledStudentsCommand(departmentID, schoolYearID);

        // Act
        DepartmentID result = command.departmentID();

        // Assert
        assertEquals(departmentID, result);
    }

    @Test
    void shouldObtainSchoolYearID() {
        // Arrange
        DepartmentID departmentID = mock(DepartmentID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        TotalEnrolledStudentsCommand command = new TotalEnrolledStudentsCommand(departmentID, schoolYearID);

        // Act
        SchoolYearID result = command.schoolYearID();

        // Assert
        assertEquals(schoolYearID, result);
    }
}