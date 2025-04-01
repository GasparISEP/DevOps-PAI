package PAI.controller;

import PAI.domain.*;
import PAI.repository.CourseEditionEnrolmentRepository;
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
        CourseEditionEnrolmentRepository repoDouble = mock(CourseEditionEnrolmentRepository.class);

        // Act
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(repoDouble);

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
        CourseEditionEnrolmentRepository repoDouble = mock(CourseEditionEnrolmentRepository.class);
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(repoDouble);

        CourseEdition_2 courseEdition1 = mock(CourseEdition_2.class);
        when(repoDouble.numberOfStudentsEnrolledInCourseEdition(courseEdition1)).thenReturn(1);

        // Act
        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEdition1);

        // Assert
        assertEquals(1, studentsEnrolled);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEditionForMoreThan1Student() throws Exception {
        // Arrange
        CourseEditionEnrolmentRepository repoDouble = mock(CourseEditionEnrolmentRepository.class);
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(repoDouble);

        CourseEdition_2 courseEdition1 = mock(CourseEdition_2.class);
        when(repoDouble.numberOfStudentsEnrolledInCourseEdition(courseEdition1)).thenReturn(2);

        // Act
        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEdition1);

        // Assert
        assertEquals(2, studentsEnrolled);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEditionWhenCourseEditionMoreThanOneAndStudentMoreThanOne() throws Exception {
        // Arrange
        CourseEditionEnrolmentRepository repoDouble = mock(CourseEditionEnrolmentRepository.class);
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(repoDouble);

        CourseEdition_2 courseEdition1 = mock(CourseEdition_2.class);
        when(repoDouble.numberOfStudentsEnrolledInCourseEdition(courseEdition1)).thenReturn(1);

        //Act
        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEdition1);

        // Assert
        assertEquals(1, studentsEnrolled);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEditionWhenCourseEditionMoreThanOneAndStudentsOnlyEnrolledInOneCourseEdition() throws Exception {
        // Arrange
        CourseEditionEnrolmentRepository repoDouble = mock(CourseEditionEnrolmentRepository.class);
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(repoDouble);

        CourseEdition_2 courseEdition1 = mock(CourseEdition_2.class);
        CourseEdition_2 courseEdition2 = mock(CourseEdition_2.class);
        when(repoDouble.numberOfStudentsEnrolledInCourseEdition(courseEdition1)).thenReturn(0);
        when(repoDouble.numberOfStudentsEnrolledInCourseEdition(courseEdition2)).thenReturn(2);

        // Act
        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEdition2);

        // Assert
        assertEquals(2, studentsEnrolled);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEditionWhenCourseEditionDoesNotHaveStudentsEnrolled() throws Exception {
        // Arrange
        CourseEditionEnrolmentRepository repoDouble = mock(CourseEditionEnrolmentRepository.class);
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(repoDouble);

        CourseEdition_2 courseEdition1 = mock(CourseEdition_2.class);
        when(repoDouble.numberOfStudentsEnrolledInCourseEdition(courseEdition1)).thenReturn(0);

        //Act
        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }

    @Test
    void testIWantToKnowNumberOfStudentsEnrolledInCourseEdition_NullCourseEdition_ShouldThrowException() throws Exception {
        // Arrange
        CourseEditionEnrolmentRepository repoDouble = mock(CourseEditionEnrolmentRepository.class);
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(repoDouble);

        when(controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(null)).thenThrow(new IllegalArgumentException("Course edition cannot be null."));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(null);
        });

        assertEquals("Course edition cannot be null.", exception.getMessage());
    }
}