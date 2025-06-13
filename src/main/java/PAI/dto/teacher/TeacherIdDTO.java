package PAI.dto.teacher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TeacherIdDTO(
        @NotBlank(message = "Teacher Acronym is required")
        @Size(min = 3, max = 3, message = "Acronym must have exactly 3 characters")
        String acronym
) {}