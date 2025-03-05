package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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

    @Test
    void shouldReturnTrueIfNameAndMaxEctsAreEqual() throws Exception {

        //Arrange
        DegreeType bachelor = new DegreeType("Bachelor", 25);
        DegreeType bachelor2 = new DegreeType("Bachelor", 25);

        boolean result = bachelor.equals(bachelor2);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNameIsEqualAndMaxEctsAreNotEqual() throws Exception {
        DegreeType bachelor = new DegreeType("Bachelor", 25);
        DegreeType bachelor2 = new DegreeType("Bachelor", 30);

        boolean result = bachelor.equals(bachelor2);

        assertFalse(result);

    }

    @Test
    void shouldReturnFalseIfNameIsNotEqualAndMaxEctsAreEqual() throws Exception {
        DegreeType bachelor = new DegreeType("Bachelor", 25);
        DegreeType master = new DegreeType("Master", 25);

        boolean result = bachelor.equals(master);

        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfObjectToCompareIsNotADegreeType() throws Exception {
        DegreeType bachelor = new DegreeType("Bachelor", 25);
        Teacher teacher = mock(Teacher.class);

        boolean result = bachelor.equals(teacher);

        assertFalse(result);
    }


}