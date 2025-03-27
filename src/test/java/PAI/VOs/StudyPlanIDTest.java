package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudyPlanIDTest {

    @Test
    void testDefaultConstructorCreatesNonNullID() {
        //arrange + act
        StudyPlanID id = new StudyPlanID();
        //assert
        assertNotNull(id.getStudyPlanId());
    }

    @Test
    void testEqualsMethodWithDifferentUUID() {
        //arrange + act
        StudyPlanID id1 = new StudyPlanID();
        StudyPlanID id2 = new StudyPlanID();
        //assert
        assertNotEquals(id1, id2);
    }

    @Test
    void testHashCodeConsistency() {
        //arrange
        StudyPlanID id = new StudyPlanID();
        //act
        int hash1 = id.hashCode();
        int hash2 = id.hashCode();
        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    void tesToStringReturnsUUIDString() {
        //arrange
        StudyPlanID id = new StudyPlanID();
        //act
        String uuidString = id.getStudyPlanId().toString();
        //assert
        assertEquals(uuidString, id.toString());
    }

    @Test
    void testEqualsSameObject() {
        StudyPlanID id = new StudyPlanID();
        assertEquals(id, id);
    }

    @Test
    void testEqualsWithNull() {
        StudyPlanID id = new StudyPlanID();
        assertNotEquals(id, null);
    }

    @Test
    void testEqualsWithDifferentClass() {
        StudyPlanID id = new StudyPlanID();
        String other = "not a StudyPlanID";
        assertNotEquals(id, other);
    }
}