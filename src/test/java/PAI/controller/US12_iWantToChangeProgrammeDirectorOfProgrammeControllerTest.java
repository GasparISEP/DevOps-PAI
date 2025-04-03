package PAI.controller;

import PAI.VOs.ProgrammeID;
import PAI.VOs.TeacherID;
import PAI.domain.Teacher;
import PAI.domain.programme.ProgrammeDDD;
import PAI.repository.ITeacherRepository;
import PAI.repository.programmeRepo.IProgrammeDDDRepository;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US12_iWantToChangeProgrammeDirectorOfProgrammeControllerTest {
    @Test
    void shouldCreateController() throws Exception{
        //arrange
        IProgrammeDDDRepository programmeRepo = mock(IProgrammeDDDRepository.class);
        ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
        US12_iWantToChangeProgrammeDirectorOfProgrammeController controller = new US12_iWantToChangeProgrammeDirectorOfProgrammeController(programmeRepo, teacherRepo);
        //assert
        assertNotNull(controller);
    }

    @Test
    void shouldCreateAnExceptionWhenRepoIsNull(){
        //assert
        assertThrows(Exception.class, () -> new US12_iWantToChangeProgrammeDirectorOfProgrammeController(null, null));
    }

    @Test
    void shouldReturnTrueWhenDirectorIsChanged() throws Exception{
        //arrange
        IProgrammeDDDRepository programmeList = mock(IProgrammeDDDRepository.class);
        ITeacherRepository teacherRepo = mock(ITeacherRepository.class);
        US12_iWantToChangeProgrammeDirectorOfProgrammeController controller = new US12_iWantToChangeProgrammeDirectorOfProgrammeController(programmeList, teacherRepo);
        Teacher teacher = mock(Teacher.class);
        ProgrammeDDD programme = mock(ProgrammeDDD.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        TeacherID teacherID = mock(TeacherID.class);

        when(programmeList.findProgrammeIdByProgramme(programme)).thenReturn(Optional.of(programmeID));
        when(teacherRepo.findTeacherIdByTeacher(teacher)).thenReturn(Optional.of(teacherID));


        //act
        boolean result = controller.changeProgrammeDirector(programme,teacher);
        //assert
        assertTrue(result);
    }


}