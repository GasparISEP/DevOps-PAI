package PAI.dto.courseInStudyPlan;

public record CourseInStudyPlanCommand(
        int semester,
        int curricularYear,

        // Course Compose
        String courseAcronym,
        String courseName,

        // StudyPlan Compose
        String programmeAcronym,
        String programmeName,
        String studyPlanDate,

        int duration,
        double credits
) { }