package PAI.dto.courseInStudyPlan;

import PAI.VOs.CourseID;
import PAI.VOs.StudyPlanID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CourseInStudyPlanResponseDTO(
        int semester,
        int curricularYear,
        @NotBlank(message = "Course acronym cannot be blank!")
        String courseAcronym,
        @NotBlank(message = "Course name cannot be blank!")
        String courseName,
        @NotBlank(message = "Programme acronym cannot be blank!")
        String programmeAcronym,
        @NotBlank(message = "Implementation date cannot be blank!")
        String implementationDate,
        @NotNull(message = "Date cannot be null!")
        LocalDate date,
        int duration,
        double credits,
        CourseID courseId,
        StudyPlanID studyPlanId
) {}
