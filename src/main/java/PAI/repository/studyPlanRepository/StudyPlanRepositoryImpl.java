package PAI.repository.studyPlanRepository;

import PAI.VOs.*;
import PAI.domain.studyPlan.StudyPlan;
import PAI.domain.studyPlan.IStudyPlanFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudyPlanRepositoryImpl implements IStudyPlanRepository {

    private final IStudyPlanFactory _studyPlanFactory_2;
    private final List<StudyPlan> _studyPlanList_2;

    public StudyPlanRepositoryImpl(IStudyPlanFactory iStudyPlanFactory_DDD, IStudyPlanListFactory iStudyPlanListFactory) {

        _studyPlanFactory_2 = iStudyPlanFactory_DDD;
        _studyPlanList_2 = iStudyPlanListFactory.newArrayList();

    }

    public boolean createStudyPlan_2(ProgrammeID programmeID, Date implementationDate, DurationInYears durationInYears, MaxEcts quantityOfEcts) {

        StudyPlan studyPlan_DDD = _studyPlanFactory_2.newStudyPlan_2(programmeID, implementationDate, durationInYears, quantityOfEcts);

        if (_studyPlanList_2.contains(studyPlan_DDD))
            return false;

        _studyPlanList_2.add(studyPlan_DDD);
        return true;
    }

    public List<StudyPlan> getAllStudyPlans_2() {
        return this._studyPlanList_2;
    }

    @Override
    public StudyPlanID getLatestStudyPlanIDByProgrammeID(ProgrammeID programmeID) {
        List<StudyPlan> list = getAllStudyPlansByProgrammeId(programmeID);
        StudyPlanID studyPlanID = list.getLast().identity();

        return studyPlanID;
    }

    public List<StudyPlan> getAllStudyPlansByProgrammeId(ProgrammeID programmeID) {
        List<StudyPlan> studyPlanList = new ArrayList<>();
        for (StudyPlan studyPlan : _studyPlanList_2) {
            ProgrammeID programmeIDInStudyPlan = studyPlan.getProgrammeID();
            if(programmeIDInStudyPlan.equals(programmeID)) {
                studyPlanList.add(studyPlan);
            }
        }
        return studyPlanList;
    }

    @Override
    public StudyPlan save(StudyPlan studyPlan) {
        _studyPlanList_2.add(studyPlan);
        return studyPlan;
    }

    @Override
    public Iterable<StudyPlan> findAll() {
        return _studyPlanList_2;
    }


    @Override
    public Optional<StudyPlan> ofIdentity(StudyPlanID id) {
        for (StudyPlan existingStudyPlan : _studyPlanList_2) {
            if (existingStudyPlan.identity().equals(id)) {
                return Optional.of(existingStudyPlan);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(StudyPlanID id) {
        return ofIdentity(id).isPresent();
    }
}