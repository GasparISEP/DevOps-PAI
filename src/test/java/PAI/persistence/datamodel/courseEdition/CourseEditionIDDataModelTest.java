package PAI.persistence.datamodel.courseEdition;

import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
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

    @Test
    void shouldReturnNullWhenTryToGetProgrammeEditionIDDataModel() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = new CourseEditionIDDataModel();

        // Act
        ProgrammeEditionIdDataModel pEIDDataModel = courseEditionIDDataModel.getProgrammeEditionIdDataModel();

        // Assert
        assertNull(pEIDDataModel);
    }

    @Test
    void shouldReturnNullWhenTryToGetCourseInStudyPlanIDDataModel() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = new CourseEditionIDDataModel();

        // Act
        CourseInStudyPlanIDDataModel cISPIDDataModel = courseEditionIDDataModel.getCourseInStudyPlanIDDataModel();

        // Assert
        assertNull(cISPIDDataModel);
    }

    @Test
    void shouldReturnFalseWhenUseEqualsMethodInCourseEditionIDDataModel() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = new CourseEditionIDDataModel();

        // Act
        boolean result = courseEditionIDDataModel.equals(courseEditionIDDataModel);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnMinus1WhenUseHashCodeMethodInCourseEditionIDDataModel() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = new CourseEditionIDDataModel();

        // Act
        int result = courseEditionIDDataModel.hashCode();

        // Assert
        assertEquals(-1, result);
    }

}