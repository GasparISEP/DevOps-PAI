package PAI.domain.studyPlan;

import PAI.VOs.*;

public interface IStudyPlanFactory {

    StudyPlan createStudyPlan(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, MaxEcts quantityOfEcts);

    StudyPlan createStudyPlanFromDataModel(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, MaxEcts quantityOfEcts, StudyPlanID studyPlanID);
}
