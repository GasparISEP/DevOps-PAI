package PAI.mapper.studyPlan;

import PAI.domain.studyPlan.StudyPlan;
import PAI.persistence.datamodel.studyPlan.StudyPlanDataModel;

public interface IStudyPlanMapper {

    StudyPlanDataModel toDataModel(StudyPlan studyPlan);

    StudyPlan toDomain(StudyPlanDataModel studyPlanDataModel) throws Exception;

}
