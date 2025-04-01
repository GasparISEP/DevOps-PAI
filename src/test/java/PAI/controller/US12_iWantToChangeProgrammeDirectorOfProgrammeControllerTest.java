package PAI.controller;

import PAI.VOs.TeacherID;
import PAI.domain.Programme;
import PAI.domain.programme.ProgrammeDDD;
import PAI.repository.ProgrammeRepository;
import PAI.domain.Teacher;
import PAI.repository.programmeRepo.ProgrammeDDDRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US12_iWantToChangeProgrammeDirectorOfProgrammeControllerTest {
    @Test
    void shouldCreateController() throws Exception{
        //arrange
        ProgrammeDDDRepository programmeRepo = mock(ProgrammeDDDRepository.class);
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
        ProgrammeDDDRepository programmeList = mock(ProgrammeDDDRepository.class);
        US12_iWantToChangeProgrammeDirectorOfProgrammeController controller = new US12_iWantToChangeProgrammeDirectorOfProgrammeController(programmeList);
        TeacherID teacher = mock(TeacherID.class);
        ProgrammeDDD programme = mock(ProgrammeDDD.class);
        //act
        boolean result = controller.changeProgrammeDirector(programme,teacher);
        //assert
        assertTrue(result);
    }


}