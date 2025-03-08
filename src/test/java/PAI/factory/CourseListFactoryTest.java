package PAI.factory;

import PAI.domain.Course;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseListFactoryTest {

    @Test
    void shouldReturnCourseListFactory(){

        //Arrange
        CourseListFactory courseListFactory = new CourseListFactory();

        //Act

        List<Course> result = courseListFactory.createCourseList();

        //Assert
        assertNotNull(result);

    }
}