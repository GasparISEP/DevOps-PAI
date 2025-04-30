package PAI.mapper.studyPlan;

import PAI.VOs.StudyPlanID;
import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;

public interface IStudyPlanIDMapper {

    StudyPlanIDDataModel toDataModel(StudyPlanID studyPlanID);

    StudyPlanID toDomain(StudyPlanIDDataModel studyPlanIDDataModel);
}