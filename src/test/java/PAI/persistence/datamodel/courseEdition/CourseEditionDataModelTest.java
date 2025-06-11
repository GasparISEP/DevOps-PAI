package PAI.persistence.datamodel.courseEdition;

import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import PAI.persistence.datamodel.teacher.TeacherIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionDataModelTest {

    // -----Constructor Tests-----
    @Test
    void shouldThrowExceptionIfGivenArgumentIsNull() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = null;
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionDataModel(courseEditionIDDataModel,courseEditionGeneratedIDDataModel);});

        // Assert
        assertEquals("courseEditionIDDataModel cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCourseEditionGeneratedIDDataModelIsNull() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionDataModel(courseEditionIDDataModel,courseEditionGeneratedIDDataModel);});

        // Assert
        assertEquals("courseEditionGeneratedIDDataModel cannot be null", exception.getMessage());
    }

    @Test
    void shouldCreateCourseEditionDataModelWithOnlyCourseEditionArgument() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);

        // Act
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel, courseEditionGeneratedIDDataModel);

        // Assert
        assertNotNull(courseEditionDataModel);
    }

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
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);

        // Act
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel,courseEditionGeneratedIDDataModel, teacherIDDataModel);

        // Assert
        assertNotNull(courseEditionDataModel);
    }

    @Test
    void shouldThrowExceptionIfCourseEditionIdDataModelGivenIsNull() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = null;
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionDataModel(courseEditionIDDataModel,courseEditionGeneratedIDDataModel, teacherIDDataModel);});

        // Assert
        assertEquals("courseEditionIDDataModel cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCourseEditionGeneratedIDDataModelGivenIsNull() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionDataModel(courseEditionIDDataModel,courseEditionGeneratedIDDataModel, teacherIDDataModel);});

        // Assert
        assertEquals("courseEditionGeneratedIDDataModel cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfTeacherIDDataModelGivenIsNull() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = null;
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new CourseEditionDataModel(courseEditionIDDataModel,courseEditionGeneratedIDDataModel, teacherIDDataModel);});

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
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);

        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel, courseEditionGeneratedIDDataModel,teacherIDDataModel);

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
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel,courseEditionGeneratedIDDataModel, teacherIDDataModel);

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
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel,courseEditionGeneratedIDDataModel, teacherIDDataModel);

        when(courseEditionIDDataModel.getCourseInStudyPlanIDDataModel()).thenReturn(courseInStudyPlanIDDataModel);

        // Act
        CourseInStudyPlanIDDataModel cISPIDDM = courseEditionDataModel.getCourseInStudyPlanIDDataModel();

        // Assert
        assertEquals(courseInStudyPlanIDDataModel, cISPIDDM);
    }

    @Test
    void shouldReturnCourseEditionGeneratedIDDataModel() {
        // Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        TeacherIDDataModel teacherIDDataModel = mock(TeacherIDDataModel.class);
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel,courseEditionGeneratedIDDataModel, teacherIDDataModel);

        // Act
        CourseEditionGeneratedIDDataModel datamodel = courseEditionDataModel.getCourseEditionGeneratedIDDataModel();

        // Assert
        assertNotNull(datamodel);
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
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel,courseEditionGeneratedIDDataModel, teacherIDDataModel);

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
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel,courseEditionGeneratedIDDataModel, teacherIDDataModel);

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
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel,courseEditionGeneratedIDDataModel, teacherIDDataModel);

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
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel,courseEditionGeneratedIDDataModel, teacherIDDataModel);

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
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel1 = new CourseEditionDataModel(courseEditionIDDataModel,courseEditionGeneratedIDDataModel, teacherIDDataModel);
        CourseEditionDataModel courseEditionDataModel2 = new CourseEditionDataModel(courseEditionIDDataModel,courseEditionGeneratedIDDataModel, teacherIDDataModel);

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
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel1 = mock(CourseEditionGeneratedIDDataModel.class);
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel2 = mock(CourseEditionGeneratedIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel1 = new CourseEditionDataModel(courseEditionIDDataModel1, courseEditionGeneratedIDDataModel1, teacherIDDataModel);
        CourseEditionDataModel courseEditionDataModel2 = new CourseEditionDataModel(courseEditionIDDataModel2, courseEditionGeneratedIDDataModel2, teacherIDDataModel);

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
        CourseEditionGeneratedIDDataModel courseEditionGeneratedIDDataModel = mock(CourseEditionGeneratedIDDataModel.class);
        CourseEditionDataModel courseEditionDataModel = new CourseEditionDataModel(courseEditionIDDataModel, courseEditionGeneratedIDDataModel, teacherIDDataModel);

        // Act
        int result = courseEditionDataModel.hashCode();

        // Assert
        assertEquals(courseEditionDataModel.hashCode(), result);
    }
}