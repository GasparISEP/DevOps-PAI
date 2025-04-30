package PAI.domain.courseEditionEnrolment;

import PAI.VOs.CourseEditionEnrolmentID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.ddd.IRepository;

import java.util.List;
import java.util.Optional;

public interface ICourseEditionEnrolmentRepository extends IRepository <CourseEditionEnrolmentID, CourseEditionEnrolment> {

    boolean isStudentEnrolledInCourseEdition(StudentID studentId, CourseEditionID courseEditionId) throws Exception;

    Optional<CourseEditionEnrolment> findByStudentAndEdition(StudentID studentId, CourseEditionID courseEditionId);

    int numberOfStudentsEnrolledInCourseEdition(CourseEditionID courseEditionId) throws Exception;

    void enrolStudentInProgrammeCourseEditions(StudentID studentId, List<CourseEditionID> courseEditions) throws Exception;

}
