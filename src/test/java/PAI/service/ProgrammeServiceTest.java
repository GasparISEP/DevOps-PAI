package PAI.service;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.factory.IProgrammeFactory;
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

        //Act
        ProgrammeService service = new ProgrammeService(programmeFactory, programmeRepository);

        //Assert
        assertNotNull(service);
    }

    @Test
    void shouldThrowExceptionIfProgrammeRepositoryIsNull() {
        //Arrange
        IProgrammeFactory programmeFactory = mock(IProgrammeFactory.class);
        IProgrammeRepository programmeRepository = null;

        //Act+Assert
        assertThrows(Exception.class, () -> new ProgrammeService(programmeFactory, programmeRepository));
    }

    @Test
    void shouldThrowExceptionIfProgrammeFactoryIsNull() {
        //Arrange
        IProgrammeFactory programmeFactory = null;
        IProgrammeRepository programmeRepository = mock(IProgrammeRepository.class);

        //Act+Assert
        assertThrows(Exception.class, () -> new ProgrammeService(programmeFactory, programmeRepository));
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

        ProgrammeService service = new ProgrammeService(programmeFactory, programmeRepository);

        //Act
        Programme result = service.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);

        //Assert
        assertNotNull(result);
    }

  
}