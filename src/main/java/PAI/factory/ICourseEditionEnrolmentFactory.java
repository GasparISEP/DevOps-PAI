package PAI.factory;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.domain.CourseEdition;
import PAI.domain.CourseEditionEnrolment;
import PAI.domain.Student;

public interface ICourseEditionEnrolmentFactory {

    CourseEditionEnrolment createCourseEditionEnrolment(StudentID student, CourseEditionID courseEdition);
}
