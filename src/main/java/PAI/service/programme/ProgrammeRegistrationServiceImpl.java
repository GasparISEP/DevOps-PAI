package PAI.service.programme;

import PAI.VOs.*;
import PAI.assembler.programme.IProgrammeAssembler;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.department.Department;
import PAI.domain.programme.IProgrammeFactory;
import PAI.domain.programme.Programme;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.domain.repositoryInterfaces.department.IDepartmentRepository;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.domain.repositoryInterfaces.teacher.ITeacherRepository;
import PAI.domain.teacher.Teacher;
import PAI.dto.Programme.ProgrammeResponseDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProgrammeRegistrationServiceImpl implements IProgrammeRegistrationService {

    private IProgrammeFactory _programmeFactory;
    private IProgrammeRepository _programmeRepository;
    private IDegreeTypeRepository _degreeTypeRepository;
    private IDepartmentRepository _departmentRepository;
    private ITeacherRepository _teacherRepository;
    private IProgrammeAssembler _programmeAssembler;

    public ProgrammeRegistrationServiceImpl(IProgrammeFactory programmeFactory, IProgrammeRepository programmeRepository, IDegreeTypeRepository degreeTypeRepository, IDepartmentRepository departmentRepository, ITeacherRepository teacherRepository, IProgrammeAssembler programmeAssembler) {

        if(programmeFactory == null)
            throw new IllegalArgumentException("Programme Factory cannot be null");

        this._programmeFactory = programmeFactory;

        if(programmeRepository == null)
            throw new IllegalArgumentException("Programme Repository cannot be null");

        this._programmeRepository = programmeRepository;

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
        QuantEcts quantityOfEcts = programmeVOsDTO.getQuantEcts();
        QuantSemesters quantityOfSemesters = programmeVOsDTO.getQuantSemesters();
        DegreeTypeID degreeTypeID = programmeVOsDTO.getDegreeTypeID();
        DepartmentID departmentID = programmeVOsDTO.getDepartmentID();
        TeacherID teacherID = programmeVOsDTO.getTeacherID();

        Programme programme = _programmeFactory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, teacherID);

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
}
