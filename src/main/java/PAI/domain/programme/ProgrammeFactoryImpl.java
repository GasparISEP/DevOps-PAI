package PAI.domain.programme;

import PAI.VOs.*;

public class ProgrammeFactoryImpl implements IProgrammeFactory {
    public Programme registerProgramme (NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) {
        return new Programme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);
    }
}
