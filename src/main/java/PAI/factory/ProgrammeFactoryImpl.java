package PAI.factory;

import PAI.domain.*;

public class ProgrammeFactoryImpl implements ProgrammeFactory {

    public Programme registerProgramme (String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, ProgrammeCourseListFactory programmeCourseListFactory, CourseInStudyPlanFactoryImpl courseInStudyPlanFactory, StudyPlanListFactoryImpl studyPlanArrayListFactory, StudyPlanFactoryImpl studyPlanFactory, CourseFactory courseFactory) {
        return new Programme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, programmeDirector, programmeCourseListFactory, courseInStudyPlanFactory, studyPlanArrayListFactory, studyPlanFactory, courseFactory);
    }
}
