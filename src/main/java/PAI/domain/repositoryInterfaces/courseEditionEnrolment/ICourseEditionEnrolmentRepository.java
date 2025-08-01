package PAI.domain.repositoryInterfaces.courseEditionEnrolment;

import PAI.VOs.CourseEditionEnrolmentGeneratedID;
import PAI.VOs.CourseEditionEnrolmentID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.ddd.IRepository;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ICourseEditionEnrolmentRepository extends IRepository <CourseEditionEnrolmentID, CourseEditionEnrolment> {

    boolean isStudentEnrolledInCourseEdition(StudentID studentId, CourseEditionID courseEditionId) throws Exception;

    boolean existsByGeneratedID(CourseEditionEnrolmentGeneratedID generatedID);

    Optional<CourseEditionEnrolment> findByStudentAndEdition(StudentID studentId, CourseEditionID courseEditionId);

    Optional<CourseEditionEnrolment> findByGeneratedID(CourseEditionEnrolmentGeneratedID id);

    int numberOfStudentsEnrolledInCourseEdition(CourseEditionID courseEditionId) throws Exception;

    void enrolStudentInProgrammeCourseEditions(StudentID studentId, List<CourseEditionID> courseEditions) throws Exception;

    Set<CourseEditionEnrolment> getInternalSet() throws Exception;

    List<CourseEditionEnrolment> findByStudentID(StudentID studentID);

    List<CourseEditionEnrolment> findActiveEnrolmentsByStudentID(StudentID studentID);
}