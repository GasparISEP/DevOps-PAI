package PAI.assembler.programme;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.dto.Programme.ProgrammeIDDTO;
import PAI.dto.Programme.ProgrammeDTO;
import PAI.dto.Programme.ProgrammeResponseDTO;
import PAI.dto.Programme.ProgrammeVOsDTO;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeAssembler implements IProgrammeAssembler {

    public ProgrammeVOsDTO fromDTOToDomain(ProgrammeDTO programmeDTO) {

        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(programmeDTO.name());
        Acronym acronym = new Acronym(programmeDTO.acronym());
        MaxEcts maxEcts = new MaxEcts(programmeDTO.maxECTS());
        QuantSemesters quantSemesters = new QuantSemesters(programmeDTO.quantSemesters());
        DegreeTypeID degreeTypeID = new DegreeTypeID(programmeDTO.degreeTypeID());
        DepartmentAcronym departmentAcronym = new DepartmentAcronym(programmeDTO.departmentID());
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym(programmeDTO.teacherID());
        TeacherID teacherID = new TeacherID(teacherAcronym);

        return new ProgrammeVOsDTO(name, acronym, maxEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
    }

    public ProgrammeDTO fromDomainToDTO(Programme programme) {

        String name = programme.getProgrammeName().getnameWithNumbersAndSpecialChars();
        String acronym = programme.getAcronym().getAcronym();
        int maxECTS = programme.getMaxEcts().getMaxEcts();
        int quantSemesters = programme.getQuantSemesters().getQuantityOfSemesters();
        String degreeTypeID = programme.getDegreeTypeID().getDTID();
        String departmentID = programme.getDepartment().getAcronym().getAcronym();
        String teacherID = programme.getProgrammeDirectorID().getTeacherAcronym().getAcronym();

        return new ProgrammeDTO(name, acronym, maxECTS, quantSemesters, degreeTypeID, departmentID, teacherID);

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