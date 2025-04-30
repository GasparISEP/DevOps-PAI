package PAI.service.studyPlan;

import PAI.VOs.*;
import PAI.domain.studyPlan.IStudyPlanFactory;
import PAI.domain.studyPlan.StudyPlan;
import PAI.repository.studyPlanRepository.IStudyPlanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudyPlanServiceImpl implements IStudyPlanService {

    private final IStudyPlanRepository _repository;
    private final IStudyPlanFactory _factory;

    public StudyPlanServiceImpl (IStudyPlanRepository repository, IStudyPlanFactory factory) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        this._repository = repository;
        if (factory == null) {
            throw new IllegalArgumentException("Factory cannot be null");
        }
        this._factory = factory;
    }

    public boolean createStudyPlan(ProgrammeID programmeID, Date implementationdate, DurationInYears durationInYears, MaxEcts quantityOfECTS) throws Exception{

        StudyPlan candidate = _factory.createStudyPlan(programmeID, implementationdate, durationInYears, quantityOfECTS);

        if (_repository.containsOfIdentity(candidate.identity())) {
            return false;
        }

        _repository.save(candidate);
        return true;
    }

    public List<StudyPlan> getAllStudyPlans() {

        List<StudyPlan> result = new ArrayList<>();
        for (StudyPlan c : _repository.findAll()) {
            result.add(c);
        }
        return result;
    }

    public List<StudyPlan> getStudyPlansByProgrammeID(ProgrammeID programmeID) {
        List<StudyPlan> result = new ArrayList<>();
        for (StudyPlan c : _repository.findAll()) {
            if (c.identity().getProgrammeID().equals(programmeID)) {
                result.add(c);
            }
        }
        return result;
    }

    public StudyPlanID getLatestStudyPlanIDByProgrammeID(ProgrammeID programmeID) {
        List<StudyPlan> list = getStudyPlansByProgrammeID(programmeID);
        if (list.isEmpty()) {
            throw new IllegalArgumentException("No study plans found for given ProgrammeID");
        }
        return list.getLast().identity();
    }

    public Optional<StudyPlan> findByID(StudyPlanID id) {
        return _repository.ofIdentity(id);
    }
}