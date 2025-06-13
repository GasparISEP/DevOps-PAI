package PAI.dto.ProgrammeAndCourses;

import jakarta.validation.constraints.*;

public record AvailableCoursesInfoRspDTO(
        @NotNull(message = "Course Acronym is required")
        @Size(min = 3, max = 3, message = "Acronym must have 3 characters")
        String acronym,
        @NotBlank(message = "Course Name is required")
        String name,
        @Min(value = 1)
        @Max(value = 60)
        double qtyECTs
) {
}
