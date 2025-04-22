package PAI.persistence.springdata;

import PAI.persistence.datamodel.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.CourseEditionEnrolmentIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseEditionEnrolmentRepositorySpringData extends JpaRepository<CourseEditionEnrolmentDataModel, CourseEditionEnrolmentIDDataModel> {
}
