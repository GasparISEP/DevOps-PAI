package PAI.persistence.datamodel.courseEdition;

import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseEditionIDDataModelTest {

    // -----Constructos Tests-----
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
    void shouldCreateCourseEditionIDWithArguments() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);

        // Act
        CourseEditionIDDataModel courseEditionIDDataModel = new CourseEditionIDDataModel(programmeEditionIDDataModel, courseInStudyPlanIDDataModel);

        // Assert
        assertNotNull(courseEditionIDDataModel);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfProgrammeEditionIdDataModelIsNull() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = null;
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionIDDataModel(programmeEditionIDDataModel, courseInStudyPlanIDDataModel);});

        // Assert
        assertEquals("ProgrammeEditionIDDataModel cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfCourseInStudyPlantIDDataModeIdIsNull() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = null;

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionIDDataModel(programmeEditionIDDataModel, courseInStudyPlanIDDataModel);});

        // Assert
        assertEquals("CourseInStudyPlanIDDataModel cannot be null", exception.getMessage());
    }

    // -----getProgrammeEditionIDDataModel Tests-----
    @Test
    void shouldReturnNullWhenTryToGetProgrammeEditionIDDataModelWhenCourseEditionIDDataModelIsCreatedWithEmptyConstructor() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = new CourseEditionIDDataModel();

        // Act
        ProgrammeEditionIdDataModel pEIDDataModel = courseEditionIDDataModel.getProgrammeEditionIDDataModel();

        // Assert
        assertNull(pEIDDataModel);
    }

    @Test
    void shouldReturnProgrammeEditionIDDataModelWhenProgrammeEditionIDDataModelIsCreatedWithArguments() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = new CourseEditionIDDataModel(programmeEditionIDDataModel, courseInStudyPlanIDDataModel);

        // Act
        ProgrammeEditionIdDataModel pEIDDM = courseEditionIDDataModel.getProgrammeEditionIDDataModel();

        // Assert
        assertNotNull(pEIDDM);
        assertEquals(programmeEditionIDDataModel, pEIDDM);
    }

    // -----getCourseInStudyPlanIDDataModel Tests-----
    @Test
    void shouldReturnNullWhenTryToGetCourseInStudyPlanIDDataModelWhenCourseEditionIDDataModelIsCreatedWithEmptyConstructor() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = new CourseEditionIDDataModel();

        // Act
        CourseInStudyPlanIDDataModel cISPIDDataModel = courseEditionIDDataModel.getCourseInStudyPlanIDDataModel();

        // Assert
        assertNull(cISPIDDataModel);
    }

    @Test
    void shouldReturnCourseInStudyPlantIDDataModelWhenProgrammeEditionIDDataModelIsCreatedWithArguments() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = new CourseEditionIDDataModel(programmeEditionIDDataModel, courseInStudyPlanIDDataModel);

        // Act
        CourseInStudyPlanIDDataModel cISPIDDM = courseEditionIDDataModel.getCourseInStudyPlanIDDataModel();

        // Assert
        assertNotNull(cISPIDDM);
        assertEquals(courseInStudyPlanIDDataModel, cISPIDDM);
    }

    // -----equals Tests-----
    @Test
    void shouldReturnTrueWhenCompareCourseEditionIDDataModelToItSelf() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = new CourseEditionIDDataModel(programmeEditionIDDataModel, courseInStudyPlanIDDataModel);

        // Act
        boolean result = courseEditionIDDataModel.equals(courseEditionIDDataModel);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenCompareCourseEditionIDDataModelToANull() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = new CourseEditionIDDataModel(programmeEditionIDDataModel, courseInStudyPlanIDDataModel);

        // Act
        boolean result = courseEditionIDDataModel.equals(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenCompareCourseEditionIDDataModelToADifferentClass() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = new CourseEditionIDDataModel(programmeEditionIDDataModel, courseInStudyPlanIDDataModel);

        // Act
        boolean result = courseEditionIDDataModel.equals(programmeEditionIDDataModel);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenCompareCourseEditionIDDataModelToADifferentCourseEditionIDDataModelInstanceButWithSameAttributes() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel1 = new CourseEditionIDDataModel(programmeEditionIDDataModel, courseInStudyPlanIDDataModel);
        CourseEditionIDDataModel courseEditionIDDataModel2 = new CourseEditionIDDataModel(programmeEditionIDDataModel, courseInStudyPlanIDDataModel);

        // Act
        boolean result = courseEditionIDDataModel1.equals(courseEditionIDDataModel2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenCompareCourseEditionIDDataModelToADifferentCourseEditionIDDataModelInstanceWithDifferentProgrammeEditionIDDataModel() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIDDataModel1 = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIDDataModel2 = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel1 = new CourseEditionIDDataModel(programmeEditionIDDataModel1, courseInStudyPlanIDDataModel);
        CourseEditionIDDataModel courseEditionIDDataModel2 = new CourseEditionIDDataModel(programmeEditionIDDataModel2, courseInStudyPlanIDDataModel);

        // Act
        boolean result = courseEditionIDDataModel1.equals(courseEditionIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenCompareCourseEditionIDDataModelToADifferentCourseEditionIDDataModelInstanceWithDifferentCourseInStudyPlanIDDataModel() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel1 = mock(CourseInStudyPlanIDDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel2 = mock(CourseInStudyPlanIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel1 = new CourseEditionIDDataModel(programmeEditionIDDataModel, courseInStudyPlanIDDataModel1);
        CourseEditionIDDataModel courseEditionIDDataModel2 = new CourseEditionIDDataModel(programmeEditionIDDataModel, courseInStudyPlanIDDataModel2);

        // Act
        boolean result = courseEditionIDDataModel1.equals(courseEditionIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenCompareCourseEditionIDDataModelToADifferentCourseEditionIDDataModelInstanceWithAllDifferentAttributes() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIDDataModel1 = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIDDataModel2 = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel1 = mock(CourseInStudyPlanIDDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel2 = mock(CourseInStudyPlanIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel1 = new CourseEditionIDDataModel(programmeEditionIDDataModel1, courseInStudyPlanIDDataModel1);
        CourseEditionIDDataModel courseEditionIDDataModel2 = new CourseEditionIDDataModel(programmeEditionIDDataModel2, courseInStudyPlanIDDataModel2);

        // Act
        boolean result = courseEditionIDDataModel1.equals(courseEditionIDDataModel2);

        // Assert
        assertFalse(result);
    }


    // ----- hashCode Tests-----
    @Test
    void shouldReturnValidHashCodeMethod() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = new CourseEditionIDDataModel(programmeEditionIDDataModel, courseInStudyPlanIDDataModel);

        // Act
        int result = courseEditionIDDataModel.hashCode();

        // Assert
        assertEquals(result, courseInStudyPlanIDDataModel.hashCode()+programmeEditionIDDataModel.hashCode());
    }

}