package PAI.domain.course;

import PAI.VOs.*;

public class CourseFactoryDDDImpl implements ICourseFactoryDDD{

    public CourseDDD createCourse(CourseID id, Name name, Acronym acronym, CourseQuantityCreditsEcts quantityCreditsEcts, DurationCourseInCurricularYear durationCourseInSemester){
        return new CourseDDD(id, name, acronym, quantityCreditsEcts, durationCourseInSemester);
    }
}
