package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseIDTest {

    @Test
    void shouldCreateCourseID() {
        //arrange + act
        CourseID id = new CourseID();
        //assert
        assertNotNull(id);
    }
}