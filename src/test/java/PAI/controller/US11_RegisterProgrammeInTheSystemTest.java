package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US11_RegisterProgrammeInTheSystemTest {

    @Test
    void newProgrammeList() throws Exception {
        //arrange
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList list = new ProgrammeList(programmeFactory);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);

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
        // Criar as instâncias reais das classes necessárias
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);

        US11_RegisterProgrammeInTheSystem controller1 = new US11_RegisterProgrammeInTheSystem(programmeList);

        // Criar dados de entrada
        String name = "Engenharia Informática";
        String acronym = "EI";
        int quantityOfEcts = 30;
        int quantityOfSemesters = 6;
        DegreeType degreeType = mock(DegreeType.class);
        Department department = mock(Department.class);
        Teacher teacher = mock(Teacher.class);

        boolean result = controller1.registerProgrammeInTheSystem(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, teacher);

        assertTrue(result);
    }

}