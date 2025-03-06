package PAI.controller;

import PAI.domain.Programme;
import PAI.repository.ProgrammeList;
import PAI.domain.Teacher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US12_iWantToChangeProgrammeDirectorOfProgrammeControllerTest {
    @Test
    void shouldCreateController() throws Exception{
        //arrange
        ProgrammeList programmeList = mock(ProgrammeList.class);
        US12_iWantToChangeProgrammeDirectorOfProgrammeController controller = new US12_iWantToChangeProgrammeDirectorOfProgrammeController(programmeList);
        //assert
        assertNotNull(controller);
    }

    @Test
    void shouldCreateAnExceptionWhenRepoIsNull(){
        //assert
        assertThrows(Exception.class, () -> new US12_iWantToChangeProgrammeDirectorOfProgrammeController(null));
    }

    @Test
    void shouldReturnTrueWhenDirectorIsChanged() throws Exception{
        //arrange
        ProgrammeList programmeList = mock(ProgrammeList.class);
        US12_iWantToChangeProgrammeDirectorOfProgrammeController controller = new US12_iWantToChangeProgrammeDirectorOfProgrammeController(programmeList);
        Teacher teacher = mock(Teacher.class);
        Programme programme = mock(Programme.class);
        //act
        boolean result = controller.changeProgrammeDirector(programme,teacher);
        //assert
        assertTrue(result);
    }


}