package PAI.repository.studyPlanRepo;

import PAI.VOs.*;
import PAI.domain.studyPlan.StudyPlanDDD;
import PAI.domain.studyPlan.IStudyPlanDDDFactory;

import java.util.List;
import java.util.Optional;

public class StudyPlanDDDRepository {

    private final IStudyPlanDDDFactory _studyPlanFactory_2;
    private final List<StudyPlanDDD> _studyPlanList_2;

    public StudyPlanDDDRepository(IStudyPlanDDDFactory iStudyPlanFactory_DDD, IStudyPlanDDDListFactory iStudyPlanDDDListFactory) {

        _studyPlanFactory_2 = iStudyPlanFactory_DDD;
        _studyPlanList_2 = iStudyPlanDDDListFactory.newArrayList();

    }

    public boolean createStudyPlan_2(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears)  throws Exception {

        StudyPlanDDD studyPlan_DDD = _studyPlanFactory_2.newStudyPlan_2(programmeID, implementationDate, durationInYears);

        if (_studyPlanList_2.contains(studyPlan_DDD))
            return false;

        _studyPlanList_2.add(studyPlan_DDD);
        return true;
    }

    public List<StudyPlanDDD> getAllStudyPlans_2() {
        return this._studyPlanList_2;
    }

    public Optional<StudyPlanDDD> findByStudyPlanID(StudyPlanID studyPlanID) {
        return _studyPlanList_2.stream()
                .filter(studyPlan_2 -> studyPlan_2.getStudyPlanID().equals(studyPlanID))
                .findFirst();
    }
}

