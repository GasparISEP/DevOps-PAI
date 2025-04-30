package PAI.persistence.springdata.course;

import PAI.persistence.datamodel.course.CourseDataModel;
import PAI.persistence.datamodel.course.CourseIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepositorySpringData extends JpaRepository<CourseDataModel, CourseIDDataModel> {

    boolean existsByAcronym(String courseAcronym);

    boolean existsByName(String courseName);

}
