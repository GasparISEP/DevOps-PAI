package PAI.factory;

import PAI.domain.Course;
import PAI.domain.CourseEdition;
import PAI.domain.ProgrammeEdition;

public interface CourseEditionFactoryInterface {
    CourseEdition newCourseEdition(Course course, ProgrammeEdition programmeEdition) throws Exception;
}
