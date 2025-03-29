package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.DurationInYears;
import PAI.VOs.ProgrammeID;
import PAI.domain.StudyPlan_2;

public class StudyPlanFactoryImpl_2 implements IStudyPlanFactory_2 {

    public StudyPlan_2 newStudyPlan_2(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears) throws Exception {
        return new StudyPlan_2(programmeID, implementationDate, durationInYears);
    }
}