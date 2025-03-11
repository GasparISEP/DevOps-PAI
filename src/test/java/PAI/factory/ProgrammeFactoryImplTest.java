package PAI.factory;

import PAI.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProgrammeFactoryImplTest {
    @Test
    void shouldCreatNewProgramme() throws Exception {
        //(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, programmeDirector, programmeCourseListFactory
        //arrange
        String name = "Mechanics";
        String acronym = "MEC";
        int quantityOfEcts = 15;
        int quantityOfSemesters = 4;
        DegreeType degreeType = mock(DegreeType.class);
        Department department = mock(Department.class);
        Teacher programmeDirector = mock(Teacher.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);
        CourseInStudyPlanFactory courseInStudyPlanFactory = mock(CourseInStudyPlanFactory.class);
        StudyPlanListFactory studyPlanListFactory = mock(StudyPlanListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        CourseFactory courseFactory = mock(CourseFactory.class);

        try (MockedConstruction<Programme> mockConstruction = mockConstruction(Programme.class, (mock, context) -> {
            String nameActual = (String) context.arguments().get(0);
            String acronymActual = (String) context.arguments().get(1);
            int qtyOfEctsActual = (int) context.arguments().get(2);
            int qtyOfSemesters = (int) context.arguments().get(3);
            DegreeType degreeTypeActual = (DegreeType) context.arguments().get(4);
            Department departmentActual = (Department) context.arguments().get(5);
            Teacher programmeDirectorActual = (Teacher) context.arguments().get(6);
            ProgrammeCourseListFactory programmeCourseListFactoryActual = (ProgrammeCourseListFactory) context.arguments().get(7);

            when(mock.getProgrammeName()).thenReturn(nameActual);
            when(mock.getAcronym()).thenReturn(acronymActual);
            when(mock.getQuantityOfEcts()).thenReturn(qtyOfEctsActual);
            when(mock.getQuantityOfSemester()).thenReturn(qtyOfSemesters);
            when(mock.getDegreeType()).thenReturn(degreeTypeActual);
            when(mock.getDepartment()).thenReturn(departmentActual);
            when(mock.getProgrammeDirector()).thenReturn(programmeDirectorActual);
            when(mock.getPprogrammeCourseListFactory()).thenReturn(programmeCourseListFactoryActual);

        })) {
            //act
            ProgrammeFactoryImpl factory = new ProgrammeFactoryImpl();
            Programme programme = factory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, programmeDirector, programmeCourseListFactory, courseInStudyPlanFactory , studyPlanListFactory, studyPlanFactory, courseFactory);

            //assert
            assertEquals(1, mockConstruction.constructed().size());
        }
    }
}
