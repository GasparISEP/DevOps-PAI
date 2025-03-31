package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseEditionEnrolmentIDTest {

    @Test
    void shouldReturnAValidCourseEditionEnrolmentID() {
        //arrange
        CourseEditionEnrolmentID ceeID = new CourseEditionEnrolmentID();

        //act & assert
        assertNotNull(ceeID.findCeeId());
    }

    @Test
    void testGeneratedIdsAreUnique() {
        //arrange
        CourseEditionEnrolmentID ceeID1 = new CourseEditionEnrolmentID();
        CourseEditionEnrolmentID ceeID2 = new CourseEditionEnrolmentID();

        // act & assert
        assertNotEquals(ceeID1.findCeeId(), ceeID2.findCeeId());
    }

    //testing equals method
    @Test
    void shouldReturnFalseWhenTwoIdAreDifferent() {
        //arrange
        CourseEditionEnrolmentID ceeID1 = new CourseEditionEnrolmentID();
        CourseEditionEnrolmentID ceeID2 = new CourseEditionEnrolmentID();

        //act
        boolean result = ceeID1.equals(ceeID2);

        //assert
        assertFalse (result);
    }

    @Test
    void shouldReturnFalseWhenIdIsNull() {
        //arrange
        CourseEditionEnrolmentID ceeID1 = new CourseEditionEnrolmentID();

        //act
        boolean result = ceeID1.equals(null);

        //assert
        assertFalse (result);
    }

    @Test
    void shouldReturnFalseWhenIdAreNotFromSameClass() {
        //arrange
        CourseEditionEnrolmentID courseEditionEnrolmentID = new CourseEditionEnrolmentID();
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        //act
        boolean result = courseEditionEnrolmentID.equals(courseEditionID);

        //assert
        assertFalse (result);
    }

    @Test
    void shouldReturnTrueWhenTwoIdHaveTheSameMemoryReference() {
        CourseEditionEnrolmentID courseEditionEnrolmentID1 = new CourseEditionEnrolmentID();
        CourseEditionEnrolmentID courseEditionEnrolmentID2 = courseEditionEnrolmentID1;

        assertEquals(courseEditionEnrolmentID1, courseEditionEnrolmentID2);
    }

    @Test
    void shouldReturnTrueWhenTwoIdHaveTheSameUUID() {
        //Arrange
        UUID uuid = UUID.randomUUID();
        CourseEditionEnrolmentID courseEditionEnrolmentID = new CourseEditionEnrolmentID();
        CourseEditionEnrolmentID courseEditionEnrolmentID2 = new CourseEditionEnrolmentID();

        //Act
        try {
            var field = CourseEditionEnrolmentID.class.getDeclaredField("_courseEditionEnrolmentId");
            field.setAccessible(true);
            field.set(courseEditionEnrolmentID, uuid);
            field.set(courseEditionEnrolmentID2, uuid);
        } catch (Exception e) {
            fail("Failed to modify UUID for test");
        }
        //Assert
        assertEquals(courseEditionEnrolmentID, courseEditionEnrolmentID2);
    }

    //testing hashCode method
    @Test
    void shouldReturnAHashCodeForOneId() {
        //Arrange
        CourseEditionEnrolmentID courseEditionEnrolmentID1 = new CourseEditionEnrolmentID();

        //Act
        int result = courseEditionEnrolmentID1.hashCode();

        //Assert
        assertNotNull (result);
    }

    @Test
    void shouldReturnTheSameHashCodeForTwoId() {
        //Arrange
        CourseEditionEnrolmentID courseEditionEnrolmentID1 = new CourseEditionEnrolmentID();
        int courseEditionEnrolmentID2 = courseEditionEnrolmentID1.hashCode();

        //Act
        int result = courseEditionEnrolmentID1.hashCode();

        //Assert
        assertEquals(courseEditionEnrolmentID2, result);
    }

    @Test
    void shouldReturnADifferentHashCodeForTwoIDs() {
        //Arrange
        CourseEditionEnrolmentID courseEditionEnrolmentID1 = new CourseEditionEnrolmentID();
        CourseEditionEnrolmentID courseEditionEnrolmentID2 = new CourseEditionEnrolmentID();

        //Act
        int result = courseEditionEnrolmentID1.hashCode();

        //Assert
        assertNotEquals(courseEditionEnrolmentID2.hashCode(), result);
    }

    //testing toString method
    @Test
    void shouldReturnAStringWithTheUUID() {
        //Arrange
        CourseEditionEnrolmentID courseEditionEnrolmentID = new CourseEditionEnrolmentID();

        //Act
        String uuidString = courseEditionEnrolmentID.toString();

        //Assert
        assertEquals(courseEditionEnrolmentID.findCeeId().toString(), uuidString);
    }
}