package PAI.domain.studyPlan;

import PAI.VOs.*;

public class StudyPlanFactoryImpl implements IStudyPlanFactory {

    public StudyPlan newStudyPlan_2(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, MaxEcts quantityOfEcts) {
        return new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts);
    }
}