package PAI.persistence.datamodel;

import PAI.VOs.CourseID;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseIDDataModelTest {

    @Test
    void shouldCreateCourseIDDataModel() {
        // Arrange + Act
        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel();

        // Assert
        assertNotNull(CourseIDDataModel);
    }

    @Test
    void shouldCreateCourseIDDataModelWithCourseID() {
        // Arrange
        CourseID courseIDDouble = mock(CourseID.class);
        // Act
        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);

        // Assert
        assertNotNull(CourseIDDataModel);
    }

    @Test
    void shouldReturnCourseIDToString() {
        // Arrange
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.toString()).thenReturn(UUID.randomUUID().toString());

        // Act
        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);

        // Assert
        assertEquals(courseIDDouble.toString(), CourseIDDataModel.getId());
    }

    @Test
    void shouldReturnTrueIfObjectsAreEquals() {
        // Arrange
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.toString()).thenReturn(UUID.randomUUID().toString());
        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);
        Object CourseIDDataModel2 = CourseIDDataModel;

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfCourseIDDataModelEquals() {
        // Arrange
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.toString()).thenReturn(UUID.randomUUID().toString());
        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);
        CourseIDDataModel CourseIDDataModel2 = new CourseIDDataModel(courseIDDouble);

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseIDDataModelNotEquals() {
        // Arrange
        CourseID courseIDDouble = mock(CourseID.class);
        CourseID CourseIDDouble2 = mock(CourseID.class);;
        when(courseIDDouble.toString()).thenReturn(UUID.randomUUID().toString());
        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);
        CourseIDDataModel CourseIDDataModel2 = new CourseIDDataModel(CourseIDDouble2);

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseIDDataModelNotEqualsWithNull() {
        // Arrange
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.toString()).thenReturn(UUID.randomUUID().toString());
        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);
        CourseIDDataModel CourseIDDataModel2 = null;

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseIDDataModelNotEqualsWithDifferentClass() {
        // Arrange
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.toString()).thenReturn(UUID.randomUUID().toString());
        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);
        Object CourseIDDataModel2 = new Object();

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseIDDataModelNotEqualsWithDifferentObject() {
        // Arrange
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.toString()).thenReturn(UUID.randomUUID().toString());
        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);
        CourseIDDataModel CourseIDDataModel2 = new CourseIDDataModel();

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertFalse(result);
    }

}