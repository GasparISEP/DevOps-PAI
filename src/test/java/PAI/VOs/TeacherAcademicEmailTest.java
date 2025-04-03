package PAI.VOs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TeacherAcademicEmailTest {

    @Test
    void shouldCreateValidTeacherAcademicEmail()  {
        // Arrange
        TeacherAcronym teacherAcronym = new TeacherAcronym("ABC");

        // Act
        TeacherAcademicEmail teacherEmail = new TeacherAcademicEmail(teacherAcronym);

        // Assert
        assertEquals("ABC@isep.ipp.pt", teacherEmail.getTeacherAcademicEmail());
    }

    @Test
    void shouldThrowExceptionIfTeacherIDIsNull() {
        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherAcademicEmail(null);
        });

        assertEquals("Teacher's acronym cannot be null!", thrown.getMessage());
    }

    @Test
    void shouldBeEqualIfEmailsAreTheSame() {
        // Arrange
        TeacherAcronym teacherAcronym1 = new TeacherAcronym("SDE");
        TeacherAcronym teacherAcronym2 = new TeacherAcronym("SDE");

        // Act
        TeacherAcademicEmail email1 = new TeacherAcademicEmail(teacherAcronym1);
        TeacherAcademicEmail email2 = new TeacherAcademicEmail(teacherAcronym2);

        // Assert
        assertEquals(email1, email2);
    }

    @Test
    void shouldNotBeEqualIfEmailsAreDifferent() {
        // Arrange
        TeacherAcronym teacherAcronym1 = new TeacherAcronym("ABC");
        TeacherAcronym teacherAcronym2 = new TeacherAcronym("XYZ");

        // Act
        TeacherAcademicEmail email1 = new TeacherAcademicEmail(teacherAcronym1);
        TeacherAcademicEmail email2 = new TeacherAcademicEmail(teacherAcronym2);

        // Assert
        assertNotEquals(email1, email2);
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        // Arrange
        TeacherAcronym teacherAcronym = new TeacherAcronym("DEF");

        // Act
        TeacherAcademicEmail teacherEmail = new TeacherAcademicEmail(teacherAcronym);

        // Assert
        assertEquals("DEF@isep.ipp.pt", teacherEmail.toString());
    }

    @Test
    void shouldGenerateEmailForDifferentTeacherIDs() {
        // Arrange
        TeacherAcronym teacherAcronym1 = new TeacherAcronym("LMN");
        TeacherAcronym teacherAcronym2 = new TeacherAcronym("XYZ");

        // Act
        TeacherAcademicEmail email1 = new TeacherAcademicEmail(teacherAcronym1);
        TeacherAcademicEmail email2 = new TeacherAcademicEmail(teacherAcronym2);

        // Assert
        assertEquals("LMN@isep.ipp.pt", email1.toString());
        assertEquals("XYZ@isep.ipp.pt", email2.toString());
    }

    @Test
    void shouldBeImmutableAfterCreation() {
        // Arrange
        TeacherAcronym teacherAcronym = new TeacherAcronym("QWE");
        TeacherAcademicEmail teacherEmail = new TeacherAcademicEmail(teacherAcronym);

        // Act
        String emailBefore = teacherEmail.getTeacherAcademicEmail();

        // Assert
        assertEquals(emailBefore, teacherEmail.getTeacherAcademicEmail());
    }

    @Test
    void testHashCodeConsistency() {
        // Arrange
        TeacherAcronym acronym1 = new TeacherAcronym("EDC");
        TeacherAcronym acronym2 = new TeacherAcronym("EDC");

        // Act
        TeacherAcademicEmail email1 = new TeacherAcademicEmail(acronym1);
        TeacherAcademicEmail email2 = new TeacherAcademicEmail(acronym2);

        // Assert
        assertEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    void testDifferentHashCodesForDifferentEmails() {
        // Arrange
        TeacherAcronym acronym1 = new TeacherAcronym("EDC");
        TeacherAcronym acronym2 = new TeacherAcronym("ABC");

        // Act
        TeacherAcademicEmail email1 = new TeacherAcademicEmail(acronym1);
        TeacherAcademicEmail email2 = new TeacherAcademicEmail(acronym2);

        // Assert
        assertNotEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    void shouldBeEqualToItself() {
        // Arrange
        TeacherAcronym teacherAcronym = new TeacherAcronym("ABC");
        TeacherAcademicEmail email = new TeacherAcademicEmail(teacherAcronym);

        // Act & Assert
        assertTrue(email.equals(email));
    }

    @Test
    void shouldNotBeEqualToNull() {
        // Arrange
        TeacherAcronym teacherAcronym = new TeacherAcronym("ABC");
        TeacherAcademicEmail email = new TeacherAcademicEmail(teacherAcronym);

        // Act & Assert
        assertFalse(email.equals(null));
    }

    @Test
    void shouldNotBeEqualToDifferentClassObject() {
        // Arrange
        TeacherAcronym teacherAcronym = new TeacherAcronym("ABC");
        TeacherAcademicEmail email = new TeacherAcademicEmail(teacherAcronym);

        // Act & Assert
        assertFalse(email.equals("ABC@isep.ipp.pt"));
    }
}