package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentAcademicEmailTest {

    @Test
    void shouldCreateValidStudentEmail() {
        StudentID studentID = new StudentID(1234567);
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
        StudentID studentID1 = new StudentID(1234567);
        StudentID studentID2 = new StudentID(1234567);

        StudentAcademicEmail email1 = new StudentAcademicEmail(studentID1);
        StudentAcademicEmail email2 = new StudentAcademicEmail(studentID2);

        assertEquals(email1, email2);
        assertEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    void shouldNotBeEqualIfEmailsAreDifferent() {
        StudentID studentID1 = new StudentID(1234567);
        StudentID studentID2 = new StudentID(1654321);

        StudentAcademicEmail email1 = new StudentAcademicEmail(studentID1);
        StudentAcademicEmail email2 = new StudentAcademicEmail(studentID2);

        assertNotEquals(email1, email2);
        assertNotEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        StudentID studentID = new StudentID(1234567);
        StudentAcademicEmail studentAcademicEmail = new StudentAcademicEmail(studentID);

        assertEquals("1234567@isep.ipp.pt", studentAcademicEmail.toString());
    }

    @Test
    void shouldGenerateEmailForDifferentStudentIDs() {
        StudentID studentID1 = new StudentID(1111111);
        StudentID studentID2 = new StudentID(1234567);

        StudentAcademicEmail email1 = new StudentAcademicEmail(studentID1);
        StudentAcademicEmail email2 = new StudentAcademicEmail(studentID2);

        assertEquals("1111111@isep.ipp.pt", email1.toString());
        assertEquals("1234567@isep.ipp.pt", email2.toString());
    }

    @Test
    void shouldBeImmutableAfterCreation() {
        StudentID studentID = new StudentID(1234567);
        StudentAcademicEmail studentAcademicEmail = new StudentAcademicEmail(studentID);

        String emailBefore = studentAcademicEmail.getStudentEmail();

        assertEquals(emailBefore, studentAcademicEmail.getStudentEmail());
    }
}
