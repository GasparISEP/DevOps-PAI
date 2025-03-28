package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEnrolmentIDTest {

    @Test
    public void testProgrammeEnrolmentId_GeneratesUniqueUUID() {
        ProgrammeEnrolmentID enrolmentId1 = new ProgrammeEnrolmentID();
        ProgrammeEnrolmentID enrolmentId2 = new ProgrammeEnrolmentID();
        assertNotEquals(enrolmentId1.getProgrammeEnrolmentId(), enrolmentId2.getProgrammeEnrolmentId());
    }

    @Test
    public void testEquals_WithNull() {
        ProgrammeEnrolmentID enrolmentId = new ProgrammeEnrolmentID();
        assertFalse(enrolmentId.equals(null));
    }

    @Test
    public void testGetClass() {
        ProgrammeEnrolmentID enrolmentId = new ProgrammeEnrolmentID();
        assertEquals(ProgrammeEnrolmentID.class, enrolmentId.getClass());
    }

    @Test
    public void testToString_Format() {
        ProgrammeEnrolmentID enrolmentId = new ProgrammeEnrolmentID();
        String uuidString = enrolmentId.toString();
        assertTrue(uuidString.matches("^[0-9a-fA-F-]{36}$")); // Verifica se o formato é o esperado para um UUID
    }

    @Test
    public void testEquals_DifferentUUID() {
        ProgrammeEnrolmentID id1 = new ProgrammeEnrolmentID();
        ProgrammeEnrolmentID id2 = new ProgrammeEnrolmentID();
        assertNotEquals(id1, id2);
    }

    @Test
    public void testEquals_SameObject() {
        ProgrammeEnrolmentID id = new ProgrammeEnrolmentID();
        assertEquals(id, id);
    }

    @Test
    public void testEquals_DifferentClass() {
        ProgrammeEnrolmentID id = new ProgrammeEnrolmentID();
        Object other = new Object();
        assertNotEquals(id, other);
    }

    @Test
    public void testHashCode_DifferentUUID() {
        ProgrammeEnrolmentID id1 = new ProgrammeEnrolmentID();
        ProgrammeEnrolmentID id2 = new ProgrammeEnrolmentID();
        assertNotEquals(id1.hashCode(), id2.hashCode());
    }

}