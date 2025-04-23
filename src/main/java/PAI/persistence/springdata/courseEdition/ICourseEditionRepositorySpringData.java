package PAI.persistence.springdata.courseEdition;

import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICourseEditionRepositorySpringData extends JpaRepository<CourseEditionDataModel, CourseEditionIDDataModel> {

    List<CourseEditionIDDataModel> findCourseEditionIDByProgrammeEditionIDDataModel(ProgrammeEditionIdDataModel programmeEditionIdDataModel);
}
