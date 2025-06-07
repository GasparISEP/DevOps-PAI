package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionGeneratedIDTest {

    @Test
    void shouldCreateCourseEditionGeneratedIDWithUUID() {
        //Arrange
        UUID uuid = UUID.randomUUID();

        //Act
        CourseEditionGeneratedID courseEditionId = new CourseEditionGeneratedID(uuid);

        //Assert
        assertNotNull(courseEditionId);
    }

    @Test
    void shouldCreateCourseEditionGeneratedIDWithRandomUUID() {
        //Arrange + Act
        CourseEditionGeneratedID courseEditionId = new CourseEditionGeneratedID();

        //Assert
        assertNotNull(courseEditionId);
    }

    @Test
    void shouldThrowExceptionWhenCreatingCourseEditionGeneratedIDWithNullUUID() {
        //Arrange
        UUID uuid = null;

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionGeneratedID(uuid);
        });
    }

    @Test
    void shouldReturnCorrectCourseEditionGeneratedID() {
        //Arrange
        UUID uuid = UUID.randomUUID();
        CourseEditionGeneratedID courseEditionId = new CourseEditionGeneratedID(uuid);

        //Act
        UUID returnedID= courseEditionId.getCourseEditionGeneratedID();

        //Assert
        assertEquals(uuid, returnedID);
    }

}