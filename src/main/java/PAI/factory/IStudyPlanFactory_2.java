package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.DurationInYears;
import PAI.VOs.ProgrammeID;
import PAI.domain.StudyPlan_2;

public interface IStudyPlanFactory_2 {

    StudyPlan_2 newStudyPlan_2(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears) throws Exception;
}
