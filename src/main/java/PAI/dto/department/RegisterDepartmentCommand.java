package PAI.dto.department;

import PAI.VOs.Acronym;
import PAI.VOs.Name;

public record RegisterDepartmentCommand (
        Name name,
        Acronym acronym
) {
    public RegisterDepartmentCommand {
        if (name == null || name.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (acronym == null || acronym.getAcronym().isBlank()) {
            throw new IllegalArgumentException("Acronym is required");
        }
    }
}
