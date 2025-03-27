package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseInStudyPlanIDTest {

    @Test
    void testDefaultConstructorCreatesNonNullID() {
        //arrange + act
        CourseInStudyPlanID id = new CourseInStudyPlanID();
        //assert
        assertNotNull(id.getCourseInStudyPlanId());
    }

    @Test
    void testEqualsMethodWithDifferentUUID() {
        //arrange + act
        CourseInStudyPlanID id1 = new CourseInStudyPlanID();
        CourseInStudyPlanID id2 = new CourseInStudyPlanID();
        //arrange
        assertNotEquals(id1, id2);
    }

    @Test
    void testHashCodeConsistency() {
        //arrange
        CourseInStudyPlanID id = new CourseInStudyPlanID();
        //act
        int hash1 = id.hashCode();
        int hash2 = id.hashCode();
        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    void testToStringReturnsUUIDString() {
        //arrange
        CourseInStudyPlanID id = new CourseInStudyPlanID();
        //act
        String uuidString = id.getCourseInStudyPlanId().toString();
        //assert
        assertEquals(uuidString, id.toString());
    }

    @Test
    void testEqualsSameObject() {
        CourseInStudyPlanID id = new CourseInStudyPlanID();
        assertEquals(id, id);
    }

    @Test
    void testEqualsWithNull() {
        CourseInStudyPlanID id = new CourseInStudyPlanID();
        assertNotEquals(id , null);
    }

    @Test
    void testEqualsWithDifferenteClass() {
        CourseInStudyPlanID id = new CourseInStudyPlanID();
        String other = "not a CourseInStudyPlanID";
        assertNotEquals(id, other);
    }
}