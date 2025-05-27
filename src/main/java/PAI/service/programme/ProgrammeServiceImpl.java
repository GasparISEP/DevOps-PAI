package PAI.service.programme;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.domain.programme.Programme;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeServiceImpl implements IProgrammeService {

    private final IProgrammeFactory _programmeFactory;
    private final IProgrammeRepository _programmeRepository;
    private IProgrammeAssembler _programmeAssembler;

    public ProgrammeServiceImpl(IProgrammeFactory programmeFactory, IProgrammeRepository programmeRepository, IProgrammeAssembler programmeAssembler) {
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
    }

    public Programme registerProgramme(ProgrammeVOsDTO programmeVOsDTO) throws Exception {

        NameWithNumbersAndSpecialChars name = programmeVOsDTO.name();
        Acronym acronym = programmeVOsDTO.acronym();
        MaxEcts maxOfEcts = programmeVOsDTO.maxEcts();
        QuantSemesters quantityOfSemesters = programmeVOsDTO.quantSemesters();
        DegreeTypeID degreeTypeID = programmeVOsDTO.degreeTypeID();
        DepartmentID departmentID = programmeVOsDTO.departmentID();
        TeacherID teacherID = programmeVOsDTO.teacherID();

        Programme programme = _programmeFactory.registerProgramme(name, acronym, maxOfEcts, quantityOfSemesters, degreeTypeID, departmentID, teacherID);

        if(_programmeRepository.containsOfIdentity(programme.identity()))
            throw new Exception("Programme is already registered");

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
                .orElseThrow(() -> new IllegalArgumentException("Programme not found"));

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


}