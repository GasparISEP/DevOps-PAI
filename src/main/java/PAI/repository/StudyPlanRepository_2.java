package PAI.repository;

import PAI.VOs.*;
import PAI.domain.CourseInStudyPlan_2;
import PAI.domain.StudyPlan_2;
import PAI.factory.IStudyPlanFactory_2;
import PAI.factory.IStudyPlanListFactory_2;

import java.util.List;
import java.util.Optional;

public class StudyPlanRepository_2 {

    private final IStudyPlanFactory_2 _studyPlanFactory_2;
    private final List<StudyPlan_2> _studyPlanList_2;

    public StudyPlanRepository_2(IStudyPlanFactory_2 iStudyPlanFactory_2, IStudyPlanListFactory_2 iStudyPlanListFactory_2) {

        _studyPlanFactory_2 = iStudyPlanFactory_2;
        _studyPlanList_2 = iStudyPlanListFactory_2.newArrayList();

    }

    public boolean createStudyPlan_2(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears)  throws Exception {

        StudyPlan_2 studyPlan_2 = _studyPlanFactory_2.newStudyPlan_2(programmeID, implementationDate, durationInYears);

        if (_studyPlanList_2.contains(studyPlan_2))
            return false;

        _studyPlanList_2.add(studyPlan_2);
        return true;
    }

    public List<StudyPlan_2> getAllStudyPlans_2() {
        return this._studyPlanList_2;
    }

    public Optional<StudyPlan_2> findByStudyPlanID(StudyPlanID studyPlanID) {
        return _studyPlanList_2.stream()
                .filter(studyPlan_2 -> studyPlan_2.getStudyPlanID().equals(studyPlanID))
                .findFirst();
    }
}

