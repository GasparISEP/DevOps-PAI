package PAI.service.studyPlan;

import PAI.VOs.*;
import PAI.assembler.studyPlan.IStudyPlanAssembler;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.programme.Programme;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.studyPlan.IStudyPlanFactory;
import PAI.domain.studyPlan.StudyPlan;
import PAI.domain.repositoryInterfaces.studyPlan.IStudyPlanRepository;
import PAI.dto.studyPlan.RegisterStudyPlanCommand;
import PAI.dto.studyPlan.StudyPlanDTO;
import PAI.exception.BusinessRuleViolationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudyPlanServiceImpl implements IStudyPlanService {

    private final IStudyPlanRepository _repository;
    private final IStudyPlanFactory _factory;
    private final IStudyPlanAssembler _assembler;
    private final IProgrammeRepository _programmeRepository;
    private final IDegreeTypeRepository _degreeTypeRepository;

    public StudyPlanServiceImpl (IStudyPlanRepository repository, IStudyPlanFactory factory,
                                 IStudyPlanAssembler assembler, IProgrammeRepository programmeRepository,
                                 IDegreeTypeRepository degreeTypeRepository) {

        isObjectNull(repository, factory, assembler, programmeRepository, degreeTypeRepository);

        this._repository = repository;
        this._factory = factory;
        this._assembler = assembler;
        this._programmeRepository = programmeRepository;
        this._degreeTypeRepository = degreeTypeRepository;
    }

    private void isObjectNull(IStudyPlanRepository repository, IStudyPlanFactory factory,
                                           IStudyPlanAssembler assembler, IProgrammeRepository programmeRepository,
                                           IDegreeTypeRepository degreeTypeRepository){
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        if (factory == null) {
            throw new IllegalArgumentException("Factory cannot be null");
        }
        if (assembler == null) {
            throw new IllegalArgumentException("Assembler cannot be null");
        }
        if (programmeRepository == null) {
            throw new IllegalArgumentException("Programme repository cannot be null");
        }
        if (degreeTypeRepository == null) {
            throw new IllegalArgumentException("Degree type repository cannot be null");
        }
    }

    public StudyPlanDTO createStudyPlan(RegisterStudyPlanCommand studyPlanCommand) throws Exception {
        Programme programme = getProgrammeAndValidate(studyPlanCommand.getProgrammeId());
        DegreeType degreeType = getDegreeTypeAndValidate(programme.getDegreeTypeID());

        StudyPlan studyPlan = _factory.createStudyPlan(programme.getProgrammeID(), studyPlanCommand.getStartDate(),
                                                        programme.getQuantSemesters(), degreeType.getMaxEcts());

        if (_repository.containsOfIdentity(studyPlan.identity())) {
            throw new BusinessRuleViolationException("Study plan already exists");
        }

        _repository.save(studyPlan);

        return _assembler.toDTO(studyPlan);
    }

    private Programme getProgrammeAndValidate(ProgrammeID programmeId){
        return _programmeRepository.ofIdentity(programmeId).
                orElseThrow(() -> new EntityNotFoundException("The programme acronym '" + programmeId.getProgrammeAcronym() + "' does not exist"));
    }

    private DegreeType getDegreeTypeAndValidate(DegreeTypeID degreeTypeID){
        return _degreeTypeRepository.ofIdentity(degreeTypeID).
                orElseThrow(() -> new EntityNotFoundException("Degree type does not exist"));
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

    public Optional<StudyPlan> findByGeneratedUUID(StudyPlanGeneratedID id) {
        return _repository.findByGeneratedID(id);
    }
}