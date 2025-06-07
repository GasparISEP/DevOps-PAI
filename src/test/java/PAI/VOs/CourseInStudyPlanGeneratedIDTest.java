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


}