package PAI.assembler.programme;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeRequestDTO;
import PAI.dto.Programme.ProgrammeResponseDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeAssembler implements IProgrammeAssembler {

    public ProgrammeVOsDTO fromDTOToDomain(ProgrammeRequestDTO programmeRequestDTO) {

        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(programmeRequestDTO.getName());
        Acronym acronym = new Acronym(programmeRequestDTO.getAcronym());
        MaxEcts maxEcts = new MaxEcts(programmeRequestDTO.getMaxECTS());
        QuantSemesters quantSemesters = new QuantSemesters(programmeRequestDTO.getQuantSemesters());
        DegreeTypeID degreeTypeID = new DegreeTypeID(programmeRequestDTO.getDegreeTypeID());
        DepartmentAcronym departmentAcronym = new DepartmentAcronym(programmeRequestDTO.getDepartmentID());
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym(programmeRequestDTO.getTeacherID());
        TeacherID teacherID = new TeacherID(teacherAcronym);

        return new ProgrammeVOsDTO(name, acronym, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
    }

    public ProgrammeResponseDTO fromDomainToDTO(Programme programme, String degreeTypeName, String departmentName, String teacherName) {

        String name = programme.getProgrammeName().getnameWithNumbersAndSpecialChars();
        String acronym = programme.getAcronym().getAcronym();
        int maxECTS = programme.getMaxEcts().getMaxEcts();
        int quantSemesters = programme.getQuantSemesters().getQuantityOfSemesters();

        return new ProgrammeResponseDTO(name, acronym, maxECTS, quantSemesters, degreeTypeName, departmentName, teacherName);

    }

    @Override
    public ProgrammeIDDTO toDTO(ProgrammeID programmeID) {
        if (programmeID == null) {
            throw new IllegalArgumentException("Programme ID cannot be null");
        }
        String programmeName = programmeID.getProgrammeName();
        String programmeAcronym = programmeID.getProgrammeAcronym();
        return new ProgrammeIDDTO(programmeName, programmeAcronym);
    }
}