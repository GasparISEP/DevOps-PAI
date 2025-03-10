package PAI.controller;

import PAI.domain.*;
import PAI.factory.CourseEditionEnrollmentFactory;
import PAI.factory.CourseEditionEnrollmentListFactory;
import PAI.repository.CourseEditionEnrollmentRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US28_RemoveTheEnrollmentOfAStudentInACourseEdition_ControllerTest {

        @Test
        void removeExistingEnrollment_ShouldReturnTrue() {
            // Arrange
            CourseEditionEnrollmentRepository mockRepository = mock(CourseEditionEnrollmentRepository.class);
            US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(mockRepository);

            Student mockStudent = mock(Student.class);
            CourseEdition mockCourseEdition = mock(CourseEdition.class);

            when(mockRepository.removeEnrollment(mockStudent, mockCourseEdition)).thenReturn(true);

            // Act
            boolean result = controller.removeStudentEnrollment(mockStudent, mockCourseEdition);

            // Assert
            assertTrue(result, "Enrollment should be removed successfully.");
            verify(mockRepository).removeEnrollment(mockStudent, mockCourseEdition);
        }

        @Test
        void removeNonExistingEnrollment_ShouldReturnFalse() {
            // Arrange
            CourseEditionEnrollmentRepository mockRepository = mock(CourseEditionEnrollmentRepository.class);
            US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(mockRepository);

            Student mockStudent = mock (Student.class);
            CourseEdition mockCourseEdition = mock (CourseEdition.class);

            // Act
            boolean result = controller.removeStudentEnrollment(mockStudent, mockCourseEdition);

            // Assert
            assertFalse(result, "Removing a non existing enrollment should return false.");
            verify(mockRepository).removeEnrollment(mockStudent, mockCourseEdition); // Ensure no enrollment creation occurs
        }

        @Test
        void removeEnrollment_WithNullCourseEditionOrStudent_ShouldThrowException() throws IllegalArgumentException {
            // Arrange
            CourseEditionEnrollmentRepository mockRepository = mock(CourseEditionEnrollmentRepository.class);
            US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(mockRepository);

            Student mockStudent = mock (Student.class);
            CourseEdition mockCourseEdition = mock (CourseEdition.class);

            // Act and assert
            // test for the case where Student is null
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                controller.removeStudentEnrollment(null, mockCourseEdition);
            });
            assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());

            // test for the case where CourseEdition is null
            thrown = assertThrows(IllegalArgumentException.class, () -> {
                controller.removeStudentEnrollment(mockStudent, null);
            });
            assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());
            verify(mockRepository, never()).removeEnrollment(any(), any());
        }


    @Test
    void removeEnrollmentTwice_ShouldReturnFalseOnSecondAttempt() {
        // Arrange
        CourseEditionEnrollmentRepository mockRepository = mock(CourseEditionEnrollmentRepository.class);
        US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(mockRepository);

        Student mockStudent = mock(Student.class);
        CourseEdition mockCourseEdition = mock(CourseEdition.class);

        when(mockRepository.removeEnrollment(mockStudent, mockCourseEdition)).thenReturn(true)
                .thenReturn(false);
        // Act
        boolean firstRemoval = controller.removeStudentEnrollment(mockStudent, mockCourseEdition);

        // Assert first removal is successful
        assertTrue(firstRemoval, "Enrollment should be removed successfully.");

        // Act again: Try removing a second time
        boolean secondRemoval = controller.removeStudentEnrollment(mockStudent, mockCourseEdition);

        // Assert second removal fails
        assertFalse(secondRemoval, "The second removal should not succeed.");
        verify(mockRepository, times(2)).removeEnrollment(mockStudent, mockCourseEdition);
    }

    @Test
    void removeAlreadyInactiveEnrollment_ShouldReturnFalse() {
        // Arrange
        CourseEditionEnrollmentRepository mockRepository = mock(CourseEditionEnrollmentRepository.class);
        US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(mockRepository);

        Student mockStudent = mock(Student.class);
        CourseEdition mockCourseEdition = mock(CourseEdition.class);
        CourseEditionEnrollment mockCee = mock(CourseEditionEnrollment.class);

        when(mockRepository.findByStudentAndEdition(mockStudent, mockCourseEdition))
                .thenReturn(Optional.of(mockCee)); // Simulates that the repository found the enrollment

        when(mockCee.isEnrollmentActive()).thenReturn(false); // Enrollment is already inactive

        // Act: Try removing an already inactive enrollment
        boolean result = controller.removeStudentEnrollment(mockStudent, mockCourseEdition);

        // Assert
        assertFalse(result, "Removing an already inactive enrollment should return false.");
        verify(mockCee, never()).deactivateEnrollment(); // Ensure deactivateEnrollment is not called
    }

    @Test
    void removeMultipleStudentsFromSameCourseEdition_ShouldReturnTrueForBoth() {
        // Arrange
        CourseEditionEnrollmentRepository mockRepository = mock(CourseEditionEnrollmentRepository.class);
        US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(mockRepository);

        CourseEdition mockCourseEdition = mock(CourseEdition.class);
        Student mockStudent1 = mock(Student.class);
        Student mockStudent2 = mock(Student.class);

        when(mockRepository.removeEnrollment(mockStudent1, mockCourseEdition)).thenReturn(true);
        when(mockRepository.removeEnrollment(mockStudent2, mockCourseEdition)).thenReturn(true);

        // Act
        boolean firstRemoval = controller.removeStudentEnrollment(mockStudent1, mockCourseEdition);
        boolean secondRemoval = controller.removeStudentEnrollment(mockStudent2, mockCourseEdition);

        // Assert
        assertTrue(firstRemoval, "First student's enrollment should be removed successfully.");
        assertTrue(secondRemoval, "Second student's enrollment should be removed successfully.");
        verify(mockRepository).removeEnrollment(mockStudent1, mockCourseEdition);
        verify(mockRepository).removeEnrollment(mockStudent2, mockCourseEdition);
    }

    @Test
    void removeStudentFromMultipleCourseEditions_ShouldReturnTrueForBoth() {
        // Arrange
        CourseEditionEnrollmentRepository mockRepository = mock(CourseEditionEnrollmentRepository.class);
        US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(mockRepository);

        CourseEdition mockCourseEdition1 = mock(CourseEdition.class);
        CourseEdition mockCourseEdition2 = mock(CourseEdition.class);
        Student mockStudent = mock(Student.class);

        when(mockRepository.removeEnrollment(mockStudent, mockCourseEdition1)).thenReturn(true);
        when(mockRepository.removeEnrollment(mockStudent, mockCourseEdition2)).thenReturn(true);

        // Act
        boolean firstRemoval = controller.removeStudentEnrollment(mockStudent, mockCourseEdition1);
        boolean secondRemoval = controller.removeStudentEnrollment(mockStudent, mockCourseEdition2);

        // Assert
        assertTrue(firstRemoval, "Student should be removed from the first course edition.");
        assertTrue(secondRemoval, "Student should be removed from the second course edition.");
        verify(mockRepository).removeEnrollment(mockStudent, mockCourseEdition1);
        verify(mockRepository).removeEnrollment(mockStudent, mockCourseEdition2);
    }
}