package PAI.dto.department;

import jakarta.validation.constraints.NotBlank;

public record DepartmentWithDirectorRequest(
    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "Acronym is required")
    String acronym,

    @NotBlank(message = "Director is required")
    String teacherID
) {}

