package PAI.persistence.datamodel.courseEdition;

import PAI.persistence.datamodel.TeacherIDDataModel;
import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);

        // Act
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel, teacherIDDataModel);

        // Assert
        assertNotNull(courseEditionDataModel);
    }

    @Test
    void shouldThrowExceptionIfCourseEditionIdDataModelGivenIsNull() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = null;
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionDataModel(courseEditionIDDataModel, teacherIDDataModel);});

        // Assert
        assertEquals("courseEditionIDDataModel cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfTeacherIDDataModelGivenIsNull() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionDataModel(courseEditionIDDataModel, teacherIDDataModel);});

        // Assert
        assertEquals("teacherIDDataModel cannot be null", exception.getMessage());
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
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);

        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel, teacherIDDataModel);

        // Act
        CourseEditionIDDataModel cEIDDM = courseEditionDataModel.getCourseEditionIDDataModel();

        // Assert
        assertEquals(courseEditionIDDataModel, cEIDDM);
    }

    // -----getProgrammeEditionIDDataModel Tests-----
    @Test
    void shouldReturnProgrammeEditionIDDataModelWhenCourseEditionDataModelIsCreatedWithArguments() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIDDataModel = mock(ProgrammeEditionIdDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel, teacherIDDataModel);

        when(courseEditionDataModel.getProgrammeEditionIDDataModel()).thenReturn(programmeEditionIDDataModel);

        // Act
        ProgrammeEditionIdDataModel pEIDDM = courseEditionDataModel.getProgrammeEditionIDDataModel();

        // Assert
        assertEquals(programmeEditionIDDataModel, pEIDDM);
    }

    // -----getCourseInStudyPlanIDDataModel Tests-----
    @Test
    void shouldReturnCourseInStudyPlanIDDataModelWhenCourseEditionDataModelIsCreatedWithArguments() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        CourseInStudyPlanIDDataModel courseInStudyPlanIDDataModel = mock(CourseInStudyPlanIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel, teacherIDDataModel);

        when(courseEditionIDDataModel.getCourseInStudyPlanIDDataModel()).thenReturn(courseInStudyPlanIDDataModel);

        // Act
        CourseInStudyPlanIDDataModel cISPIDDM = courseEditionDataModel.getCourseInStudyPlanIDDataModel();

        // Assert
        assertEquals(courseInStudyPlanIDDataModel, cISPIDDM);
    }

    // -----getCourseInStudyPlanIDDataModel Tests-----
    @Test
    void shouldReturnNullWhenTryToTeacherIDDataModelWhenCourseEditionDataModelIsCreatedWithEmptyConstructor() {
        // Arrange
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel();

        // Act
        TeacherIDDataModel teacherIDDataModel = courseEditionDataModel.getTeacherIDDataModel();

        // Assert
        assertNull(teacherIDDataModel);
    }

    @Test
    void shouldReturnTeacherIDDataModelWhenCourseEditionDataModelIsCreatedWithArguments() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel, teacherIDDataModel);

        // Act
        TeacherIDDataModel teacherIDDataModelResult = courseEditionDataModel.getTeacherIDDataModel();

        // Assert
        assertEquals(teacherIDDataModel, teacherIDDataModelResult);
    }

    // -----equals Tests-----
    @Test
    void shouldReturnTrueWhenCompareCourseEditionDataModelToItSelf() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel, teacherIDDataModel);

        // Act
        boolean result = courseEditionDataModel.equals(courseEditionDataModel);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenCompareCourseEditionDataModelToANull() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel, teacherIDDataModel);

        // Act
        boolean result = courseEditionDataModel.equals(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenCompareCourseEditionIDDataModelToADifferentClass () {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel, teacherIDDataModel);

        // Act
        boolean result = courseEditionDataModel.equals(courseEditionIDDataModel);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenCompareCourseEditionDataModelToADifferentCourseEditionDataModelInstanceButWithSameAttributes() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel1 = new CourseEditionDataModel(courseEditionIDDataModel, teacherIDDataModel);
        CourseEditionDataModel courseEditionDataModel2 = new CourseEditionDataModel(courseEditionIDDataModel, teacherIDDataModel);

        // Act
        boolean result = courseEditionDataModel1.equals(courseEditionDataModel2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenComparedTwoCourseEditionDataModelInstancesWithDifferentCourseEditionIDs() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel1 = mock(CourseEditionIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel2 = mock(CourseEditionIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel1 = new CourseEditionDataModel(courseEditionIDDataModel1, teacherIDDataModel);
        CourseEditionDataModel courseEditionDataModel2 = new CourseEditionDataModel(courseEditionIDDataModel2, teacherIDDataModel);

        // Act
        boolean result = courseEditionDataModel1.equals(courseEditionDataModel2);

        // Assert
        assertFalse(result);
    }

    // -----hashCode Tests-----
    @Test
    void shouldReturnZeroWhenUseHashCodeMethod() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel, teacherIDDataModel);

        // Act
        int result = courseEditionDataModel.hashCode();

        // Assert
        assertEquals(courseEditionDataModel.hashCode(), result);
    }
}