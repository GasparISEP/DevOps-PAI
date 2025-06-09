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


    @Test
    void shouldReturnSameHashCodeForEqualCourseEditionGeneratedIDs() {
        //Arrange
        UUID uuid = UUID.randomUUID();
        CourseEditionGeneratedID courseEditionId1 = new CourseEditionGeneratedID(uuid);
        CourseEditionGeneratedID courseEditionId2 = new CourseEditionGeneratedID(uuid);

        //Act + Assert
        assertEquals(courseEditionId1.hashCode(), courseEditionId2.hashCode());
    }

    @Test
    void shouldReturnDifferentHashCodeForDifferentCourseEditionGeneratedIDs() {
        //Arrange
        CourseEditionGeneratedID courseEditionId1 = new CourseEditionGeneratedID(UUID.randomUUID());
        CourseEditionGeneratedID courseEditionId2 = new CourseEditionGeneratedID(UUID.randomUUID());

        //Act + Assert
        assertNotEquals(courseEditionId1.hashCode(), courseEditionId2.hashCode());
    }

    @Test
    void shouldReturnFalseHashCodeForCourseEditionGeneratedID() {
        //Arrange
        UUID uuid = UUID.randomUUID();
        CourseEditionGeneratedID courseEditionId = new CourseEditionGeneratedID();

        //Act + Assert
        assertNotEquals(uuid.hashCode(), courseEditionId.hashCode());
    }

    @Test
    void shouldReturnCorrectStringRepresentationOfCourseEditionGeneratedID() {
        //Arrange
        UUID uuid = UUID.randomUUID();
        CourseEditionGeneratedID courseEditionId = new CourseEditionGeneratedID(uuid);

        //Act + Assert
        assertEquals(uuid.toString(), courseEditionId.toString());
    }


    @Test
    void shouldReturnTrueWhenComparingSameCourseEditionGeneratedID() {
        //Arrange
        UUID uuid = UUID.randomUUID();
        CourseEditionGeneratedID courseEditionId = new CourseEditionGeneratedID(uuid);

        //Act
        boolean result = courseEditionId.equals(courseEditionId);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenComparingEqualCourseEditionGeneratedIDs() {
        //Arrange
        UUID uuid = UUID.randomUUID();
        CourseEditionGeneratedID courseEditionId1 = new CourseEditionGeneratedID(uuid);
        CourseEditionGeneratedID courseEditionId2 = new CourseEditionGeneratedID(uuid);

        //Act
        boolean result= courseEditionId1.equals(courseEditionId2);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenComparingDifferentCourseEditionGeneratedIDs() {
        //Arrange
        CourseEditionGeneratedID courseEditionId1 = new CourseEditionGeneratedID(UUID.randomUUID());
        CourseEditionGeneratedID courseEditionId2 = new CourseEditionGeneratedID(UUID.randomUUID());

        //Act
        boolean result= courseEditionId1.equals(courseEditionId2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenComparingCourseEditionGeneratedIDWithNull() {
        //Arrange
        CourseEditionGeneratedID courseEditionId = new CourseEditionGeneratedID(UUID.randomUUID());

        //Act
        boolean result= courseEditionId.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenComparingCourseEditionGeneratedIDWithDifferentClass() {
        //Arrange
        CourseEditionGeneratedID courseEditionId = new CourseEditionGeneratedID(UUID.randomUUID());
        CourseGeneratedID courseGeneratedID = new CourseGeneratedID(UUID.randomUUID());

        //Act
        boolean result= courseEditionId.equals(courseGeneratedID);

        //Assert
        assertFalse(result);
    }

}