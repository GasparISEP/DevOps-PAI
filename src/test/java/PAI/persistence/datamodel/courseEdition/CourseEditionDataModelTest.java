package PAI.persistence.datamodel.courseEdition;

import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
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

    @Test
    void shouldReturnNullWhenUseGetCourseEditionIDDataModelMethod() {
        // Arrange
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel();

        // Act
        CourseEditionIDDataModel courseEditionIDDataModel = courseEditionDataModel.getCourseEditionIDDataModel();

        // Assert
        assertNull(courseEditionIDDataModel);
    }

    @Test
    void shouldReturnNullWhenUseGetProgrammeEditionIDDataModelMethod() {
        // Arrange
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel();

        // Act
        ProgrammeEditionIdDataModel courseEditionIDDataModel = courseEditionDataModel.getProgrammeEditionIDDataModel();

        // Assert
        assertNull(courseEditionIDDataModel);
    }
}