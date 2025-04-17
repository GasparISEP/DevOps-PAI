package PAI.domain.course;

import PAI.VOs.*;

public class CourseFactoryImpl implements ICourseFactory {

    public Course createCourse(Name name, Acronym acronym, CourseQuantityCreditsEcts quantityCreditsEcts, DurationCourseInCurricularYear durationCourseInSemester) {
        CourseID courseID = new CourseID(acronym, name);
        return new Course(courseID, name, acronym, quantityCreditsEcts, durationCourseInSemester);
    }


    public Course createCourse(CourseID courseID, Name name, Acronym acronym, CourseQuantityCreditsEcts quantityCreditsEcts, DurationCourseInCurricularYear durationCourseInSemester) {
        return new Course(courseID, name, acronym, quantityCreditsEcts, durationCourseInSemester);
    }
}
