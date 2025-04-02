package PAI.repository.studyPlanRepo;

import PAI.VOs.*;
import PAI.domain.studyPlan.StudyPlanDDD;
import PAI.domain.studyPlan.IStudyPlanDDDFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudyPlanDDDRepository {

    private final IStudyPlanDDDFactory _studyPlanFactory_2;
    private final List<StudyPlanDDD> _studyPlanList_2;

    public StudyPlanDDDRepository(IStudyPlanDDDFactory iStudyPlanFactory_DDD, IStudyPlanDDDListFactory iStudyPlanDDDListFactory) {

        _studyPlanFactory_2 = iStudyPlanFactory_DDD;
        _studyPlanList_2 = iStudyPlanDDDListFactory.newArrayList();

    }

    public boolean createStudyPlan_2(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, QuantEcts quantityOfEcts)  throws Exception {

        StudyPlanDDD studyPlan_DDD = _studyPlanFactory_2.newStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEcts);

        if (_studyPlanList_2.contains(studyPlan_DDD))
            return false;

        _studyPlanList_2.add(studyPlan_DDD);
        return true;
    }

    public List<StudyPlanDDD> getAllStudyPlans_2() {
        return this._studyPlanList_2;
    }

    public Optional<StudyPlanDDD> findStudyPlanByID(StudyPlanID studyPlanID) {
        for (StudyPlanDDD studyPlanDDD : _studyPlanList_2) {
            if (studyPlanDDD.getStudyPlanID().equals(studyPlanID));
            return Optional.of(studyPlanDDD);
        }
        return Optional.empty();
    }

    public List<StudyPlanDDD> getAllStudyPlansByProgrammeId(ProgrammeID programmeID) {
        List<StudyPlanDDD> studyPlanDDDList = new ArrayList<>();
        for (StudyPlanDDD studyPlanDDD : _studyPlanList_2) {
            ProgrammeID programmeIDInStudyPlan = studyPlanDDD.getProgrammeID();
            if(programmeIDInStudyPlan.equals(programmeID)) {
                studyPlanDDDList.add(studyPlanDDD);
            }
        }
        return studyPlanDDDList;
    }
}