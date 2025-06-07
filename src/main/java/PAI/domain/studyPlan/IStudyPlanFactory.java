package PAI.domain.studyPlan;

import PAI.VOs.*;

public interface IStudyPlanFactory {

    StudyPlan createStudyPlan(ProgrammeID programmeID, Date startDate, QuantSemesters quantSemesters, MaxEcts maxEcts) throws Exception;

    StudyPlan createStudyPlanFromDataModel(ProgrammeID programmeID, Date startDate, DurationInYears durationInYears, MaxEcts MaxEcts, StudyPlanID studyPlanID, StudyPlanGeneratedID uuid);
}
