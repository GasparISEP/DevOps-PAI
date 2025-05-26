package PAI.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CourseIDDTO(

        @NotNull(message = "Course Acronym is required")
        @Size(min = 3, max = 3, message = "Acronym must have 3 characters")
        String acronym,
        @NotBlank(message = "Course Name is required")
        String name) {
}
