package PAI.dto.Programme;

import PAI.VOs.*;

public record ProgrammeVOsDTO(
        NameWithNumbersAndSpecialChars name,
        Acronym acronym,
        MaxEcts maxEcts,
        QuantSemesters quantSemesters,
        DegreeTypeID degreeTypeID,
        DepartmentID departmentID,
        TeacherID teacherID
) {}