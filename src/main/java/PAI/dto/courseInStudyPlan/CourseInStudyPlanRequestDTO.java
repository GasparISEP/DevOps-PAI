package PAI.dto.courseInStudyPlan;

import PAI.VOs.Date;
import jakarta.validation.constraints.*;

public record CourseInStudyPlanRequestDTO(

        int semester,
        int curricularYear,
        @NotBlank(message = "Course Acronym is required")
        String courseAcronym,
        @NotBlank(message = "Course Name is required")
        String courseName,
        @NotBlank(message = "Programme Acronym is required")
        String programmeAcronym,
        @NotBlank(message = "Programme Name is required")
        String programmeName,
        @NotNull(message = "Date is required")
        Date date,
        int duration,
        double credits

) {}
