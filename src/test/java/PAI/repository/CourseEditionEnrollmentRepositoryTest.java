package PAI.repository;

import PAI.domain.*;
import PAI.factory.CourseEditionEnrollmentFactory;
import PAI.factory.CourseEditionEnrollmentFactoryInterface;
import PAI.factory.CourseEditionEnrollmentListFactory;
import PAI.factory.CourseEditionEnrollmentListFactoryInterface;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseEditionEnrollmentRepositoryTest {

    //test enroll a student in a course edition method
    @Test
    void shouldReturnTrueWithAValidCourseEditionEnrollment () {
        //arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, CeeListFactory);

        Student doubleSt1 = mock(Student.class);
        CourseEdition doubleCe1 = mock(CourseEdition.class);
        CourseEditionEnrollment doubleCee1 = mock(CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(doubleSt1,doubleCe1)).thenReturn(doubleCee1);

        //act
        boolean result = repository.enrollStudentInACourseEdition(doubleSt1,doubleCe1);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWithTwoValidCourseEditionEnrollments () {
        //arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface doubleCeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

        Student st1 = mock(Student.class);
        CourseEdition ce1 = mock(CourseEdition.class);
        CourseEditionEnrollment cee1 = mock(CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(st1,ce1)).thenReturn(cee1);

        Student st2 = mock(Student.class);
        CourseEdition ce2 = mock(CourseEdition.class);
        CourseEditionEnrollment cee2 = mock(CourseEditionEnrollment.class);

        when(doubleCeeFactory.createCourseEditionEnrollment(st2,ce2)).thenReturn(cee2);

        //act
        boolean result1 = repository.enrollStudentInACourseEdition(st1,ce1);
        boolean result2 = repository.enrollStudentInACourseEdition(st2,ce2);

        //assert
        assertEquals(true,result1);
        assertEquals(true,result2);
    }

    @Test
    void shouldReturnFalseWhenCourseEditionEnrollmentAlreadyExists() {
        //arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface doubleCeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

        Student doubleSt1 = mock(Student.class);
        CourseEdition doubleCe1 = mock(CourseEdition.class);

        repository.enrollStudentInACourseEdition(doubleSt1,doubleCe1);

        //act
        boolean result2 = repository.enrollStudentInACourseEdition(doubleSt1,doubleCe1);

        //assert
        assertFalse(result2);
    }

    @Test
    void shouldReturnFalseIfStudentIsNull (){
        //arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface doubleCeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

        CourseEdition doubleCe1 = mock(CourseEdition.class);

        when(doubleCeeFactory.createCourseEditionEnrollment(null,doubleCe1)).thenThrow();

        //act
        boolean result = repository.enrollStudentInACourseEdition(null, doubleCe1);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsNull (){
        //arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface doubleCeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

        Student doubleSt1 = mock(Student.class);

        when(doubleCeeFactory.createCourseEditionEnrollment(doubleSt1,null)).thenThrow();

        //act
        boolean result = repository.enrollStudentInACourseEdition(doubleSt1, null);

        //assert
        assertFalse(result);
    }

    //test isStudentEnrolledInCourseEdition method
    @Test
    void shouldConfirmStudentIsEnrollInACourseEdition () throws Exception {
        //arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, CeeListFactory);

        CourseEdition ce1 = mock(CourseEdition.class);
        Student student1 = mock(Student.class);
        CourseEditionEnrollment cee1 = mock(CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(student1,ce1)).thenReturn(cee1);
        when(cee1.knowStudent()).thenReturn(student1);
        when(cee1.knowCourseEdition()).thenReturn(ce1);
        when(cee1.isEnrollmentActive()).thenReturn(true);
        repository.enrollStudentInACourseEdition(student1, ce1);

        //act & assert
        assertTrue(repository.isStudentEnrolledInCourseEdition(student1, ce1));
    }

    @Test
    void shouldConfirmStudentIsNotEnrollInACourseEdition () {
        //arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, CeeListFactory);

        CourseEdition ce1 = mock(CourseEdition.class);
        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEditionEnrollment cee1 = mock(CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(student1,ce1)).thenReturn(cee1);
        when(cee1.knowStudent()).thenReturn(student1);
        when(cee1.knowCourseEdition()).thenReturn(ce1);
        when(cee1.isEnrollmentActive()).thenReturn(true);
        repository.enrollStudentInACourseEdition(student1, ce1);

        //act
        //assert
        assertFalse(repository.isStudentEnrolledInCourseEdition(student2, ce1));
    }

    @Test
    void shouldReturnCourseEditionEnrollmentWhenStudentIsEnrolled() {
        // Arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, CeeListFactory);

        Student doubleSt1 = mock (Student.class);
        CourseEdition doubleCe1 = mock (CourseEdition.class);
        CourseEditionEnrollment courseEEnrollments = mock(CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(doubleSt1, doubleCe1)).thenReturn(courseEEnrollments);

        when (courseEEnrollments.findStudentInCourseEditionEnrollment()).thenReturn(doubleSt1);

        when (courseEEnrollments.findCourseEditionInEnrollment()).thenReturn(doubleCe1);

        when (courseEEnrollments.isEnrollmentActive()).thenReturn(true);

        repository.enrollStudentInACourseEdition(doubleSt1, doubleCe1);
        // Act
        Optional<CourseEditionEnrollment> result = repository.findByStudentAndEdition(doubleSt1, doubleCe1);

        // Assert
        assertTrue(result.isPresent(), "The student was enrolled in the course edition successfully.");
        assertEquals(result.get().findStudentInCourseEditionEnrollment(), doubleSt1, "The student enrolled in the correct course edition.");
        assertEquals(result.get().findCourseEditionInEnrollment(), doubleCe1, "The course edition enrolled is correct.");
    }


    @Test
    void shouldReturnCourseEditionFromEnrollment() {
        // Arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, CeeListFactory);

        Student doubleSt1 = mock (Student.class);
        CourseEdition doubleCe1 = mock (CourseEdition.class);
        CourseEditionEnrollment courseEEnrollments = mock(CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(doubleSt1,doubleCe1)).thenReturn(courseEEnrollments);

        when (courseEEnrollments.findStudentInCourseEditionEnrollment()).thenReturn(doubleSt1);

        when (courseEEnrollments.findCourseEditionInEnrollment()).thenReturn(doubleCe1);

        repository.enrollStudentInACourseEdition(doubleSt1, doubleCe1);

        // Act
        Optional<CourseEditionEnrollment> result = repository.findByStudentAndEdition(doubleSt1, doubleCe1);

        // Assert
        assertTrue(result.isPresent(), "The student should be enrolled in the course edition.");
        assertEquals(doubleCe1, result.get().findCourseEditionInEnrollment(), "The course edition returned should match the one in the enrollment.");
    }


    @Test
    void shouldThrowExceptionWhenStudentOrCourseEditionIsNull() {
        // Arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, CeeListFactory);

        CourseEdition doubleCe1 = mock (CourseEdition.class);
        Student doubleSt1 = mock (Student.class);

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.findByStudentAndEdition(null, doubleCe1);
        });
        assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.findByStudentAndEdition(doubleSt1, null);
        });
        assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());
    }

    @Test
    void shouldReturnOptionalEmptyWhenNoEnrollmentFound() {
        // Arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, CeeListFactory);

        Student doubleSt1 = mock(Student.class);
        CourseEdition doubleCe1 = mock(CourseEdition.class);

        // Act
        Optional<CourseEditionEnrollment> result = repository.findByStudentAndEdition(doubleSt1, doubleCe1);

        // Assert
        assertFalse(result.isPresent(), "The result should be empty if the student is not enrolled in the course edition.");
    }


    @Test
    void shouldReturnEmptyWhenStudentIsNotEnrolledInCourseEdition() {
        // Arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, CeeListFactory);

        Student doubleSt1 = mock(Student.class);
        CourseEdition doubleCe1 = mock(CourseEdition.class);

        // Act
        Optional<CourseEditionEnrollment> result = repository.findByStudentAndEdition(doubleSt1, doubleCe1);

        // Assert
        assertFalse(result.isPresent(), "Expected no enrollment to be found");
    }


    @Test
    void shouldReturnNumberOfStudentsEnrolledInCourseEdition() throws Exception {
        // Arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository (doubleCeeFactory, CeeListFactory);

        CourseEdition doubleCourseEdition1 = mock(CourseEdition.class);
        Student doubleStudent1 = mock (Student.class);
        CourseEditionEnrollment cee1 = mock (CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(doubleStudent1, doubleCourseEdition1)).thenReturn(cee1);

        when (cee1.knowCourseEdition()).thenReturn(doubleCourseEdition1);

        repo.enrollStudentInACourseEdition(doubleStudent1, doubleCourseEdition1);

        // Act
        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(1, studentsEnrolled);
    }

    @Test
    void ShouldReturnZeroWhenThereAreNoEnrolmentsInACourse() throws Exception {
        // Arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository (doubleCeeFactory, CeeListFactory);

        CourseEdition doubleCourseEdition1 = mock(CourseEdition.class);
        CourseEdition doubleCourseEdition2 = mock (CourseEdition.class);

        Student doubleStudent1 = mock(Student.class);
        Student doubleStudent2 = mock(Student.class);

        CourseEditionEnrollment cee1 = mock(CourseEditionEnrollment.class);
        CourseEditionEnrollment cee2 = mock(CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(doubleStudent1, doubleCourseEdition2)).thenReturn(cee1);
        when (doubleCeeFactory.createCourseEditionEnrollment(doubleStudent2, doubleCourseEdition2)).thenReturn(cee2);

        when (cee1.knowCourseEdition()).thenReturn(doubleCourseEdition2);
        when (cee2.knowCourseEdition()).thenReturn(doubleCourseEdition2);

        repo.enrollStudentInACourseEdition(doubleStudent1, doubleCourseEdition2);
        repo.enrollStudentInACourseEdition(doubleStudent2, doubleCourseEdition2);

        // Act
        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }

    @Test
    void shouldReturnZeroWhenThereAreNoStudents() throws Exception {
        // Arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository (doubleCeeFactory, CeeListFactory);

        // Create a course edition
        CourseEdition doubleCourseEdition1 = mock(CourseEdition.class);

        // Act
        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }

    @Test
    void testNumberOfStudentsEnrolledInCourseEdition_NullCourseEdition_ShouldThrowException() {
        // Arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, CeeListFactory);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            repository.numberOfStudentsEnrolledInCourseEdition(null);
        });

        // Assert
        assertEquals("Course edition cannot be null.", exception.getMessage());
    }

    // System should allow the successful removal of a student enrolled in a course edition
    @Test
    void removeExistingEnrollment_ShouldReturnTrue() {
        // Arrange
        CourseEditionEnrollmentFactoryInterface enrollmentFactoryMock = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository (enrollmentFactoryMock, CeeListFactory);

        Student mockStudent = mock(Student.class);
        CourseEdition mockCourseEdition = mock(CourseEdition.class);
        CourseEditionEnrollment mockCee = mock(CourseEditionEnrollment.class);

        when(enrollmentFactoryMock.createCourseEditionEnrollment(mockStudent, mockCourseEdition))
                .thenReturn(mockCee);

        when(mockCee.findStudentInCourseEditionEnrollment()).thenReturn(mockStudent);
        when(mockCee.findCourseEditionInEnrollment()).thenReturn(mockCourseEdition);
        when(mockCee.isEnrollmentActive()).thenReturn(true);

        enrollmentRepository.enrollStudentInACourseEdition(mockStudent, mockCourseEdition);

        // Act
        boolean result = enrollmentRepository.removeEnrollment(mockStudent, mockCourseEdition);

        // Assert
        assertTrue(result, "Enrollment should be removed successfully.");
        verify(mockCee).deactivateEnrollment(); // Verify that deactivateEnrollment() was called
    }

    // Ensures that the system does not allow the removal of a non-existent enrollment
    @Test
    void removeNonExistingEnrollment_ShouldReturnFalse() {
        // Arrange
        CourseEditionEnrollmentFactoryInterface enrollmentFactoryMock = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (enrollmentFactoryMock, CeeListFactory);

        Student mockStudent = mock (Student.class);
        CourseEdition mockCourseEdition = mock (CourseEdition.class);

        // Act
        boolean result = repository.removeEnrollment(mockStudent, mockCourseEdition);

        // Assert
        assertFalse(result, "Removing a non existing enrollment should return false.");
        verify(enrollmentFactoryMock, never()).createCourseEditionEnrollment(any(), any()); // Ensure no enrollment creation occurs
    }

    // If the student or course edition information is missing (null), the system should reject the operation and throw an exception
    @Test
    void removeEnrollment_WithNullCourseEditionOrStudent_ShouldThrowException() throws IllegalArgumentException {
        // Arrange
        CourseEditionEnrollmentFactoryInterface enrollmentFactoryMock = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (enrollmentFactoryMock, CeeListFactory);

        Student mockStudent = mock (Student.class);
        CourseEdition mockCourseEdition = mock (CourseEdition.class);

        // Act and assert
        // test for the case where Student is null
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.removeEnrollment(null, mockCourseEdition);
        });
        assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());

        // test for the case where CourseEdition is null
        thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.removeEnrollment(mockStudent, null);
        });
        assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());
        verify(enrollmentFactoryMock, never()).createCourseEditionEnrollment(any(), any());
    }

    // Confirms that removing the same enrollment multiple times should only succeed on the first attempt, while subsequent attempts should be denied
    @Test
    void removeEnrollmentTwice_ShouldReturnFalseOnSecondAttempt() {
        // Arrange
        CourseEditionEnrollmentFactoryInterface enrollmentFactoryMock = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository (enrollmentFactoryMock, CeeListFactory);

        Student mockStudent = mock(Student.class);
        CourseEdition mockCourseEdition = mock(CourseEdition.class);
        CourseEditionEnrollment mockCee = mock(CourseEditionEnrollment.class);

        when(enrollmentFactoryMock.createCourseEditionEnrollment(mockStudent, mockCourseEdition))
                .thenReturn(mockCee);

        when(mockCee.findStudentInCourseEditionEnrollment()).thenReturn(mockStudent);
        when(mockCee.findCourseEditionInEnrollment()).thenReturn(mockCourseEdition);

        // Simulate first call: Enrollment starts as active, then becomes inactive
        when(mockCee.isEnrollmentActive()).thenReturn(true).thenReturn(false);

        enrollmentRepository.enrollStudentInACourseEdition(mockStudent, mockCourseEdition);

        // Act
        boolean firstRemoval = enrollmentRepository.removeEnrollment(mockStudent, mockCourseEdition);

        // Assert first removal is successful
        assertTrue(firstRemoval, "Enrollment should be removed successfully.");
        verify(mockCee).deactivateEnrollment(); // Ensure deactivation was called

        // Act again: Try removing a second time
        boolean secondRemoval = enrollmentRepository.removeEnrollment(mockStudent, mockCourseEdition);

        // Assert second removal fails
        assertFalse(secondRemoval, "The second removal should not succeed.");
        verify(mockCee, times(1)).deactivateEnrollment(); // Ensure deactivation was called exactly once
    }

    // Ensures that the system does not allow the removal of an enrollment that has already been deactivated
    @Test
    void removeAlreadyInactiveEnrollment_ShouldReturnFalse() {
        // Arrange
        CourseEditionEnrollmentFactoryInterface enrollmentFactoryMock = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository (enrollmentFactoryMock, CeeListFactory);

        Student mockStudent = mock(Student.class);
        CourseEdition mockCourseEdition = mock(CourseEdition.class);
        CourseEditionEnrollment mockCee = mock(CourseEditionEnrollment.class);

        when(mockCee.findStudentInCourseEditionEnrollment()).thenReturn(mockStudent);
        when(mockCee.findCourseEditionInEnrollment()).thenReturn(mockCourseEdition);
        when(mockCee.isEnrollmentActive()).thenReturn(false); // Enrollment is already inactive

        // Act: Try removing an already inactive enrollment
        boolean result = enrollmentRepository.removeEnrollment(mockStudent, mockCourseEdition);

        // Assert
        assertFalse(result, "Removing an already inactive enrollment should return false.");
        verify(mockCee, never()).deactivateEnrollment(); // Ensure deactivateEnrollment is not called
    }

    // Ensures that different students enrolled in the same course edition can be removed without issues
    @Test
    void removeMultipleStudentsFromSameCourseEdition_ShouldReturnTrueForBoth() {
        // Arrange
        CourseEditionEnrollmentFactoryInterface enrollmentFactoryMock = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository (enrollmentFactoryMock, CeeListFactory);

        CourseEdition mockCourseEdition = mock(CourseEdition.class);
        Student mockStudent1 = mock(Student.class);
        Student mockStudent2 = mock(Student.class);
        CourseEditionEnrollment mockCee1 = mock(CourseEditionEnrollment.class);
        CourseEditionEnrollment mockCee2 = mock(CourseEditionEnrollment.class);

        when(enrollmentFactoryMock.createCourseEditionEnrollment(mockStudent1, mockCourseEdition))
                .thenReturn(mockCee1);
        when(enrollmentFactoryMock.createCourseEditionEnrollment(mockStudent2, mockCourseEdition))
                .thenReturn(mockCee2);

        when(mockCee1.findStudentInCourseEditionEnrollment()).thenReturn(mockStudent1);
        when(mockCee1.findCourseEditionInEnrollment()).thenReturn(mockCourseEdition);
        when(mockCee1.isEnrollmentActive()).thenReturn(true);

        when(mockCee2.findStudentInCourseEditionEnrollment()).thenReturn(mockStudent2);
        when(mockCee2.findCourseEditionInEnrollment()).thenReturn(mockCourseEdition);
        when(mockCee2.isEnrollmentActive()).thenReturn(true);

        enrollmentRepository.enrollStudentInACourseEdition(mockStudent1, mockCourseEdition);
        enrollmentRepository.enrollStudentInACourseEdition(mockStudent2, mockCourseEdition);

        // Act
        boolean firstRemoval = enrollmentRepository.removeEnrollment(mockStudent1, mockCourseEdition);
        boolean secondRemoval = enrollmentRepository.removeEnrollment(mockStudent2, mockCourseEdition);

        // Assert
        assertTrue(firstRemoval, "First student's enrollment should be removed successfully.");
        assertTrue(secondRemoval, "Second student's enrollment should be removed successfully.");
        verify(mockCee1).deactivateEnrollment();
        verify(mockCee2).deactivateEnrollment();
    }

    // Ensures that a student can be removed from multiple course editions correctly
    @Test
    void removeStudentFromMultipleCourseEditions_ShouldReturnTrueForBoth() {
        // Arrange
        CourseEditionEnrollmentFactoryInterface enrollmentFactoryMock = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository (enrollmentFactoryMock, CeeListFactory);

        Student mockStudent = mock(Student.class);
        CourseEdition mockCourseEdition1 = mock(CourseEdition.class);
        CourseEdition mockCourseEdition2 = mock(CourseEdition.class);
        CourseEditionEnrollment mockCee1 = mock(CourseEditionEnrollment.class);
        CourseEditionEnrollment mockCee2 = mock(CourseEditionEnrollment.class);

        when(enrollmentFactoryMock.createCourseEditionEnrollment(mockStudent, mockCourseEdition1)).thenReturn(mockCee1);
        when(enrollmentFactoryMock.createCourseEditionEnrollment(mockStudent, mockCourseEdition2)).thenReturn(mockCee2);

        when(mockCee1.findStudentInCourseEditionEnrollment()).thenReturn(mockStudent);
        when(mockCee1.findCourseEditionInEnrollment()).thenReturn(mockCourseEdition1);
        when(mockCee1.isEnrollmentActive()).thenReturn(true);

        when(mockCee2.findStudentInCourseEditionEnrollment()).thenReturn(mockStudent);
        when(mockCee2.findCourseEditionInEnrollment()).thenReturn(mockCourseEdition2);
        when(mockCee2.isEnrollmentActive()).thenReturn(true);

        enrollmentRepository.enrollStudentInACourseEdition(mockStudent, mockCourseEdition1);
        enrollmentRepository.enrollStudentInACourseEdition(mockStudent, mockCourseEdition2);

        // Act
        boolean firstRemoval = enrollmentRepository.removeEnrollment(mockStudent, mockCourseEdition1);
        boolean secondRemoval = enrollmentRepository.removeEnrollment(mockStudent, mockCourseEdition2);

        // Assert
        assertTrue(firstRemoval, "Student should be removed from the first course edition.");
        assertTrue(secondRemoval, "Student should be removed from the second course edition.");
        verify(mockCee1).deactivateEnrollment();
        verify(mockCee2).deactivateEnrollment();
    }

    @Test
    void shouldEnrollStudentWhenStudentNotEnrolled() {
        // Arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, CeeListFactory);

        Student doubleStudent = mock(Student.class);
        CourseEdition doubleCourseEdition1 = mock(CourseEdition.class);
        CourseEditionEnrollment doubleEnrollment = mock(CourseEditionEnrollment.class);

        when(doubleCeeFactory.createCourseEditionEnrollment(doubleStudent, doubleCourseEdition1)).thenReturn(doubleEnrollment);
        when(doubleEnrollment.findStudentInCourseEditionEnrollment()).thenReturn(doubleStudent);
        when(doubleEnrollment.findCourseEditionInEnrollment()).thenReturn(doubleCourseEdition1);

        // Act
        repository.enrollStudentInACourseEdition(doubleStudent, doubleCourseEdition1);

        // Assert
        assertTrue(repository.findByStudentAndEdition(doubleStudent, doubleCourseEdition1).isPresent());
    }

    @Test
    void shouldThrowExceptionWhenStudentAlreadyEnrolled() {
        // Arrange
        CourseEditionEnrollmentFactoryInterface doubleFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleFactory, CeeListFactory);

        Student doubleStudent = mock(Student.class);
        CourseEdition doubleCourseEdition1 = mock(CourseEdition.class);
        CourseEdition doubleCourseEdition2 = mock(CourseEdition.class);
        List<CourseEdition> courseEditions = List.of(doubleCourseEdition1, doubleCourseEdition2);

        CourseEditionEnrollment doubleEnrollment1 = mock (CourseEditionEnrollment.class);
        CourseEditionEnrollment doubleEnrollment2 = mock (CourseEditionEnrollment.class);

        when(doubleFactory.createCourseEditionEnrollment(doubleStudent, doubleCourseEdition1)).thenReturn(doubleEnrollment1);

        when(doubleFactory.createCourseEditionEnrollment(doubleStudent, doubleCourseEdition2)).thenReturn(doubleEnrollment2);

        when(doubleEnrollment1.findStudentInCourseEditionEnrollment()).thenReturn(doubleStudent);

        when(doubleEnrollment2.findStudentInCourseEditionEnrollment()).thenReturn(doubleStudent);

        when(doubleEnrollment1.findCourseEditionInEnrollment()).thenReturn(doubleCourseEdition1);

        when(doubleEnrollment2.findCourseEditionInEnrollment()).thenReturn(doubleCourseEdition2);

        //act
        repository.enrollStudentInProgrammeCourseEditions(doubleStudent,courseEditions);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->{
                repository.enrollStudentInProgrammeCourseEditions(doubleStudent,courseEditions);
        });

        //assert
        assertEquals("This course edition enrollment is already in the list.", exception.getMessage());
    }

    @Test
    void shouldReturnZeroWhenThereAreNoEnrollmentsInCourseEdition() throws Exception {
        // Arrange
        CourseEditionEnrollmentFactoryInterface doubleCeeFactory = mock (CourseEditionEnrollmentFactoryInterface.class);
        CourseEditionEnrollmentListFactoryInterface CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, CeeListFactory);

        Student doubleStudent = mock(Student.class);
        CourseEdition doubleCourseEdition1 = mock(CourseEdition.class);

        when(doubleCeeFactory.createCourseEditionEnrollment(doubleStudent,doubleCourseEdition1)).thenReturn(null);

        // Act

        int studentsEnrolled = repository.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }

}
