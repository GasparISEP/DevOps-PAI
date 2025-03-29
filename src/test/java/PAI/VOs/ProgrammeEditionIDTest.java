package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionIDTest {

    @Test
    void shouldCreateProgrammeEditionID() {
        // Arrange

        // Act
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID();

        // Assert
        assertNotNull(programmeEditionID);
    }

    @Test
    void shouldReturnProgrammeEditionID() {
        // Arrange
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID();

        // Act
        String id = programmeEditionID.getProgrammeEditionID();

        // Assert
        assertEquals("5", id);
    }

    @Test
    void shouldReturnTrueIfProgrammeEditionIDIsEqualToOtherProgrammeEditionID() {
        // Arrange
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID();

        // Act
        boolean result = programmeEditionID.equals(new ProgrammeEditionID());

        // Assert
        assertTrue(result);
    }
}