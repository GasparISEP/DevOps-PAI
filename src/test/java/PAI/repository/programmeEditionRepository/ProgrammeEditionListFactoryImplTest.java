package PAI.repository.programmeEditionRepository;


import PAI.domain.programmeEdition.ProgrammeEdition;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionListFactoryImplTest {

    @Test
    void shouldCreateAccessMethodListFactory(){
        // Arrange

        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();

        // Act
        Set<ProgrammeEdition> programmeEditions = programmeEditionDDDListFactory.createProgrammeEditionList();

        //assert
        assertNotNull(programmeEditions);
        assertTrue(programmeEditions.isEmpty());
        assertInstanceOf(Set.class, programmeEditions);
    }
}