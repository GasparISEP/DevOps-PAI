package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseInStudyPlanGeneratedIDTest {

    @Test
    void constructor_shouldCreateWithValidUUID() {
        UUID uuid = UUID.randomUUID();
        CourseInStudyPlanGeneratedID id = new CourseInStudyPlanGeneratedID(uuid);
        assertEquals(uuid, id.getId());
    }

    @Test
    void constructor_shouldThrowException_whenUUIDIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new CourseInStudyPlanGeneratedID(null)
        );
        assertEquals("Course In Study Plan Generated ID cannot be null", exception.getMessage());
    }

    @Test
    void randomID_shouldReturnNewInstanceWithNonNullUUID() {
        CourseInStudyPlanGeneratedID id = CourseInStudyPlanGeneratedID.randomID();
        assertNotNull(id);
        assertNotNull(id.getId());
    }

    @Test
    void fromString_shouldCreateWithValidUUIDString() {
        UUID uuid = UUID.randomUUID();
        CourseInStudyPlanGeneratedID id = CourseInStudyPlanGeneratedID.fromString(uuid.toString());
        assertEquals(uuid, id.getId());
    }

    @Test
    void fromString_shouldThrowException_whenStringIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> CourseInStudyPlanGeneratedID.fromString(null)
        );
        assertEquals("ID string cannot be null or empty", exception.getMessage());
    }

    @Test
    void fromString_shouldThrowException_whenStringIsEmpty() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> CourseInStudyPlanGeneratedID.fromString("")
        );
        assertEquals("ID string cannot be null or empty", exception.getMessage());
    }
}