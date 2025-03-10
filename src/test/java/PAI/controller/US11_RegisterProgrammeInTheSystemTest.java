package PAI.controller;

import PAI.domain.*;
import PAI.factory.ProgrammeCourseListFactory;
import PAI.factory.ProgrammeFactoryImpl;
import PAI.factory.ProgrammeListArrayListFactory;
import PAI.factory.*;
import PAI.repository.ProgrammeList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US11_RegisterProgrammeInTheSystemTest {

    @Test
    void newProgrammeList() throws Exception {
        //arrange
        ProgrammeFactoryImpl programmeFactory = mock(ProgrammeFactoryImpl.class);
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory,programmeListArrayListFactory);

        //act
        US11_RegisterProgrammeInTheSystem controller1 = new US11_RegisterProgrammeInTheSystem(programmeList);

        //assert
        assertNotNull(controller1);
    }

    @Test
    void nullProgrammeInTheSystemFailure() throws Exception{
        //arrange
        ProgrammeList programmeList = null;

        //act + assert
        Exception exception = assertThrows(Exception.class, () -> {
            new US11_RegisterProgrammeInTheSystem(programmeList);
        });

        assertEquals("Programme List cannot be null.", exception.getMessage());
    }

    @Test
    void testRegisterProgrammeInTheSystemCorrectly() throws Exception{

        ProgrammeFactoryImpl programmeFactory = mock(ProgrammeFactoryImpl.class);
        ProgrammeListArrayListFactory programmeListArrayListFactory = mock(ProgrammeListArrayListFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory,programmeListArrayListFactory);

        US11_RegisterProgrammeInTheSystem controller1 = new US11_RegisterProgrammeInTheSystem(programmeList);

        // Criar dados de entrada
        String name = "Engenharia Informática";
        String acronym = "EI";
        int quantityOfEcts = 30;
        int quantityOfSemesters = 6;
        DegreeType degreeType = mock(DegreeType.class);
        Department department = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);
        CourseInStudyPlanFactory courseInStudyPlanFactory = mock(CourseInStudyPlanFactory.class);
        StudyPlanArrayListFactory studyPlanArrayListFactory = mock(StudyPlanArrayListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        CourseFactory courseFactory = mock(CourseFactory.class);

        boolean result = controller1.registerProgrammeInTheSystem(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);

        assertTrue(result);
    }
}