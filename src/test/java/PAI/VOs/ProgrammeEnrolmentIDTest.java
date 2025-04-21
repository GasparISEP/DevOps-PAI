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
        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);

        when(studentIDDouble.toString()).thenReturn("1234567");
        when(programmeIDDouble.toString()).thenReturn("EM");

        ProgrammeEnrolmentID enrolmentID = new ProgrammeEnrolmentID(studentIDDouble, programmeIDDouble);

        String result = enrolmentID.toString();

        assertEquals("ProgrammeEnrolmentID{studentID=1234567, programmeID=EM}", result);
    }


    @Test
    public void testEquals_DifferentCompositeIDs() {
        StudentID studentIDDouble1 = mock(StudentID.class);
        ProgrammeID programmeIDDouble1 = mock(ProgrammeID.class);

        StudentID studentIDDouble2 = mock(StudentID.class);
        ProgrammeID programmeIDDouble2 = mock(ProgrammeID.class);

        ProgrammeEnrolmentID id1 = new ProgrammeEnrolmentID(studentIDDouble1, programmeIDDouble1);
        ProgrammeEnrolmentID id2 = new ProgrammeEnrolmentID(studentIDDouble2, programmeIDDouble2);

        assertNotEquals(id1, id2);
    }


    @Test
    public void testEquals_SameObject() {
        StudentID studentIDDouble1 = mock(StudentID.class);
        ProgrammeID programmeIDDouble1 = mock(ProgrammeID.class);

        ProgrammeEnrolmentID id = new ProgrammeEnrolmentID(studentIDDouble1, programmeIDDouble1);
        assertEquals(id, id);
    }

    @Test
    public void testEquals_DifferentClass() {
        StudentID studentIDDouble1 = mock(StudentID.class);
        ProgrammeID programmeIDDouble1 = mock(ProgrammeID.class);

        ProgrammeEnrolmentID id = new ProgrammeEnrolmentID(studentIDDouble1, programmeIDDouble1);
        Object other = new Object();
        assertNotEquals(id, other);
    }

    @Test
    public void testHashCode_DifferentCompositeIDs() {
        StudentID studentIDDouble1 = mock(StudentID.class);
        ProgrammeID programmeIDDouble1 = mock(ProgrammeID.class);

        StudentID studentIDDouble2 = mock(StudentID.class);
        ProgrammeID programmeIDDouble2 = mock(ProgrammeID.class);

        ProgrammeEnrolmentID id1 = new ProgrammeEnrolmentID(studentIDDouble1, programmeIDDouble1);
        ProgrammeEnrolmentID id2 = new ProgrammeEnrolmentID(studentIDDouble2, programmeIDDouble2);

        assertNotEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    public void testEquals_SameValues() {
        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeID programmeIDDouble = new ProgrammeID(new NameWithNumbersAndSpecialChars("name"), new Acronym("ACR"));


        ProgrammeEnrolmentID id1 = new ProgrammeEnrolmentID(studentIDDouble, programmeIDDouble);
        ProgrammeEnrolmentID id2 = new ProgrammeEnrolmentID(studentIDDouble, programmeIDDouble);

        assertEquals(id1, id2);
    }


    @Test
    public void testHashCode_SameCompositeIDs() {
        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);

        ProgrammeEnrolmentID id1 = new ProgrammeEnrolmentID(studentIDDouble, programmeIDDouble);
        ProgrammeEnrolmentID id2 = new ProgrammeEnrolmentID(studentIDDouble, programmeIDDouble);

        assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    public void testGetStudentID() {
        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);

        ProgrammeEnrolmentID programmeEnrolmentID = new ProgrammeEnrolmentID(studentIDDouble, programmeIDDouble);

        assertEquals(studentIDDouble, programmeEnrolmentID.getStudentID());
    }

    @Test
    public void testGetProgrammeID() {
        StudentID studentIDDouble = mock(StudentID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);

        ProgrammeEnrolmentID programmeEnrolmentID = new ProgrammeEnrolmentID(studentIDDouble, programmeIDDouble);

        assertEquals(programmeIDDouble, programmeEnrolmentID.getProgrammeID());
    }

}