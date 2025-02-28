package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US28_RemoveTheEnrollmentOfAStudentInACourseEdition_ControllerTest {
        //US28
        @Test
        void removeExistingEnrollment() throws IllegalArgumentException {
            // arrange
            CourseEditionEnrollmentRepository repository = mock(CourseEditionEnrollmentRepository.class);
            US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(repository);
            Student student = mock(Student.class);
            CourseEdition courseEdition = mock(CourseEdition.class);

            when(repository.removeEnrollment(student, courseEdition)).thenReturn(true);

            // act
            boolean result = controller.removeStudentEnrolment(student, courseEdition);

            // assert
            assertTrue(result, "Enrollment should be removed successfully.");
        }

        @Test
        void removeNonExistingEnrollment() throws IllegalArgumentException {
            // arrange
            CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
            CourseEditionEnrollmentRepository repository= new CourseEditionEnrollmentRepository (factoryDouble);
            US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(repository);
            Student student = mock (Student.class);
            CourseEdition courseEdition = mock (CourseEdition.class);

            // act
            boolean result = controller.removeStudentEnrolment(student, courseEdition);

            // assert
            assertFalse(result, "Enrollment should not be removed successfully.");
        }

        @Test
        void removeEnrollment_WithNullCourseEdition_ShouldThrowException() throws IllegalArgumentException {
            // arrange
            CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
            CourseEditionEnrollmentRepository repository= new CourseEditionEnrollmentRepository (factoryDouble);
            US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(repository);

            Student std1 = mock (Student.class);

            // act
            boolean result = controller.removeStudentEnrolment(std1, null);

            // assert
            assertFalse(result, "Enrollment should not be removed successfully.");
        }

    @Test
    void removeEnrollment_WithNullStudent_ShouldThrowException() throws IllegalArgumentException {
        // arrange
        CourseEditionEnrollmentFactory factoryDouble = mock(CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository(factoryDouble);
        US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(repository);

        CourseEdition ce1 = mock(CourseEdition.class);

        // act
        boolean result = controller.removeStudentEnrolment(null, ce1);

        // assert
        assertFalse(result, "Enrollment should not be removed successfully.");
    }

    @Test
    void removeEnrollmentTwice_ShouldReturnFalseOnSecondAttempt() {
        // arrange
        CourseEditionEnrollmentRepository repository = mock(CourseEditionEnrollmentRepository.class);
        US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(repository);
        Student student = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);

        when(repository.removeEnrollment(student, courseEdition))
                .thenReturn(true)
                .thenThrow(new IllegalArgumentException("Enrollment does not exist."));

        // act: first removal attempt
        boolean firstRemoval = controller.removeStudentEnrolment(student, courseEdition);

        // assert: first removal should succeed
        assertTrue(firstRemoval, "The first removal should succeed.");

        // act: second removal attempt (should fail)
        boolean secondRemoval = controller.removeStudentEnrolment(student, courseEdition);

        // assert: second removal should fail and return false
        assertFalse(secondRemoval, "The second removal should not succeed.");
    }
}