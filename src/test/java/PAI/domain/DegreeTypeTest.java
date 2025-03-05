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


    @Test
    void negativeECTSDontCreateADegreeType() throws Exception {

        // arrange + act + assert
        assertThrows(Exception.class, () -> new DegreeType("Master", -1));
    }

    @Test
    void testMaxEctsBoundary() {
        Exception exceptionZero = assertThrows(IllegalArgumentException.class, () ->
                new DegreeType("Bachelor", 0));
        assertEquals("The number os ECTS cannot be 0 or below", exceptionZero.getMessage());

        Exception exceptionNegative = assertThrows(IllegalArgumentException.class, () ->
                new DegreeType("Bachelor", -1));
        assertEquals("The number os ECTS cannot be 0 or below", exceptionNegative.getMessage());
    }

}