package PAI.repository.courseRepositoryDDD;

import PAI.domain.CourseDDD;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseRepositoryListFactoryImplTest {

    @Test
    void createCourseRepositoryListFactory() {
        // arrange
        // act
        CourseRepositoryListFactoryImpl courseRepositoryListFactory = new CourseRepositoryListFactoryImpl();
        // assert
        assertNotNull(courseRepositoryListFactory);
    }

    @Test
    void createCourseRepositoryList() {
        // arrange
        CourseRepositoryListFactoryImpl courseRepositoryListFactory = new CourseRepositoryListFactoryImpl();
        // act
        List<CourseDDD> list = courseRepositoryListFactory.createCourseRepositoryList();
        // assert
        assertNotNull(list);
        assertInstanceOf(ArrayList.class,list);
    }
}