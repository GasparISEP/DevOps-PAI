package PAI.domain.studyPlan;

import PAI.VOs.Date;
import PAI.VOs.DurationInYears;
import PAI.VOs.ProgrammeID;
import PAI.VOs.QuantEcts;

public class StudyPlanDDDFactoryImpl implements IStudyPlanDDDFactory {

    public StudyPlanDDD newStudyPlan_2(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, QuantEcts quantityOfEcts) throws Exception {
        return new StudyPlanDDD(programmeID, implementationDate, durationInYears, quantityOfEcts);
    }
}