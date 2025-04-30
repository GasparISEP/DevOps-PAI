package PAI.service;

import PAI.VOs.CourseEditionID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;

import java.util.List;

public interface ICourseEditionEnrolmentService {

    List<ProgrammeEditionID> findProgrammeEditionIDsThatStudentIsEnrolled(StudentID studentId);

    List<CourseEditionID> findCourseEditionIDsByProgrammeEdition(ProgrammeEditionID programmeEditionID);

    boolean enrolStudentInACourseEdition(StudentID studentId, CourseEditionID courseEditionId);

    boolean removeCourseEditionEnrolment(StudentID studentID, CourseEditionID courseEditionID) throws Exception;
}
