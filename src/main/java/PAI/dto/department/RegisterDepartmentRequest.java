package PAI.dto.department;

import jakarta.validation.constraints.NotBlank;

public record RegisterDepartmentRequest(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Acronym is required")
        String acronym
) {}
