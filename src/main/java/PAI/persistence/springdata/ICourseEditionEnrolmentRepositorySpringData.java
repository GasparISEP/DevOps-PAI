package PAI.persistence.springdata;

import PAI.persistence.datamodel.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.CourseEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ICourseEditionEnrolmentRepositorySpringData extends JpaRepository<CourseEditionEnrolmentDataModel, CourseEditionEnrolmentIDDataModel> {

    boolean existsById_StudentIDAndId_CourseEditionIDAndActiveTrue(StudentIDDataModel studentID, CourseEditionIDDataModel courseEditionID);

    long countById_CourseEditionIDAndActiveIsTrue(CourseEditionIDDataModel courseEditionId);

    Optional<CourseEditionEnrolmentDataModel> findById_StudentIDAndId_CourseEditionID(StudentIDDataModel studentId, CourseEditionIDDataModel courseEditionId);

}
