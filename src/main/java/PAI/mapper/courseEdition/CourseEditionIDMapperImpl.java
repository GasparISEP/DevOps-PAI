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

    @Override
    public CourseEditionID toDomain(CourseEditionIDDataModel courseEditionIDDataModel, IProgrammeEditionIdMapper programmeEditionIdMapper, ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper) throws Exception {
        if (courseEditionIDDataModel == null)
            throw new IllegalArgumentException("CourseEditionIDDataModel cannot be null");
        if (programmeEditionIdMapper == null)
            throw new IllegalArgumentException("ProgrammeEditionIdMapper cannot be null");
        if (courseInStudyPlanIDMapper == null)
            throw new IllegalArgumentException("CourseInStudyPlanIDMapper cannot be null");

        ProgrammeEditionID pEID = programmeEditionIdMapper.dataModelToDomain(courseEditionIDDataModel.getProgrammeEditionIDDataModel());
        CourseInStudyPlanID cISPID = courseInStudyPlanIDMapper.toDomain(courseEditionIDDataModel.getCourseInStudyPlanIDDataModel());
        return new CourseEditionID(pEID, cISPID);
    }

    @Override
    public CourseEditionIDDataModel toDataModel(CourseEditionID courseEditionID) throws Exception {
        return null;
    }
}
