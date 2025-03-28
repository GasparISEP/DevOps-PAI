package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherAcademicEmailTest {

    @Test
    void shouldCreateValidTeacherAcademicEmail() throws Exception {
        TeacherID teacherID = new TeacherID("ABC");
        TeacherAcademicEmail teacherEmail = new TeacherAcademicEmail(teacherID);

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
        TeacherID teacherID1 = new TeacherID("SDE");
        TeacherID teacherID2 = new TeacherID("SDE");

        TeacherAcademicEmail email1 = new TeacherAcademicEmail(teacherID1);
        TeacherAcademicEmail email2 = new TeacherAcademicEmail(teacherID2);

        assertEquals(email1, email2);

    }

    @Test
    void shouldNotBeEqualIfEmailsAreDifferent() throws Exception {
        TeacherID teacherID1 = new TeacherID("ABC");
        TeacherID teacherID2 = new TeacherID("XYZ");

        TeacherAcademicEmail email1 = new TeacherAcademicEmail(teacherID1);
        TeacherAcademicEmail email2 = new TeacherAcademicEmail(teacherID2);

        assertNotEquals(email1, email2);

    }

    @Test
    void shouldReturnCorrectStringRepresentation() throws Exception {
        TeacherID teacherID = new TeacherID("DEF");
        TeacherAcademicEmail teacherEmail = new TeacherAcademicEmail(teacherID);

        assertEquals("DEF@isep.ipp.pt", teacherEmail.toString());
    }

    @Test
    void shouldGenerateEmailForDifferentTeacherIDs() throws Exception {
        TeacherID teacherID1 = new TeacherID("LMN");
        TeacherID teacherID2 = new TeacherID("XYZ");

        TeacherAcademicEmail email1 = new TeacherAcademicEmail(teacherID1);
        TeacherAcademicEmail email2 = new TeacherAcademicEmail(teacherID2);

        assertEquals("LMN@isep.ipp.pt", email1.toString());
        assertEquals("XYZ@isep.ipp.pt", email2.toString());
    }

    @Test
    void shouldBeImmutableAfterCreation() throws Exception {
        TeacherID teacherID = new TeacherID("QWE");
        TeacherAcademicEmail teacherEmail = new TeacherAcademicEmail(teacherID);

        String emailBefore = teacherEmail.getTeacherAcademicEmail();

        assertEquals(emailBefore, teacherEmail.getTeacherAcademicEmail());
    }
}