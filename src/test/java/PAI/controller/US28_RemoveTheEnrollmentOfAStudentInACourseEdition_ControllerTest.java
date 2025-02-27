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
        public void removeExistingEnrollment() throws IllegalArgumentException {
            // Arrange

            CourseEditionEnrollmentRepository repository = mock(CourseEditionEnrollmentRepository.class);
            US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(repository);
            Student student = mock(Student.class);
            CourseEdition courseEdition = mock(CourseEdition.class);

            when(repository.removeEnrollment(student, courseEdition)).thenReturn(true);

            // Act
            boolean result = controller.removeStudentEnrolment(student, courseEdition);

            // Assert
            assertTrue(result, "Enrollment should be removed successfully.");
        }


        @Test
        public void removeNonExistingEnrollment() throws IllegalArgumentException {
            // arrange
            CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
            CourseEditionEnrollmentRepository repository= new CourseEditionEnrollmentRepository (factoryDouble);
            US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(repository);
            Student student = mock (Student.class);
            CourseEdition courseEdition = mock (CourseEdition.class);


            // act and assert
//            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//                controller.removeStudentEnrolment(student, courseEdition);
//            });

            // assert
            boolean result = controller.removeStudentEnrolment(student, courseEdition);
            assertFalse(result, "Enrollment should not be removed successfully.");
//            assertEquals("Enrollment does not exist.", exception.getMessage());
        }

        @Test
        void removeEnrollment_WithNullCourseEdition_ShouldThrowException() throws IllegalArgumentException {
            // arrange
            CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
            CourseEditionEnrollmentRepository repository= new CourseEditionEnrollmentRepository (factoryDouble);
            US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(repository);


            Student std1 = mock (Student.class);

            // act and assert
            boolean result = controller.removeStudentEnrolment(std1, null);
            assertFalse(result, "Enrollment should not be removed successfully.");
//            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
//                controller.removeStudentEnrolment(std1, null);
//            });
//            assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());
        }
    @Test
    void removeEnrollment_WithNullStudent_ShouldThrowException() throws IllegalArgumentException {
        // arrange
        CourseEditionEnrollmentFactory factoryDouble = mock(CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository(factoryDouble);
        US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(repository);

        CourseEdition ce1 = mock(CourseEdition.class);

        // act and assert
//        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
//            controller.removeStudentEnrolment(null, ce1);
//        });
//        assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());
        boolean result = controller.removeStudentEnrolment(null, ce1);
        assertFalse(result, "Enrollment should not be removed successfully.");
    }
        @Test
        public void removeEnrollmentTwice_ShouldThrowExceptionOnSecondAttempt() throws IllegalArgumentException {
            // arrange
            CourseEditionEnrollmentRepository repository= mock(CourseEditionEnrollmentRepository.class);
            US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(repository);

            Student student = mock (Student.class);
            CourseEdition courseEdition = mock (CourseEdition.class);

            when(repository.removeEnrollment(student, courseEdition)).thenReturn(true);

            // act: Remove enrollment the first time
            boolean firstRemoval = controller.removeStudentEnrolment(student, courseEdition);

            // assert first removal
            assertTrue(firstRemoval, "The first removal should succeed.");

            doThrow(new IllegalArgumentException("Enrollment does not exist."))
                    .when(repository).removeEnrollment(student, courseEdition);

            // act and assert: Try removing again and expect an exception
//            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//                controller.removeStudentEnrolment(student, courseEdition);
//            });
//
//            // assert second removal throws correct exception
//            assertEquals("Enrollment does not exist.", exception.getMessage());
            boolean result = controller.removeStudentEnrolment(student, courseEdition);
            assertFalse(result, "Enrollment should not be removed successfully.");
        }
    }