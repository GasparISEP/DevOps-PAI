package PAI.factory;

import PAI.domain.CourseEdition_2;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class CourseEditionListFactoryImpl_2Test {

    @Test
    void shouldCreateCourseEditionArrayList() {
        // Arrange
        ICourseEditionListFactory_2 courseEditionListFactory = new CourseEditionListFactoryImpl_2();

        // Act
        List<CourseEdition_2> courseEditionArrayList = courseEditionListFactory.newList();

        // Assert
        assertNotNull(courseEditionArrayList);
    }
}