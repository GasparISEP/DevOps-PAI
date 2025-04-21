package PAI.mapper.courseEdition;

import PAI.domain.CourseEdition;
import PAI.factory.ICourseEditionFactory;
import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;

public class CourseEditionMapperImpl implements ICourseEditionMapper {

    @Override
    public CourseEdition toDomain(CourseEditionDataModel courseEditionDataModel, ICourseEditionFactory courseEditionFactory) {
        return null;
    }

    @Override
    public CourseEditionDataModel toDataModel(CourseEdition courseEdition) {
        return null;
    }
}
