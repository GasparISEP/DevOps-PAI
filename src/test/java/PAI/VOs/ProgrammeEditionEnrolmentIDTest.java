package PAI.VOs;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionEnrolmentIDTest {

    @Test
    void shouldCreateNonNullProgrammeEditionEnrolmentID() {
        // Act
        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID();

        // Assert
        assertNotNull(enrolmentID, "Enrolment ID should not be null");
    }

    @Test
    void shouldReturnValidUUIDString() {
        // Arrange
        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID();

        // Act
        UUID uuid = UUID.fromString(enrolmentID.toString());

        // Assert
        assertEquals(enrolmentID.toString(), uuid.toString(), "UUID string should match the expected format");
    }

    @Test
    void shouldReturnEqualForSameInstance() {
        // Arrange
        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID();
        ProgrammeEditionEnrolmentID sameReference = enrolmentID;

        // Act & Assert
        assertEquals(enrolmentID, sameReference, "Same instance should be equal");
    }


    @Test
    void shouldReturnNotEqualForDifferentUUIDs() {
        // Arrange
        ProgrammeEditionEnrolmentID enrolmentID1 = new ProgrammeEditionEnrolmentID();
        ProgrammeEditionEnrolmentID enrolmentID2 = new ProgrammeEditionEnrolmentID();

        // Act & Assert
        assertNotEquals(enrolmentID1, enrolmentID2, "Objects with different UUIDs should not be equal");
    }

    @Test
    void shouldReturnNotEqualWhenComparedWithNull() {
        // Arrange
        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID();
        ProgrammeEditionEnrolmentID nullReference = null;

        // Act & Assert
        assertNotEquals(enrolmentID, nullReference, "Comparing with null should return false");
    }

    @Test
    void shouldReturnNotEqualForDifferentClassObjects() {
        // Arrange
        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID();
        Object differentClassObject = new Object();

        // Act & Assert
        assertNotEquals(enrolmentID, differentClassObject, "Objects of different classes should not be equal");
    }
}

