package PAI.domain.course;

import PAI.VOs.*;

public interface ICourseFactoryDDD {

    public Course createCourse(CourseID id, Name name, Acronym acronym, CourseQuantityCreditsEcts quantityCreditsEcts, DurationCourseInCurricularYear durationCourseInSemester);
}
