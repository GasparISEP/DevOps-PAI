package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudyPlanGeneratedIDTest {

    @Test
    void testContructorNotNull () {
        // Arrange & Act
        UUID uuid = UUID.randomUUID();
        StudyPlanGeneratedID generatedID = new StudyPlanGeneratedID(uuid);

        // Assert
        assertNotNull(uuid);
    }

    @Test
    void getUUIDShouldReturnUUID () {
        // Arrange
        UUID uuid = UUID.randomUUID();
        StudyPlanGeneratedID generatedID = new StudyPlanGeneratedID(uuid);

        // Act
        UUID result = generatedID.getUUID();

        // Assert
        assertEquals(result, uuid);
    }

    @Test
    void equalsShouldReturnTrueForSameObjectInstance() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        StudyPlanGeneratedID id = new StudyPlanGeneratedID(uuid);

        // Act + Assert
        assertTrue(id.equals(id));
    }

    @Test
    void equalsShouldReturnTrueForObjectsWithSameUUID() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        StudyPlanGeneratedID id1 = new StudyPlanGeneratedID(uuid);
        StudyPlanGeneratedID id2 = new StudyPlanGeneratedID(uuid);

        // Act + Assert
        assertTrue(id1.equals(id2));
    }

    @Test
    void equalsShouldReturnFalseForObjectsWithDifferentUUIDs() {
        // Arrange
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        StudyPlanGeneratedID id1 = new StudyPlanGeneratedID(uuid1);
        StudyPlanGeneratedID id2 = new StudyPlanGeneratedID(uuid2);

        // Act + Assert
        assertFalse(id1.equals(id2));
    }

    @Test
    void equalsShouldReturnFalseWhenComparingWithNull() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        StudyPlanGeneratedID id = new StudyPlanGeneratedID(uuid);

        // Act + Assert
        assertFalse(id.equals(null));
    }

    @Test
    void equalsShouldReturnFalseWhenComparingWithDifferentClass() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        StudyPlanGeneratedID id = new StudyPlanGeneratedID(uuid);
        Object differentObject = new Object();

        // Act + Assert
        assertFalse(id.equals(differentObject));
    }


    @Test
    void hashCodeShouldBeConsistentForSameObjectInstance() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        StudyPlanGeneratedID id = new StudyPlanGeneratedID(uuid);
        int hashCode1 = id.hashCode();
        int hashCode2 = id.hashCode();

        // Act + Assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void hashCodeShouldBeEqualForEqualObjects() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        StudyPlanGeneratedID id1 = new StudyPlanGeneratedID(uuid);
        StudyPlanGeneratedID id2 = new StudyPlanGeneratedID(uuid);

        // Act + Assert
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    void hashCodeShouldBeDifferentForUnequalObjects() {
        // Arrange
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        StudyPlanGeneratedID id1 = new StudyPlanGeneratedID(uuid1);
        StudyPlanGeneratedID id2 = new StudyPlanGeneratedID(uuid2);

        // Act + Assert
        assertNotEquals(id1.hashCode(), id2.hashCode());
    }


    @Test
    void toStringShouldReturnExpectedFormat() {
        // Arrange
        UUID uuid = UUID.randomUUID();
        StudyPlanGeneratedID id = new StudyPlanGeneratedID(uuid);
        String expectedToString = "StudyPlanGeneratedID{_uuid=" + uuid + "}";

        // Act + Assert
        assertEquals(expectedToString, id.toString());
    }
}