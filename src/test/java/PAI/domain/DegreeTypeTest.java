package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeTest {
    @Test
    void creationOfAnObjectDegreeType() throws Exception {
        // arrange + act
        DegreeType bachelor = new DegreeType("Bachelor", 25);
    }


    @Test
    void emptyNameDontCreateADegreeType() throws Exception {

        // arrange + act + assert
        assertThrows(Exception.class, () -> new DegreeType("", 20));
    }

    @Test
    void nullNameDontCreateADegreeType() throws Exception {

        // arrange + act + assert
        assertThrows(Exception.class, () -> new DegreeType(null, 20));
    }

}