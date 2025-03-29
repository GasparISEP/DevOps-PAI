package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.UUID;

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
        UUID id = programmeEditionID.getProgrammeEditionID();

        // Assert
        assertNotNull(id);
        assertEquals(id, programmeEditionID.getProgrammeEditionID());
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