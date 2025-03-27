package PAI.VOs;

import PAI.domain.Course;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseInStudyPlanIdTest {

    @Test
    void testDefaultConstructorCreatesNonNullId() {
        //arrange + act
        CourseInStudyPlanId id = new CourseInStudyPlanId();
        //assert
        assertNotNull(id.getCourseInStudyPlanId());
    }

    @Test
    void testEqualsMethodWithDifferentUUID() {
        //arrange + act
        CourseInStudyPlanId id1 = new CourseInStudyPlanId();
        CourseInStudyPlanId id2 = new CourseInStudyPlanId();
        //arrange
        assertNotEquals(id1, id2);
    }

    @Test
    void testHashCodeConsistency() {
        //arrange
        CourseInStudyPlanId id = new CourseInStudyPlanId();
        //act
        int hash1 = id.hashCode();
        int hash2 = id.hashCode();
        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    void testToStringReturnsUUIDString() {
        //arrange
        CourseInStudyPlanId id = new CourseInStudyPlanId();
        //act
        String uuidString = id.getCourseInStudyPlanId().toString();
        //assert
        assertEquals(uuidString, id.toString());
    }
}