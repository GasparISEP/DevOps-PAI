package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseIDTest {

    @Test
    void shouldCreateCourseID() {
        //arrange + act
        CourseID id = new CourseID();
        //assert
        assertNotNull(id);
    }

    @Test
    void shouldReturnsEqualsIfObjectsAreEqual() {
        //Arrange
        CourseID courseID = new CourseID();
        CourseID courseID2 = courseID;
        //Act+Assert
        assertEquals(courseID, courseID2);
    }

    @Test
    void shouldReturnNotEqualsIfComparingWithNull() {
        //Arrange
        CourseID courseID = new CourseID();
        CourseID courseID2 = null;
        //Act+Assert
        assertNotEquals(courseID, courseID2);
    }

    @Test
    void shouldReturnNotEqualsIfObjectsAreNotFromSameClass() {
        //Arrange
        CourseID courseID = new CourseID();
        CourseEditionID courseEditionID = new CourseEditionID();
        //Act+Assert
        assertNotEquals(courseID, courseEditionID);
    }

    @Test
    void shouldReturnNotEqualsIfCourseIDsHaveDifferentUUIDs() {
        //Arrange
        CourseID courseID = new CourseID();
        CourseID courseID2 = new CourseID();
        //Act+Assert
        assertNotEquals(courseID, courseID2);
    }
}