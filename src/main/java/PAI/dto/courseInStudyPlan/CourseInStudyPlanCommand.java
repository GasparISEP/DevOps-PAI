package PAI.dto.courseInStudyPlan;

import PAI.VOs.*;

public record CourseInStudyPlanCommand(
        Semester semester,
        CurricularYear curricularYear,

        // Course Compose
        Acronym courseAcronym,
        Name courseName,

        // StudyPlan Compose
        Acronym programmeAcronym,
        NameWithNumbersAndSpecialChars programmeName,
        Date studyPlanDate,

        DurationCourseInCurricularYear duration,
        CourseQuantityCreditsEcts credits
) { }