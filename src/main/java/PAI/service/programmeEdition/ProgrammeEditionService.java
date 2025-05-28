package PAI.service.programmeEdition;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.assembler.programmeEdition.IProgrammeEditionAssembler;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.programmeEditionEnrolment.IProgrammeEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.dto.programmeEdition.CountStudentsDto;
import PAI.dto.programmeEdition.ProgrammeEditionDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

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

        if (programmeRepository == null) {
            throw new IllegalArgumentException("ProgrammeRepository cannot be null!");
        }
        this.programmeRepository = programmeRepository;

        if (schoolYearRepository == null) {
            throw new IllegalArgumentException("SchoolYearRepository cannot be null!");
        }
        this.schoolYearRepository = schoolYearRepository;

        if (programmeEditionEnrolmentRepository == null) {
            throw new IllegalArgumentException("ProgrammeEditionEnrolmentRepository cannot be null!");
        }
        this.programmeEditionEnrolmentRepository = programmeEditionEnrolmentRepository;

        if (programmeEditionAssembler == null) {
            throw new IllegalArgumentException("ProgrammeEditionAssembler cannot be null!");
        }
        this.programmeEditionAssembler = programmeEditionAssembler;
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
    public Optional<ProgrammeEdition> saveProgrammeEdition(ProgrammeEdition programmeEdition) throws Exception {
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
    public List<ProgrammeEdition> findAllProgrammeEditions() {
        Iterable<ProgrammeEdition> iterable = programmeEditionRepository.findAll();
        List<ProgrammeEdition> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list;
    }
    @Override
    public int countTotalNumberOfStudentsInAProgrammeEdition(CountStudentsDto programmeEditionDTO) throws Exception {
        ProgrammeEdition programmeEdition = programmeEditionAssembler.CountStudentsInProgrammeEditionDTOtoDomain(programmeEditionDTO);
        List<ProgrammeEditionEnrolment> allProgrammeEditionEnrolment = programmeEditionEnrolmentRepository.getAllProgrammeEditionsEnrollmentByProgrammeEditionID(programmeEdition.identity());
        return allProgrammeEditionEnrolment.size();
    }

    @Override
    public ProgrammeEditionDTO createProgrammeEditionAndSave(ProgrammeEditionDTO programmeEditionDTO) throws Exception {
        ProgrammeID programmeID = programmeEditionAssembler.toProgrammeID(programmeEditionDTO);
        SchoolYearID schoolYearID = programmeEditionAssembler.toSchoolYearID(programmeEditionDTO);

        // Validate if Programme and SchoolYear exist
        boolean programmeExists = programmeRepository.containsOfIdentity(programmeID);
        boolean schoolYearExists = schoolYearRepository.containsOfIdentity(schoolYearID);

        if (programmeExists && schoolYearExists) {
            ProgrammeEdition programmeEdition = programmeEditionFactory.createProgrammeEdition(programmeID, schoolYearID);
            Optional<ProgrammeEdition> savedProgramme = this.saveProgrammeEdition(programmeEdition);
            if (savedProgramme.isPresent()) {
                ProgrammeEditionID programmeEditionID = savedProgramme.get().identity();
                return programmeEditionAssembler.toDTO(programmeEditionID.getProgrammeID(), programmeEditionID.getSchoolYearID());
            } else {
                throw new IllegalArgumentException("Programme is already Registered");
            }
        } else {
            throw new IllegalArgumentException("Invalid Programme and or School Year");
        }
    }

    @Override
    public List<ProgrammeEditionID> getProgrammeEditionIDsByProgrammeID(ProgrammeID programmeID) {
        Iterable<ProgrammeEdition> allEditions = programmeEditionRepository.findAll();

        return StreamSupport.stream(allEditions.spliterator(), false)
                .filter(p -> p.identity().getProgrammeID().equals(programmeID))
                .map(ProgrammeEdition::identity)
                .toList();
    }


}

