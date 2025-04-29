package PAI.domain.studyPlan;

import PAI.VOs.*;

public class StudyPlanFactoryImpl implements IStudyPlanFactory {

    public StudyPlan createStudyPlan(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, MaxEcts quantityOfEcts) {

        StudyPlanID studyPlanID = new StudyPlanID(programmeID, implementationDate);

        return new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);
    }

    public StudyPlan createStudyPlanFromDataModel(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, MaxEcts quantityOfEcts, StudyPlanID studyPlanID) {

        return new StudyPlan(programmeID, implementationDate, durationInYears, quantityOfEcts, studyPlanID);
    }
}