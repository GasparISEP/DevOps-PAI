package PAI.factory;

import PAI.VOs.QuantEcts;
import PAI.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeFactoryImplTest {
    @Test
    void shouldCreatNewProgramme() throws Exception {
        //(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, programmeDirector, programmeCourseListFactoryImpl1
        //arrange
        String name = "Mechanics";
        String acronym = "MEC";
        QuantEcts quantityOfEcts = new QuantEcts(15);
        int quantityOfSemesters = 4;
        DegreeType degreeType = mock(DegreeType.class);
        Department department = mock(Department.class);
        Teacher programmeDirector = mock(Teacher.class);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = mock(ProgrammeCourseListFactoryImpl.class);
        CourseInStudyPlanFactory courseInStudyPlanFactory = mock(CourseInStudyPlanFactory.class);
        StudyPlanListFactory studyPlanListFactory = mock(StudyPlanListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        CourseFactoryImpl courseFactoryImpl = mock(CourseFactoryImpl.class);

        try (MockedConstruction<Programme> mockConstruction = mockConstruction(Programme.class, (mock, context) -> {
            String nameActual = (String) context.arguments().get(0);
            String acronymActual = (String) context.arguments().get(1);
            QuantEcts qtyOfEctsActual = (QuantEcts) context.arguments().get(2);
            int qtyOfSemesters = (int) context.arguments().get(3);

            when(mock.getProgrammeName()).thenReturn(nameActual);
            when(mock.getAcronym()).thenReturn(acronymActual);
            when(mock.getQuantEcts()).thenReturn(qtyOfEctsActual);
            when(mock.getQuantityOfSemester()).thenReturn(qtyOfSemesters);

        })) {
            //act
            ProgrammeFactoryImpl factory = new ProgrammeFactoryImpl();
            Programme programme = factory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, programmeDirector, programmeCourseListFactoryImpl1, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, courseFactoryImpl);

            //assert
            assertEquals(1, mockConstruction.constructed().size());
            Programme createdProgramme = mockConstruction.constructed().get(0);

            assertEquals(name, createdProgramme.getProgrammeName());
            assertEquals(acronym, createdProgramme.getAcronym());
            assertEquals(quantityOfEcts, createdProgramme.getQuantEcts());
            assertEquals(quantityOfSemesters, createdProgramme.getQuantityOfSemester());
        }
    }
}
