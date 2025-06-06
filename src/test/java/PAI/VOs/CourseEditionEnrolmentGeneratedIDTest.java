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
    public void testingHashCodeForDifferentUUID() {
        //Arrange
        CourseEditionEnrolmentGeneratedID ceeGeneratedID1 = new CourseEditionEnrolmentGeneratedID();
        CourseEditionEnrolmentGeneratedID ceeGeneratedID2 = new CourseEditionEnrolmentGeneratedID();
        //Act + Assert
        assertNotEquals(ceeGeneratedID1.hashCode(), ceeGeneratedID2.hashCode());
    }

    @Test
    public void shouldTransformTransformUUIDToString() {
        //Arrange
        CourseEditionEnrolmentGeneratedID ceeGeneratedID = new CourseEditionEnrolmentGeneratedID();
        //Act
        String uuidString = ceeGeneratedID.toString();
        //Assert
        assertTrue(uuidString.matches("^[0-9a-fA-F-]{36}$"));
    }
}