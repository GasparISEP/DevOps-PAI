package PAI.dto.totalEnrolledStudents;

import PAI.VOs.DepartmentID;
import PAI.VOs.SchoolYearID;

public record TotalEnrolledStudentsCommand(
        DepartmentID departmentID,
        SchoolYearID schoolYearID
) {}