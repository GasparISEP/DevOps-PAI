package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CourseEditionGeneratedIDTest {

    @Test
    void shouldCreateCourseEditionGeneratedIDWithUUID() {
        UUID uuid = UUID.randomUUID();
        CourseEditionGeneratedID courseEditionId = new CourseEditionGeneratedID(uuid);
        assertNotNull(courseEditionId);
    }

    @Test
    void shouldCreateCourseEditionGeneratedIDWithRandomUUID() {
        CourseEditionGeneratedID courseEditionId = new CourseEditionGeneratedID();
        assertNotNull(courseEditionId);
    }

    @Test
    void shouldThrowExceptionWhenCreatingCourseEditionGeneratedIDWithNullUUID() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionGeneratedID(null);
        });
    }

}