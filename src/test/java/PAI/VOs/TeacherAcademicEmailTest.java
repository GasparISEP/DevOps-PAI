package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherAcademicEmailTest {

    @Test
    void shouldCreateValidTeacherAcademicEmail() throws Exception {
        TeacherAcronym teacherAcronym = new TeacherAcronym("ABC");
        TeacherAcademicEmail teacherEmail = new TeacherAcademicEmail(teacherAcronym);

        assertEquals("ABC@isep.ipp.pt", teacherEmail.getTeacherAcademicEmail());
    }

    @Test
    void shouldThrowExceptionIfTeacherIDIsNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new TeacherAcademicEmail(null);
        });

        assertEquals("Teacher's acronym cannot be null!", thrown.getMessage());
    }

    @Test
    void shouldBeEqualIfEmailsAreTheSame() throws Exception {
        TeacherAcronym teacherAcronym1 = new TeacherAcronym("SDE");
        TeacherAcronym teacherAcronym2 = new TeacherAcronym("SDE");

        TeacherAcademicEmail email1 = new TeacherAcademicEmail(teacherAcronym1);
        TeacherAcademicEmail email2 = new TeacherAcademicEmail(teacherAcronym2);

        assertEquals(email1, email2);

    }

    @Test
    void shouldNotBeEqualIfEmailsAreDifferent() throws Exception {
        TeacherAcronym teacherAcronym1 = new TeacherAcronym("ABC");
        TeacherAcronym teacherAcronym2 = new TeacherAcronym("XYZ");

        TeacherAcademicEmail email1 = new TeacherAcademicEmail(teacherAcronym1);
        TeacherAcademicEmail email2 = new TeacherAcademicEmail(teacherAcronym2);

        assertNotEquals(email1, email2);

    }

    @Test
    void shouldReturnCorrectStringRepresentation() throws Exception {
        TeacherAcronym teacherAcronym = new TeacherAcronym("DEF");
        TeacherAcademicEmail teacherEmail = new TeacherAcademicEmail(teacherAcronym);

        assertEquals("DEF@isep.ipp.pt", teacherEmail.toString());
    }

    @Test
    void shouldGenerateEmailForDifferentTeacherIDs() throws Exception {
        TeacherAcronym teacherAcronym1 = new TeacherAcronym("LMN");
        TeacherAcronym teacherAcronym2 = new TeacherAcronym("XYZ");

        TeacherAcademicEmail email1 = new TeacherAcademicEmail(teacherAcronym1);
        TeacherAcademicEmail email2 = new TeacherAcademicEmail(teacherAcronym2);

        assertEquals("LMN@isep.ipp.pt", email1.toString());
        assertEquals("XYZ@isep.ipp.pt", email2.toString());
    }

    @Test
    void shouldBeImmutableAfterCreation() throws Exception {
        TeacherAcronym teacherAcronym = new TeacherAcronym("QWE");
        TeacherAcademicEmail teacherEmail = new TeacherAcademicEmail(teacherAcronym);

        String emailBefore = teacherEmail.getTeacherAcademicEmail();

        assertEquals(emailBefore, teacherEmail.getTeacherAcademicEmail());
    }
}