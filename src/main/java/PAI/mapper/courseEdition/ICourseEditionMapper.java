package PAI.mapper.courseEdition;

import PAI.domain.CourseEdition;
import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;

public interface ICourseEditionMapper {

    CourseEdition toDomain (CourseEditionDataModel courseEditionDataModel);

    CourseEditionDataModel toDataModel (CourseEdition courseEdition);
}
