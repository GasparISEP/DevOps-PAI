package PAI.dto.department;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.VOs.TeacherID;

public record DepartmentWithDirectorCommand(
        Name name,
        DepartmentAcronym acronym,
        TeacherID director
) {
    public DepartmentWithDirectorCommand {
        if (name == null || name.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (acronym == null || acronym.getAcronym().isBlank()) {
            throw new IllegalArgumentException("Acronym is required");
        }
        if (director == null) {
            throw new IllegalArgumentException("Director is required");
        }
    }
}

