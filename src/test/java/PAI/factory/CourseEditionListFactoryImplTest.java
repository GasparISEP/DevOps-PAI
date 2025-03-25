package PAI.factory;

import PAI.domain.CourseEdition;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionListFactoryImplTest {

    @Test
    void shouldCreateCourseEditionArrayList() {
        // Arrange
        ICourseEditionListFactory ICourseEditionListFactory = new CourseEditionListFactoryImpl();

        // Act
        List<CourseEdition> courseEditionArrayList = ICourseEditionListFactory.newArrayList();

        // Assert
        assertNotNull(courseEditionArrayList);
    }
}