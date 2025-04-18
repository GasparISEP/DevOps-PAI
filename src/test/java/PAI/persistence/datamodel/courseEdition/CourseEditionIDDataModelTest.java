package PAI.persistence.datamodel.courseEdition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionIDDataModelTest {

    @Test
    void shouldCreateCourseEditionIDWithEmptyArguments() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel;

        // Act
        courseEditionIDDataModel = new CourseEditionIDDataModel();

        // Assert
        assertNotNull(courseEditionIDDataModel);
    }

}