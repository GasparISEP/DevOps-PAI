package PAI.domain.programme;

import PAI.VOs.*;
import PAI.domain.*;

public class ProgrammeDDDFactoryImpl {
    public ProgrammeDDD registerProgramme (NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeType_ID degreeTypeID, Department department, TeacherID programmeDirectorID) {
        return new ProgrammeDDD(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, department, programmeDirectorID);
    }
}
