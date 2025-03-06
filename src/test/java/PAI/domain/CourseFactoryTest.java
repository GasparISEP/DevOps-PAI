package PAI.domain;

import PAI.factory.CourseFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseFactoryTest {

    @Test
    void shouldCreateCourse() throws Exception {
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        //act
        Course course = courseFactory.createCourse("Matemática", "MAT", 10, 1);
        //assert
        assertNotNull(course);
    }

    @Test
    void shouldNotCreateCourseIfCourseHasNoName() throws Exception {
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        //act + assert
        assertThrows(Exception.class, () -> courseFactory.createCourse("", "MAT", 10, 1));
    }

    @Test
    void shouldNotCreateCourseIfCourseHasNoAcronym() throws Exception {
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        //act + assert
        assertThrows(Exception.class, () -> courseFactory.createCourse("Matemática", "", 10, 1));
    }

    @Test
    void shouldNotCreateCourseIfCourseHasZeroQuantityCreditsETC() throws Exception {
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        //act + assert
        assertThrows(Exception.class, () -> courseFactory.createCourse("Matemática", "MAT", 0, 1));
    }
    @Test
    void shouldNotCreateCourseIfCourseHasZeroDurationCourseSemester() throws Exception {
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        //act + assert
        assertThrows(Exception.class, () -> courseFactory.createCourse("Matemática", "MAT", 1, 0));
    }


}