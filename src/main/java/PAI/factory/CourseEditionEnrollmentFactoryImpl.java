package PAI.factory;

import PAI.domain.CourseEdition;
import PAI.domain.CourseEditionEnrollment;
import PAI.domain.Student;


public class CourseEditionEnrollmentFactoryImpl implements ICourseEditionEnrollmentFactory {

    public CourseEditionEnrollment createCourseEditionEnrollment(Student student, CourseEdition courseEdition) {
        return new CourseEditionEnrollment(student, courseEdition);
    }
}
