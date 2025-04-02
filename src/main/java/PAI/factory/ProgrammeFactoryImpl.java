//package PAI.factory;
//
//import PAI.VOs.Acronym;
//import PAI.VOs.NameWithNumbersAndSpecialChars;
//import PAI.VOs.QuantEcts;
//import PAI.VOs.QuantSemesters;
//import PAI.domain.*;
//
//public class ProgrammeFactoryImpl implements IProgrammeFactory {
//
//    public Programme registerProgramme (NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, IProgrammeCourseListFactory programmeCourseListFactory, ICourseInStudyPlanFactory ICourseInStudyPlanFactory, IStudyPlanListFactory IStudyPlanListFactory, IStudyPlanFactory IStudyPlanFactory, ICourseFactory ICourseFactory) {
//        return new Programme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, programmeDirector, programmeCourseListFactory, ICourseInStudyPlanFactory, IStudyPlanListFactory, IStudyPlanFactory, ICourseFactory);
//    }
//}
