package PAI.domain.course;

import PAI.VOs.*;

public interface ICourseFactory {

    Course createCourse(Name name, Acronym acronym, CourseQuantityCreditsEcts quantityCreditsEcts, DurationCourseInCurricularYear durationCourseInSemester);

    Course createCourse(CourseID courseID, Name name, Acronym acronym, CourseQuantityCreditsEcts quantityCreditsEcts, DurationCourseInCurricularYear durationCourseInSemester) throws Exception;
}
