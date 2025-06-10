package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionEnrolmentGeneratedIDTest {

    @Test
    public void shouldConstructSchoolYearIDWithGeneratedUniqueUUID() {
        //Arrange

        //Act
        CourseEditionEnrolmentGeneratedID ceeGeneratedID1 = new CourseEditionEnrolmentGeneratedID();
        CourseEditionEnrolmentGeneratedID ceeGeneratedID2 = new CourseEditionEnrolmentGeneratedID();
        //Assert
        assertNotEquals(ceeGeneratedID1.getCourseEditionEnrolmentGeneratedID(), ceeGeneratedID2.getCourseEditionEnrolmentGeneratedID());
    }

    @Test
    public void shouldConstructCourseEditionEnrolmentGeneratedIDWithValidUUIDReceivedAsParameter() {
        //Arrange
        UUID validUUID = UUID.randomUUID();
        CourseEditionEnrolmentGeneratedID ceeGeneratedID = new CourseEditionEnrolmentGeneratedID(validUUID);
        //Act + Assert
        assertNotNull(ceeGeneratedID);
    }

    @Test
    public void shouldThrowExceptionIfParameterIsNull() {
        //Arrange

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new CourseEditionEnrolmentGeneratedID(null));
    }

    @Test
    public void shouldGetUUID() {
        //Arrange

        //Act
        CourseEditionEnrolmentGeneratedID ceeGeneratedID = new CourseEditionEnrolmentGeneratedID();
        //Assert
        assertEquals(CourseEditionEnrolmentGeneratedID.class, ceeGeneratedID.getClass());
    }

    @Test
    void equals_shouldReturnTrue_whenComparingSameInstance() {
        CourseEditionEnrolmentGeneratedID id = new CourseEditionEnrolmentGeneratedID(UUID.randomUUID());

        assertTrue(id.equals(id));
    }

    @Test
    void equals_shouldReturnFalse_whenComparingWithNull() {
        CourseEditionEnrolmentGeneratedID id = new CourseEditionEnrolmentGeneratedID(UUID.randomUUID());

        assertFalse(id.equals(null));
    }

    @Test
    void equals_shouldReturnFalse_whenComparingWithDifferentClass() {
        CourseEditionEnrolmentGeneratedID id = new CourseEditionEnrolmentGeneratedID(UUID.randomUUID());

        assertFalse(id.equals("String"));
    }

    @Test
    void equals_shouldReturnTrue_whenUUIDsAreEqual() {
        UUID sharedUUID = UUID.randomUUID();
        CourseEditionEnrolmentGeneratedID id1 = new CourseEditionEnrolmentGeneratedID(sharedUUID);
        CourseEditionEnrolmentGeneratedID id2 = new CourseEditionEnrolmentGeneratedID(sharedUUID);

        assertTrue(id1.equals(id2));
    }

    @Test
    void equals_shouldReturnFalse_whenUUIDsAreDifferent() {
        CourseEditionEnrolmentGeneratedID id1 = new CourseEditionEnrolmentGeneratedID(UUID.randomUUID());
        CourseEditionEnrolmentGeneratedID id2 = new CourseEditionEnrolmentGeneratedID(UUID.randomUUID());

        assertFalse(id1.equals(id2));
    }

    @Test
    void isEquals_shouldReturnTrue_whenUUIDsAreEqual() {
        //Arrange
        UUID uuid = UUID.randomUUID();
        CourseEditionEnrolmentGeneratedID generatedID = new CourseEditionEnrolmentGeneratedID(uuid);

        //Act
        boolean result = generatedID.isEquals(uuid);

        //Assert
        assertTrue(result);
    }

    @Test
    void isEquals_shouldReturnFalse_whenUUIDsAreDifferent() {
        //Arrange
        UUID uuidStored = UUID.randomUUID();
        UUID uuidCompared = UUID.randomUUID();
        CourseEditionEnrolmentGeneratedID generatedID = new CourseEditionEnrolmentGeneratedID(uuidStored);

        //Act
        boolean result = generatedID.isEquals(uuidCompared);

        //Assert
        assertFalse(result);
    }

    @Test
    void isEquals_shouldReturnFalse_whenUUIDIsNull() {
        //Arrange
        CourseEditionEnrolmentGeneratedID generatedID = new CourseEditionEnrolmentGeneratedID(UUID.randomUUID());

        //ACt
        boolean result = generatedID.isEquals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    public void testingHashCodeForDifferentUUID() {
        //Arrange
        CourseEditionEnrolmentGeneratedID ceeGeneratedID1 = new CourseEditionEnrolmentGeneratedID();
        CourseEditionEnrolmentGeneratedID ceeGeneratedID2 = new CourseEditionEnrolmentGeneratedID();
        //Act + Assert
        assertNotEquals(ceeGeneratedID1.hashCode(), ceeGeneratedID2.hashCode());
    }

    @Test
    public void shouldTransformUUIDToString() {
        //Arrange
        CourseEditionEnrolmentGeneratedID ceeGeneratedID = new CourseEditionEnrolmentGeneratedID();
        //Act
        String uuidString = ceeGeneratedID.toString();
        //Assert
        assertTrue(uuidString.matches("^[0-9a-fA-F-]{36}$"));
    }
}