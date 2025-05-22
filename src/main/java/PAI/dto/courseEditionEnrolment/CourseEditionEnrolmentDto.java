package PAI.dto.courseEditionEnrolment;

import jakarta.validation.constraints.NotBlank;

public record CourseEditionEnrolmentDto(
    @NotBlank(message = "Course edition ID is required")
    String courseEditionId,
    @NotBlank(message = "Student ID is required")
    String studentId
) {}

