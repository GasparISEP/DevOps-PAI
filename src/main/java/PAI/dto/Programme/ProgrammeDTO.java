package PAI.dto.Programme;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ProgrammeDTO(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Acronym is required")
        String acronym,

        @Min(value = 1, message = "maxECTS must be greater than 0")
        int maxECTS,

        @Min(value = 1, message = "quantSemesters must be greater than 0")
        int quantSemesters,

        @NotBlank(message = "Degree Type ID is required")
        String degreeTypeID,

        @NotBlank(message = "Department ID is required")
        String departmentID,

        @NotBlank(message = "Teacher ID is required")
        String teacherID
) {}
