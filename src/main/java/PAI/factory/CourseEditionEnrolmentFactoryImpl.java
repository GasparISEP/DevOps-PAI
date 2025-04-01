package PAI.factory;

import PAI.domain.CourseEditionEnrolment;
import PAI.domain.CourseEdition_2;
import PAI.domain.Student;


public class CourseEditionEnrolmentFactoryImpl implements ICourseEditionEnrolmentFactory {

    public CourseEditionEnrolment createCourseEditionEnrolment(Student student, CourseEdition_2 courseEdition) {
        return new CourseEditionEnrolment(student, courseEdition);
    }
}
