package PAI.factory;

import PAI.VOs.*;
import PAI.domain.*;

public class ProgrammeFactoryImpl_2 {
    public Programme_2 registerProgramme (NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeType_ID degreeTypeID, Department department, TeacherID programmeDirectorID) {
        return new Programme_2(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, department, programmeDirectorID);
    }
}
