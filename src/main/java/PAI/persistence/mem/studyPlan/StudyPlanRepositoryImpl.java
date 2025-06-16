package PAI.persistence.mem.studyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.StudyPlan;
import PAI.domain.repositoryInterfaces.studyPlan.IStudyPlanRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private List<StudyPlan> findByProgrammeID(ProgrammeID programmeID) {
        return _studyPlanList_2.stream()
                .filter(sp -> sp.identity().getProgrammeID().equals(programmeID))
                .collect(Collectors.toList());
    }

    @Override
    public StudyPlanID findLatestByProgrammeID(ProgrammeID programmeID) {
        List<StudyPlan> list = findByProgrammeID(programmeID);
        if (list.isEmpty()) {
            throw new IllegalArgumentException("No study plans found for given ProgrammeID");
        }
        return list.getLast().identity();
    }
}