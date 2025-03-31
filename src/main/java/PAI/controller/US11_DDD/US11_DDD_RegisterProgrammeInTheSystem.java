package PAI.controller.US11_DDD;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.repository.programmeRepo.ProgrammeDDDRepository;

public class US11_DDD_RegisterProgrammeInTheSystem {

    ProgrammeDDDRepository _programmeDDDList;

    public US11_DDD_RegisterProgrammeInTheSystem (ProgrammeDDDRepository programmeDDDList) throws Exception {

        if (programmeDDDList == null) {
            throw new Exception("Programme Repository cannot be null.");
        }

        _programmeDDDList = programmeDDDList;
    }

    public boolean registerAProgrammeDDDInTheSystem(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeType_ID degreeTypeID, Department department, TeacherID programmeDirectorID) throws Exception {

        _programmeDDDList.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, department, programmeDirectorID);
        return true;
    }
}