package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEnrolmentGeneratedIDTest {

    @Test
    void constructor_shouldStoreUUID_whenGivenValidUUID() {
        // Arrange
        UUID uuid = UUID.randomUUID();

        // Act
        ProgrammeEnrolmentGeneratedID id = new ProgrammeEnrolmentGeneratedID(uuid);

        // Assert
        assertEquals(uuid, id.getProgrammeEnrolmentGID());
    }

    @Test
    void noArgsConstructor_shouldGenerateNonNullUUID() {
        // Act
        ProgrammeEnrolmentGeneratedID id = new ProgrammeEnrolmentGeneratedID();

        // Assert
        assertNotNull(id.getProgrammeEnrolmentGID());
    }

    @Test
    void equals_shouldReturnTrue_whenUUIDsAreEqual() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        ProgrammeEnrolmentGeneratedID id1 = new ProgrammeEnrolmentGeneratedID(uuid);
        ProgrammeEnrolmentGeneratedID id2 = new ProgrammeEnrolmentGeneratedID(uuid);

        // Assert
        assertEquals(id1, id2);
    }

    @Test
    void equals_shouldReturnFalse_whenUUIDsAreDifferent() {
        // Arrange
        ProgrammeEnrolmentGeneratedID id1 = new ProgrammeEnrolmentGeneratedID();
        ProgrammeEnrolmentGeneratedID id2 = new ProgrammeEnrolmentGeneratedID();

        // Assert
        assertNotEquals(id1, id2);
    }

    @Test
    void constructor_shouldThrowException_whenUUIDIsNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolmentGeneratedID(null));
    }

    @Test
    void toString_shouldReturnUUIDStringRepresentation() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        ProgrammeEnrolmentGeneratedID id = new ProgrammeEnrolmentGeneratedID(uuid);

        // Act
        String stringValue = id.toString();

        // Assert
        assertEquals(uuid.toString(), stringValue);
    }

    @Test
    void hashCode_shouldBeEqualForSameUUID() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        ProgrammeEnrolmentGeneratedID id1 = new ProgrammeEnrolmentGeneratedID(uuid);
        ProgrammeEnrolmentGeneratedID id2 = new ProgrammeEnrolmentGeneratedID(uuid);

        // Assert
        assertEquals(id1.hashCode(), id2.hashCode());
    }
}
