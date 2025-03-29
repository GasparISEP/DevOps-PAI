package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DurationCourseInCurricularYearTest {

    @Test
    void shouldReturnTrueWhenDurationIs1() {
        // arrange
        int duration = 1;
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(duration);
        // act
        boolean result = durationCourseInCurricularYear.isDurationCurricularYearValid();
        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenDurationIs2() {
        // arrange
        int duration = 2;
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(duration);
        // act
        boolean result = durationCourseInCurricularYear.isDurationCurricularYearValid();
        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenDurationIsLessThan1() {
        // arrange
        int duration = 0;
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(duration);
        // act
        boolean result = durationCourseInCurricularYear.isDurationCurricularYearValid();
        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenDurationIsHigherThan2() {
        // arrange
        int duration = 3;
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(duration);
        // act
        boolean result = durationCourseInCurricularYear.isDurationCurricularYearValid();
        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenDurationIsNegative() {
        // arrange
        int duration = -1;
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(duration);
        // act
        boolean result = durationCourseInCurricularYear.isDurationCurricularYearValid();
        // assert
        assertFalse(result);
    }
}