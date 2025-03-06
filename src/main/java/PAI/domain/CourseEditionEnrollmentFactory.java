package PAI.domain;

import PAI.factory.CourseEditionEnrollmentFactoryInterface;


public class CourseEditionEnrollmentFactory implements CourseEditionEnrollmentFactoryInterface {

    public CourseEditionEnrollment createCourseEditionEnrollment(Student student, CourseEdition courseEdition) {
        return new CourseEditionEnrollment(student, courseEdition);
    }
}
