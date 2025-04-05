package PAI.domain.course;

import PAI.VOs.*;

public class CourseFactoryImpl implements ICourseFactoryDDD{

    public Course createCourse(CourseID id, Name name, Acronym acronym, CourseQuantityCreditsEcts quantityCreditsEcts, DurationCourseInCurricularYear durationCourseInSemester){
        return new Course(id, name, acronym, quantityCreditsEcts, durationCourseInSemester);
    }
}
