package PAI.service.programmeEdition;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.assembler.programmeEdition.IProgrammeEditionAssembler;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeEditionService implements IProgrammeEditionService {
    private final IProgrammeEditionFactory programmeEditionFactory;
    private final IProgrammeEditionRepository programmeEditionRepository;
    private final IProgrammeRepository programmeRepository;
    private final ISchoolYearRepository schoolYearRepository;
    private final IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository;
    private final IProgrammeEditionAssembler programmeEditionAssembler;

    public ProgrammeEditionService (IProgrammeEditionFactory programmeEditionFactory,
                                    IProgrammeEditionRepository programmeEditionRepository,
                                    IProgrammeRepository programmeRepository,
                                    ISchoolYearRepository schoolYearRepository,
                                    IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository,
                                    IProgrammeEditionAssembler programmeEditionAssembler) {
        if(programmeEditionFactory == null){
            throw new IllegalArgumentException("ProgrammeEditionFactory cannot be null!");
        }
        this.programmeEditionFactory = programmeEditionFactory;

        if (programmeEditionRepository == null) {
            throw new IllegalArgumentException("ProgrammeEditionRepository cannot be null!");
        }
        this.programmeEditionRepository = programmeEditionRepository;

        if(programmeRepository == null){
            throw new IllegalArgumentException("ProgrammeRepository cannot be null!");
        }
        this.programmeRepository = programmeRepository;

        if(schoolYearRepository == null){
            throw new IllegalArgumentException("SchoolYearRepository cannot be null!");
        }
        this.schoolYearRepository = schoolYearRepository;

        if(programmeEditionEnrolmentRepository == null){
            throw new IllegalArgumentException("ProgrammeEditionEnrolmentRepository cannot be null!");
        }
        this.programmeEditionEnrolmentRepository = programmeEditionEnrolmentRepository;

        if(programmeEditionAssembler == null){
            throw new IllegalArgumentException("ProgrammeEditionAssembler cannot be null!");
        }
        this.programmeEditionAssembler = programmeEditionAssembler;
    }

    @Override
    public ProgrammeEdition createProgrammeEdition (ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception {
        if(programmeID == null){
            throw new IllegalArgumentException("ProgrammeID cannot be null!");
        }
        if(schoolYearID == null){
            throw new IllegalArgumentException("SchoolYearID cannot be null!");
        }
        return this.programmeEditionFactory.createProgrammeEdition(programmeID, schoolYearID);
    }

    @Override
    public Optional<ProgrammeEdition> saveProgrammeEdition (ProgrammeEdition programmeEdition) throws Exception {
        if (programmeEdition == null) {
            return Optional.empty();
        }

        boolean isProgrammeEditionAlreadyRegistered = this.programmeEditionRepository.containsOfIdentity(programmeEdition.identity());

        if (!isProgrammeEditionAlreadyRegistered) {
            ProgrammeEdition programmeEditionSaved = this.programmeEditionRepository.save(programmeEdition);

            //If programmeEditionSaved is null return optional empty
            return Optional.ofNullable(programmeEditionSaved);
        }

        return Optional.empty();
    }

    @Override
    public List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeID) throws Exception {
        if (programmeID == null) {
            return List.of();
        }

        return programmeEditionRepository.getProgrammeEditionsByProgrammeID(programmeID);
    }
    @Override
    public Iterable<ProgrammeEdition> findAllProgrammeEditions(){
        return programmeEditionRepository.findAll();
    }

}
