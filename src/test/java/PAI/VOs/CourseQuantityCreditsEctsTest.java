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

}