package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherIdTest {

    @Test
    void shouldCreateAcronym() throws Exception {
        //Arrange
        TeacherId teacherAcronym1 = new TeacherId("ABC");

        //Act+Assert
        assertNotNull(teacherAcronym1);
    }

    @Test
    void shouldReturnCorrectAcronym() throws Exception {
        //Arrange
        String acronym1 = "ABC";
        TeacherId teacherAcronym2 = new TeacherId(acronym1);

        //Act
        String acronym2String = teacherAcronym2.getAcronym();

        //Assert
        assertEquals(acronym1, acronym2String);
    }

    @Test
    void shouldNotReturnAcronymIfNull() {
        //Act+Assert
        assertThrows(Exception.class, () -> new TeacherId(null));
    }

    @Test
    void shouldNotReturnAcronymIfBlank()  {
        assertThrows(Exception.class, () -> new TeacherId(""));
    }

    @Test
    void shouldNotReturnAcronymIfContainsNumbers()  {
        assertThrows(Exception.class, () -> new TeacherId("AB1"));
    }

    @Test
    void shouldNotReturnAcronymIfContainsLowerCases() {
        assertThrows(Exception.class, () -> new TeacherId("ABc"));
    }

    @Test
    void shouldNotReturnAcronymIfContainsSpecialChars() {
        assertThrows(Exception.class, () -> new TeacherId("AB@"));
    }


    @Test
    void shouldNotReturnAcronymIfMoreThan3Letters() {
        assertThrows(Exception.class, () -> new TeacherId("ABCD"));
    }

}