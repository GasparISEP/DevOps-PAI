package PAI.service.programme;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.department.Department;
import PAI.domain.programme.Programme;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.Teacher;
import PAI.dto.Programme.ProgrammeResponseDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeServiceImpl implements IProgrammeService {

    private final IProgrammeFactory _programmeFactory;
    private final IProgrammeRepository _programmeRepository;
    private IDegreeTypeRepository _degreeTypeRepository;
    private IDepartmentRepository _departmentRepository;
    private ITeacherRepository _teacherRepository;
    private IProgrammeAssembler _programmeAssembler;

    public ProgrammeServiceImpl(IProgrammeFactory programmeFactory, IProgrammeRepository programmeRepository, IDegreeTypeRepository degreeTypeRepository, IDepartmentRepository departmentRepository, ITeacherRepository teacherRepository, IProgrammeAssembler programmeAssembler) {
        if (programmeFactory == null) {
            throw new IllegalArgumentException("Programme Factory cannot be null");
        }
        _programmeFactory = programmeFactory;

        if (programmeRepository == null) {
            throw new IllegalArgumentException("Programme Repository cannot be null");
        }
        _programmeRepository = programmeRepository;

        if(degreeTypeRepository == null)
            throw new IllegalArgumentException("Degree Type Repository cannot be null");

        this._degreeTypeRepository = degreeTypeRepository;

        if(departmentRepository == null)
            throw new IllegalArgumentException("Department Repository cannot be null");

        this._departmentRepository = departmentRepository;

        if(teacherRepository == null)
            throw new IllegalArgumentException("Teacher Repository cannot be null");

        this._teacherRepository = teacherRepository;

        if(programmeAssembler == null)
            throw new IllegalArgumentException("Programme Assembler cannot be null");

        this._programmeAssembler = programmeAssembler;
    }

    public ProgrammeResponseDTO registerProgramme(ProgrammeVOsDTO programmeVOsDTO) throws Exception {

        NameWithNumbersAndSpecialChars name = programmeVOsDTO.getName();
        Acronym acronym = programmeVOsDTO.getAcronym();
        MaxEcts maxOfEcts = programmeVOsDTO.getMaxEcts();
        QuantSemesters quantityOfSemesters = programmeVOsDTO.getQuantSemesters();
        DegreeTypeID degreeTypeID = programmeVOsDTO.getDegreeTypeID();
        DepartmentID departmentID = programmeVOsDTO.getDepartmentID();
        TeacherID teacherID = programmeVOsDTO.getTeacherID();

        Programme programme = _programmeFactory.registerProgramme(name, acronym, maxOfEcts, quantityOfSemesters, degreeTypeID, departmentID, teacherID);

        if(_programmeRepository.containsOfIdentity(programme.identity()))
            throw new Exception("Programme is already registered");

        Programme programmeCreated = _programmeRepository.save(programme);

        String degreeTypeName = findDegreeTypeNameByID(degreeTypeID);
        String departmentName = findDepartmentByID(departmentID);
        String teacherName = findTeacherByID(teacherID);

        return _programmeAssembler.fromDomainToDTO(programmeCreated, degreeTypeName, departmentName, teacherName);
    }

    private String findDegreeTypeNameByID (DegreeTypeID degreeTypeID) {

        Optional<DegreeType> degreeType = _degreeTypeRepository.ofIdentity(degreeTypeID);
        String degreeTypeName;

        if (degreeType.isPresent())
            degreeTypeName = degreeType.get().getName().getName();
        else
            throw new EntityNotFoundException("Degree Type not found");

        return degreeTypeName;
    }

    private String findDepartmentByID (DepartmentID departmentID) {

        Optional <Department> department = _departmentRepository.ofIdentity(departmentID);
        String departmentName;

        if(department.isPresent())
            departmentName = department.get().getName().getName();
        else
            throw new EntityNotFoundException("Department not found");

        return departmentName;
    }

    private String findTeacherByID (TeacherID teacherID) {

        Optional <Teacher> programmeDirector = _teacherRepository.ofIdentity(teacherID);
        String programmeDirectorName;

        if(programmeDirector.isPresent())
            programmeDirectorName = programmeDirector.get().getName().getName();
        else
            throw new EntityNotFoundException("Teacher not found");

        return programmeDirectorName;
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
}