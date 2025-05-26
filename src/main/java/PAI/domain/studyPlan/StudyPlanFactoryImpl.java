package PAI.domain.studyPlan;

import PAI.VOs.*;
import org.springframework.stereotype.Component;

@Component
public class StudyPlanFactoryImpl implements IStudyPlanFactory {

    public StudyPlan createStudyPlan(ProgrammeID programmeID, Date startDate, QuantSemesters quantSemesters, MaxEcts maxEcts) throws Exception {

        StudyPlanID studyPlanID = new StudyPlanID(programmeID, startDate);
        DurationInYears durationInYears = new DurationInYears(quantSemesters.getQuantityOfSemesters());

        return new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID);
    }

    public StudyPlan createStudyPlanFromDataModel(ProgrammeID programmeID, Date startDate, DurationInYears durationInYears, MaxEcts maxEcts, StudyPlanID studyPlanID) {

        return new StudyPlan(programmeID, startDate, durationInYears, maxEcts, studyPlanID);
    }
}