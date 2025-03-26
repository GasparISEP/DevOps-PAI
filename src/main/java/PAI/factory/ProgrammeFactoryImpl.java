package PAI.factory;

import PAI.domain.*;

public class ProgrammeFactoryImpl implements IProgrammeFactory {

    public Programme registerProgramme (String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, IProgrammeCourseListFactory programmeCourseListFactory, CourseInStudyPlanFactory courseInStudyPlanFactory, StudyPlanListFactory studyPlanListFactory, StudyPlanFactory studyPlanFactory, ICourseFactory ICourseFactory) {
        return new Programme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, programmeDirector, programmeCourseListFactory, courseInStudyPlanFactory, studyPlanListFactory, studyPlanFactory, ICourseFactory);
    }
}
