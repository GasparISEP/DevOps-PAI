package PAI.factory;

import PAI.domain.CourseEditionEnrolment;
import PAI.domain.CourseEdition_2;
import PAI.domain.Student;

public interface ICourseEditionEnrolmentFactory {

    CourseEditionEnrolment createCourseEditionEnrolment(Student student, CourseEdition_2 courseEdition);
}
