package PAI.VOs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CurricularYearTest {

    @Test
    void shouldCreateCurricularYear() {
        //arrange+act
        CurricularYear curricularYear = new CurricularYear(1);
        //assert
        assertNotNull(curricularYear);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void shouldNotCreateNonPositiveCurricularYear(int year) {
        // arrange + act +  assert
        assertThrows(IllegalArgumentException.class, () -> new CurricularYear(year));
    }

    @Test
    void shouldReturnCurricularYear() {
        //arrange
        CurricularYear curricularYear = new CurricularYear(3);
        //act
        int resultado = curricularYear.toInt();
        //assert
        assertEquals(3, resultado);
    }


    /*@Test
    void shouldNotAllowCurricularYearGreaterThanTotalYears() {
        // arrange + act +  assert
        assertThrows(IllegalArgumentException.class, () -> new CurricularYear(3));
    }*/

    @Test
    void shouldAllowCurricularYearEqualToTotalYears() {
        //arrange + act
        CurricularYear curricularYear = new CurricularYear(3);
        //assert
        assertNotNull(curricularYear);
        assertEquals(3, curricularYear.toInt());
    }

    @Test
    void testEquals() {
        //arrange + act
        CurricularYear CY1 = new CurricularYear(1);
        CurricularYear CY2 = new CurricularYear(1);
        CurricularYear CY3 = new CurricularYear(2);
        //assert - Verifica que dois semestres com o mesmo valor são iguais
        assertEquals(CY1, CY2);
        //assert - Verifica que semestres com valores diferentes não são iguais
        assertNotEquals(CY1, CY3);
    }

    @Test
    void testHashCode() {
        //arrange + act
        CurricularYear CY1 = new CurricularYear(1);
        CurricularYear CY2 = new CurricularYear(1);
        CurricularYear CY3 = new CurricularYear(2);
        //assert - Dois objetos iguais devem ter o mesmo hashCode
        assertEquals(CY1.hashCode(), CY2.hashCode());
        assertNotEquals(CY1.hashCode(), CY3.hashCode());

    }

   /* @ParameterizedTest
    @ValueSource(ints = {1, 2, 5})
    void shouldValidatePositiveCurricularYears(int year) {
        //arrange + act
        CurricularYear curricularYear = new CurricularYear(year);
        //assert
        assertTrue(curricularYear.isCurricularYearPositive(year));
    }*/

    /*@ParameterizedTest
    @ValueSource(ints = {0, -2})
    void shouldInvalidateNonPositiveCurricularYears(int year) {
        //arrange + act
        CurricularYear curricularYear = new CurricularYear(5);
        //assert
        assertFalse(curricularYear.isCurricularYearPositive(year));
    }*/
}