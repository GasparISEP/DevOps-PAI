package PAI.factory;

import PAI.domain.*;

public interface ProgrammeFactory {

     Programme registerProgramme (String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, IProgrammeCourseListFactory programmeCourseListFactory, CourseInStudyPlanFactory courseInStudyPlanFactory, StudyPlanListFactory studyPlanListFactory, StudyPlanFactory studyPlanFactory, CourseFactory courseFactory);
}
