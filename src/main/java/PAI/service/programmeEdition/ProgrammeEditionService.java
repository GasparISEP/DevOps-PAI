package PAI.service.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.assembler.programmeEdition.IProgrammeEditionServiceAssembler;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.dto.programmeEdition.CountStudentsRequestDto;
import PAI.dto.programmeEdition.ProgrammeEditionRequestServiceDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseServiceDTO;
import PAI.service.schoolYear.ISchoolYearService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.springframework.cglib.core.CollectionUtils.filter;

@Service
public class ProgrammeEditionService implements IProgrammeEditionService {
    private final IProgrammeEditionFactory programmeEditionFactory;
    private final IProgrammeEditionRepository programmeEditionRepository;
    private final IProgrammeRepository programmeRepository;
    private final IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository;
    private final IProgrammeEditionServiceAssembler programmeEditionAssembler;
    private final ISchoolYearService schoolYearService;

    public ProgrammeEditionService (IProgrammeEditionFactory programmeEditionFactory,
                                    IProgrammeEditionRepository programmeEditionRepository,
                                    IProgrammeRepository programmeRepository,
                                    IProgrammeEditionEnrolmentRepository programmeEditionEnrolmentRepository,
                                    IProgrammeEditionServiceAssembler programmeEditionAssembler, ISchoolYearService schoolYearService) {
        if(programmeEditionFactory == null){
            throw new IllegalArgumentException("ProgrammeEditionFactory cannot be null!");
        }
        this.programmeEditionFactory = programmeEditionFactory;

        if (programmeEditionRepository == null) {
            throw new IllegalArgumentException("ProgrammeEditionRepository cannot be null!");
        }
        this.programmeEditionRepository = programmeEditionRepository;

        if (programmeRepository == null) {
            throw new IllegalArgumentException("ProgrammeRepository cannot be null!");
        }
        this.programmeRepository = programmeRepository;

        if (programmeEditionEnrolmentRepository == null) {
            throw new IllegalArgumentException("ProgrammeEditionEnrolmentRepository cannot be null!");
        }
        this.programmeEditionEnrolmentRepository = programmeEditionEnrolmentRepository;

        if (programmeEditionAssembler == null) {
            throw new IllegalArgumentException("ProgrammeEditionAssembler cannot be null!");
        }
        this.programmeEditionAssembler = programmeEditionAssembler;

        if(schoolYearService == null) {
            throw new IllegalArgumentException("SchoolYearService cannot be null!");
        }
        this.schoolYearService = schoolYearService;
    }

    @Override
    public ProgrammeEdition createProgrammeEdition(ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception {
        if (programmeID == null) {
            throw new IllegalArgumentException("ProgrammeID cannot be null!");
        }
        if (schoolYearID == null) {
            throw new IllegalArgumentException("SchoolYearID cannot be null!");
        }
        return this.programmeEditionFactory.createProgrammeEdition(programmeID, schoolYearID);
    }

    @Override
    public Optional<ProgrammeEdition> saveProgrammeEdition(ProgrammeEdition programmeEdition) {
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
    public List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeID) {
        if (programmeID == null) {
            return List.of();
        }

        return programmeEditionRepository.getProgrammeEditionsByProgrammeID(programmeID);
    }

    @Override
    public List<ProgrammeEdition> findAllProgrammeEditions() {
        Iterable<ProgrammeEdition> iterable = programmeEditionRepository.findAll();
        List<ProgrammeEdition> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
    @Override
    public int countTotalNumberOfStudentsInAProgrammeEdition(CountStudentsRequestDto programmeEditionDTO) throws Exception {
        ProgrammeEdition programmeEdition = programmeEditionAssembler.countStudentsInProgrammeEditionDTOtoDomain(programmeEditionDTO);
        List<ProgrammeEditionEnrolment> allProgrammeEditionEnrolment = programmeEditionEnrolmentRepository.getAllProgrammeEditionsEnrollmentByProgrammeEditionID(programmeEdition.identity());
        return allProgrammeEditionEnrolment.size();
    }

    @Override
    public ProgrammeEditionResponseServiceDTO createProgrammeEditionAndSave(ProgrammeEditionRequestServiceDTO programmeEditionDTO) throws Exception {
        ProgrammeID programmeID = programmeEditionAssembler.toProgrammeID(programmeEditionDTO);
        SchoolYearID schoolYearID = schoolYearService.getCurrentSchoolYearID().orElseThrow(() -> new IllegalArgumentException("No current School Year found."));

        if (!programmeRepository.containsOfIdentity(programmeID)) {
            throw new IllegalArgumentException("Programme does not exist.");
        }

        ProgrammeEdition programmeEdition = programmeEditionFactory.createProgrammeEdition(programmeID, schoolYearID);
        Optional<ProgrammeEdition> savedProgramme = this.saveProgrammeEdition(programmeEdition);

        if (savedProgramme.isPresent()) {
            ProgrammeEdition savedEdition = savedProgramme.get();
            ProgrammeEditionID programmeEditionID = savedEdition.identity();

            return programmeEditionAssembler.toServiceResponseDTO(
                    programmeEditionID.getProgrammeID(),
                    programmeEditionID.getSchoolYearID()
            );
        } else {
            throw new IllegalArgumentException("ProgrammeEdition already registered.");
        }
    }

    @Override
    public List<ProgrammeEditionResponseServiceDTO> getProgrammeEditionIDsByProgrammeID(ProgrammeEditionRequestServiceDTO requestDTO) {

        ProgrammeID programmeID = programmeEditionAssembler.toProgrammeID(requestDTO);

        Iterable<ProgrammeEdition> allEditions = programmeEditionRepository.findAll();

        return StreamSupport.stream(allEditions.spliterator(), false)
                .filter(p -> p.identity().getProgrammeID().equals(programmeID))
                .map(p -> {
                    ProgrammeEditionID id = p.identity();
                    return programmeEditionAssembler.toServiceResponseDTO(id.getProgrammeID(), id.getSchoolYearID());
                })
                .toList();
    }
}
