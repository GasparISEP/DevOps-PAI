package PAI.persistence.datamodel.courseEdition;

import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionDataModelTest {

    // -----Constructos Tests-----
    @Test
    void shouldCreateCourseEditionDataModelWithEmptyArguments() {
        // Arrange
        CourseEditionDataModel courseEditionDataModel;

        // Act
        courseEditionDataModel = new CourseEditionDataModel();

        // Assert
        assertNotNull(courseEditionDataModel);
    }

    // -----getCourseEditionIDDataModel Tests-----
    @Test
    void shouldReturnNullWhenUseGetCourseEditionIDDataModelMethod() {
        // Arrange
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel();

        // Act
        CourseEditionIDDataModel courseEditionIDDataModel = courseEditionDataModel.getCourseEditionIDDataModel();

        // Assert
        assertNull(courseEditionIDDataModel);
    }

    // -----getProgrammeEditionIDDataModel Tests-----
    @Test
    void shouldReturnNullWhenUseGetProgrammeEditionIDDataModelMethod() {
        // Arrange
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel();

        // Act
        ProgrammeEditionIdDataModel courseEditionIDDataModel = courseEditionDataModel.getProgrammeEditionIDDataModel();

        // Assert
        assertNull(courseEditionIDDataModel);
    }

    // -----getCourseInStudyPlanIDDataModel Tests-----
    @Test
    void shouldReturnNullWhenUseGetCourseInStudyPlanIDDataModelMethod() {
        // Arrange
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel();

        // Act
        CourseInStudyPlanIDDataModel courseEditionIDDataModel = courseEditionDataModel.getCourseInStudyPlanIDDataModel();

        // Assert
        assertNull(courseEditionIDDataModel);
    }

    // -----equals Tests-----
    @Test
    void shouldReturnFalseWhenUseEqualsMethod() {
        // Arrange
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel();

        // Act
        boolean result = courseEditionDataModel.equals(courseEditionDataModel);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnZeroWhenUseHashCodeMethod() {
        // Arrange
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel();

        // Act
        int result = courseEditionDataModel.hashCode();

        // Assert
        assertEquals(0, result);
    }
}