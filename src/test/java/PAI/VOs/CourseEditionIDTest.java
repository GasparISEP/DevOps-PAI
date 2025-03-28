package PAI.VOs;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class CourseEditionIDTest {

    @Test
    void shouldReturnCourseEditionIDNotNull() {
        //Act
        CourseEditionID courseEditionID = new CourseEditionID();
        //Assert
        assertNotNull(courseEditionID);
    }

    @Test
    void shouldReturnCorrectUUIDString() {
        //Arrange
        CourseEditionID courseEditionID = new CourseEditionID();
        //Act
        UUID uuid = UUID.fromString(courseEditionID.toString());
        //Assert
        assertEquals(courseEditionID.toString(), uuid.toString());
    }

    @Test
    void shouldReturnsEqualsIfObjectsAreEqual() {
        CourseEditionID courseEditionID = new CourseEditionID();
        CourseEditionID courseEditionID2 = courseEditionID;

        assertEquals(courseEditionID, courseEditionID2);
    }

    @Test
    void shouldReturnEqualsIfDifferentObjectsHaveSameUUID() {
        //Arrange
        UUID uuid = UUID.randomUUID();
        CourseEditionID courseEditionID = new CourseEditionID();
        CourseEditionID courseEditionID2 = new CourseEditionID();

        //Act
        try {
            var field = CourseEditionID.class.getDeclaredField("_courseEditionId");
            field.setAccessible(true);
            field.set(courseEditionID, uuid);
            field.set(courseEditionID2, uuid);
        } catch (Exception e) {
            fail("Falha ao modificar UUID para teste");
        }
        //Assert
        assertEquals(courseEditionID, courseEditionID2);
    }

    @Test
    void shouldReturnNotEqualsIfCourseEditionIDsHaveDifferentUUIDs() {
        //Arrange
        CourseEditionID courseEditionID = new CourseEditionID();
        CourseEditionID courseEditionID2 = new CourseEditionID();
        //Act+Assert
        assertNotEquals(courseEditionID, courseEditionID2);
    }

    @Test
    void shouldReturnNotEqualsIfComparingWithNull() {
        //Arrange
        CourseEditionID courseEditionID = new CourseEditionID();
        CourseEditionID courseEditionID2 = null;
        //Act+Assert
        assertNotEquals(courseEditionID2, courseEditionID);
    }

    @Test
    void shouldReturnNotEqualsIfObjectsAreNotFromSameClass() {
        CourseEditionID courseEditionID = new CourseEditionID();
        CourseID courseID = new CourseID();
        assertNotEquals(courseEditionID, courseID);
    }

}