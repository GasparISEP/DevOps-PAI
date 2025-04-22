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
import org.springframework.stereotype.Component;

@Component
public class CourseEditionMapperImpl implements ICourseEditionMapper {

    private final ICourseEditionIDMapper _courseEditionIDMapper;
    private final IProgrammeEditionIdMapper _programmeEditionIdMapper;
    private final ICourseInStudyPlanIDMapper _courseInStudyPlanIDMapper;
    private final ICourseEditionFactory _courseEditionFactory;

    public CourseEditionMapperImpl(ICourseEditionIDMapper courseEditionIDMapper, IProgrammeEditionIdMapper programmeEditionIdMapper, ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper, ICourseEditionFactory courseEditionFactory) {

        if (courseEditionIDMapper == null)
            throw new IllegalArgumentException("courseEditionIDMapper cannot be null");
        if (programmeEditionIdMapper == null)
            throw new IllegalArgumentException("programmeEditionIdMapper cannot be null");
        if (courseInStudyPlanIDMapper == null)
            throw new IllegalArgumentException("courseInStudyPlanIDMapper cannot be null");
        if (courseEditionFactory == null)
            throw new IllegalArgumentException("courseEditionFactory cannot be null");

        _courseEditionIDMapper = courseEditionIDMapper;
        _programmeEditionIdMapper = programmeEditionIdMapper;
        _courseInStudyPlanIDMapper = courseInStudyPlanIDMapper;
        _courseEditionFactory = courseEditionFactory;
    }

    @Override
    public CourseEdition toDomain(CourseEditionDataModel courseEditionDataModel) throws Exception {

        if (courseEditionDataModel == null)
            throw new IllegalArgumentException("courseEditionDataModel cannot be null");

        CourseEditionID courseEditionID = _courseEditionIDMapper.toDomain(courseEditionDataModel.getCourseEditionIDDataModel());
        ProgrammeEditionID programmeEditionID = _programmeEditionIdMapper.toDomain(courseEditionDataModel.getProgrammeEditionIDDataModel());
        CourseInStudyPlanID courseInStudyPlanID = _courseInStudyPlanIDMapper.toDomain(courseEditionDataModel.getCourseInStudyPlanIDDataModel());
        CourseEdition result = _courseEditionFactory.newCourseEdition_2(courseEditionID, courseInStudyPlanID, programmeEditionID);

        return result;
    }

    @Override
    public CourseEditionDataModel toDataModel(CourseEdition courseEdition) throws Exception{

        if (courseEdition == null)
            throw new IllegalArgumentException("courseEdition cannot be null");

        CourseEditionIDDataModel courseEditionIDDataModel = _courseEditionIDMapper.toDataModel(courseEdition.identity());
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = _programmeEditionIdMapper.toDataModel(courseEdition.getProgrammeEditionID());
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = _courseInStudyPlanIDMapper.toDataModel(courseEdition.getCourseInStudyPlanID());

        CourseEditionDataModel result = new CourseEditionDataModel(courseEditionIDDataModel, programmeEditionIdDataModel, courseInStudyPlanIDDataModel);
        return result;
    }
}
