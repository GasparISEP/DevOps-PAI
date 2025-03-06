package PAI.controller;

import PAI.domain.*;
import PAI.factory.ProgrammeCourseListFactory;
import PAI.factory.ProgrammeFactory;
import PAI.factory.ProgrammeListArrayListFactory;
import PAI.repository.ProgrammeList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US11_RegisterProgrammeInTheSystemTest {

    @Test
    void newProgrammeList() throws Exception {
        //arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
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

        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
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

        boolean result = controller1.registerProgrammeInTheSystem(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, teacher, programmeCourseListFactory);

        assertTrue(result);
    }

}