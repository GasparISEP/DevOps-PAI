package PAI.repository.programmeEditionRepository;


import PAI.domain.programmeEdition.ProgrammeEditionDDD;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionDDDListFactoryImplTest {

    @Test
    void shouldCreateAccessMethodListFactory(){
        // Arrange

        IProgrammeEditionDDDListFactory programmeEditionDDDListFactory = new ProgrammeEditionDDDListFactoryImpl();

        // Act
        Set<ProgrammeEditionDDD> programmeEditions = programmeEditionDDDListFactory.createProgrammeEditionList();

        //assert
        assertNotNull(programmeEditions);
        assertTrue(programmeEditions.isEmpty());
        assertInstanceOf(Set.class, programmeEditions);
    }
}