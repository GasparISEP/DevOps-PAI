package PAI.domain.courseEditionEnrolment;

import PAI.VOs.*;


public interface ICourseEditionEnrolmentFactory {

    CourseEditionEnrolment createCourseEditionEnrolment(StudentID studentId, CourseEditionID courseEditionId);

    CourseEditionEnrolment createWithEnrolmentDateAndUUID(CourseEditionEnrolmentGeneratedID uuid, StudentID studentID, CourseEditionID courseEditionID, Date enrolmentDate, EnrolmentStatus active);
}
