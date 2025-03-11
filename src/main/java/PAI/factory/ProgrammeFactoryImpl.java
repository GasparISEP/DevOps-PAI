package PAI.factory;

import PAI.domain.*;

public class ProgrammeFactoryImpl implements ProgrammeFactory {

    public Programme registerProgramme (String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1, CourseInStudyPlanFactory courseInStudyPlanFactory, StudyPlanListFactory studyPlanListFactory, StudyPlanFactory studyPlanFactory, CourseFactory courseFactory) {
        return new Programme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, programmeDirector, programmeCourseListFactoryImpl1, courseInStudyPlanFactory, studyPlanListFactory, studyPlanFactory, courseFactory);
    }
}
