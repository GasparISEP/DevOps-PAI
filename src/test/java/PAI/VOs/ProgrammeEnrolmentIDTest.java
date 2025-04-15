package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEnrolmentIDTest {

    @Test
    public void testProgrammeEnrolmentId_GeneratesUniqueUUID() {
        StudentID studentIDDouble1 = mock(StudentID.class);
        ProgrammeID programmeIDDouble1 = mock(ProgrammeID.class);

        StudentID studentIDDouble2 = mock(StudentID.class);
        ProgrammeID programmeIDDouble2 = mock(ProgrammeID.class);

        ProgrammeEnrolmentID enrolmentId1 = new ProgrammeEnrolmentID(studentIDDouble1, programmeIDDouble1);
        ProgrammeEnrolmentID enrolmentId2 = new ProgrammeEnrolmentID(studentIDDouble2, programmeIDDouble2);
        assertNotEquals(enrolmentId1.getProgrammeEnrolmentId(), enrolmentId2.getProgrammeEnrolmentId());
    }

    @Test
    public void testEquals_WithNull() {
        StudentID studentIDDouble1 = mock(StudentID.class);
        ProgrammeID programmeIDDouble1 = mock(ProgrammeID.class);

        ProgrammeEnrolmentID enrolmentId = new ProgrammeEnrolmentID(studentIDDouble1, programmeIDDouble1);
        assertFalse(enrolmentId.equals(null));
    }

    @Test
    public void testGetClass() {
        StudentID studentIDDouble1 = mock(StudentID.class);
        ProgrammeID programmeIDDouble1 = mock(ProgrammeID.class);

        ProgrammeEnrolmentID enrolmentId = new ProgrammeEnrolmentID(studentIDDouble1, programmeIDDouble1);
        assertEquals(ProgrammeEnrolmentID.class, enrolmentId.getClass());
    }

    @Test
    public void testToString_Format() {
        StudentID studentID = mock(StudentID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        when(studentID.toString()).thenReturn("1234567");
        when(programmeID.toString()).thenReturn("EM");

        ProgrammeEnrolmentID enrolmentID = new ProgrammeEnrolmentID(studentID, programmeID);

        String result = enrolmentID.toString();

        assertEquals("ProgrammeEnrolmentID{studentID=1234567, programmeID=EM}", result);
    }


    @Test
    public void testEquals_DifferentCompositeIDs() {
        StudentID studentID1 = mock(StudentID.class);
        ProgrammeID programmeID1 = mock(ProgrammeID.class);

        StudentID studentID2 = mock(StudentID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);

        ProgrammeEnrolmentID id1 = new ProgrammeEnrolmentID(studentID1, programmeID1);
        ProgrammeEnrolmentID id2 = new ProgrammeEnrolmentID(studentID2, programmeID2);

        assertNotEquals(id1, id2);
    }


    @Test
    public void testEquals_SameObject() {
        StudentID studentID1 = mock(StudentID.class);
        ProgrammeID programmeID1 = mock(ProgrammeID.class);

        ProgrammeEnrolmentID id = new ProgrammeEnrolmentID(studentID1, programmeID1);
        assertEquals(id, id);
    }

    @Test
    public void testEquals_DifferentClass() {
        StudentID studentID1 = mock(StudentID.class);
        ProgrammeID programmeID1 = mock(ProgrammeID.class);

        ProgrammeEnrolmentID id = new ProgrammeEnrolmentID(studentID1, programmeID1);
        Object other = new Object();
        assertNotEquals(id, other);
    }

    @Test
    public void testHashCode_DifferentCompositeIDs() {
        StudentID studentID1 = mock(StudentID.class);
        ProgrammeID programmeID1 = mock(ProgrammeID.class);

        StudentID studentID2 = mock(StudentID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);

        ProgrammeEnrolmentID id1 = new ProgrammeEnrolmentID(studentID1, programmeID1);
        ProgrammeEnrolmentID id2 = new ProgrammeEnrolmentID(studentID2, programmeID2);

        assertNotEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    public void testEquals_SameValues() {
        StudentID studentID1 = new StudentID(1241204);
        ProgrammeID programmeID1 = new ProgrammeID(new NameWithNumbersAndSpecialChars("name"), new Acronym("ACR"));


        ProgrammeEnrolmentID id1 = new ProgrammeEnrolmentID(studentID1, programmeID1);
        ProgrammeEnrolmentID id2 = new ProgrammeEnrolmentID(studentID1, programmeID1);

        assertEquals(id1, id2);
    }


    @Test
    public void testHashCode_SameCompositeIDs() {
        StudentID studentID1 = mock(StudentID.class);
        ProgrammeID programmeID1 = mock(ProgrammeID.class);

        ProgrammeEnrolmentID id1 = new ProgrammeEnrolmentID(studentID1, programmeID1);
        ProgrammeEnrolmentID id2 = new ProgrammeEnrolmentID(studentID1, programmeID1);

        assertEquals(id1.hashCode(), id2.hashCode());
    }


}