package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentAcademicEmailTest {

    @Test
    void shouldCreateValidStudentEmail() {
        int uniqueNumber= 1234567;
        StudentAcademicEmail studentAcademicEmail = new StudentAcademicEmail(uniqueNumber);

        assertEquals("1234567@isep.ipp.pt", studentAcademicEmail.getStudentEmail());
    }

    @Test
    void shouldNotCreateValidStudentEmail() {
        int uniqueNumber= 2134567;

        assertThrows(IllegalArgumentException.class, () -> {
            new StudentAcademicEmail(uniqueNumber);
        });

    }

    @Test
    void shouldBeEqualIfEmailsAreTheSame() {

        int uniqueNumber = 1234567;
        int uniqueNumber2 = 1234567;

        StudentAcademicEmail email1 = new StudentAcademicEmail(uniqueNumber);
        StudentAcademicEmail email2 = new StudentAcademicEmail(uniqueNumber2);

        assertEquals(email1, email2);
        assertEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    void shouldNotBeEqualIfEmailsAreDifferent() {

        int uniqueNumber = 1234567;
        int uniqueNumber2 = 1234568;

        StudentAcademicEmail email1 = new StudentAcademicEmail(uniqueNumber);
        StudentAcademicEmail email2 = new StudentAcademicEmail(uniqueNumber2);

        assertNotEquals(email1, email2);
        assertNotEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {

        int uniqueNumber = 1234567;
        StudentAcademicEmail studentAcademicEmail = new StudentAcademicEmail(uniqueNumber);

        assertEquals("1234567@isep.ipp.pt", studentAcademicEmail.toString());
    }

    @Test
    void shouldGenerateEmailForDifferentStudentIDs() {

        int uniqueNumber = 1234567;
        int uniqueNumber2 = 1324567;


        StudentAcademicEmail email1 = new StudentAcademicEmail(uniqueNumber);
        StudentAcademicEmail email2 = new StudentAcademicEmail(uniqueNumber2);

        assertEquals("1234567@isep.ipp.pt", email1.toString());
        assertEquals("1324567@isep.ipp.pt", email2.toString());
    }

    @Test
    void shouldBeImmutableAfterCreation() {

        int uniqueNumber = 1234567;
        StudentAcademicEmail studentAcademicEmail = new StudentAcademicEmail(uniqueNumber);

        String emailBefore = studentAcademicEmail.getStudentEmail();

        assertEquals(emailBefore, studentAcademicEmail.getStudentEmail());
    }
}
