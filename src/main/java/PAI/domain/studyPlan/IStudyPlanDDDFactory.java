package PAI.domain.studyPlan;

import PAI.VOs.Date;
import PAI.VOs.DurationInYears;
import PAI.VOs.ProgrammeID;

public interface IStudyPlanDDDFactory {

    StudyPlanDDD newStudyPlan_2(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears) throws Exception;
}
