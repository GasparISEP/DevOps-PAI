package PAI.persistence.springdata.courseEdition;

import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseEditionRepositorySpringData extends JpaRepository<CourseEditionDataModel, CourseEditionIDDataModel> {

}
