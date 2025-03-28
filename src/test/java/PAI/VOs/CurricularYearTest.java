package PAI.VOs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CurricularYearTest {

    @Test
    void shouldCreateCurricularYear() {
        //arrange+act
        CurricularYear curricularYear = new CurricularYear(1, 3);
        //assert
        assertNotNull(curricularYear);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void shouldNotCreateNonPositiveCurricularYear(int year) {
        // arrange + act +  assert
        assertThrows(IllegalArgumentException.class, () -> new CurricularYear(year, 3));
    }

    @Test
    void shouldReturnCurricularYear() {
        //arrange
        CurricularYear curricularYear = new CurricularYear(1, 3);
        //act
        int resultado = curricularYear.getCurricularYear();
        //assert
        assertEquals(1, resultado);
    }


    @Test
    void shouldNotAllowCurricularYearGreaterThanTotalYears() {
        // arrange + act +  assert
        assertThrows(IllegalArgumentException.class, () -> new CurricularYear(4, 3));
    }

    @Test
    void shouldAllowCurricularYearEqualToTotalYears() {
        //arrange + act
        CurricularYear curricularYear = new CurricularYear(3, 3);
        //assert
        assertNotNull(curricularYear);
        assertEquals(3, curricularYear.getCurricularYear());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5})
    void shouldValidatePositiveCurricularYears(int year) {
        //arrange + act
        CurricularYear curricularYear = new CurricularYear(1, 5);
        //assert
        assertTrue(curricularYear.isCurricularYearPositive(year));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -2})
    void shouldInvalidateNonPositiveCurricularYears(int year) {
        //arrange + act
        CurricularYear curricularYear = new CurricularYear(1, 5);
        //assert
        assertFalse(curricularYear.isCurricularYearPositive(year));
    }
}