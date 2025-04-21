package PAI.mapper.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.mapper.courseInStudyPlanID.ICourseInStudyPlanIDMapper;
import PAI.mapper.programmeEdition.IProgrammeEditionIdMapper;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;

public interface ICourseEditionIDMapper {

    CourseEditionID toDomain (CourseEditionIDDataModel courseEditionIDDataModel, IProgrammeEditionIdMapper programmeEditionIdMapper, ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper) throws Exception;

    CourseEditionIDDataModel toDataModel (CourseEditionID courseEditionID) throws Exception;
}
