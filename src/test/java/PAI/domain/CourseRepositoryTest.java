package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CourseRepositoryTest {

    @Test
    void testCourseRepositoryCreationValid() {
        //arrange
        //act
        CourseRepository cR1 = new CourseRepository ();
        //assert
        assertNotNull(cR1);
    }

    @Test
    void shouldReturnTrueIfCourseIsRegistered() throws Exception {
        //arrange
        CourseRepository cR1 = new CourseRepository ();
        //act
        boolean result = cR1.registerCourse("Matemática", "MAT", 10);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAccessMethodIsNotRegistered() throws Exception {
        //arrange
        CourseRepository cR1 = new CourseRepository ();
        cR1.registerCourse("Matemática", "MAT", 10);
        //act
        boolean result = cR1.registerCourse("Matemática", "MAT", 10);
        //assert
        assertFalse(result);
    }
}