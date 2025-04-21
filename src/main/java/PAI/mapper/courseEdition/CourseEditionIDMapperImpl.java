package PAI.mapper.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.mapper.courseInStudyPlanID.ICourseInStudyPlanIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionDataModel;
import org.springframework.stereotype.Service;

@Service
public class CourseEditionIDMapperImpl implements ICourseEditionIDMapper {

    private final IProgrammeEditionIdMapper _programmeEditionIdMapper;
    private final ICourseInStudyPlanIDMapper _courseInStudyPlanIDMapper;

    public CourseEditionIDMapperImpl(IProgrammeEditionIdMapper programmeEditionIdMapper, ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper) {
        if (programmeEditionIdMapper == null)
            throw new IllegalArgumentException("ProgrammeEditionIdMapper cannot be null");
        if (courseInStudyPlanIDMapper == null)
            throw new IllegalArgumentException("CourseInStudyPlanIDMapper cannot be null");

        this._programmeEditionIdMapper = programmeEditionIdMapper;
        this._courseInStudyPlanIDMapper = courseInStudyPlanIDMapper;
    }

    @Override
    public CourseEditionID toDomain(CourseEditionIDDataModel courseEditionIDDataModel) throws Exception {
        if (courseEditionIDDataModel == null)
            throw new IllegalArgumentException("CourseEditionIDDataModel cannot be null");


        ProgrammeEditionID pEID = _programmeEditionIdMapper.dataModelToDomain(courseEditionIDDataModel.getProgrammeEditionIDDataModel());
        CourseInStudyPlanID cISPID = _courseInStudyPlanIDMapper.toDomain(courseEditionIDDataModel.getCourseInStudyPlanIDDataModel());
        return new CourseEditionID(pEID, cISPID);
    }

    @Override
    public CourseEditionIDDataModel toDataModel(CourseEditionID courseEditionID) throws Exception {
        return null;
    }
}
