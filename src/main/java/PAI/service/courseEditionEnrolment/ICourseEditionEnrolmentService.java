package PAI.service.courseEditionEnrolment;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.VOs.US35EnrolledCourseDetails;

import java.util.List;

public interface ICourseEditionEnrolmentService {

    List<ProgrammeEditionID> findProgrammeEditionIDsThatStudentIsEnrolled(StudentID studentId);

    List<CourseEditionID> findCourseEditionIDsByProgrammeEdition(ProgrammeEditionID programmeEditionID);

    boolean enrolStudentInACourseEdition(StudentID studentId, CourseEditionID courseEditionId);

    boolean enrolStudentInACourseEditionFromPersistence(CourseEditionEnrolmentGeneratedID uuid, StudentID studentId, CourseEditionID courseEditionId, Date enrolmentDate, EnrolmentStatus status);

    boolean removeCourseEditionEnrolment(StudentID studentID, CourseEditionID courseEditionID) throws Exception;

    int numberOfStudentsEnrolledInCourseEdition(CourseEditionID courseEditionId) throws Exception;

    List<CourseEditionEnrolment> findByStudentID(int studentUniqueNumber);

    List<US35EnrolledCourseDetails> findEnrolledCourseEditionsForStudent(StudentID studentID);
}
