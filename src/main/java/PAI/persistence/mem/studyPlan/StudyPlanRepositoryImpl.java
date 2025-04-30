package PAI.persistence.mem.studyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.StudyPlan;
import PAI.repository.studyPlanRepository.IStudyPlanRepository;

import java.util.List;
import java.util.Optional;

public class StudyPlanRepositoryImpl implements IStudyPlanRepository {

    private final List<StudyPlan> _studyPlanList_2;

    public StudyPlanRepositoryImpl(IStudyPlanListFactory iStudyPlanListFactory) {

        _studyPlanList_2 = iStudyPlanListFactory.newArrayList();

    }

    public List<StudyPlan> getAllStudyPlansList() {
        return _studyPlanList_2;
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
        for (StudyPlan existingStudyPlan: _studyPlanList_2) {
            if (existingStudyPlan.identity().equals(id)) {
                return true;
            }
        }
        return false;
    }
}