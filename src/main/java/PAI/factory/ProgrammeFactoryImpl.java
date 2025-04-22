package PAI.factory;

import PAI.VOs.*;
import PAI.domain.programme.Programme;

public class ProgrammeFactoryImpl implements IProgrammeFactory {
    public Programme registerProgramme (NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) {
        return new Programme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);
    }
}
