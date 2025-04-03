package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Department;
import PAI.repository.programmeRepo.IProgrammeDDDRepository;

public class US11_DDD_RegisterProgrammeInTheSystem {

    IProgrammeDDDRepository _programmeDDDList;

    public US11_DDD_RegisterProgrammeInTheSystem (IProgrammeDDDRepository programmeDDDList) throws Exception {

        if (programmeDDDList == null) {
            throw new Exception("Programme Repository cannot be null.");
        }

        _programmeDDDList = programmeDDDList;
    }

    public boolean registerAProgrammeDDDInTheSystem(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) throws Exception {

        _programmeDDDList.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);
        return true;
    }
}