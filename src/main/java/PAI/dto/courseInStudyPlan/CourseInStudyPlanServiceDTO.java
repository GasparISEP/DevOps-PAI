package PAI.dto.courseInStudyPlan;

import PAI.VOs.CourseID;
import PAI.VOs.StudyPlanID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseInStudyPlanServiceDTO(
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
        CourseID courseId,
        StudyPlanID studyPlanId
) { }
