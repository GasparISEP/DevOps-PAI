package PAI.controller;

import PAI.domain.*;

public class US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan {

    // Instanciar os Repositórios
    ProgrammeList _programmeList;

    // Construtor
    public US27_RegisterAProgrammeInTheSystemIncludingTheStudyPlan(ProgrammeList programmeList) throws Exception {
        if (programmeList == null) {
            throw new Exception("Programme List cannot be null.");
        }
        _programmeList = programmeList;
    }

    // Metodo para o Controlador de US de registar Programme no sistema
    public boolean registerProgrammeInTheSystemIncludingStudyPlan(String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl) throws Exception {
        _programmeList.registerProgramme(name, acronym,  quantityOfEcts,  quantityOfSemesters,  degreeType, department, programmeDirector, programmeCourseListFactoryImpl);
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
