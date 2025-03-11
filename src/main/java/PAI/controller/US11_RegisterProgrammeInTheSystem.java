package PAI.controller;

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
    public boolean registerProgrammeInTheSystem(String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1, CourseInStudyPlanFactory courseInStudyPlanFactory, StudyPlanListFactory studyPlanListFactory, StudyPlanFactory studyPlanFactory, CourseFactory courseFactory) throws Exception {
        _programmeRepo.registerProgramme(name, acronym,  quantityOfEcts,  quantityOfSemesters,  degreeType, department, programmeDirector, programmeCourseListFactoryImpl1, courseInStudyPlanFactory, studyPlanListFactory, studyPlanFactory, courseFactory);
        return true;
    }
}