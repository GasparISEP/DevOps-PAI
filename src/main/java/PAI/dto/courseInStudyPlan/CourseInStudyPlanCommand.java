package PAI.dto.courseInStudyPlan;

import PAI.VOs.*;

public record CourseInStudyPlanCommand(
        Semester semester,
        CurricularYear curricularYear,
        Acronym courseAcronym,
        Name courseName,
        ProgrammeID programmeAcronym,
        Date implementationDate,
        DurationCourseInCurricularYear duration,
        CourseQuantityCreditsEcts credits
) {
    public StudyPlanID studyPlanID() {
        return new StudyPlanID(programmeAcronym, implementationDate);
    }

    public DurationCourseInCurricularYear durationOfCourse() {
        return duration;
    }

    public CourseQuantityCreditsEcts quantityOfCreditsEcts() throws Exception {
        return credits;
    }

    public CourseID courseID() {
        return new CourseID(courseAcronym, courseName);
    }
}