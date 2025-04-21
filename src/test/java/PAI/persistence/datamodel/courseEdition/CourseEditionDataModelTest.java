package PAI.persistence.datamodel.courseEdition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionDataModelTest {

    @Test
    void shouldCreateCourseEditionDataModelWithEmptyArguments() {
        // Arrange
        CourseEditionDataModel courseEditionDataModel;

        // Act
        courseEditionDataModel = new CourseEditionDataModel();

        // Assert
        assertNotNull(courseEditionDataModel);
    }
}