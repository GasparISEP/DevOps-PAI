package PAI.persistence.springdata;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.persistence.datamodel.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.CourseEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ICourseEditionEnrolmentRepositorySpringData extends JpaRepository<CourseEditionEnrolmentDataModel, CourseEditionEnrolmentIDDataModel> {

    boolean existsById_StudentIDAndId_CourseEditionIDAndIsActiveTrue(StudentIDDataModel studentID, CourseEditionIDDataModel courseEditionID);

    long countById_CourseEditionIDAndIsActiveIsTrue(CourseEditionID courseEditionId);

    Optional<CourseEditionEnrolmentDataModel> findById_StudentIDAndId_CourseEditionID(StudentID studentId, CourseEditionID courseEditionId);

    Object existsById_StudentIDAndId_CourseEditionIDAndIsActiveIsTrue(StudentIDDataModel studentIDDataModel, CourseEditionIDDataModel courseEditionIDDataModel);
}
