package PAI.persistence.datamodel.courseEdition;

import PAI.VOs.CourseInStudyPlanID;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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

    @Test
    void shouldCreateCourseEditionWithArguments() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);

        // Act
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel, programmeEditionIDDataModel, courseInStudyPlanIDDataModel);

        // Assert
        assertNotNull(courseEditionDataModel);
    }

    @Test
    void shouldThrowExceptionIfCourseEditionIdDataModelGivenIsNull() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = null;
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionDataModel(courseEditionIDDataModel, programmeEditionIDDataModel, courseInStudyPlanIDDataModel);});

        // Assert
        assertEquals("courseEditionIDDataModel cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfProgrammeEditionIdDataModelGivenIsNull() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = null;
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionDataModel(courseEditionIDDataModel, programmeEditionIDDataModel, courseInStudyPlanIDDataModel);});

        // Assert
        assertEquals("programmeEditionIDDataModel cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCourseInStudyPlanIdDataModelGivenIsNull() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionDataModel(courseEditionIDDataModel, programmeEditionIDDataModel, courseInStudyPlanIDDataModel);});

        // Assert
        assertEquals("courseInStudyPlanIDDataModel cannot be null", exception.getMessage());
    }

    // -----getCourseEditionIDDataModel Tests-----
    @Test
    void shouldReturnNullWhenTryToGetCourseEditionIDDataModelWhenCourseEditionDataModelIsCreatedWithEmptyConstructor() {
        // Arrange
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel();

        // Act
        CourseEditionIDDataModel courseEditionIDDataModel = courseEditionDataModel.getCourseEditionIDDataModel();

        // Assert
        assertNull(courseEditionIDDataModel);
    }

    @Test
    void shouldReturnCourseEditionIDDataModelWhenCourseEditionDataModelIsCreatedWithArguments() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel, programmeEditionIDDataModel, courseInStudyPlanIDDataModel);


        // Act
        CourseEditionIDDataModel cEIDDM = courseEditionDataModel.getCourseEditionIDDataModel();

        // Assert
        assertEquals(courseEditionIDDataModel, cEIDDM);
    }

    // -----getProgrammeEditionIDDataModel Tests-----
    @Test
    void shouldReturnNullWhenTryToGetProgrammeEditionIDDataModelWhenCourseEditionDataModelIsCreatedWithEmptyConstructor() {
        // Arrange
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel();

        // Act
        ProgrammeEditionIdDataModel courseEditionIDDataModel = courseEditionDataModel.getProgrammeEditionIDDataModel();

        // Assert
        assertNull(courseEditionIDDataModel);
    }

    @Test
    void shouldReturnProgrammeEditionIDDataModelWhenCourseEditionDataModelIsCreatedWithArguments() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel, programmeEditionIDDataModel, courseInStudyPlanIDDataModel);


        // Act
        ProgrammeEditionIdDataModel pEIDDM = courseEditionDataModel.getProgrammeEditionIDDataModel();

        // Assert
        assertEquals(programmeEditionIDDataModel, pEIDDM);
    }

    // -----getCourseInStudyPlanIDDataModel Tests-----
    @Test
    void shouldReturnNullWhenTryToGetCourseInStudyPlanIDDataModelWhenCourseEditionDataModelIsCreatedWithEmptyConstructor() {
        // Arrange
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel();

        // Act
        CourseInStudyPlanIDDataModel courseEditionIDDataModel = courseEditionDataModel.getCourseInStudyPlanIDDataModel();

        // Assert
        assertNull(courseEditionIDDataModel);
    }

    @Test
    void shouldReturnCourseInStudyPlanIDDataModelWhenCourseEditionDataModelIsCreatedWithArguments() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel, programmeEditionIDDataModel, courseInStudyPlanIDDataModel);

        // Act
        CourseInStudyPlanIDDataModel cISPIDDM = courseEditionDataModel.getCourseInStudyPlanIDDataModel();

        // Assert
        assertEquals(courseInStudyPlanIDDataModel, cISPIDDM);
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