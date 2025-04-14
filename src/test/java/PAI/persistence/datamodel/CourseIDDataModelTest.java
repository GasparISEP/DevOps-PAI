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
}