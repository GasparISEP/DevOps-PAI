package PAI.mapper.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.CourseEdition;
import PAI.factory.ICourseEditionFactory;
import PAI.mapper.courseInStudyPlan.ICourseInStudyPlanIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;

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
        if (courseEditionFactory == null)
            throw new IllegalArgumentException("courseEditionFactory cannot be null");

        CourseEditionID cEID = _courseEditionIDMapper.toDomain(courseEditionDataModel.getCourseEditionIDDataModel());
        ProgrammeEditionID pEID = _programmeEditionIdMapper.toDomain(courseEditionDataModel.getProgrammeEditionIDDataModel());
        CourseInStudyPlanID cISPID = _courseInStudyPlanIDMapper.toDomain(courseEditionDataModel.getCourseInStudyPlanIDDataModel());
        CourseEdition result = courseEditionFactory.newCourseEdition_2(cEID, cISPID, pEID);

        return result;
    }

    @Override
    public CourseEditionDataModel toDataModel(CourseEdition courseEdition) throws Exception{

        if (courseEdition == null)
            throw new IllegalArgumentException("courseEdition cannot be null");

        CourseEditionIDDataModel cEIDDM = _courseEditionIDMapper.toDataModel(courseEdition.identity());
        ProgrammeEditionIdDataModel pEIDDM = _programmeEditionIdMapper.toDataModel(courseEdition.getProgrammeEditionID());
        CourseInStudyPlanIDDataModel cISPIDDM = _courseInStudyPlanIDMapper.toDataModel(courseEdition.getCourseInStudyPlanID());

        CourseEditionDataModel result = new CourseEditionDataModel(cEIDDM, pEIDDM, cISPIDDM);
        return result;
    }
}
