package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEditionIDTest {

    @Test
    void shouldCreateProgrammeEditionID() {
        // Arrange
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // Act
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);

        // Assert
        assertNotNull(programmeEditionID);
    }

    @Test
    void shouldReturnTrueIfProgrammeEditionIDIsComparedToItSelf() {
        // Arrange
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);

        // Act
        boolean result = programmeEditionID.equals(programmeEditionID);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnExpectedStringRepresentation() {
        // Arrange
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);

        // Act
        String result = programmeEditionID.toString();

        // Assert
        assertEquals("testing toString", result);
    }
}