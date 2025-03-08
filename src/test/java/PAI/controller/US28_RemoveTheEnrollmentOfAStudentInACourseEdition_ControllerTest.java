package PAI.controller;

import PAI.domain.*;
import PAI.factory.CourseEditionEnrollmentFactory;
import PAI.factory.CourseEditionEnrollmentListFactory;
import PAI.repository.CourseEditionEnrollmentRepository;
import org.junit.jupiter.api.Test;

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

            CourseEditionEnrollmentRepository repository = mock (CourseEditionEnrollmentRepository.class);
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
            CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
            CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
            CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);
            US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(repository);

            Student std1 = mock (Student.class);
            CourseEdition ce1 =mock(CourseEdition.class);

            // act
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> controller.removeStudentEnrolment(null, ce1));

            // assert
            assertEquals( "Student and CourseEdition cannot be null", e.getMessage());
            // act
            e = assertThrows(IllegalArgumentException.class, () -> controller.removeStudentEnrolment(std1, null));

            // assert
            assertEquals( "Student and CourseEdition cannot be null", e.getMessage());
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
                .thenReturn(false);

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