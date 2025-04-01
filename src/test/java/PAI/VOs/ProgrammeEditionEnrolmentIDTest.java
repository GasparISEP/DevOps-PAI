package PAI.VOs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import PAI.VOs.ProgrammeEditionEnrolmentID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.StudentID;
import PAI.VOs.SchoolYearID;

class ProgrammeEditionEnrolmentIDTest {

    private ProgrammeEditionID programmeId;
    private StudentID studentId;
    private SchoolYearID schoolYearId;

    @BeforeEach
    void setUp() {
        programmeId = mock(ProgrammeEditionID.class);
        studentId = mock(StudentID.class);
        schoolYearId = mock(SchoolYearID.class);
    }

    @Test
    void shouldReturnProgrammeEditionEnrolmentIDNotNull() {
        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID(programmeId, studentId, schoolYearId);
        assertNotNull(enrolmentID);
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIDIsNull() {
        assertThrows(Exception.class, () -> new ProgrammeEditionEnrolmentID(null, studentId, schoolYearId));
    }

    @Test
    void shouldThrowExceptionIfStudentIDIsNull() {
        assertThrows(Exception.class, () -> new ProgrammeEditionEnrolmentID(programmeId, null, schoolYearId));
    }

    @Test
    void shouldThrowExceptionIfSchoolYearIDIsNull() {
        assertThrows(Exception.class, () -> new ProgrammeEditionEnrolmentID(programmeId, studentId, null));
    }

    @Test
    void shouldThrowExceptionIfAllAttributesAreNull() {
        assertThrows(Exception.class, () -> new ProgrammeEditionEnrolmentID(null, null, null));
    }

    @Test
    void shouldReturnCorrectToString() {
        when(programmeId.toString()).thenReturn("ProgrammeEditionID{123}");
        when(studentId.toString()).thenReturn("StudentID{456}");
        when(schoolYearId.toString()).thenReturn("SchoolYearID{789}");

        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID(programmeId, studentId, schoolYearId);
        String expected = "ProgrammeEditionEnrolmentID{" +
                "_programmeEditionId=ProgrammeEditionID{123}, " +
                "_studentiD=StudentID{456}, " +
                "_schoolYearId=SchoolYearID{789}" +
                '}';
        assertEquals(expected, enrolmentID.toString());
    }

    @Test
    void shouldReturnEqualsIfObjectsAreEqual() {
        ProgrammeEditionEnrolmentID enrolmentID1 = new ProgrammeEditionEnrolmentID(programmeId, studentId, schoolYearId);
        ProgrammeEditionEnrolmentID enrolmentID2 = enrolmentID1;
        assertEquals(enrolmentID1, enrolmentID2);
    }

    @Test
    void shouldReturnEqualsIfDifferentObjectsHaveSameAttributes() {
        ProgrammeEditionEnrolmentID enrolmentID1 = new ProgrammeEditionEnrolmentID(programmeId, studentId, schoolYearId);
        ProgrammeEditionEnrolmentID enrolmentID2 = new ProgrammeEditionEnrolmentID(programmeId, studentId, schoolYearId);
        assertTrue(enrolmentID1.equals(enrolmentID2));
    }

    @Test
    void shouldReturnNotEqualsIfProgrammeEditionEnrolmentIDsHaveDifferentIDs() {
        ProgrammeEditionID programmeId2 = mock(ProgrammeEditionID.class);
        StudentID studentId2 = mock(StudentID.class);
        SchoolYearID schoolYearId2 = mock(SchoolYearID.class);

        ProgrammeEditionEnrolmentID enrolmentID1 = new ProgrammeEditionEnrolmentID(programmeId, studentId, schoolYearId);
        ProgrammeEditionEnrolmentID enrolmentID2 = new ProgrammeEditionEnrolmentID(programmeId2, studentId2, schoolYearId2);
        ProgrammeEditionEnrolmentID enrolmentID3 = new ProgrammeEditionEnrolmentID(programmeId2, studentId, schoolYearId);
        ProgrammeEditionEnrolmentID enrolmentID4 = new ProgrammeEditionEnrolmentID(programmeId, studentId2, schoolYearId);

        assertNotEquals(enrolmentID1, enrolmentID2);
        assertNotEquals(enrolmentID1, enrolmentID3);
        assertNotEquals(enrolmentID1, enrolmentID4);
        assertNotEquals(enrolmentID2, enrolmentID3);
        assertNotEquals(enrolmentID3, enrolmentID4);
    }

    @Test
    void shouldReturnNotEqualsIfComparingWithNull() {
        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID(programmeId, studentId, schoolYearId);
        assertNotEquals(enrolmentID, null);
    }

    @Test
    void shouldReturnNotEqualsIfObjectsAreNotFromSameClass() {
        ProgrammeEditionEnrolmentID enrolmentID = new ProgrammeEditionEnrolmentID(programmeId, studentId, schoolYearId);
        Object differentObject = new Object();
        assertNotEquals(enrolmentID, differentObject);
    }
}
