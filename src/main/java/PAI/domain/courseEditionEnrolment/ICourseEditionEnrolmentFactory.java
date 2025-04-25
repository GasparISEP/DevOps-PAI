package PAI.domain.courseEditionEnrolment;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;

import java.time.LocalDate;

public interface ICourseEditionEnrolmentFactory {

    CourseEditionEnrolment createCourseEditionEnrolment(StudentID studentId, CourseEditionID courseEditionId);

    CourseEditionEnrolment createWithEnrolmentDate(StudentID studentID, CourseEditionID courseEditionID, LocalDate enrolmentDate, boolean active);
}
