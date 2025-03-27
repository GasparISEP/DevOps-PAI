package PAI.controller;

import PAI.VOs.QuantEcts;
import PAI.VOs.QuantSemesters;
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
        QuantSemesters quantityOfSemesters = new QuantSemesters(6);
        DegreeType degreeType = mock(DegreeType.class);
        Department department = mock(Department.class);
        Teacher teacher = mock(Teacher.class);
        IProgrammeCourseListFactory programmeCourseListFactory = mock(IProgrammeCourseListFactory.class);
        ICourseInStudyPlanFactory ICourseInStudyPlanFactory = mock(ICourseInStudyPlanFactory.class);
        IStudyPlanListFactory IStudyPlanListFactory = mock(IStudyPlanListFactory.class);
        IStudyPlanFactory IStudyPlanFactory = mock(IStudyPlanFactory.class);
        ICourseFactory ICourseFactory = mock(ICourseFactory.class);

        //act
        boolean result = controller1.registerProgrammeInTheSystem(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, teacher, programmeCourseListFactory, ICourseInStudyPlanFactory, IStudyPlanListFactory, IStudyPlanFactory, ICourseFactory);

        //assert
        assertTrue(result);
    }
}