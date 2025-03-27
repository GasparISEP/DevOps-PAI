package PAI.factory;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.QuantEcts;
import PAI.VOs.QuantSemesters;
import PAI.domain.*;

public interface IProgrammeFactory {

     Programme registerProgramme (NameWithNumbersAndSpecialChars name, String acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, IProgrammeCourseListFactory programmeCourseListFactory, CourseInStudyPlanFactory courseInStudyPlanFactory, StudyPlanListFactory studyPlanListFactory, StudyPlanFactory studyPlanFactory, ICourseFactory ICourseFactory);
}
