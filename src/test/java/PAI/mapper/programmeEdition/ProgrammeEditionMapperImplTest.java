package PAI.mapper.programmeEdition;

import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEditionMapperImplTest {

    @Test
    void shouldCreateProgrammeEditionMapperWithParameters() {
        // arrange
        IProgrammeEditionFactory programmeEditionFactory = mock(IProgrammeEditionFactory.class);
        // act
        ProgrammeEditionMapperImpl programmeEditionMapper = new ProgrammeEditionMapperImpl(programmeEditionFactory);
        // assert
        assertNotNull(programmeEditionMapper);
    }

    @Test
    void shouldNotCreateProgrammeEditionMapperWithProgrammeEditionFactoryNull() {
        // arrange
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionMapperImpl(null));
    }

}