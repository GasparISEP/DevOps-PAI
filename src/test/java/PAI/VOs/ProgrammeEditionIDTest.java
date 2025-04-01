package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    void shouldReturnFalseIfProgrammeEditionIDIsComparedToANull() throws Exception {
        // Arrange
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);

        // Act
        boolean result = programmeEditionID.equals(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionIDIsComparedToADifferentInstanceOfObject() throws Exception {
        // Arrange
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);

        // Act
        boolean result = programmeEditionID.equals(schoolYearID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTwoProgrammeEditionIDHaveTheSameValue() throws Exception {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = spy(SchoolYearID.class);

        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID, schoolYearID);
        ProgrammeEditionID programmeEditionID2 = new ProgrammeEditionID(programmeID, schoolYearID);

        // Act
        boolean result = programmeEditionID1.equals(programmeEditionID2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoProgrammeEditionIDHaveTheSameSchoolYearIDButDifferentProgrammeID() throws Exception {
        // Arrange
        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);

        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID);
        ProgrammeEditionID programmeEditionID2 = new ProgrammeEditionID(programmeID2, schoolYearID);

        // Act
        boolean result = programmeEditionID1.equals(programmeEditionID2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTwoProgrammeEditionIDHaveTheSameProgrammeIDButDifferentSchoolYearID() throws Exception {
        // Arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID1 = mock(SchoolYearID.class);
        SchoolYearID schoolYearID2 = mock(SchoolYearID.class);

        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID, schoolYearID1);
        ProgrammeEditionID programmeEditionID2 = new ProgrammeEditionID(programmeID, schoolYearID2);

        // Act
        boolean result = programmeEditionID1.equals(programmeEditionID2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTwoProgrammeEditionIDHaveDifferentProgrammeIDAndSchoolYearID() throws Exception {
        // Arrange
        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        SchoolYearID schoolYearID1 = mock(SchoolYearID.class);
        SchoolYearID schoolYearID2 = mock(SchoolYearID.class);

        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);
        ProgrammeEditionID programmeEditionID2 = new ProgrammeEditionID(programmeID2, schoolYearID2);

        // Act
        boolean result = programmeEditionID1.equals(programmeEditionID2);

        // Assert
        assertFalse(result);
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