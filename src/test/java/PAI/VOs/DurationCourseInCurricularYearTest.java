package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DurationCourseInCurricularYearTest {

    @Test
    void shouldCreateDurationCourseInCurricularYearWhenDurationIs1() {
        // arrange
        int duration = 1;
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(duration);
        // act && assert
        assertNotNull(durationCourseInCurricularYear);
    }

    @Test
    void shouldCreateDurationCourseInCurricularYearWhenDurationIs2() {
        // arrange
        int duration = 2;
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(duration);
        // act && assert
        assertNotNull(durationCourseInCurricularYear);
    }

    @Test
    void shouldNotCreateDurationCourseInCurricularYearWhenDurationIs0() {
        // arrange
        int duration = 0;
        // act && assert
        assertThrows(IllegalArgumentException.class, () -> {
            new DurationCourseInCurricularYear(duration);
        });
    }

    @Test
    void shouldNotCreateDurationCourseInCurricularYearWhenDurationIs3() {
        // arrange
        int duration = 3;
        // act && assert
        assertThrows(IllegalArgumentException.class, () -> {
            new DurationCourseInCurricularYear(duration);
        });
    }

    @Test
    void shouldNotCreateDurationCourseInCurricularYearWhenDurationIsNegative() {
        // arrange
        int duration = -1;
        // act && assert
        assertThrows(IllegalArgumentException.class, () -> {
            new DurationCourseInCurricularYear(duration);
        });
    }

    @Test
    void shouldReturnTrueIfComparingTheSameObject() {
        // arrange
        int duration = 1;
        DurationCourseInCurricularYear durationCourseInCurricularYear1 = new DurationCourseInCurricularYear(duration);
        // act
        boolean result = durationCourseInCurricularYear1.equals(durationCourseInCurricularYear1);
        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfObjectsAreEqual() {
        // arrange
        int duration = 1;
        DurationCourseInCurricularYear durationCourseInCurricularYear1 = new DurationCourseInCurricularYear(duration);
        DurationCourseInCurricularYear durationCourseInCurricularYear2 = new DurationCourseInCurricularYear(duration);
        // act
        boolean result = durationCourseInCurricularYear1.equals(durationCourseInCurricularYear2);
        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectsAreNotEqual() {
        // arrange
        int duration1 = 1;
        int duration2 = 2;
        DurationCourseInCurricularYear durationCourseInCurricularYear1 = new DurationCourseInCurricularYear(duration1);
        DurationCourseInCurricularYear durationCourseInCurricularYear2 = new DurationCourseInCurricularYear(duration2);
        // act
        boolean result = durationCourseInCurricularYear1.equals(durationCourseInCurricularYear2);
        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfObjectsAreOfDifferentType() throws Exception {
        // arrange
        int duration = 1;
        DurationCourseInCurricularYear durationCourseInCurricularYear1 = new DurationCourseInCurricularYear(duration);
        QuantEcts quantEcts = new QuantEcts(1);
        // act
        boolean result = durationCourseInCurricularYear1.equals(quantEcts);
        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfObjectToCompareIsNull() {
        // arrange
        int duration = 1;
        DurationCourseInCurricularYear durationCourseInCurricularYear1 = new DurationCourseInCurricularYear(duration);
        // act
        boolean result = durationCourseInCurricularYear1.equals(null);
        // assert
        assertFalse(result);
    }
}