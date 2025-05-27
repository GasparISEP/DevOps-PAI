package PAI.dto.department;


import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;

public record RegisterDepartmentRequestVOs(
        Name name,
        DepartmentAcronym acronym
) {
    public RegisterDepartmentRequestVOs {
        if (name == null || name.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (acronym == null || acronym.getAcronym().isBlank()) {
            throw new IllegalArgumentException("Acronym is required");
        }
    }
}
