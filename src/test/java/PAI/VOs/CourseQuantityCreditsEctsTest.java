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

}