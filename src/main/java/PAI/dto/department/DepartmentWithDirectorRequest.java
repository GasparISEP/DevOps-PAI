package PAI.dto.department;

import jakarta.validation.constraints.NotBlank;

public record DepartmentWithDirectorRequest(

    @NotBlank(message = "Director is required")
    String teacherID
) {}