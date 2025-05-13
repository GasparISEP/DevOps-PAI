package PAI.factory;

import PAI.domain.courseEdition.CourseEdition;
import PAI.persistence.mem.courseEdition.CourseEditionListFactoryImpl;
import PAI.persistence.mem.courseEdition.ICourseEditionListFactory;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class CourseEditionListFactoryImplTest {

    @Test
    void shouldCreateCourseEditionArrayList() {
        // Arrange
        ICourseEditionListFactory courseEditionListFactory = new CourseEditionListFactoryImpl();

        // Act
        List<CourseEdition> courseEditionArrayList = courseEditionListFactory.newList();

        // Assert
        assertNotNull(courseEditionArrayList);
    }
}