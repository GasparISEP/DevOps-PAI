package PAI.factory;

import PAI.domain.CourseEdition;
import PAI.domain.CourseEditionEnrollment;
import PAI.domain.Student;

public interface ICourseEditionEnrollmentFactory {

    CourseEditionEnrollment createCourseEditionEnrollment(Student student, CourseEdition courseEdition);
}
