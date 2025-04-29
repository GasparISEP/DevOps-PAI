package PAI.service;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.domain.programme.Programme;
import PAI.factory.IProgrammeFactory;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.service.programme.ProgrammeServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeServiceImplTest {

    @Test
    void shouldCreateProgrammeService() {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);

        //Act
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        //Assert
        assertNotNull(service);
    }

    @Test
    void shouldThrowExceptionIfProgrammeRepositoryIsNull() {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = null;

        //Act+Assert
        assertThrows(Exception.class, () -> new ProgrammeServiceImpl(programmeFactory, programmeRepository));
    }

    @Test
    void shouldThrowExceptionIfProgrammeFactoryIsNull() {
        //Arrange
        IProgrammeFactory programmeFactory = null;
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);


        //Act+Assert
        assertThrows(Exception.class, () -> new ProgrammeServiceImpl(programmeFactory, programmeRepository));
    }

    @Test
    void shouldRegisterProgramme() throws Exception {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
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

        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        //Act
        boolean result = service.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);

        //Assert
        assertNotNull(result);
    }

    @Test
    void shouldChangeProgrammeDirector() throws Exception {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        Programme programme = mock(Programme.class);
        Teacher teacher = mock(Teacher.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        TeacherID teacherID = mock(TeacherID.class);

        when(programmeRepository.ofIdentity(programmeID)).thenReturn(Optional.of(programme));
        when(programme.identity()).thenReturn(programmeID);
        when(teacher.identity()).thenReturn(teacherID);

        //Act
        boolean result = service.changeProgrammeDirector(programmeID,teacherID);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldNotChangeProgrammeDirectorIfProgrammeIsNull() throws Exception {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ProgrammeID programmeID = null;
        TeacherID teacherID  = mock(TeacherID.class);

        //Act+Assert
        assertThrows(Exception.class, () -> service.changeProgrammeDirector(programmeID, teacherID));
    }

    @Test
    void shouldNotChangeProgrammeDirectorIfTeacherIsNull() throws Exception {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);
        ProgrammeServiceImpl service = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ProgrammeID programmeID  = mock(ProgrammeID.class);
        TeacherID teacherID = null;

        //Act+Assert
        assertThrows(Exception.class, () -> service.changeProgrammeDirector(programmeID, teacherID));

    }

  
}