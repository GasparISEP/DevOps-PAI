package PAI.persistence.springdata.courseEditionEnrolment;

import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentGeneratedIDDataModel;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICourseEditionEnrolmentRepositorySpringData extends JpaRepository<CourseEditionEnrolmentDataModel, CourseEditionEnrolmentIDDataModel> {

    boolean existsById_StudentIDAndId_CourseEditionIDAndActiveTrue(StudentIDDataModel studentID, CourseEditionIDDataModel courseEditionID);

    long countById_CourseEditionIDAndActiveTrue(CourseEditionIDDataModel courseEditionId);

    Optional<CourseEditionEnrolmentDataModel> findById_StudentIDAndId_CourseEditionID(StudentIDDataModel studentId, CourseEditionIDDataModel courseEditionId);

    boolean existsByGeneratedID(CourseEditionEnrolmentGeneratedIDDataModel generatedID);

    Optional<CourseEditionEnrolmentDataModel> findByGeneratedID(CourseEditionEnrolmentGeneratedIDDataModel generatedID);

    List<CourseEditionEnrolmentDataModel> findById_StudentID (StudentIDDataModel studentID);

    List<CourseEditionEnrolmentDataModel> findById_StudentIDAndActiveTrue(StudentIDDataModel studentIDData);
}
