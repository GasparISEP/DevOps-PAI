package PAI.domain.programme;

import PAI.VOs.*;
import PAI.domain.*;

public class ProgrammeDDDFactoryImpl implements IProgrammeDDDFactory {
    public ProgrammeDDD registerProgramme (NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) {
        return new ProgrammeDDD(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);
    }
}
