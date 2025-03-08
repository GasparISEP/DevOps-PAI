package PAI.controller;

import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.ProgrammeList;

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
    public boolean registerProgrammeInTheSystemIncludingStudyPlan(String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, ProgrammeCourseListFactory programmeCourseListFactory, CourseInStudyPlanFactory courseInStudyPlanFactory, StudyPlanArrayListFactory studyPlanArrayListFactory, StudyPlanFactory studyPlanFactory, CourseFactory courseFactory) throws Exception {
        _programmeList.registerProgramme(name, acronym,  quantityOfEcts,  quantityOfSemesters,  degreeType, department, programmeDirector, programmeCourseListFactory, courseInStudyPlanFactory, studyPlanArrayListFactory, studyPlanFactory, courseFactory);
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
