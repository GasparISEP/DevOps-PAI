package PAI.domain.studyPlan;

import PAI.VOs.*;

public interface IStudyPlanFactory {

    StudyPlan newStudyPlan_2(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, MaxEcts quantityOfEcts);
}
