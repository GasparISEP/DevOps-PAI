package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AcronymTest {

    @Test
    void shouldCreateAcronym() {
        //Arrange
        Acronym acronym1 = new Acronym("ABC");

        //Act+Assert
        assertNotNull(acronym1);
    }

}