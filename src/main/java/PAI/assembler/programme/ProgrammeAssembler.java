package PAI.assembler.programme;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.dto.Programme.ProgrammeRequestDTO;
import PAI.dto.Programme.ProgrammeResponseDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;

public class ProgrammeAssembler implements IProgrammeAssembler {

    public ProgrammeVOsDTO fromDTOToDomain (ProgrammeRequestDTO programmeRequestDTO) {

        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(programmeRequestDTO.getName());
        Acronym acronym = new Acronym(programmeRequestDTO.getAcronym());
        QuantEcts quantEcts = new QuantEcts(programmeRequestDTO.getQuantECTS());
        QuantSemesters quantSemesters = new QuantSemesters(programmeRequestDTO.getQuantSemesters());
        DegreeTypeID degreeTypeID = new DegreeTypeID(programmeRequestDTO.getDegreeTypeID());
        DepartmentAcronym departmentAcronym = new DepartmentAcronym(programmeRequestDTO.getDepartmentID());
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym(programmeRequestDTO.getTeacherID());
        TeacherID teacherID = new TeacherID(teacherAcronym);

        return new ProgrammeVOsDTO(name, acronym, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
    }

    public ProgrammeResponseDTO fromDomainToDTO (Programme programme, String degreeTypeName, String departmentName, String teacherName) {

        String name = programme.getProgrammeName().getnameWithNumbersAndSpecialChars();
        String acronym = programme.getAcronym().getAcronym();
        int quantECTS = programme.getQuantEcts().getQuantEcts();
        int quantSemesters = programme.getQuantSemesters().getQuantityOfSemesters();

        return new ProgrammeResponseDTO(name, acronym, quantECTS, quantSemesters, degreeTypeName, departmentName, teacherName);

    }
}

