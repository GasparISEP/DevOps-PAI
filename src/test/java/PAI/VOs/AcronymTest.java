package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AcronymTest {

    @Test
    void shouldCreateAcronym() throws Exception {
        //Arrange
        Acronym acronym1 = new Acronym("ABC");

        //Act+Assert
        assertNotNull(acronym1);
    }

    @Test
    void shouldReturnCorrectAcronym() throws Exception {
        //Arrange
        String acronym1 = "ABC";
        Acronym acronym2 = new Acronym(acronym1);

        //Act
        String acronym2String = acronym2.getAcronym();

        //Assert
        assertEquals(acronym1, acronym2String);
    }

    @Test
    void shouldNotReturnAcronymIfNull() {
        //Act+Assert
        assertThrows(Exception.class, () -> new Acronym(null));
    }

    @Test
    void shouldNotReturnAcronymIfBlank()  {
        assertThrows(Exception.class, () -> new Acronym(""));
    }

    @Test
    void shouldNotReturnAcronymIfContainsNumbers()  {
        assertThrows(Exception.class, () -> new Acronym("AB1"));
    }

    @Test
    void shouldNotReturnAcronymIfContainsLowerCases() {
        assertThrows(Exception.class, () -> new Acronym("ABc"));
    }

    @Test
    void shouldNotReturnAcronymIfContainsSpecialChars() {
        assertThrows(Exception.class, () -> new Acronym("AB@"));
    }


    @Test
    void shouldNotReturnAcronymIfMoreThan3Letters() {
        assertThrows(Exception.class, () -> new Acronym("ABCD"));
    }

}