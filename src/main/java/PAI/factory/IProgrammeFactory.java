package PAI.factory;

import PAI.VOs.QuantEcts;
import PAI.VOs.QuantSemesters;
import PAI.domain.*;

public interface IProgrammeFactory {

     Programme registerProgramme (String name, String acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, IProgrammeCourseListFactory programmeCourseListFactory, ICourseInStudyPlanFactory ICourseInStudyPlanFactory, IStudyPlanListFactory IStudyPlanListFactory, IStudyPlanFactory IStudyPlanFactory, ICourseFactory ICourseFactory);
}
