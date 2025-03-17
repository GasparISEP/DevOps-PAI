package PAI.factory;

import PAI.domain.CourseEdition;
import PAI.domain.CourseEditionEnrolment;
import PAI.domain.Student;

public interface ICourseEditionEnrolmentFactory {

    CourseEditionEnrolment createCourseEditionEnrolment(Student student, CourseEdition courseEdition);
}
