package PAI.factory;

import PAI.domain.DegreeType;
import PAI.domain.Department;
import PAI.domain.Programme;
import PAI.domain.Teacher;

public interface ProgrammeFactory {

     Programme registerProgramme (String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, ProgrammeCourseListFactory programmeCourseListFactory, CourseInStudyPlanFactoryImpl courseInStudyPlanFactory, StudyPlanArrayListFactory studyPlanArrayListFactory, StudyPlanFactory studyPlanFactory, CourseFactory courseFactory);
}
