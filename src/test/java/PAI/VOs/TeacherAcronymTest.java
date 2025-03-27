package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherAcronymTest {

    @Test
    void shouldCreateAcronym() throws Exception {
        //Arrange
        TeacherAcronym teacherAcronym1 = new TeacherAcronym("ABC");

        //Act+Assert
        assertNotNull(teacherAcronym1);
    }

    @Test
    void shouldReturnCorrectAcronym() throws Exception {
        //Arrange
        String acronym1 = "ABC";
        TeacherAcronym teacherAcronym2 = new TeacherAcronym(acronym1);

        //Act
        String acronym2String = teacherAcronym2.getAcronym();

        //Assert
        assertEquals(acronym1, acronym2String);
    }

    @Test
    void shouldNotReturnAcronymIfNull() {
        //Act+Assert
        assertThrows(Exception.class, () -> new TeacherAcronym(null));
    }

    @Test
    void shouldNotReturnAcronymIfBlank()  {
        assertThrows(Exception.class, () -> new TeacherAcronym(""));
    }

    @Test
    void shouldNotReturnAcronymIfContainsNumbers()  {
        assertThrows(Exception.class, () -> new TeacherAcronym("AB1"));
    }

    @Test
    void shouldNotReturnAcronymIfContainsLowerCases() {
        assertThrows(Exception.class, () -> new TeacherAcronym("ABc"));
    }

    @Test
    void shouldNotReturnAcronymIfContainsSpecialChars() {
        assertThrows(Exception.class, () -> new TeacherAcronym("AB@"));
    }


    @Test
    void shouldNotReturnAcronymIfMoreThan3Letters() {
        assertThrows(Exception.class, () -> new TeacherAcronym("ABCD"));
    }

}