package PAI.domain.studyPlan;

import PAI.VOs.Date;
import PAI.VOs.DurationInYears;
import PAI.VOs.ProgrammeID;
import PAI.VOs.QuantEcts;

public class StudyPlanFactoryImpl implements IStudyPlanFactory {

    public StudyPlan newStudyPlan_2(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, QuantEcts quantityOfEcts) {
        return new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts);
    }
}