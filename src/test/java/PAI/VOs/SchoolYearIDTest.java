package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.UUID;

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

    //Test new constructor for SchoolYearID accepting a UUID instead of automatically generating one

    @Test
    public void testConstructingNewSchoolYearIDWithValidUUIDReceivedAsParameter() {
        UUID validUUID = UUID.randomUUID();
        SchoolYearID schoolYearID = new SchoolYearID(validUUID);
        assertNotNull(schoolYearID);
    }

    @Test
    public void testConstructingNewSchoolYearIDWithValidStringUUID() {
        UUID validUUID = UUID.fromString("9fbd7db8-2ab5-456b-98ac-e372589d57bb");
        SchoolYearID schoolYearID = new SchoolYearID(validUUID);
        assertNotNull(schoolYearID);
    }

    @Test
    public void testConstructingNewSchoolYearIDWithInvalidStringUUID() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            UUID validUUID = UUID.fromString("a");
            SchoolYearID schoolYearID = new SchoolYearID(validUUID);
                });
        assertEquals("Invalid UUID string: a", exception.getMessage());
    }

    @Test
    public void testConstructingNewSchoolYearIDBasedOnNullUUIDReceivedAsParameter() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           SchoolYearID schoolYearID= new SchoolYearID(null);
        });

        assertEquals("School Year ID cannot be null", exception.getMessage());

    }


}