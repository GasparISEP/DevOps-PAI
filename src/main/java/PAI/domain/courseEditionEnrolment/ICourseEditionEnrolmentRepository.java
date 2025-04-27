package PAI.domain.courseEditionEnrolment;

import PAI.VOs.CourseEditionEnrolmentID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.ddd.IRepository;

import java.util.List;
import java.util.Optional;

public interface ICourseEditionEnrolmentRepository extends IRepository <CourseEditionEnrolmentID, CourseEditionEnrolment> {

    boolean enrolStudentInACourseEdition(CourseEditionEnrolment courseEditionEnrolment) throws Exception;

    boolean isStudentEnrolledInCourseEdition(StudentID studentId, CourseEditionID courseEditionId) throws Exception;

    Optional<CourseEditionEnrolment> findByStudentAndEdition(StudentID studentId, CourseEditionID courseEditionId);

    int numberOfStudentsEnrolledInCourseEdition(CourseEditionID courseEditionId) throws Exception;

    boolean removeEnrolment(StudentID studentId, CourseEditionID courseEditionId);

    void enrolStudentInProgrammeCourseEditions(StudentID studentId, List<CourseEditionID> courseEditions) throws Exception;

}
