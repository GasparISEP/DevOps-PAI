package PAI.factory;

import PAI.domain.Programme;
import PAI.domain.ProgrammeEdition;
import PAI.domain.SchoolYear;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionFactoryTest {

    @Test
    void shouldCreateProgrammeEdition() throws Exception {
        // Arrange
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        when(programme.getProgrammeName()).thenReturn("Programme");

        // Act
        ProgrammeEdition programmeEdition = programmeEditionFactory.createProgrammeEdition(programme, schoolYear);

        // Assert
        assertNotNull(programmeEdition);
        assertEquals("Programme", programmeEdition.findProgrammeInProgrammeEdition().getProgrammeName());
        assertEquals(programmeEdition.findSchoolYearInProgrammeEdition(), schoolYear);
    }
}