package PAI.repository;

import PAI.VOs.CourseEditionEnrolmentID;
import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.ddd.IRepository;
import PAI.domain.CourseEditionEnrolment;

import java.util.List;
import java.util.Optional;

public interface ICourseEditionEnrolmentRepository extends IRepository <CourseEditionEnrolmentID, CourseEditionEnrolment> {

    boolean enrolStudentInACourseEdition(StudentID studentId, CourseEditionID courseEditionId);

    boolean isStudentEnrolledInCourseEdition(StudentID student, CourseEditionID courseEdition);

    Optional<CourseEditionEnrolment> findByStudentAndEdition(StudentID student, CourseEditionID courseEdition);

    int numberOfStudentsEnrolledInCourseEdition(CourseEditionID courseEditionId) throws Exception;

    boolean removeEnrolment(StudentID studentID, CourseEditionID courseEditionID);

    void enrolStudentInProgrammeCourseEditions(StudentID studentId, List<CourseEditionID> courseEditions);

}
