package PAI.mapper.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
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

        if (courseEditionIDMapper == null)
            throw new IllegalArgumentException("courseEditionIDMapper cannot be null");
        if (programmeEditionIdMapper == null)
            throw new IllegalArgumentException("programmeEditionIdMapper cannot be null");
        if (courseInStudyPlanIDMapper == null)
            throw new IllegalArgumentException("courseInStudyPlanIDMapper cannot be null");

        _courseEditionIDMapper = courseEditionIDMapper;
        _programmeEditionIdMapper = programmeEditionIdMapper;
        _courseInStudyPlanIDMapper = courseInStudyPlanIDMapper;
    }

    @Override
    public CourseEdition toDomain(CourseEditionDataModel courseEditionDataModel, ICourseEditionFactory courseEditionFactory) throws Exception {

        if (courseEditionDataModel == null)
            throw new IllegalArgumentException("courseEditionDataModel cannot be null");

        CourseEditionID cEID = _courseEditionIDMapper.toDomain(courseEditionDataModel.getCourseEditionIDDataModel());
        ProgrammeEditionID pEID = _programmeEditionIdMapper.dataModelToDomain(courseEditionDataModel.getProgrammeEditionIDDataModel());
        CourseInStudyPlanID cISPID = _courseInStudyPlanIDMapper.toDomain(courseEditionDataModel.getCourseInStudyPlanIDDataModel());
        CourseEdition result = courseEditionFactory.newCourseEdition_2(cEID, cISPID, pEID);

        return result;
    }

    @Override
    public CourseEditionDataModel toDataModel(CourseEdition courseEdition) {
        return null;
    }
}
