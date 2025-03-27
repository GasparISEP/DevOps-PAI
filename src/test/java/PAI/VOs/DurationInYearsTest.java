package PAI.VOs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DurationInYearsTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2}) // Testa com os valores 1 e 2
    void shouldCreateDurationInYears(int quantSemester) throws Exception {
        //arrenge + act
        DurationInYears durationInYears = new DurationInYears(quantSemester);
        //assert
        assertNotNull(durationInYears);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void shouldNotCreateDurationInYears(int quantSemesters) throws Exception {
        //arrenge + act +  assert
        assertThrows(IllegalArgumentException.class, () -> new DurationInYears(quantSemesters));
    }

    @Test
    void shouldReturnDurationInYears() throws Exception {
        //arrange
        DurationInYears durationInYears = new DurationInYears(1);
        //act
        int resultado = durationInYears.getDurationInYears();
        //assert
        assertEquals(1, resultado);
    }

}