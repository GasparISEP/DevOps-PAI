package PAI.factory;

import PAI.domain.Course;
import PAI.domain.CourseEdition;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionListFactoryTest {

    @Test
    void shouldCreateCourseEditionArrayList() {
        // Arrange
        CourseEditionListFactory courseEditionListFactory = new CourseEditionListFactory();

        // Act
        List<CourseEdition> courseEditionArrayList = courseEditionListFactory.newArrayList();

        // Assert
        assertNotNull(courseEditionArrayList);
    }
}