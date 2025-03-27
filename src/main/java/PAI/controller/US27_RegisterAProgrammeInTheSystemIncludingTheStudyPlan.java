package PAI.controller;

import PAI.VOs.QuantEcts;
import PAI.VOs.QuantSemesters;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.ProgrammeRepository;

public class US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan {

    // Instanciar os Repositórios
    ProgrammeRepository _programmeList;

    // Construtor
    public US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(ProgrammeRepository programmeList) throws Exception {
        if (programmeList == null) {
            throw new Exception("Programme List cannot be null.");
        }
        _programmeList = programmeList;
    }

    // Metodo para o Controlador de US de registar Programme no sistema
    public boolean registerProgrammeInTheSystemIncludingStudyPlan(String name, String acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1, ICourseInStudyPlanFactory ICourseInStudyPlanFactory, IStudyPlanListFactory IStudyPlanListFactory, IStudyPlanFactory IStudyPlanFactory, ICourseFactory ICourseFactory) throws Exception {
        _programmeList.registerProgramme(name, acronym,  quantityOfEcts,  quantityOfSemesters,  degreeType, department, programmeDirector, programmeCourseListFactoryImpl1, ICourseInStudyPlanFactory, IStudyPlanListFactory, IStudyPlanFactory, ICourseFactory);
        return true;
    }

    //Metodo para o Controlador de US de registar um Course no StudyPlan
    public boolean addCourseToStudyPlan(int semester, int curricularYear, Course course, Programme programme) throws Exception {
        if (programme == null) {
            throw new Exception("Programme cannot be null.");
        }
        return programme.getStudyPlan().addCourseToStudyPlan(semester, curricularYear, course, programme);
    }
}
