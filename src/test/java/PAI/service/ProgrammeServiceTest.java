package PAI.service;

import PAI.factory.IProgrammeFactory;
import PAI.repository.programmeRepository.IProgrammeRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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

  
}