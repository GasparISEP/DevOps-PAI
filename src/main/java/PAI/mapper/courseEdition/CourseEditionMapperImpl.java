package PAI.mapper.courseEdition;

import PAI.domain.CourseEdition;
import PAI.factory.ICourseEditionFactory;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;

public class CourseEditionMapperImpl implements ICourseEditionMapper {

    private final ICourseEditionIDMapper _courseEditionIDMapper;
    private final IProgrammeEditionIdMapper _programmeEditionIdMapper;
    private final ICourseInStudyPlanIDMapper _courseInStudyPlanIDMapper;

    public CourseEditionMapperImpl(ICourseEditionIDMapper courseEditionIDMapper, IProgrammeEditionIdMapper programmeEditionIdMapper, ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper) {
        _courseEditionIDMapper = courseEditionIDMapper;
        _programmeEditionIdMapper = programmeEditionIdMapper;
        _courseInStudyPlanIDMapper = courseInStudyPlanIDMapper;
    }

    @Override
    public CourseEdition toDomain(CourseEditionDataModel courseEditionDataModel, ICourseEditionFactory courseEditionFactory) {
        return null;
    }

    @Override
    public CourseEditionDataModel toDataModel(CourseEdition courseEdition) {
        return null;
    }
}
