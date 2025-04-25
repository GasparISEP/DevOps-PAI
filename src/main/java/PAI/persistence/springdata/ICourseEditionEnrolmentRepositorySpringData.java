package PAI.persistence.springdata;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.persistence.datamodel.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.CourseEditionEnrolmentIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseEditionEnrolmentRepositorySpringData extends JpaRepository<CourseEditionEnrolmentDataModel, CourseEditionEnrolmentIDDataModel> {

    boolean existsById_StudentIDAndId_CourseEditionIDAndIsActiveTrue(StudentID studentID, CourseEditionID courseEditionID);
}
