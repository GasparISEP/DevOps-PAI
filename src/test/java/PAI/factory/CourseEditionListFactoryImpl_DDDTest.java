package PAI.factory;

import PAI.domain.CourseEditionDDD;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class CourseEditionListFactoryImpl_DDDTest {

    @Test
    void shouldCreateCourseEditionArrayList() {
        // Arrange
        ICourseEditionListFactoryDDD courseEditionListFactory = new CourseEditionListFactoryDDDImpl();

        // Act
        List<CourseEditionDDD> courseEditionArrayList = courseEditionListFactory.newList();

        // Assert
        assertNotNull(courseEditionArrayList);
    }
}