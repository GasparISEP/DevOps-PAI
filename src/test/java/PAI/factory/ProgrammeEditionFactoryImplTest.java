package PAI.factory;

import PAI.domain.Programme;
import PAI.domain.ProgrammeEdition;
import PAI.domain.SchoolYear;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionFactoryImplTest {

    @Test
    void shouldCreateProgrammeEdition() throws Exception {
        // Arrange
        ProgrammeEditionFactoryImpl programmeEditionFactoryImpl = new ProgrammeEditionFactoryImpl();
        Programme programme = mock(Programme.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        when(programme.getProgrammeName()).thenReturn("Programme");

        // Act
        ProgrammeEdition programmeEdition = programmeEditionFactoryImpl.createProgrammeEdition(programme, schoolYear);

        // Assert
        assertNotNull(programmeEdition);
        assertEquals("Programme", programmeEdition.findProgrammeInProgrammeEdition().getProgrammeName());
        assertEquals(programmeEdition.findSchoolYearInProgrammeEdition(), schoolYear);
    }
}