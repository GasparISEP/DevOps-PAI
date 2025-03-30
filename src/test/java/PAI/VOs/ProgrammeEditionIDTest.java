package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    void shouldReturnTrueIfUUIDIsComparedToItSelf() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        boolean result = id.equals(id);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfUUIDIsComparedToANullUUID() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = null;

        // Act
        boolean result = id1.equals(id2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfUUIDIsComparedToAnObjectThatIsNotAInstanceOfUUID() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID();

        // Act
        boolean result = id1.equals(programmeEditionID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTwoUUIDsAreTheSame() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = id1;

        // Act
        boolean result = id1.equals(id2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTwoUUIDsAreNotTheSame() {
        // Arrange
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        // Act
        boolean result = id1.equals(id2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnExpectedStringRepresentation() {
        // Arrange
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID();
        String idString = programmeEditionID.getProgrammeEditionID().toString();

        // Act
        String result = programmeEditionID.toString();

        // Assert
        assertEquals(idString, result);
    }

    @Test
    void shouldReturnDifferentStringsForDifferentInstances() {
        // Arrange
        ProgrammeEditionID id1 = new ProgrammeEditionID();
        ProgrammeEditionID id2 = new ProgrammeEditionID();
        String id1String1 = id1.toString();

        // Act
        String result = id2.toString();

        // Assert
        assertNotEquals(id1String1, result);
    }
}