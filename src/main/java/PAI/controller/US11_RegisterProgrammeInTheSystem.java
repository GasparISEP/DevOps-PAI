package PAI.controller;

import PAI.VOs.QuantEcts;
import PAI.VOs.QuantSemesters;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.ProgrammeRepository;

public class US11_RegisterProgrammeInTheSystem {

    private ProgrammeRepository _programmeRepo;

    // Construtor
    public US11_RegisterProgrammeInTheSystem(ProgrammeRepository programmeRepo) throws Exception {
        if (programmeRepo == null) {
            throw new Exception("Programme List cannot be null.");
        }
        _programmeRepo = programmeRepo;
    }

    // Metodo para o Controlador de US de registar Programme no sistema
    public boolean registerProgrammeInTheSystem(String name, String acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, IProgrammeCourseListFactory programmeCourseListFactory, ICourseInStudyPlanFactory ICourseInStudyPlanFactory, IStudyPlanListFactory IStudyPlanListFactory, IStudyPlanFactory IStudyPlanFactory, ICourseFactory ICourseFactory) throws Exception {
        _programmeRepo.registerProgramme(name, acronym,  quantityOfEcts,  quantityOfSemesters,  degreeType, department, programmeDirector, programmeCourseListFactory, ICourseInStudyPlanFactory, IStudyPlanListFactory, IStudyPlanFactory, ICourseFactory);
        return true;
    }
}