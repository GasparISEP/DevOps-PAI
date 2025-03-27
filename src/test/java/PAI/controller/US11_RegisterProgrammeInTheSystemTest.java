package PAI.controller;

import PAI.VOs.QuantEcts;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.ProgrammeRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US11_RegisterProgrammeInTheSystemTest {

    @Test
    void newProgrammeList() throws Exception {
        //arrange
        ProgrammeRepository programmeRepo = mock(ProgrammeRepository.class);

        //act
        US11_RegisterProgrammeInTheSystem controller1 = new US11_RegisterProgrammeInTheSystem(programmeRepo);

        //assert
        assertNotNull(controller1);
    }

    @Test
    void nullProgrammeInTheSystemFailure() throws IllegalArgumentException{
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
        //arrange
        ProgrammeRepository programmeRepo = mock(ProgrammeRepository.class);

        US11_RegisterProgrammeInTheSystem controller1 = new US11_RegisterProgrammeInTheSystem(programmeRepo);

        // Criar dados de entrada
        String name = "Engenharia Informática";
        String acronym = "EI";
        QuantEcts quantityOfEcts = new QuantEcts(30);
        int quantityOfSemesters = 6;
        DegreeType degreeType = mock(DegreeType.class);
        Department department = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        IProgrammeCourseListFactory programmeCourseListFactory = mock(IProgrammeCourseListFactory.class);
        CourseInStudyPlanFactory courseInStudyPlanFactory = mock(CourseInStudyPlanFactory.class);
        StudyPlanListFactory studyPlanListFactory = mock(StudyPlanListFactory.class);
        StudyPlanFactory studyPlanFactory = mock(StudyPlanFactory.class);
        ICourseFactory ICourseFactory = mock(ICourseFactory.class);

        //act
        boolean result = controller1.registerProgrammeInTheSystem(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanListFactory, studyPlanFactory, ICourseFactory);

        //assert
        assertTrue(result);
    }
}