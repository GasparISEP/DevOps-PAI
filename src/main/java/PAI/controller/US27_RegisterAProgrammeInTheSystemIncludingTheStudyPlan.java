package PAI.controller;

import PAI.domain.*;

public class US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan {

    // Instanciar os Repositórios
    ProgrammeList _programmeList;

    // Construtor
    public void RegisterProgrammeInSystem(ProgrammeList programmeList) {
        _programmeList = programmeList;
    }

    // Metodo para o Controlador de US de registar Programme no sistema
    public boolean registerProgrammeInTheSystem (String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, CourseRepository courseRepository) throws Exception {
        return _programmeList.registerProgramme(name, acronym,  quantityOfEcts,  quantityOfSemesters,  degreeType, department, programmeDirector, courseRepository);
    }

    //Metodo para o Controlador de US de registar um Course no StudyPlan
    public boolean registerCourseInStudyPlan(int semester, int curricularYear, Course course, Programme programme) throws Exception {
        return programme.getStudyPlan().registerCourseInStudyPlan(semester, curricularYear, course, programme);
    }
}
