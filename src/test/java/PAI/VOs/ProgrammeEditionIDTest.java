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
    void getProgrammeEditionID() {
        // Arrange
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID();

        // Act
        String id = programmeEditionID.getProgrammeEditionID();

        assertEquals("5", id);
    }


}