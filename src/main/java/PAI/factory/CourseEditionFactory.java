package PAI.factory;

import PAI.domain.Course;
import PAI.domain.CourseEdition;
import PAI.domain.ProgrammeEdition;

public class CourseEditionFactory implements CourseEditionFactoryInterface {

    public CourseEdition newCourseEdition (Course course, ProgrammeEdition programmeEdition) throws Exception {
        return new CourseEdition(course, programmeEdition);
    }
}
