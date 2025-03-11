package PAI.controller;

import PAI.domain.*;
import PAI.factory.ProgrammeCourseListFactoryImpl;
import PAI.factory.*;
import PAI.repository.ProgrammeRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US11_RegisterProgrammeInTheSystemTest {

    @Test
    void newProgrammeList() throws Exception {
        //arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeRepositoryListFactory programmeRepoListFactory = mock(ProgrammeRepositoryListFactory.class);
        ProgrammeRepository programmeRepo = new ProgrammeRepository(programmeFactory,programmeRepoListFactory);

        //act
        US11_RegisterProgrammeInTheSystem controller1 = new US11_RegisterProgrammeInTheSystem(programmeRepo);

        //assert
        assertNotNull(controller1);
    }

    @Test
    void nullProgrammeInTheSystemFailure() throws Exception{
        //arrange
        ProgrammeRepository programmeRepo = null;

        //act + assert
        Exception exception = assertThrows(Exception.class, () -> {
            new US11_RegisterProgrammeInTheSystem(programmeRepo);
        });

        assertEquals("Programme List cannot be null.", exception.getMessage());
    }

    @Test
    void testRegisterProgrammeInTheSystemCorrectly() throws Exception{

        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeRepositoryListFactory programmeRepoListFactory = mock(ProgrammeRepositoryListFactory.class);
        ProgrammeRepository programmeRepo = new ProgrammeRepository(programmeFactory,programmeRepoListFactory);

        US11_RegisterProgrammeInTheSystem controller1 = new US11_RegisterProgrammeInTheSystem(programmeRepo);

        // Criar dados de entrada
        String name = "Engenharia Informática";
        String acronym = "EI";
        int quantityOfEcts = 30;
        int quantityOfSemesters = 6;
        DegreeType degreeType = mock(DegreeType.class);
        Department department = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = mock(ProgrammeCourseListFactoryImpl.class);
        CourseInStudyPlanFactory courseInStudyPlanFactory = mock(CourseInStudyPlanFactory.class);
        StudyPlanListFactory studyPlanListFactory = mock(StudyPlanListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        CourseFactory courseFactory = mock(CourseFactory.class);

        boolean result = controller1.registerProgrammeInTheSystem(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, teacher, programmeCourseListFactoryImpl1, courseInStudyPlanFactory ,studyPlanListFactory, studyPlanFactory, courseFactory);

        assertTrue(result);
    }
}