package PAI.controller;

import PAI.VOs.*;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.service.IProgrammeService;

public class US11_RegisterProgrammeInTheSystemController {

    private final IProgrammeService programmeService;

    public US11_RegisterProgrammeInTheSystemController(IProgrammeService programmeService) throws Exception {
        if (programmeService == null) {
            throw new Exception("Programme Service cannot be null.");
        }
        this.programmeService = programmeService;
    }

    public boolean registerProgramme(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) throws Exception {
        return programmeService.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);
    }
}