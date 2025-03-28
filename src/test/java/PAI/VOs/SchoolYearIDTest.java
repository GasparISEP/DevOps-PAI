package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchoolYearIDTest {

    @Test
    public void testSchoolYearID_GeneratesUniqueUUID() {
        SchoolYearID schoolYearID1 = new SchoolYearID();
        SchoolYearID schoolYearID2 = new SchoolYearID();
        assertNotEquals(schoolYearID1.getSchoolYearID(), schoolYearID2.getSchoolYearID());
    }

    @Test
    public void testEquals_WithNull() {
        SchoolYearID schoolYearID = new SchoolYearID();
        assertFalse(schoolYearID.equals(null));
    }

    @Test
    public void testGetClass() {
        SchoolYearID schoolYearID = new SchoolYearID();
        assertEquals(SchoolYearID.class, schoolYearID.getClass());
    }

    @Test
    public void testToString_Format() {
        SchoolYearID schoolYearID = new SchoolYearID();
        String uuidString = schoolYearID.toString();
        assertTrue(uuidString.matches("^[0-9a-fA-F-]{36}$"));
    }

    @Test
    public void testEquals_DifferentUUID() {
        SchoolYearID id1 = new SchoolYearID();
        SchoolYearID id2 = new SchoolYearID();
        assertNotEquals(id1, id2);
    }

    @Test
    public void testEquals_SameObject() {
        SchoolYearID id = new SchoolYearID();
        assertEquals(id, id);
    }

    @Test
    public void testEquals_DifferentClass() {
        SchoolYearID id = new SchoolYearID();
        Object other = new Object();
        assertNotEquals(id, other);
    }

    @Test
    public void testHashCode_DifferentUUID() {
        SchoolYearID id1 = new SchoolYearID();
        SchoolYearID id2 = new SchoolYearID();
        assertNotEquals(id1.hashCode(), id2.hashCode());
    }


}