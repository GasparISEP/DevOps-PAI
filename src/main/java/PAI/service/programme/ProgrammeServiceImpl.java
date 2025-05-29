package PAI.service.programme;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.programme.Programme;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.dto.Programme.ProgrammeDTO;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;
import PAI.exception.AlreadyRegisteredException;
import PAI.exception.BusinessRuleViolationException;
import PAI.exception.NotFoundException;
import PAI.service.degreeType.IDegreeTypeRegistrationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeServiceImpl implements IProgrammeService {

    private final IProgrammeFactory _programmeFactory;
    private final IProgrammeRepository _programmeRepository;
    private final IProgrammeAssembler _programmeAssembler;
    private final IDegreeTypeRegistrationService _degreeTypeService;

    public ProgrammeServiceImpl(IProgrammeFactory programmeFactory, IProgrammeRepository programmeRepository,
                                IProgrammeAssembler programmeAssembler, IDegreeTypeRegistrationService degreeTypeService) {

        if (programmeFactory == null) {
            throw new IllegalArgumentException("Programme Factory cannot be null");
        }
        _programmeFactory = programmeFactory;

        if (programmeRepository == null) {
            throw new IllegalArgumentException("Programme Repository cannot be null");
        }
        _programmeRepository = programmeRepository;

        if(programmeAssembler == null)
            throw new IllegalArgumentException("Programme Assembler cannot be null");

        this._programmeAssembler = programmeAssembler;

        if (degreeTypeService == null)
            throw new IllegalArgumentException("Degree Type Registration Service cannot be null");

        _degreeTypeService = degreeTypeService;
    }

    public Programme registerProgramme(ProgrammeVOsDTO programmeVOsDTO) throws Exception {

        NameWithNumbersAndSpecialChars name = programmeVOsDTO.name();
        Acronym acronym = programmeVOsDTO.acronym();
        MaxEcts maxOfEcts = programmeVOsDTO.maxEcts();
        QuantSemesters quantityOfSemesters = programmeVOsDTO.quantSemesters();
        DegreeTypeID degreeTypeID = programmeVOsDTO.degreeTypeID();
        DepartmentID departmentID = programmeVOsDTO.departmentID();
        TeacherID teacherID = programmeVOsDTO.teacherID();

        DegreeType degreeType = _degreeTypeService.getDegreeTypeById(degreeTypeID)
                .orElseThrow(() -> new Exception("Degree type not found"));

        if (!areQtyOfSemesterAndDegreeTypeAligned(quantityOfSemesters, degreeType)) {
            throw new BusinessRuleViolationException(
                    String.format("Invalid number of semesters for Degree Type: %s",
                            degreeType.getName().getName()));
        }

        Programme programme = _programmeFactory.registerProgramme(name, acronym, maxOfEcts, quantityOfSemesters, degreeTypeID, departmentID, teacherID);

        if (_programmeRepository.existsByName(name.toString()))
            throw new AlreadyRegisteredException("Programme name");

        if (_programmeRepository.existsByAcronym(acronym.toString()))
            throw new AlreadyRegisteredException("Programme acronym");

        return _programmeRepository.save(programme);
    }

    @Override
    public List<ProgrammeIDDTO> getAllProgrammeIDDTOs() {
        List<ProgrammeIDDTO> programmeIDDTOs = new ArrayList<>();
        for (Programme programme : _programmeRepository.findAll()) {
            ProgrammeIDDTO programmeIDDTO = _programmeAssembler.toDTO(programme.getProgrammeID());
        programmeIDDTOs.add(programmeIDDTO);
        }
        return programmeIDDTOs;
    }

    public boolean changeProgrammeDirector(ProgrammeID programmeID, TeacherID programmeDirectorID) throws Exception {
        if (programmeID == null) {
            throw new IllegalArgumentException("ProgrammeID cannot be null");
        }
        if (programmeDirectorID == null) {
            throw new IllegalArgumentException("ProgrammeDirectorID cannot be null");
        }

        Programme programme = _programmeRepository.ofIdentity(programmeID)
                .orElseThrow(() -> new NotFoundException("Programme not found"));

        programme.newProgrammeDirector(programmeDirectorID);

        _programmeRepository.save(programme);

        return true;
    }

    public List<ProgrammeID> findProgrammeByDepartment(DepartmentID departmentID) {
        List<ProgrammeID> programmeIDs;
        if (departmentID == null) {
            programmeIDs = List.of();
        } else {
            programmeIDs = _programmeRepository.findProgrammeByDepartment(departmentID);
        }
        return programmeIDs;
    }

    public List<Programme> getProgrammesByDegreeTypeID(DegreeTypeID id) {
        List <Programme> programmeList = new ArrayList<>();
        for (Programme programme : _programmeRepository.findAll()) {
            if (programme.hasThisDegreeTypeID(id))
                programmeList.add(programme);
        }
        return programmeList;
    }

    public Optional<Programme> getProgrammeByName(NameWithNumbersAndSpecialChars name) {
        for (Programme programme : _programmeRepository.findAll()) {
            if (programme.hasThisProgrammeName(name)) {
                return Optional.of(programme);
            }
        }
        return Optional.empty();
    }

    public Optional<Programme> getProgrammeByAcronym(Acronym acronym) {
        for (Programme programme : _programmeRepository.findAll()) {
            if (programme.getAcronym().equals(acronym)) {
                return Optional.of(programme);
            }
        }
        return Optional.empty();
    }

    public List<ProgrammeID> getAllProgrammeIDs() {
        List<ProgrammeID> programmeIDs = new ArrayList<>();
        for (Programme programme : _programmeRepository.findAll()) {
            programmeIDs.add(programme.getProgrammeID());
        }
        return programmeIDs;
    }

    public Iterable<Programme> findAll() {
        return _programmeRepository.findAll();
    }

    public Optional<Programme> getProgrammeByID(ProgrammeID id) {
        for (Programme programme : _programmeRepository.findAll()) {
            if (programme.identity().equals(id)) {
                return Optional.of(programme);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<ProgrammeIDDTO> getProgrammeIDDTOsByDegreeTypeID(DegreeTypeID id) {
        List<ProgrammeIDDTO> programmeIDDTOList = new ArrayList<>();

        for (Programme programme : _programmeRepository.findAll()) {
            if (programme.hasThisDegreeTypeID(id)) {
                ProgrammeIDDTO dto = _programmeAssembler.toDTO(programme.getProgrammeID());
                programmeIDDTOList.add(dto);
            }
        }
        return programmeIDDTOList;
    }

    public ProgrammeDTO getProgrammeDTOByID(ProgrammeID id) {
        if(id == null) {
            throw new IllegalArgumentException("ProgrammeID cannot be null");
        }
        return _programmeRepository.ofIdentity(id)
                .map(_programmeAssembler::fromDomainToDTO)
                .orElseThrow(() -> new NotFoundException("The Programme with ID " + id + " was not found"));
    }

    private boolean areQtyOfSemesterAndDegreeTypeAligned (QuantSemesters semesters, DegreeType degreeType) {

        String degreeTypeName = degreeType.getName().getName();
        int quantityOfSemesters = semesters.getQuantityOfSemesters();

        if (degreeTypeName.equals("Bachelor") && (quantityOfSemesters < 6 || quantityOfSemesters > 8))
            return false;
        if (degreeTypeName.equals("Master") && (quantityOfSemesters < 2 || quantityOfSemesters > 4))
            return false;
        if (degreeTypeName.equals("Integrated Master") && (quantityOfSemesters < 10 || quantityOfSemesters > 12))
            return false;
        if (degreeTypeName.equals("PhD") && (quantityOfSemesters < 6 || quantityOfSemesters > 8))
            return false;

        return true;
    }
}