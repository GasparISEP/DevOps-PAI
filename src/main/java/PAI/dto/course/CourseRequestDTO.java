package PAI.dto.course;

import jakarta.validation.constraints.NotBlank;

public record CourseRequestDTO (

        @NotBlank (message = "Acronym cannot be blank.")
        String _acronym,

        @NotBlank (message = "Name cannot be blank.")
        String _name
) {}