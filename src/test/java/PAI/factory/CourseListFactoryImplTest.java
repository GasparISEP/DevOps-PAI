package PAI.factory;

import PAI.domain.Course;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseListFactoryImplTest {

    @Test
    void shouldReturnCourseListFactory(){

        //Arrange
        CourseListFactoryImpl courseListFactoryImpl = new CourseListFactoryImpl();

        //Act

        List<Course> result = courseListFactoryImpl.createCourseList();

        //Assert
        assertNotNull(result);

    }
}