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
        // arrenge + act +  assert
        assertThrows(IllegalArgumentException.class, () -> new CurricularYear(year));
    }

    @Test
    void shouldReturnCurricularYear() {
        //arrange
        CurricularYear curricularYear = new CurricularYear(1);
        //act
        int resultado = curricularYear.getCurricularYear();
        //assert
        assertEquals(1, resultado);
    }
}