package PAI.controller;

import PAI.VOs.*;
import PAI.service.programme.IProgrammeService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class US11_RegisterProgrammeInTheSystemController {

    private final IProgrammeService programmeService;

    public US11_RegisterProgrammeInTheSystemController(IProgrammeService programmeService) throws Exception {
        if (programmeService == null) {
            throw new Exception("Programme Service cannot be null.");
        }
        this.programmeService = programmeService;
    }

    public boolean registerProgramme(String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) throws Exception {

        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars(name);
        Acronym programmeAcronym = new Acronym(acronym);
        QuantEcts programmeQuantityOfEcts = new QuantEcts(quantityOfEcts);
        QuantSemesters programmeQuantityOfSemesters = new QuantSemesters(quantityOfSemesters);

        return programmeService.registerProgramme(programmeName, programmeAcronym, programmeQuantityOfEcts, programmeQuantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);
    }
}