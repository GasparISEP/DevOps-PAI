package PAI.controller;

import PAI.domain.*;
import PAI.repository.ProgrammeList;

public class US11_RegisterProgrammeInTheSystem {

    private ProgrammeList _programmeList;

    // Construtor
    public US11_RegisterProgrammeInTheSystem(ProgrammeList programmeList) throws Exception {
        if (programmeList == null) {
            throw new Exception("Programme List cannot be null.");
        }
        _programmeList = programmeList;
    }

    // Metodo para o Controlador de US de registar Programme no sistema
    public boolean registerProgrammeInTheSystem(String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, ProgrammeCourseListFactory programmeCourseListFactory) throws Exception {
        _programmeList.registerProgramme(name, acronym,  quantityOfEcts,  quantityOfSemesters,  degreeType, department, programmeDirector, programmeCourseListFactory);
        return true;
    }
}