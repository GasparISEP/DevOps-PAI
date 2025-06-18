package PAI.dto.courseInStudyPlan;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CourseInStudyPlanResponseDTO(
        int semester,
        int curricularYear,
        @NotBlank(message = "Course acronym cannot be blank!")
        String courseAcronym,
        @NotBlank(message = "Course name cannot be blank!")
        String courseName,
        @NotBlank(message = "Programme acronym cannot be blank!")
        String programmeAcronym,
        @NotNull(message = "Date is required")
        String studyPlanDate,
        int duration,
        double credits,
        UUID generatedID,
        @NotBlank(message = "Programme ID cannot be blank!")
        String programmeID
) {
}
