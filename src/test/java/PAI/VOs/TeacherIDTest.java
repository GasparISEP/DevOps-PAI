package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherIDTest {

    @Test
    void shouldCreateAcronym() throws Exception {
        //Arrange
        TeacherID teacherAcronym1 = new TeacherID("ABC");

        //Act+Assert
        assertNotNull(teacherAcronym1);
    }

    @Test
    void shouldReturnCorrectAcronym() throws Exception {
        //Arrange
        String acronym1 = "ABC";
        TeacherID teacherAcronym2 = new TeacherID(acronym1);

        //Act
        String acronym2String = teacherAcronym2.getAcronym();

        //Assert
        assertEquals(acronym1, acronym2String);
    }

    @Test
    void shouldNotReturnAcronymIfNull() {
        //Act+Assert
        assertThrows(Exception.class, () -> new TeacherID(null));
    }

    @Test
    void shouldNotReturnAcronymIfBlank()  {
        assertThrows(Exception.class, () -> new TeacherID(""));
    }

    @Test
    void shouldNotReturnAcronymIfContainsNumbers()  {
        assertThrows(Exception.class, () -> new TeacherID("AB1"));
    }

    @Test
    void shouldNotReturnAcronymIfContainsLowerCases() {
        assertThrows(Exception.class, () -> new TeacherID("ABc"));
    }

    @Test
    void shouldNotReturnAcronymIfContainsSpecialChars() {
        assertThrows(Exception.class, () -> new TeacherID("AB@"));
    }


    @Test
    void shouldNotReturnAcronymIfMoreThan3Letters() {
        assertThrows(Exception.class, () -> new TeacherID("ABCD"));
    }

}