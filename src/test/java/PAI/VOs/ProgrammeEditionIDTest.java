package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEditionIDTest {

    // constructor Tests
    @Test
    void shouldCreateProgrammeEditionID() throws Exception {
        // Arrange
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // Act
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);

        // Assert
        assertNotNull(programmeEditionID);
    }

    @Test
    void shouldNotCreateProgrammeEditionIDIfSchoolYearIsNull() throws Exception {
        // Arrange
        SchoolYearID schoolYearID = null;
        ProgrammeID programmeID = mock(ProgrammeID.class);

        // Act
        Exception exception = assertThrows(Exception.class, () -> new ProgrammeEditionID(programmeID, schoolYearID));

        // Assert
        assertEquals("schoolYearID cannot be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeEditionIDIfProgrammeIDIsNull() throws Exception {
        // Arrange
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeID programmeID = null;

        // Act
        Exception exception = assertThrows(Exception.class, () -> new ProgrammeEditionID(programmeID, schoolYearID));

        // Assert
        assertEquals("programmeID cannot be null", exception.getMessage());
    }

    // equals Test
    @Test
    void shouldReturnTrueIfProgrammeEditionIDIsComparedToItSelf() throws Exception {
        // Arrange
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);

        // Act
        boolean result = programmeEditionID.equals(programmeEditionID);

        // Assert
        assertTrue(result);
    }

    // toString Tests
    @Test
    void shouldReturnExpectedStringRepresentation() throws Exception {
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