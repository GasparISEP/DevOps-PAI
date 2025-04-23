package PAI.VOs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Objects;

class ProgrammeEditionEnrolmentIDTest {

    private ProgrammeEditionID programmeId;
    private StudentID studentId;

    @BeforeEach
    void setUp() {
        programmeId = mock(ProgrammeEditionID.class);
        studentId = mock(StudentID.class);
    }

    @Test
    void shouldReturnProgrammeEditionEnrolmentIDNotNull() {
        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID(programmeId, studentId);
        assertNotNull(enrolmentID);
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIDIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentID(null, studentId));
    }

    @Test
    void shouldThrowExceptionIfStudentIDIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionEnrolmentID(programmeId, null));
    }

    @Test
    void shouldReturnCorrectToString() {
        when(programmeId.toString()).thenReturn("ProgrammeEditionID{123}");
        when(studentId.toString()).thenReturn("StudentID{456}");

        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID(programmeId, studentId);
        String expected = "ProgrammeEditionEnrolmentID{" +
                "_programmeEditionId=ProgrammeEditionID{123}, " +
                "_studentiD=StudentID{456}" +
                '}';
        assertEquals(expected, enrolmentID.toString());
    }

    @Test
    void shouldReturnEqualsIfObjectsAreEqual() {
        ProgrammeEditionEnrolmentID enrolmentID1 = new ProgrammeEditionEnrolmentID(programmeId, studentId);
        ProgrammeEditionEnrolmentID enrolmentID2 = enrolmentID1;
        assertEquals(enrolmentID1, enrolmentID2);
    }

    @Test
    void shouldReturnEqualsIfDifferentObjectsHaveSameAttributes() {
        ProgrammeEditionEnrolmentID enrolmentID1 = new ProgrammeEditionEnrolmentID(programmeId, studentId);
        ProgrammeEditionEnrolmentID enrolmentID2 = new ProgrammeEditionEnrolmentID(programmeId, studentId);
        assertTrue(enrolmentID1.equals(enrolmentID2));
    }

    @Test
    void shouldReturnNotEqualsIfProgrammeEditionEnrolmentIDsHaveDifferentIDs() {
        ProgrammeEditionID programmeId2 = mock(ProgrammeEditionID.class);
        StudentID studentId2 = mock(StudentID.class);

        ProgrammeEditionEnrolmentID enrolmentID1 = new ProgrammeEditionEnrolmentID(programmeId, studentId);
        ProgrammeEditionEnrolmentID enrolmentID2 = new ProgrammeEditionEnrolmentID(programmeId2, studentId2);
        ProgrammeEditionEnrolmentID enrolmentID3 = new ProgrammeEditionEnrolmentID(programmeId2, studentId);
        ProgrammeEditionEnrolmentID enrolmentID4 = new ProgrammeEditionEnrolmentID(programmeId, studentId2);

        assertNotEquals(enrolmentID1, enrolmentID2);
        assertNotEquals(enrolmentID1, enrolmentID3);
        assertNotEquals(enrolmentID1, enrolmentID4);
        assertNotEquals(enrolmentID2, enrolmentID3);
        assertNotEquals(enrolmentID3, enrolmentID4);
    }

    @Test
    void shouldReturnNotEqualsIfComparingWithNull() {
        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID(programmeId, studentId);
        assertNotEquals(enrolmentID, null);
    }

    @Test
    void shouldReturnNotEqualsIfObjectsAreNotFromSameClass() {
        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID(programmeId, studentId);
        Object differentObject = new Object();
        assertNotEquals(enrolmentID, differentObject);
    }

    @Test
    void shouldReturnDifferentHashCodeForDifferentObjects() {
        ProgrammeEditionID programmeId2 = mock(ProgrammeEditionID.class);
        StudentID studentId2 = mock(StudentID.class);

        ProgrammeEditionEnrolmentID enrolmentID1 = new ProgrammeEditionEnrolmentID(programmeId, studentId);
        ProgrammeEditionEnrolmentID enrolmentID2 = new ProgrammeEditionEnrolmentID(programmeId2, studentId2);

        assertNotEquals(enrolmentID1.hashCode(), enrolmentID2.hashCode());
    }

    @Test
    void shouldReturnCorrectProgrammeEditionID() {
        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID(programmeId, studentId);
        assertSame(programmeId, enrolmentID.getProgrammeEditionId());
    }

    @Test
    void shouldReturnCorrectStudentID() {
        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID(programmeId, studentId);
        assertSame(studentId, enrolmentID.getStudentiD());
    }


}

