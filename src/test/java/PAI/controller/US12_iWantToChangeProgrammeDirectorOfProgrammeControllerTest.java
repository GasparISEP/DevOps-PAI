package PAI.controller;

import PAI.VOs.ProgrammeID;
import PAI.VOs.TeacherID;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US12_iWantToChangeProgrammeDirectorOfProgrammeControllerTest {
    @Test
    void shouldCreateController() throws Exception{
        //arrange
        ProgrammeDDDRepositoryImpl programmeRepo = mock(ProgrammeDDDRepositoryImpl.class);
        US12_iWantToChangeProgrammeDirectorOfProgrammeController controller = new US12_iWantToChangeProgrammeDirectorOfProgrammeController(programmeRepo);
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
        ProgrammeDDDRepositoryImpl programmeList = mock(ProgrammeDDDRepositoryImpl.class);
        US12_iWantToChangeProgrammeDirectorOfProgrammeController controller = new US12_iWantToChangeProgrammeDirectorOfProgrammeController(programmeList);
        TeacherID teacher = mock(TeacherID.class);
        ProgrammeID programme = mock(ProgrammeID.class);
        //act
        boolean result = controller.changeProgrammeDirector(programme,teacher);
        //assert
        assertTrue(result);
    }


}