package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseRepositoryTest {

    @Test
    void testCourseRepositoryCreationValid() {
        //arrange
        CourseFactory courseFactory = mock(CourseFactory.class);
        //act
        CourseRepository cR1 = new CourseRepository (courseFactory);
        //assert
        assertNotNull(cR1);
    }

    @Test
    void shouldReturnTrueIfCourseIsRegistered() throws Exception {
        //arrange
        CourseFactory courseFactory = mock(CourseFactory.class);
        CourseRepository cR1 = new CourseRepository (courseFactory);
        //act
        boolean result = cR1.registerCourse("Matemática", "MAT", 10, 1);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAccessMethodIsNotRegistered() throws Exception {
        //arrange
        CourseFactory courseFactory = mock(CourseFactory.class);
        CourseRepository cR1 = new CourseRepository (courseFactory);
        cR1.registerCourse("Matemática", "MAT", 10, 1);
        //act
        boolean result = cR1.registerCourse("Matemática", "MAT", 10, 1);
        //assert
        assertFalse(result);
    }
}