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
import PAI.dto.programmeEdition.RequestServiceDto;
import PAI.dto.programmeEdition.ProgrammeEditionRequestServiceDTO;
import PAI.dto.programmeEdition.ProgrammeEditionResponseServiceDTO;
import PAI.service.programme.IProgrammeService;
import PAI.service.programmeEditionEnrolment.IProgrammeEditionEnrolmentService;
import PAI.service.schoolYear.ISchoolYearService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ProgrammeEditionService implements IProgrammeEditionService {
    private final IProgrammeEditionFactory programmeEditionFactory;
    private final IProgrammeEditionRepository programmeEditionRepository;
    private final IProgrammeService programmeService;
    private final IProgrammeEditionServiceAssembler programmeEditionAssembler;
    private final ISchoolYearService schoolYearService;
    private final IProgrammeEditionEnrolmentService programmeEditionEnrolmentService;

    public ProgrammeEditionService (IProgrammeEditionFactory programmeEditionFactory,
                                    IProgrammeEditionRepository programmeEditionRepository,
                                    IProgrammeService programmeService,
                                    IProgrammeEditionServiceAssembler programmeEditionAssembler,
                                    ISchoolYearService schoolYearService,
                                    IProgrammeEditionEnrolmentService programmeEditionEnrolmentService) {


        if(programmeEditionFactory == null){
            throw new IllegalArgumentException("ProgrammeEditionFactory cannot be null!");
        }
        this.programmeEditionFactory = programmeEditionFactory;

        if (programmeEditionRepository == null) {
            throw new IllegalArgumentException("ProgrammeEditionRepository cannot be null!");
        }
        this.programmeEditionRepository = programmeEditionRepository;

        if (programmeService == null) {
            throw new IllegalArgumentException("ProgrammeService cannot be null!");
        }
        this.programmeService = programmeService;

        if (programmeEditionAssembler == null) {
            throw new IllegalArgumentException("ProgrammeEditionAssembler cannot be null!");
        }
        this.programmeEditionAssembler = programmeEditionAssembler;

        if(schoolYearService == null) {
            throw new IllegalArgumentException("SchoolYearService cannot be null!");
        }
        this.schoolYearService = schoolYearService;

        if (programmeEditionEnrolmentService == null) {
            throw new IllegalArgumentException("ProgrammeEditionEnrolmentService cannot be null!");
        }
        this.programmeEditionEnrolmentService = programmeEditionEnrolmentService;
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
    public List<ProgrammeEditionResponseServiceDTO> findAllProgrammeEditions() {
        Iterable<ProgrammeEdition> iterable = programmeEditionRepository.findAll();

        List<ProgrammeEditionResponseServiceDTO> list = new ArrayList<>();

        for (ProgrammeEdition programmeEdition : iterable) {
            ProgrammeEditionResponseServiceDTO dto = programmeEditionAssembler.toServiceResponseDTOFromIDs(
                    programmeEdition.findProgrammeIDInProgrammeEdition(),
                    programmeEdition.findSchoolYearIDInProgrammeEdition());
            list.add(dto);
        }
        return list;
    }

    @Override
    public int countTotalNumberOfStudentsInAProgrammeEdition(RequestServiceDto programmeEditionDTO) {
        try {
            ProgrammeEdition programmeEdition = programmeEditionAssembler.toProgrammeEditionFromRequestServiceDTO(programmeEditionDTO);
            return programmeEditionEnrolmentService.totalStudentsInProgrammeEdition(programmeEdition.identity());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao contar estudantes na edição do programa", e);
        }
    }

    @Override
    public ProgrammeEditionResponseServiceDTO createProgrammeEditionAndSave(ProgrammeEditionRequestServiceDTO programmeEditionDTO) throws Exception {
        ProgrammeID programmeID = programmeEditionAssembler.toProgrammeID(programmeEditionDTO);
        SchoolYearID schoolYearID = schoolYearService.getCurrentSchoolYearID().orElseThrow(() -> new IllegalArgumentException("No current School Year found."));

        if (programmeService.getProgrammeByID(programmeID).isEmpty()) {
            throw new IllegalArgumentException("Programme does not exist.");
        }

        ProgrammeEdition programmeEdition = programmeEditionFactory.createProgrammeEdition(programmeID, schoolYearID);
        Optional<ProgrammeEdition> savedProgramme = this.saveProgrammeEdition(programmeEdition);

        if (savedProgramme.isPresent()) {
            ProgrammeEdition savedEdition = savedProgramme.get();
            ProgrammeEditionID programmeEditionID = savedEdition.identity();

            return programmeEditionAssembler.toServiceResponseDTOFromIDs(
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
                    return programmeEditionAssembler.toServiceResponseDTOFromIDs(id.getProgrammeID(), id.getSchoolYearID());
                })
                .toList();
    }
}
