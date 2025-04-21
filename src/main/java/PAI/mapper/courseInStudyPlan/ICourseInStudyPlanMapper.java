package PAI.mapper.courseInStudyPlan;

import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanDataModel;

public interface ICourseInStudyPlanMapper {

    CourseInStudyPlan toDomain(CourseInStudyPlanDataModel courseInStudyPlanDataModel);

    CourseInStudyPlanDataModel toDataModel(CourseInStudyPlan courseInStudyPlan);
}
