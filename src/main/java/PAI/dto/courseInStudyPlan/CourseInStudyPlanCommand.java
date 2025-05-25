package PAI.dto.courseInStudyPlan;

import PAI.VOs.*;

public record CourseInStudyPlanCommand(
        Semester semester,
        CurricularYear curricularYear,
        Acronym courseAcronym,
        Name courseName,
        ProgrammeID programmeAcronym,
        Date implementationDate,
        Date date,
        int duration,
        double credits
) {
    public StudyPlanID studyPlanID() {
        return new StudyPlanID(programmeAcronym, implementationDate);
    }

    public DurationCourseInCurricularYear durationOfCourse() {
        return new DurationCourseInCurricularYear(duration);
    }

    public CourseQuantityCreditsEcts quantityOfCreditsEcts() throws Exception {
        return new CourseQuantityCreditsEcts(credits);
    }

    public CourseID courseID() {
        return new CourseID(courseAcronym, courseName);
    }
}