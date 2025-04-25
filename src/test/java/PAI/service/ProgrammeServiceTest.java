package PAI.service;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.domain.programme.Programme;
import PAI.factory.IProgrammeFactory;
import PAI.repository.ITeacherRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeServiceTest {

    @Test
    void shouldCreateProgrammeService() {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);

        //Act
        ProgrammeService service = new ProgrammeService(programmeFactory, programmeRepository, teacherRepository);

        //Assert
        assertNotNull(service);
    }

    @Test
    void shouldThrowExceptionIfProgrammeRepositoryIsNull() {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = null;
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);

        //Act+Assert
        assertThrows(Exception.class, () -> new ProgrammeService(programmeFactory, programmeRepository, teacherRepository));
    }

    @Test
    void shouldThrowExceptionIfProgrammeFactoryIsNull() {
        //Arrange
        IProgrammeFactory programmeFactory = null;
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);

        //Act+Assert
        assertThrows(Exception.class, () -> new ProgrammeService(programmeFactory, programmeRepository,teacherRepository));
    }

    @Test
    void shouldThrowExceptionIfTeacherRepositoryIsNull() {
        //Arrange
        ITeacherRepository teacherRepository = null;
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);

        //Act+Assert
        assertThrows(Exception.class, () -> new ProgrammeService(programmeFactory, programmeRepository,teacherRepository));
    }

    @Test
    void shouldRegisterProgramme() throws Exception {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts quantityOfEcts = mock(QuantEcts.class);
        QuantSemesters quantityOfSemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID departmentID = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme programme =mock(Programme.class);

        when(programmeFactory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID)).thenReturn(programme);
        when(programmeRepository.save(programme)).thenReturn(programme);

        ProgrammeService service = new ProgrammeService(programmeFactory, programmeRepository, teacherRepository);

        //Act
        Programme result = service.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);

        //Assert
        assertNotNull(result);
    }

    @Test
    void shouldChangeProgrammeDirector() throws Exception {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ProgrammeService service = new ProgrammeService(programmeFactory, programmeRepository, teacherRepository);

        Programme programme = mock(Programme.class);
        Teacher teacher = mock(Teacher.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        TeacherID teacherID = mock(TeacherID.class);

        when(programme.identity()).thenReturn(programmeID);
        when(teacher.identity()).thenReturn(teacherID);
        when(programmeRepository.changeProgrammeDirector(programmeID,teacherID)).thenReturn(true);

        //Act
        boolean result = service.changeProgrammeDirector(programme,teacher);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldNotChangeProgrammeDirectorIfProgrammeIsNull() throws Exception {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ProgrammeService service = new ProgrammeService(programmeFactory, programmeRepository, teacherRepository);

        Programme programme = null;
        Teacher teacher = mock(Teacher.class);

        //Act+Assert
        assertThrows(Exception.class, () -> service.changeProgrammeDirector(programme, teacher));
    }

    @Test
    void shouldNotChangeProgrammeDirectorIfTeacherIsNull() throws Exception {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ITeacherRepository teacherRepository = mock(ITeacherRepository.class);
        ProgrammeService service = new ProgrammeService(programmeFactory, programmeRepository, teacherRepository);

        Programme programme = mock(Programme.class);
        Teacher teacher = null;

        //Act+Assert
        assertThrows(Exception.class, () -> service.changeProgrammeDirector(programme, teacher));

    }

  
}