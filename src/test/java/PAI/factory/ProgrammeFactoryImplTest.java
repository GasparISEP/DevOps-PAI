//package PAI.factory;
//
//import PAI.VOs.Acronym;
//import PAI.VOs.NameWithNumbersAndSpecialChars;
//import PAI.VOs.QuantEcts;
//import PAI.VOs.QuantSemesters;
//import PAI.domain.*;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockedConstruction;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class ProgrammeFactoryImplTest {
//    @Test
//    void shouldCreatNewProgramme() throws Exception {
//        //(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, programmeDirector, programmeCourseListFactoryImpl1
//        //arrange
//        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Engineering");
//        Acronym acronym = new Acronym("CE");
//        QuantEcts quantityOfEcts = new QuantEcts(15);
//        QuantSemesters quantityOfSemesters = new QuantSemesters(4);
//        DegreeType degreeType = mock(DegreeType.class);
//        Department department = mock(Department.class);
//        Teacher programmeDirector = mock(Teacher.class);
//        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = mock(ProgrammeCourseListFactoryImpl.class);
//        ICourseInStudyPlanFactory ICourseInStudyPlanFactory = mock(ICourseInStudyPlanFactory.class);
//        IStudyPlanListFactory IStudyPlanListFactory = mock(IStudyPlanListFactory.class);
//        IStudyPlanFactory IStudyPlanFactory = mock(IStudyPlanFactory.class);
//        CourseFactoryImpl courseFactoryImpl = mock(CourseFactoryImpl.class);
//
//        try (MockedConstruction<Programme> mockConstruction = mockConstruction(Programme.class, (mock, context) -> {
//            NameWithNumbersAndSpecialChars nameActual = (NameWithNumbersAndSpecialChars) context.arguments().get(0);
//            Acronym acronymActual = (Acronym) context.arguments().get(1);
//            QuantEcts qtyOfEctsActual = (QuantEcts) context.arguments().get(2);
//            QuantSemesters qtyOfSemesters = (QuantSemesters) context.arguments().get(3);
//
//            when(mock.getProgrammeNameWithNumbersAndSpecialChars()).thenReturn(nameActual);
//            when(mock.getAcronymm()).thenReturn(acronymActual);
//            when(mock.getQuantEcts()).thenReturn(qtyOfEctsActual);
//            when(mock.getQuantSemesters()).thenReturn(qtyOfSemesters);
//
//        })) {
//            //act
//            ProgrammeFactoryImpl factory = new ProgrammeFactoryImpl();
//            Programme programme = factory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, programmeDirector, programmeCourseListFactoryImpl1, ICourseInStudyPlanFactory, IStudyPlanListFactory, IStudyPlanFactory, courseFactoryImpl);
//
//            //assert
//            assertEquals(1, mockConstruction.constructed().size());
//            Programme createdProgramme = mockConstruction.constructed().get(0);
//
//            assertEquals(name, createdProgramme.getProgrammeNameWithNumbersAndSpecialChars());
//            assertEquals(acronym, createdProgramme.getAcronymm());
//            assertEquals(quantityOfEcts, createdProgramme.getQuantEcts());
//            assertEquals(quantityOfSemesters, createdProgramme.getQuantSemesters());
//        }
//    }
//}
