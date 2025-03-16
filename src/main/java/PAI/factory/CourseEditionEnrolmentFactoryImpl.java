package PAI.factory;

import PAI.domain.CourseEdition;
import PAI.domain.CourseEditionEnrolment;
import PAI.domain.Student;


public class CourseEditionEnrolmentFactoryImpl implements ICourseEditionEnrolmentFactory {

    public CourseEditionEnrolment createCourseEditionEnrollment(Student student, CourseEdition courseEdition) {
        return new CourseEditionEnrolment(student, courseEdition);
    }
}
