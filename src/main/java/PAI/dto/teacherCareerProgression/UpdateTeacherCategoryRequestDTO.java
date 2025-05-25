package PAI.dto.teacherCareerProgression;

import jakarta.validation.constraints.NotBlank;

public record UpdateTeacherCategoryRequestDTO(

        @NotBlank(message = "Date is required.")
        String date,
        @NotBlank(message = "Teacher ID required.")
        String teacherID,
        @NotBlank(message = "New teacher category required")
        String teacherCategoryID
) {
}
