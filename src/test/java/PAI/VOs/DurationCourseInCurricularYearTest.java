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
}