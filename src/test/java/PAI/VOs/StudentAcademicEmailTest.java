package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentAcademicEmailTest {

    @Test
    void shouldCreateValidStudentEmail() {
        UniqueNumber uniqueNumberDouble= mock(UniqueNumber.class);
        NIF nifDouble = mock(NIF.class);
        StudentID studentID = new StudentID(uniqueNumberDouble, nifDouble);
        when(uniqueNumberDouble.toString()).thenReturn("1234567");
        StudentAcademicEmail studentAcademicEmail = new StudentAcademicEmail(studentID);

        assertEquals("1234567@isep.ipp.pt", studentAcademicEmail.getStudentEmail());
    }

    @Test
    void shouldThrowExceptionIfStudentIDIsNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new StudentAcademicEmail(null);
        });

        assertEquals("Student's unique number cannot be null!", thrown.getMessage());
    }

    @Test
    void shouldBeEqualIfEmailsAreTheSame() {
        UniqueNumber uniqueNumberDouble= mock(UniqueNumber.class);
        NIF nifDouble = mock(NIF.class);
        StudentID studentID = new StudentID(uniqueNumberDouble, nifDouble);
        StudentID studentID2 = new StudentID(uniqueNumberDouble, nifDouble);

        StudentAcademicEmail email1 = new StudentAcademicEmail(studentID);
        StudentAcademicEmail email2 = new StudentAcademicEmail(studentID2);

        assertEquals(email1, email2);
        assertEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    void shouldNotBeEqualIfEmailsAreDifferent() {
        UniqueNumber uniqueNumberDouble= mock(UniqueNumber.class);
        NIF nifDouble = mock(NIF.class);
        StudentID studentID = new StudentID(uniqueNumberDouble, nifDouble);
        UniqueNumber uniqueNumberDouble2= mock(UniqueNumber.class);
        NIF nifDouble2 = mock(NIF.class);
        StudentID studentID2 = new StudentID(uniqueNumberDouble2, nifDouble2);

        StudentAcademicEmail email1 = new StudentAcademicEmail(studentID);
        StudentAcademicEmail email2 = new StudentAcademicEmail(studentID2);

        assertNotEquals(email1, email2);
        assertNotEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        UniqueNumber uniqueNumberDouble= mock(UniqueNumber.class);
        NIF nifDouble = mock(NIF.class);

        when(uniqueNumberDouble.toString()).thenReturn("1234567");
        StudentID studentID = new StudentID(uniqueNumberDouble, nifDouble);
        StudentAcademicEmail studentAcademicEmail = new StudentAcademicEmail(studentID);

        assertEquals("1234567@isep.ipp.pt", studentAcademicEmail.toString());
    }

    @Test
    void shouldGenerateEmailForDifferentStudentIDs() {
        UniqueNumber uniqueNumberDouble= mock(UniqueNumber.class);
        NIF nifDouble = mock(NIF.class);
        StudentID studentID = new StudentID(uniqueNumberDouble, nifDouble);
        when(uniqueNumberDouble.toString()).thenReturn("1234567");
        UniqueNumber uniqueNumberDouble2= mock(UniqueNumber.class);
        NIF nifDouble2 = mock(NIF.class);
        StudentID studentID2 = new StudentID(uniqueNumberDouble2, nifDouble2);
        when(uniqueNumberDouble2.toString()).thenReturn("7654321");

        StudentAcademicEmail email1 = new StudentAcademicEmail(studentID);
        StudentAcademicEmail email2 = new StudentAcademicEmail(studentID2);

        assertEquals("1234567@isep.ipp.pt", email1.toString());
        assertEquals("7654321@isep.ipp.pt", email2.toString());
    }

    @Test
    void shouldBeImmutableAfterCreation() {
        UniqueNumber uniqueNumberDouble= mock(UniqueNumber.class);
        NIF nifDouble = mock(NIF.class);
        StudentID studentID = new StudentID(uniqueNumberDouble, nifDouble);
        StudentAcademicEmail studentAcademicEmail = new StudentAcademicEmail(studentID);

        String emailBefore = studentAcademicEmail.getStudentEmail();

        assertEquals(emailBefore, studentAcademicEmail.getStudentEmail());
    }
}
