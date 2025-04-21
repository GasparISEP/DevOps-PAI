package PAI.mapper.courseEdition;

import PAI.domain.CourseEdition;
import PAI.factory.ICourseEditionFactory;
import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;

public interface ICourseEditionMapper {

    CourseEdition toDomain (CourseEditionDataModel courseEditionDataModel, ICourseEditionFactory courseEditionFactory);

    CourseEditionDataModel toDataModel (CourseEdition courseEdition);
}
