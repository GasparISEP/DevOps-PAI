package PAI.mapper.courseInStudyPlan;

import PAI.VOs.CourseInStudyPlanGeneratedID;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanGeneratedIDDataModel;

public interface ICourseInStudyPlanGeneratedIDMapper {

    CourseInStudyPlanGeneratedID toDomain(CourseInStudyPlanGeneratedIDDataModel dataModel);

    CourseInStudyPlanGeneratedIDDataModel toDataModel(CourseInStudyPlanGeneratedID id);
}
