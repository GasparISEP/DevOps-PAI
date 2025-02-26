package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEditionFactoryTest {

    @Test
    void shouldCreateProgrammeEdition() throws Exception {
        // Arrange
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);

        // Act
        ProgrammeEdition programmeEdition = programmeEditionFactory.createProgrammeEdition(programme, schoolYear);

        // Assert
        assertNotNull(programmeEdition);
    }
}