package PAI.factory;

import PAI.domain.CourseEdition;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionListFactoryImplTest {

    @Test
    void shouldCreateCourseEditionArrayList() {
        // Arrange
        CourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();

        // Act
        List<CourseEdition> courseEditionArrayList = courseEditionListFactory.newArrayList();

        // Assert
        assertNotNull(courseEditionArrayList);
    }
}