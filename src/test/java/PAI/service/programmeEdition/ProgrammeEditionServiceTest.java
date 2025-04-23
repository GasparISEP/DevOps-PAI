package PAI.service.programmeEdition;

import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEditionServiceTest {
    @Test
    void shouldCreateProgrammeEditionService() {
        //arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        //act
        ProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);
        //assert
        assertNotNull(programmeEditionService);
    }

}