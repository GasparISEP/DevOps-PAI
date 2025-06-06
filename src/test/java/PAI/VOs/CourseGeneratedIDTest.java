package PAI.VOs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class CourseGeneratedIDTest {

    @Test
    void shouldCreateCourseGeneratedIDWithUUID() {
        UUID uuid = UUID.randomUUID();
        CourseGeneratedID courseId = new CourseGeneratedID(uuid);
        assertNotNull(courseId);
        assertEquals(uuid, courseId.getCourseGeneratedID());
    }

    @Test
    void shouldCreateCourseGeneratedIDWithRandomUUID() {
        CourseGeneratedID courseId = new CourseGeneratedID();
        assertNotNull(courseId.getCourseGeneratedID());
    }

    @Test
    void shouldThrowExceptionWhenCreatingCourseGeneratedIDWithNullUUID() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CourseGeneratedID(null);
        });
    }

    @Test
    void shouldReturnTrueWhenComparingSameCourseGeneratedID() {
        UUID uuid = UUID.randomUUID();
        CourseGeneratedID courseId = new CourseGeneratedID(uuid);
        assertTrue(courseId.equals(courseId));
    }

    @Test
    void shouldReturnTrueWhenComparingEqualCourseGeneratedIDs() {
        UUID uuid = UUID.randomUUID();
        CourseGeneratedID courseId1 = new CourseGeneratedID(uuid);
        CourseGeneratedID courseId2 = new CourseGeneratedID(uuid);
        assertTrue(courseId1.equals(courseId2));
    }

    @Test
    void shouldReturnFalseWhenComparingDifferentCourseGeneratedIDs() {
        CourseGeneratedID courseId1 = new CourseGeneratedID(UUID.randomUUID());
        CourseGeneratedID courseId2 = new CourseGeneratedID(UUID.randomUUID());
        assertFalse(courseId1.equals(courseId2));
    }

    @Test
    void shouldReturnFalseWhenComparingCourseGeneratedIDWithNull() {
        CourseGeneratedID courseId = new CourseGeneratedID(UUID.randomUUID());
        assertFalse(courseId.equals(null));
    }

    @Test
    void shouldReturnFalseWhenComparingCourseGeneratedIDWithDifferentClass() {
        CourseGeneratedID courseId = new CourseGeneratedID(UUID.randomUUID());
        Name name = new Name("John Doe");
        assertFalse(courseId.equals(name));
    }

    @Test
    void shouldReturnSameHashCodeForEqualCourseGeneratedIDs() {
        UUID uuid = UUID.randomUUID();
        CourseGeneratedID courseId1 = new CourseGeneratedID(uuid);
        CourseGeneratedID courseId2 = new CourseGeneratedID(uuid);
        assertEquals(courseId1.hashCode(), courseId2.hashCode());
    }

    @Test
    void shouldReturnDifferentHashCodeForDifferentCourseGeneratedIDs() {
        CourseGeneratedID courseId1 = new CourseGeneratedID(UUID.randomUUID());
        CourseGeneratedID courseId2 = new CourseGeneratedID(UUID.randomUUID());
        assertNotEquals(courseId1.hashCode(), courseId2.hashCode());
    }

    @Test
    void shouldReturnCorrectStringRepresentationOfCourseGeneratedID() {
        UUID uuid = UUID.randomUUID();
        CourseGeneratedID courseId = new CourseGeneratedID(uuid);
        assertEquals(uuid.toString(), courseId.toString());
    }

    @Test
    void shouldReturnFalseHashCodeForCourseGeneratedID() {
        UUID uuid = UUID.randomUUID();
        CourseGeneratedID courseId = new CourseGeneratedID();
        assertNotEquals(uuid.hashCode(), courseId.hashCode());
    }

    @Test
    void shouldReturnCorrectCourseGeneratedID() {
        UUID uuid = UUID.randomUUID();
        CourseGeneratedID courseId = new CourseGeneratedID(uuid);
        assertEquals(uuid, courseId.getCourseGeneratedID());
    }
}
