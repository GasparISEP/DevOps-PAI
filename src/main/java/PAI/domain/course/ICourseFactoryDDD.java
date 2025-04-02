package PAI.domain.course;

import PAI.VOs.*;

public interface ICourseFactoryDDD {

    public CourseDDD createCourse(CourseID id, Name name, Acronym acronym, CourseQuantityCreditsEcts quantityCreditsEcts, DurationCourseInCurricularYear durationCourseInSemester);
}
