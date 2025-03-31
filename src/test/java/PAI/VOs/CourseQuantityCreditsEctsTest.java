package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseQuantityCreditsEctsTest {

    @Test
    void shouldCreateCourseQuantityCreditsEcts() throws Exception{
        //act
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(6);
        //assert
        assertNotNull(courseQuantityCreditsEcts);
    }

    @Test
    void shouldThrowExceptionWhenCourseQuantityCreditsEctsIsLower() throws Exception{
        //act + assert
        assertThrows(Exception.class, () -> new CourseQuantityCreditsEcts(0));
    }

    @Test
    void shouldThrowExceptionWhenCourseQuantityCreditsEctsIsHigher() throws Exception{
        //act + assert
        assertThrows(Exception.class, () -> new CourseQuantityCreditsEcts(61));
    }

    @Test
    void shouldThrowExceptionWhenCourseQuantityCreditsEctsHas2DecimalPlace() throws Exception{
        //act + assert
        assertThrows(Exception.class, () -> new CourseQuantityCreditsEcts(5.22));
    }

    @Test
    void shouldCreateCourseQuantityCreditsEctsWithZeroDecimalPlace() throws Exception{
        //act
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        //assert
        assertNotNull(courseQuantityCreditsEcts);
    }

    @Test
    void shouldCreateCourseQuantityCreditsEctsWithOneDecimalPlace() throws Exception{
        //act
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5.0);
        //assert
        assertNotNull(courseQuantityCreditsEcts);
    }

    @Test
    void shouldCreateCourseQuantityCreditsEctsWithTwoZeroDecimalPlace() throws Exception{
        //act
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5.00);
        //assert
        assertNotNull(courseQuantityCreditsEcts);
    }

}