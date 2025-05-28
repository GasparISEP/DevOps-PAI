package PAI.dto.department;

import PAI.VOs.DepartmentID;
import PAI.VOs.TeacherID;

public record DepartmentWithDirectorCommand(

        DepartmentID department,
        TeacherID director
) {
    public DepartmentWithDirectorCommand {

        if (department == null) {
            throw new IllegalArgumentException("Department is required");
        }
        if (director == null) {
            throw new IllegalArgumentException("Director is required");
        }
    }
}

