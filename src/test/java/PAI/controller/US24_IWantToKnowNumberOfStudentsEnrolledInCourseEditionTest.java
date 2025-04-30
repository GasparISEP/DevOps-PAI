package PAI.controller;

import PAI.VOs.CourseEditionID;
import PAI.service.ICourseEditionEnrolmentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionTest {

    @Test
    void checksIfControllerInitializes() {
        // Arrange
        ICourseEditionEnrolmentService serviceDouble = mock(ICourseEditionEnrolmentService.class);

        // Act
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(serviceDouble);

        // Assert
        // Check if the controller is properly initialized
        assertNotNull(controller);
    }

    @Test
    void shouldNotCreateControllerIfNullCourseEditionEnrolmentRepository() {
        // Arrange

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(null));

    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEdition() throws Exception {
        // Arrange
        ICourseEditionEnrolmentService serviceDouble = mock(ICourseEditionEnrolmentService.class);
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(serviceDouble);

        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        when(serviceDouble.numberOfStudentsEnrolledInCourseEdition(courseEditionIDDouble)).thenReturn(1);

        // Act
        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEditionIDDouble);

        // Assert
        assertEquals(1, studentsEnrolled);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEditionForMoreThan1Student() throws Exception {
        // Arrange
        ICourseEditionEnrolmentService serviceDouble = mock(ICourseEditionEnrolmentService.class);
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(serviceDouble);

        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        when(serviceDouble.numberOfStudentsEnrolledInCourseEdition(courseEditionIDDouble)).thenReturn(2);

        // Act
        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEditionIDDouble);

        // Assert
        assertEquals(2, studentsEnrolled);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEditionWhenCourseEditionMoreThanOneAndStudentMoreThanOne() throws Exception {
        // Arrange
        ICourseEditionEnrolmentService serviceDouble = mock(ICourseEditionEnrolmentService.class);
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(serviceDouble);

        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        when(serviceDouble.numberOfStudentsEnrolledInCourseEdition(courseEditionIDDouble)).thenReturn(1);

        //Act
        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEditionIDDouble);

        // Assert
        assertEquals(1, studentsEnrolled);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEditionWhenCourseEditionMoreThanOneAndStudentsOnlyEnrolledInOneCourseEdition() throws Exception {
        // Arrange
        ICourseEditionEnrolmentService serviceDouble = mock(ICourseEditionEnrolmentService.class);
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(serviceDouble);

        CourseEditionID courseEditionIDDouble1 = mock(CourseEditionID.class);
        CourseEditionID courseEditionIDDouble2 = mock(CourseEditionID.class);
        when(serviceDouble.numberOfStudentsEnrolledInCourseEdition(courseEditionIDDouble1)).thenReturn(0);
        when(serviceDouble.numberOfStudentsEnrolledInCourseEdition(courseEditionIDDouble2)).thenReturn(2);

        // Act
        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEditionIDDouble2);

        // Assert
        assertEquals(2, studentsEnrolled);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEditionWhenCourseEditionDoesNotHaveStudentsEnrolled() throws Exception {
        // Arrange
        ICourseEditionEnrolmentService serviceDouble = mock(ICourseEditionEnrolmentService.class);
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(serviceDouble);

        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        when(serviceDouble.numberOfStudentsEnrolledInCourseEdition(courseEditionIDDouble)).thenReturn(0);

        //Act
        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEditionIDDouble);

        // Assert
        assertEquals(0, studentsEnrolled);
    }

    @Test
    void testIWantToKnowNumberOfStudentsEnrolledInCourseEdition_NullCourseEdition_ShouldThrowException() throws Exception {
        // Arrange
        ICourseEditionEnrolmentService serviceDouble = mock(ICourseEditionEnrolmentService.class);
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(serviceDouble);

        when(controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(null)).thenThrow(new IllegalArgumentException("Course edition cannot be null."));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(null);
        });

        assertEquals("Course edition cannot be null.", exception.getMessage());
    }
}