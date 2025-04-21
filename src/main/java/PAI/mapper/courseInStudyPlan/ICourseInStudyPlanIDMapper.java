package PAI.mapper.courseInStudyPlan;

import PAI.VOs.CourseInStudyPlanID;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;

public interface ICourseInStudyPlanIDMapper {

    CourseInStudyPlanID toDomain(CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel);

    CourseInStudyPlanIDDataModel toDataModel(CourseInStudyPlanID courseInStudyPlanID);
}