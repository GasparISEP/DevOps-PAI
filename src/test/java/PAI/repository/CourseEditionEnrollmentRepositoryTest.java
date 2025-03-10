package PAI.repository;

import PAI.domain.*;
import PAI.factory.CourseEditionEnrollmentFactory;
import PAI.factory.CourseEditionEnrollmentListFactory;
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
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
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
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, CeeListFactory);

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
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

        Student doubleSt1 = mock(Student.class);
        CourseEdition doubleCe1 = mock(CourseEdition.class);
        CourseEditionEnrollment doubleCee1 = mock(CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(doubleSt1,doubleCe1)).thenReturn(doubleCee1);

        repository.enrollStudentInACourseEdition(doubleSt1,doubleCe1);

        //act
        boolean result2 = repository.enrollStudentInACourseEdition(doubleSt1,doubleCe1);

        //assert
        assertFalse(result2);
    }

    @Test
    void shouldReturnFalseIfStudentIsNull (){
        //arrange
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
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
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
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
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

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
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

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
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

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
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

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
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

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
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

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
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

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
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

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
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

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
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

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
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            repository.numberOfStudentsEnrolledInCourseEdition(null);
        });

        // Assert
        assertEquals("Course edition cannot be null.", exception.getMessage());
    }


    @Test
    void removeExistingEnrollment_ShouldReturnTrue() {
        // Arrange
        CourseEditionEnrollmentFactory enrollmentFactoryMock = mock(CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
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

    @Test
    void removeNonExistingEnrollment_ShouldReturnFalse() {
        // Arrange
        CourseEditionEnrollmentFactory enrollmentFactoryMock = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (enrollmentFactoryMock, CeeListFactory);

        Student mockStudent = mock (Student.class);
        CourseEdition mockCourseEdition = mock (CourseEdition.class);

        // Act
        boolean result = repository.removeEnrollment(mockStudent, mockCourseEdition);

        // Assert
        assertFalse(result, "Removing a non existing enrollment should return false.");
        verify(enrollmentFactoryMock, never()).createCourseEditionEnrollment(any(), any()); // Ensure no enrollment creation occurs
    }

    @Test
    void removeEnrollment_WithNullCourseEditionOrStudent_ShouldThrowException() throws IllegalArgumentException {
        // Arrange
        CourseEditionEnrollmentFactory enrollmentFactoryMock = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
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

    @Test
    void removeEnrollmentTwice_ShouldReturnFalseOnSecondAttempt() {
        // Arrange
        CourseEditionEnrollmentFactory enrollmentFactoryMock = mock(CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
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

    @Test
    void removeAlreadyInactiveEnrollment_ShouldReturnFalse() {
        // Arrange
        CourseEditionEnrollmentFactory enrollmentFactoryMock = mock(CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory CeeListFactory = mock (CourseEditionEnrollmentListFactory.class);
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

    @Test
    void shouldEnrollStudentWhenStudentNotEnrolled() {
        // Arrange
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

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
        CourseEditionEnrollmentFactory doubleFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleFactory, doubleCeeListFactory);

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
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentListFactory doubleCeeListFactory = mock(CourseEditionEnrollmentListFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory, doubleCeeListFactory);

        Student doubleStudent = mock(Student.class);
        CourseEdition doubleCourseEdition1 = mock(CourseEdition.class);

        when(doubleCeeFactory.createCourseEditionEnrollment(doubleStudent,doubleCourseEdition1)).thenReturn(null);

        // Act

        int studentsEnrolled = repository.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }

}
