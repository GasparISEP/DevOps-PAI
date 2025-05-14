package PAI.persistence.springdata.courseEditionEnrolment;

import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.courseEditionEnrolment.CourseEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ICourseEditionEnrolmentRepositorySpringData extends JpaRepository<CourseEditionEnrolmentDataModel, CourseEditionEnrolmentIDDataModel> {

    boolean existsById_StudentIDAndId_CourseEditionIDAndActiveTrue(StudentIDDataModel studentID, CourseEditionIDDataModel courseEditionID);

    long countById_CourseEditionIDAndActiveTrue(CourseEditionIDDataModel courseEditionId);

    Optional<CourseEditionEnrolmentDataModel> findById_StudentIDAndId_CourseEditionID(StudentIDDataModel studentId, CourseEditionIDDataModel courseEditionId);

}
