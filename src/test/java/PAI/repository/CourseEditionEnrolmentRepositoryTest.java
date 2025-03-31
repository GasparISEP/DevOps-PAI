package PAI.repository;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.factory.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseEditionEnrolmentRepositoryTest {

    //test enroll a student in a course edition method
    @Test
    void shouldReturnTrueWithAValidCourseEditionEnrollment() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        Student doubleSt1 = mock(Student.class);
        CourseEdition doubleCe1 = mock(CourseEdition.class);
        CourseEditionEnrolment doubleCee1 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleSt1, doubleCe1)).thenReturn(doubleCee1);

        //act
        boolean result = repository.enrolStudentInACourseEdition(doubleSt1, doubleCe1);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWithTwoValidCourseEditionEnrollments() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        Student st1 = mock(Student.class);
        CourseEdition ce1 = mock(CourseEdition.class);
        CourseEditionEnrolment cee1 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(st1, ce1)).thenReturn(cee1);

        Student st2 = mock(Student.class);
        CourseEdition ce2 = mock(CourseEdition.class);
        CourseEditionEnrolment cee2 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(st2, ce1)).thenReturn(cee2);

        //act
        boolean result1 = repository.enrolStudentInACourseEdition(st1, ce1);
        boolean result2 = repository.enrolStudentInACourseEdition(st2, ce2);

        //assert
        assertEquals(true, result1);
        assertEquals(true, result2);
    }

    @Test
    void shouldReturnFalseWhenCourseEditionEnrollmentAlreadyExists() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        Student doubleSt1 = mock(Student.class);
        CourseEdition doubleCe1 = mock(CourseEdition.class);

        CourseEditionEnrolment cee1 = new CourseEditionEnrolmentDoubleEqualsTrue(doubleSt1, doubleCe1);
        CourseEditionEnrolment cee2 = new CourseEditionEnrolmentDoubleEqualsTrue(doubleSt1, doubleCe1);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleSt1, doubleCe1)).thenReturn(cee1);
        repository.enrolStudentInACourseEdition(doubleSt1, doubleCe1);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleSt1, doubleCe1)).thenReturn(cee2);

        //act
        boolean result2 = repository.enrolStudentInACourseEdition(doubleSt1, doubleCe1);

        //assert
        assertFalse(result2);
    }

    @Test
    void shouldReturnFalseIfStudentIsNull() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        CourseEdition doubleCe1 = mock(CourseEdition.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(null, doubleCe1)).thenThrow();

        //act
        boolean result = repository.enrolStudentInACourseEdition(null, doubleCe1);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsNull() {
        //arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        Student doubleSt1 = mock(Student.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleSt1, null)).thenThrow();

        //act
        boolean result = repository.enrolStudentInACourseEdition(doubleSt1, null);

        //assert
        assertFalse(result);
    }

    //test isStudentEnrolledInCourseEdition method
    @Test
    void shouldConfirmStudentIsEnrollInACourseEdition() throws Exception {
        // arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory ceeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);

        Set<CourseEditionEnrolment> mockSet = new HashSet<>();
        when(ceeListFactory.getCourseEditionEnrolmentList()).thenReturn(mockSet);

        CourseEditionEnrolmentRepository repository =
                new CourseEditionEnrolmentRepository(doubleCeeFactory, ceeListFactory);

        StudentID realStudentID = new StudentID(1_500_000);
        Student studentReal = new Student(
                realStudentID,
                mock(Name.class),
                mock(NIF.class),
                mock(PhoneNumber.class),
           mock(Email.class),
                mock(Address.class),
                mock(StudentAcademicEmail.class)
        );

        CourseEdition ce1 = mock(CourseEdition.class);

        CourseEditionEnrolment cee1 = mock(CourseEditionEnrolment.class);
        when(doubleCeeFactory.createCourseEditionEnrolment(studentReal, ce1))
                .thenReturn(cee1);
        when(cee1.knowStudent()).thenReturn(studentReal);
        when(cee1.knowCourseEdition()).thenReturn(ce1);
        when(cee1.isEnrollmentActive()).thenReturn(true);

        // act
        repository.enrolStudentInACourseEdition(studentReal, ce1);

        // assert
        assertTrue(repository.isStudentEnrolledInCourseEdition(studentReal.identity(), ce1));
    }


    @Test
    void shouldConfirmStudentIsNotEnrollInACourseEdition() throws Exception {
        // arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);

        Set<CourseEditionEnrolment> mockSet = new HashSet<>();
        when(CeeListFactory.getCourseEditionEnrolmentList()).thenReturn(mockSet);


        CourseEditionEnrolmentRepository repository =
                new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        // Criamos um Student real com um StudentID válido
        // Lembre que StudentID deve estar no range (1_000_001 a 1_999_999).
        StudentID validID1 = new StudentID(1_500_000);
        Student student1 = new Student(
                validID1,
                mock(Name.class),
                mock(NIF.class),
                mock(PhoneNumber.class),
                mock(Email.class),
                mock(Address.class),
                mock(StudentAcademicEmail.class)
        );


        StudentID differentStudentID = new StudentID(1_600_000);


        CourseEdition ce1 = mock(CourseEdition.class);


        CourseEditionEnrolment cee1 = mock(CourseEditionEnrolment.class);


        when(doubleCeeFactory.createCourseEditionEnrolment(student1, ce1)).thenReturn(cee1);


        when(cee1.knowStudent()).thenReturn(student1);
        when(cee1.knowCourseEdition()).thenReturn(ce1);
        when(cee1.isEnrollmentActive()).thenReturn(true);


        repository.enrolStudentInACourseEdition(student1, ce1);

        // act

        boolean result = repository.isStudentEnrolledInCourseEdition(differentStudentID, ce1);

        // assert

        assertFalse(result);
    }


    @Test
    void shouldReturnCourseEditionEnrollmentWhenStudentIsEnrolled() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        Student doubleSt1 = mock(Student.class);
        CourseEdition doubleCe1 = mock(CourseEdition.class);
        CourseEditionEnrolment courseEEnrollments = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleSt1, doubleCe1)).thenReturn(courseEEnrollments);

        when(courseEEnrollments.hasStudent(doubleSt1)).thenReturn(true);

        when(courseEEnrollments.hasCourseEdition(doubleCe1)).thenReturn(true);

        when(courseEEnrollments.isEnrollmentActive()).thenReturn(true);

        repository.enrolStudentInACourseEdition(doubleSt1, doubleCe1);
        // Act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(doubleSt1, doubleCe1);

        // Assert
        assertTrue(result.isPresent(), "The student was enrolled in the course edition successfully.");

    }


    @Test
    void shouldReturnCourseEditionFromEnrollment() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        Student doubleSt1 = mock(Student.class);
        CourseEdition doubleCe1 = mock(CourseEdition.class);
        CourseEditionEnrolment courseEEnrollments = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleSt1, doubleCe1)).thenReturn(courseEEnrollments);

        when(courseEEnrollments.hasStudent(doubleSt1)).thenReturn(true);

        when(courseEEnrollments.hasCourseEdition(doubleCe1)).thenReturn(true);

        repository.enrolStudentInACourseEdition(doubleSt1, doubleCe1);

        // Act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(doubleSt1, doubleCe1);

        // Assert
        assertTrue(result.isPresent(), "The student should be enrolled in the course edition.");

    }


    @Test
    void shouldThrowOptionalEmptyWhenStudentIsNull() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        CourseEdition doubleCe1 = mock(CourseEdition.class);

        // Act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(null, doubleCe1);

        //Assert
        assertTrue(result.isEmpty(), "Expected Optional.empty() when student is null");

    }

    @Test
    void shouldThrowOptionalEmptyWhenCourseEditionIsNull() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        Student doubleSt1 = mock(Student.class);

        // Act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(doubleSt1, null);

        //Assert
        assertTrue(result.isEmpty(), "Expected Optional.empty() when courseEdition is null");

    }

    @Test
    void shouldReturnOptionalEmptyWhenNoEnrollmentFound() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        Student doubleSt1 = mock(Student.class);
        CourseEdition doubleCe1 = mock(CourseEdition.class);

        // Act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(doubleSt1, doubleCe1);

        // Assert
        assertFalse(result.isPresent(), "The result should be empty if the student is not enrolled in the course edition.");
    }


    @Test
    void shouldReturnEmptyWhenStudentIsNotEnrolledInCourseEdition() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        Student doubleSt1 = mock(Student.class);
        CourseEdition doubleCe1 = mock(CourseEdition.class);

        // Act
        Optional<CourseEditionEnrolment> result = repository.findByStudentAndEdition(doubleSt1, doubleCe1);

        // Assert
        assertFalse(result.isPresent(), "Expected no enrollment to be found");
    }


    @Test
    void shouldReturnNumberOfStudentsEnrolledInCourseEdition() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repo = new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        CourseEdition doubleCourseEdition1 = mock(CourseEdition.class);
        Student doubleStudent1 = mock(Student.class);
        CourseEditionEnrolment cee1 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStudent1, doubleCourseEdition1)).thenReturn(cee1);

        when(cee1.knowCourseEdition()).thenReturn(doubleCourseEdition1);

        repo.enrolStudentInACourseEdition(doubleStudent1, doubleCourseEdition1);

        // Act
        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(1, studentsEnrolled);
    }

    @Test
    void ShouldReturnZeroWhenThereAreNoEnrolmentsInACourse() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repo = new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        CourseEdition doubleCourseEdition1 = mock(CourseEdition.class);
        CourseEdition doubleCourseEdition2 = mock(CourseEdition.class);

        Student doubleStudent1 = mock(Student.class);
        Student doubleStudent2 = mock(Student.class);

        CourseEditionEnrolment cee1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment cee2 = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStudent1, doubleCourseEdition2)).thenReturn(cee1);
        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStudent2, doubleCourseEdition2)).thenReturn(cee2);

        when(cee1.knowCourseEdition()).thenReturn(doubleCourseEdition2);
        when(cee2.knowCourseEdition()).thenReturn(doubleCourseEdition2);

        repo.enrolStudentInACourseEdition(doubleStudent1, doubleCourseEdition2);
        repo.enrolStudentInACourseEdition(doubleStudent2, doubleCourseEdition2);

        // Act
        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }

    @Test
    void shouldReturnZeroWhenThereAreNoStudents() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repo = new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        // Create a course edition
        CourseEdition doubleCourseEdition1 = mock(CourseEdition.class);

        // Act
        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }

    // System should allow the successful removal of a student enrolled in a course edition
    @Test
    void removeExistingEnrollment_ShouldReturnTrue() {
        // Arrange
        ICourseEditionEnrolmentFactory enrollmentFactoryMock = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository enrollmentRepository = new CourseEditionEnrolmentRepository(enrollmentFactoryMock, CeeListFactory);

        Student mockStudent = mock(Student.class);
        CourseEdition mockCourseEdition = mock(CourseEdition.class);
        CourseEditionEnrolment mockCee = mock(CourseEditionEnrolment.class);

        when(enrollmentFactoryMock.createCourseEditionEnrolment(mockStudent, mockCourseEdition))
                .thenReturn(mockCee);

        when(mockCee.hasStudent(mockStudent)).thenReturn(true);
        when(mockCee.hasCourseEdition(mockCourseEdition)).thenReturn(true);
        when(mockCee.isEnrollmentActive()).thenReturn(true);

        enrollmentRepository.enrolStudentInACourseEdition(mockStudent, mockCourseEdition);

        // Act
        boolean result = enrollmentRepository.removeEnrolment(mockStudent, mockCourseEdition);

        // Assert
        assertTrue(result, "Enrollment should be removed successfully.");
        verify(mockCee).deactivateEnrollment(); // Verify that deactivateEnrollment() was called
    }

    // Ensures that the system does not allow the removal of a non-existent enrollment
    @Test
    void removeNonExistingEnrollment_ShouldReturnFalse() {
        // Arrange
        ICourseEditionEnrolmentFactory enrollmentFactoryMock = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(enrollmentFactoryMock, CeeListFactory);

        Student mockStudent = mock(Student.class);
        CourseEdition mockCourseEdition = mock(CourseEdition.class);

        // Act
        boolean result = repository.removeEnrolment(mockStudent, mockCourseEdition);

        // Assert
        assertFalse(result, "Removing a non existing enrollment should return false.");
        verify(enrollmentFactoryMock, never()).createCourseEditionEnrolment(any(), any()); // Ensure no enrollment creation occurs
    }

    // If the student or course edition information is missing (null), the system should reject the operation
    @Test
    void removeEnrolment_WithNullStudentOrCourseEdition_ShouldReturnFalse() {
        // Arrange
        ICourseEditionEnrolmentFactory enrollmentFactoryMock = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(enrollmentFactoryMock, CeeListFactory);

        Student mockStudent = mock(Student.class);
        CourseEdition mockCourseEdition = mock(CourseEdition.class);

        // Act & Assert for null Student
        boolean result1 = repository.removeEnrolment(null, mockCourseEdition);
        assertFalse(result1);
        verify(enrollmentFactoryMock, never()).createCourseEditionEnrolment(any(), any());

        // Act & Assert for null CourseEdition
        boolean result2 = repository.removeEnrolment(mockStudent, null);
        assertFalse(result2);
        verify(enrollmentFactoryMock, never()).createCourseEditionEnrolment(any(), any());
    }

    // Confirms that removing the same enrollment multiple times should only succeed on the first attempt, while subsequent attempts should be denied
    @Test
    void removeEnrolmentTwice_ShouldReturnFalseOnSecondAttempt() {
        // Arrange
        ICourseEditionEnrolmentFactory enrollmentFactoryMock = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository enrollmentRepository = new CourseEditionEnrolmentRepository(enrollmentFactoryMock, CeeListFactory);

        Student mockStudent = mock(Student.class);
        CourseEdition mockCourseEdition = mock(CourseEdition.class);
        CourseEditionEnrolment mockCee = mock(CourseEditionEnrolment.class);

        when(enrollmentFactoryMock.createCourseEditionEnrolment(mockStudent, mockCourseEdition))
                .thenReturn(mockCee);

        when(mockCee.hasStudent(mockStudent)).thenReturn(true);
        when(mockCee.hasCourseEdition(mockCourseEdition)).thenReturn(true);

        // Simulate first call: Enrollment starts as active, then becomes inactive
        when(mockCee.isEnrollmentActive()).thenReturn(true).thenReturn(false);

        enrollmentRepository.enrolStudentInACourseEdition(mockStudent, mockCourseEdition);

        // Act
        boolean firstRemoval = enrollmentRepository.removeEnrolment(mockStudent, mockCourseEdition);

        // Assert first removal is successful
        assertTrue(firstRemoval, "Enrollment should be removed successfully.");
        verify(mockCee).deactivateEnrollment(); // Ensure deactivation was called

        // Act again: Try removing a second time
        boolean secondRemoval = enrollmentRepository.removeEnrolment(mockStudent, mockCourseEdition);

        // Assert second removal fails
        assertFalse(secondRemoval, "The second removal should not succeed.");
        verify(mockCee, times(1)).deactivateEnrollment(); // Ensure deactivation was called exactly once
    }

    // Ensures that the system does not allow the removal of an enrollment that has already been deactivated
    @Test
    void removeAlreadyInactiveEnrollment_ShouldReturnFalse() {
        // Arrange
        ICourseEditionEnrolmentFactory enrollmentFactoryMock = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository enrollmentRepository = new CourseEditionEnrolmentRepository(enrollmentFactoryMock, CeeListFactory);

        Student mockStudent = mock(Student.class);
        CourseEdition mockCourseEdition = mock(CourseEdition.class);
        CourseEditionEnrolment mockCee = mock(CourseEditionEnrolment.class);

        when(mockCee.hasStudent(mockStudent)).thenReturn(true);
        when(mockCee.hasCourseEdition(mockCourseEdition)).thenReturn(true);
        when(mockCee.isEnrollmentActive()).thenReturn(false); // Enrollment is already inactive

        // Act: Try removing an already inactive enrollment
        boolean result = enrollmentRepository.removeEnrolment(mockStudent, mockCourseEdition);

        // Assert
        assertFalse(result, "Removing an already inactive enrollment should return false.");
        verify(mockCee, never()).deactivateEnrollment(); // Ensure deactivateEnrollment is not called
    }

    // Ensures that different students enrolled in the same course edition can be removed without issues
    @Test
    void removeMultipleStudentsFromSameCourseEdition_ShouldReturnTrueForBoth() {
        // Arrange
        ICourseEditionEnrolmentFactory enrollmentFactoryMock = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository enrollmentRepository = new CourseEditionEnrolmentRepository(enrollmentFactoryMock, CeeListFactory);

        CourseEdition mockCourseEdition = mock(CourseEdition.class);
        Student mockStudent1 = mock(Student.class);
        Student mockStudent2 = mock(Student.class);
        CourseEditionEnrolment mockCee1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment mockCee2 = mock(CourseEditionEnrolment.class);

        when(enrollmentFactoryMock.createCourseEditionEnrolment(mockStudent1, mockCourseEdition))
                .thenReturn(mockCee1);
        when(enrollmentFactoryMock.createCourseEditionEnrolment(mockStudent2, mockCourseEdition))
                .thenReturn(mockCee2);

        when(mockCee1.hasStudent(mockStudent1)).thenReturn(true);
        when(mockCee1.hasCourseEdition(mockCourseEdition)).thenReturn(true);
        when(mockCee1.isEnrollmentActive()).thenReturn(true);

        when(mockCee2.hasStudent(mockStudent2)).thenReturn(true);
        when(mockCee2.hasCourseEdition(mockCourseEdition)).thenReturn(true);
        when(mockCee2.isEnrollmentActive()).thenReturn(true);

        enrollmentRepository.enrolStudentInACourseEdition(mockStudent1, mockCourseEdition);
        enrollmentRepository.enrolStudentInACourseEdition(mockStudent2, mockCourseEdition);

        // Act
        boolean firstRemoval = enrollmentRepository.removeEnrolment(mockStudent1, mockCourseEdition);
        boolean secondRemoval = enrollmentRepository.removeEnrolment(mockStudent2, mockCourseEdition);

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
        ICourseEditionEnrolmentFactory enrollmentFactoryMock = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository enrollmentRepository = new CourseEditionEnrolmentRepository(enrollmentFactoryMock, CeeListFactory);

        Student mockStudent = mock(Student.class);
        CourseEdition mockCourseEdition1 = mock(CourseEdition.class);
        CourseEdition mockCourseEdition2 = mock(CourseEdition.class);
        CourseEditionEnrolment mockCee1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment mockCee2 = mock(CourseEditionEnrolment.class);

        when(enrollmentFactoryMock.createCourseEditionEnrolment(mockStudent, mockCourseEdition1)).thenReturn(mockCee1);
        when(enrollmentFactoryMock.createCourseEditionEnrolment(mockStudent, mockCourseEdition2)).thenReturn(mockCee2);

        when(mockCee1.hasStudent(mockStudent)).thenReturn(true);
        when(mockCee1.hasCourseEdition(mockCourseEdition1)).thenReturn(true);
        when(mockCee1.isEnrollmentActive()).thenReturn(true);

        when(mockCee2.hasStudent(mockStudent)).thenReturn(true);
        when(mockCee2.hasCourseEdition(mockCourseEdition2)).thenReturn(true);
        when(mockCee2.isEnrollmentActive()).thenReturn(true);

        enrollmentRepository.enrolStudentInACourseEdition(mockStudent, mockCourseEdition1);
        enrollmentRepository.enrolStudentInACourseEdition(mockStudent, mockCourseEdition2);

        // Act
        boolean firstRemoval = enrollmentRepository.removeEnrolment(mockStudent, mockCourseEdition1);
        boolean secondRemoval = enrollmentRepository.removeEnrolment(mockStudent, mockCourseEdition2);

        // Assert
        assertTrue(firstRemoval, "Student should be removed from the first course edition.");
        assertTrue(secondRemoval, "Student should be removed from the second course edition.");
        verify(mockCee1).deactivateEnrollment();
        verify(mockCee2).deactivateEnrollment();
    }

    @Test
    void shouldEnrollStudentWhenStudentNotEnrolled() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        Student doubleStudent = mock(Student.class);
        CourseEdition doubleCourseEdition1 = mock(CourseEdition.class);
        CourseEditionEnrolment doubleEnrollment = mock(CourseEditionEnrolment.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStudent, doubleCourseEdition1)).thenReturn(doubleEnrollment);
        when(doubleEnrollment.hasStudent(doubleStudent)).thenReturn(true);
        when(doubleEnrollment.hasCourseEdition(doubleCourseEdition1)).thenReturn(true);

        // Act
        repository.enrolStudentInACourseEdition(doubleStudent, doubleCourseEdition1);

        // Assert
        assertTrue(repository.findByStudentAndEdition(doubleStudent, doubleCourseEdition1).isPresent());
    }

    @Test
    void shouldThrowExceptionWhenStudentAlreadyEnrolled() {
        // Arrange
        ICourseEditionEnrolmentFactory doubleFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(doubleFactory, CeeListFactory);

        Student doubleStudent = mock(Student.class);
        CourseEdition doubleCourseEdition1 = mock(CourseEdition.class);
        CourseEdition doubleCourseEdition2 = mock(CourseEdition.class);
        List<CourseEdition> courseEditions = List.of(doubleCourseEdition1, doubleCourseEdition2);

        CourseEditionEnrolment doubleEnrollment1 = mock(CourseEditionEnrolment.class);
        CourseEditionEnrolment doubleEnrollment2 = mock(CourseEditionEnrolment.class);

        when(doubleFactory.createCourseEditionEnrolment(doubleStudent, doubleCourseEdition1)).thenReturn(doubleEnrollment1);

        when(doubleFactory.createCourseEditionEnrolment(doubleStudent, doubleCourseEdition2)).thenReturn(doubleEnrollment2);

        when(doubleEnrollment1.hasStudent(doubleStudent)).thenReturn(true);

        when(doubleEnrollment2.hasStudent(doubleStudent)).thenReturn(true);

        when(doubleEnrollment1.hasCourseEdition(doubleCourseEdition1)).thenReturn(true);

        when(doubleEnrollment2.hasCourseEdition(doubleCourseEdition2)).thenReturn(true);

        //act
        repository.enrolStudentInProgrammeCourseEditions(doubleStudent, courseEditions);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            repository.enrolStudentInProgrammeCourseEditions(doubleStudent, courseEditions);
        });

        //assert
        assertEquals("This course edition enrolment is already in the list.", exception.getMessage());
    }

    @Test
    void shouldReturnZeroWhenThereAreNoEnrollmentsInCourseEdition() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory doubleCeeFactory = mock(ICourseEditionEnrolmentFactory.class);
        ICourseEditionEnrolmentListFactory CeeListFactory = mock(CourseEditionEnrolmentListFactoryImpl.class);
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(doubleCeeFactory, CeeListFactory);

        Student doubleStudent = mock(Student.class);
        CourseEdition doubleCourseEdition1 = mock(CourseEdition.class);

        when(doubleCeeFactory.createCourseEditionEnrolment(doubleStudent, doubleCourseEdition1)).thenReturn(null);

        // Act

        int studentsEnrolled = repository.numberOfStudentsEnrolledInCourseEdition(doubleCourseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }


    //---------------Integration Test--------------
/*

    @Test
    void removeExistingEnrollment_ShouldReturnTrue_IntegrationTest() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);

        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");

        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", addressFactory, date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
                new TeacherCareerProgressionListFactoryImpl());

        StudentID studentID = new StudentID(1000001);
        Name name = new Name("João Silva");
        String countryName = "Portugal";
        Country country = new Country(countryName);
        NIF nif = new NIF("999999999", country);
        PhoneNumber phone = new PhoneNumber("+351","221234567");
        Email email = new Email("joao123@gmail.com");
        StudentAcademicEmail academicEmail = new StudentAcademicEmail(studentID);

        Student student = new Student(studentID, name, nif, phone, email, add1, academicEmail);

        Course c1 = new Course("Development", "DEV", 5, 1);

        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());

        programme1.addCourseToAProgramme(c1);

        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("01-09-2024");
        Date endDate = new Date ("31-07-2025");
        SchoolYear schoolYear = new SchoolYear(description, startDate, endDate);
        ProgrammeEdition pe = new ProgrammeEdition(programme1, schoolYear);
        CourseEdition courseEdition = new CourseEdition(c1, pe);

        courseEditionEnrolmentRepository.enrolStudentInACourseEdition(student, courseEdition);

        //Act
        boolean result = courseEditionEnrolmentRepository.removeEnrolment(student, courseEdition);

        // Assert
        assertTrue(result, "Enrollment should be removed successfully.");
    }

    @Test
    void removeNonExistingEnrollment_ShouldReturnFalse_IntegrationTest() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);

        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", addressFactory, date, tcID , wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
                new TeacherCareerProgressionListFactoryImpl());

        StudentID studentID = new StudentID(1000001);
        Name name = new Name("João Silva");
        String countryName = "Portugal";
        Country country = new Country(countryName);
        NIF nif = new NIF("999999999", country);
        PhoneNumber phone = new PhoneNumber("+351","221234567");
        Email email = new Email("joao123@gmail.com");
        StudentAcademicEmail academicEmail = new StudentAcademicEmail(studentID);

        Student student = new Student(studentID, name, nif, phone, email, add1, academicEmail);

        Course c1 = new Course("Development", "DEV", 5, 1);

        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());

        programme1.addCourseToAProgramme(c1);

        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("01-09-2024");
        Date endDate = new Date ("31-07-2025");
        SchoolYear schoolYear = new SchoolYear(description, startDate,endDate);
        ProgrammeEdition pe = new ProgrammeEdition(programme1, schoolYear);
        CourseEdition courseEdition = new CourseEdition(c1, pe);

        //Act
        boolean result = courseEditionEnrolmentRepository.removeEnrolment(student, courseEdition);

        // Assert
        assertFalse(result, "Removing a non existing enrollment should return false.");
    }

    @Test
    void removeEnrolment_WithNullStudentOrCourseEdition_ShouldReturnFalse_IntegrationTest() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);

        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", addressFactory, date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
                new TeacherCareerProgressionListFactoryImpl());

        StudentID studentID = new StudentID(1000001);
        Name name = new Name("João Silva");
        String countryName = "Portugal";
        Country country = new Country(countryName);
        NIF nif = new NIF("999999999", country);
        PhoneNumber phone = new PhoneNumber("+351","221234567");
        Email email = new Email("joao123@gmail.com");
        StudentAcademicEmail academicEmail = new StudentAcademicEmail(studentID);

        Student student = new Student(studentID, name, nif, phone, email, add1, academicEmail);

        Course c1 = new Course("Development", "DEV", 5, 1);

        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());

        programme1.addCourseToAProgramme(c1);

        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("01-09-2024");
        Date endDate = new Date ("31-07-2025");
        SchoolYear schoolYear = new SchoolYear(description, startDate, endDate);
        ProgrammeEdition pe = new ProgrammeEdition(programme1, schoolYear);
        CourseEdition courseEdition = new CourseEdition(c1, pe);

        // Act and assert
        // test for the case where Student is null
        boolean result1 = repository.removeEnrolment(null, courseEdition);
        assertFalse(result1, "Removing a non existing enrollment should return false.");

        // test for the case where CourseEdition is null.
        boolean result2 = repository.removeEnrolment(student, null);
        assertFalse(result2, "Removing a non existing enrollment should return false.");
    }

    @Test
    void removeEnrolmentTwice_ShouldReturnFalseOnSecondAttempt_IntegrationTest() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);

        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", addressFactory, date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
                new TeacherCareerProgressionListFactoryImpl());

        StudentID studentID = new StudentID(1000001);
        Name name = new Name("João Silva");
        String countryName = "Portugal";
        Country country = new Country(countryName);
        NIF nif = new NIF("999999999", country);
        PhoneNumber phone = new PhoneNumber("+351","221234567");
        Email email = new Email("joao123@gmail.com");
        StudentAcademicEmail academicEmail = new StudentAcademicEmail(studentID);

        Student student = new Student(studentID, name, nif, phone, email, add1, academicEmail);

        Course c1 = new Course("Development", "DEV", 5, 1);

        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());

        programme1.addCourseToAProgramme(c1);

        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("01-09-2024");
        Date endDate = new Date ("31-07-2025");
        SchoolYear schoolYear = new SchoolYear(description, startDate, endDate);
        ProgrammeEdition pe = new ProgrammeEdition(programme1, schoolYear);
        CourseEdition courseEdition = new CourseEdition(c1, pe);

        repository.enrolStudentInACourseEdition(student, courseEdition);

        // Act
        boolean firstRemoval = repository.removeEnrolment(student, courseEdition);
        boolean secondRemoval = repository.removeEnrolment(student, courseEdition);

        // Assert
        assertTrue(firstRemoval, "Enrollment should be removed successfully.");
        assertFalse(secondRemoval, "The second removal should not succeed.");
    }

    @Test
    void removeMultipleStudentsFromSameCourseEdition_ShouldReturnTrueForBoth_IntegrationTest() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);

        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", addressFactory, date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
                new TeacherCareerProgressionListFactoryImpl());

        StudentID studentID1 = new StudentID(1000001);
        Name name = new Name("João Silva");
        String countryName = "Portugal";
        Country country = new Country(countryName);
        NIF nif = new NIF("999999999", country);
        PhoneNumber phone = new PhoneNumber("+351","221234567");
        Email email = new Email("joao123@gmail.com");
        StudentAcademicEmail academicEmail = new StudentAcademicEmail(studentID1);

        Student student1 = new Student(studentID1, name, nif, phone, email, add1, academicEmail);

        StudentID studentID2 = new StudentID(1000002);
        Name name2 = new Name("João Santos");
        NIF nif2 = new NIF("998194999", country);
        PhoneNumber phone2 = new PhoneNumber("+351","221234467");
        Email email2 = new Email("joao456@gmail.com");
        StudentAcademicEmail academicEmail2 = new StudentAcademicEmail(studentID2);


        Student student2 = new Student(studentID2, name2, nif2, phone2, email2, add1, academicEmail);

        Course c1 = new Course("Development", "DEV", 5, 1);

        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());

        programme1.addCourseToAProgramme(c1);

        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("01-09-2024");
        Date endDate = new Date ("31-07-2025");
        SchoolYear schoolYear = new SchoolYear(description, startDate, endDate);
        ProgrammeEdition pe = new ProgrammeEdition(programme1, schoolYear);
        CourseEdition courseEdition = new CourseEdition(c1, pe);

        repository.enrolStudentInACourseEdition(student1, courseEdition);
        repository.enrolStudentInACourseEdition(student2, courseEdition);

        //Act
        boolean firstRemoval = repository.removeEnrolment(student1, courseEdition);
        boolean secondRemoval = repository.removeEnrolment(student2, courseEdition);

        // Assert
        assertTrue(firstRemoval, "First student's enrollment should be removed successfully.");
        assertTrue(secondRemoval, "Second student's enrollment should be removed successfully.");
    }

    @Test
    void removeStudentFromMultipleCourseEditions_ShouldReturnTrueForBoth_IntegrationTest() throws Exception {
        // Arrange
        ICourseEditionEnrolmentFactory courseEditionEnrollmentFactory = new CourseEditionEnrolmentFactoryImpl();
        ICourseEditionEnrolmentListFactory courseEditionEnrollmentListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(courseEditionEnrollmentFactory, courseEditionEnrollmentListFactory);

        Date date = new Date("20-12-2010");
        TeacherCategoryID tcID = new TeacherCategoryID();
        WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        DegreeType master = new DegreeType("Master", 240);
        Department department1 = new Department("DEI", "Departamento Engenharia Informática");
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Address add1 = new Address("Rua São Tomé Porto", "4249-015", "Porto", "Portugal");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123666789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto",
                "Portugal", addressFactory, date, tcID, wp, teacherID, department1, new TeacherCareerProgressionFactoryImpl(),
                new TeacherCareerProgressionListFactoryImpl());

        StudentID studentID = new StudentID(1000001);
        Name name = new Name("João Silva");
        String countryName = "Portugal";
        Country country = new Country(countryName);
        NIF nif = new NIF("999999999", country);
        PhoneNumber phone = new PhoneNumber("+351","221234567");
        Email email = new Email("joao123@gmail.com");
        StudentAcademicEmail academicEmail = new StudentAcademicEmail(studentID);

        Student student = new Student(studentID, name, nif, phone, email, add1, academicEmail);

        Student student1 = new Student(studentID, name, nif, phone, email, add1, academicEmail);

        Course c1 = new Course("Development", "DEV", 5, 1);
        Course c2 = new Course("Algebra", "ALG", 5, 2);

        Programme programme1 = new Programme("Computer Engineering", "CSE", 25, 6, master, department1, teacher1,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(), new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(),
                new CourseFactoryImpl());

        programme1.addCourseToAProgramme(c1);
        programme1.addCourseToAProgramme(c2);

        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("01-09-2024");
        Date endDate = new Date ("31-07-2025");
        SchoolYear schoolYear = new SchoolYear(description, startDate, endDate);
        ProgrammeEdition pe = new ProgrammeEdition(programme1, schoolYear);
        CourseEdition courseEdition1 = new CourseEdition(c1, pe);
        CourseEdition courseEdition2 = new CourseEdition(c2, pe);

        repository.enrolStudentInACourseEdition(student1, courseEdition1);
        repository.enrolStudentInACourseEdition(student1, courseEdition2);

        //Act
        boolean firstRemoval = repository.removeEnrolment(student1, courseEdition1);
        boolean secondRemoval = repository.removeEnrolment(student1, courseEdition2);

        // Assert
        assertTrue(firstRemoval, "First student's enrollment should be removed successfully.");
        assertTrue(secondRemoval, "Second student's enrollment should be removed successfully.");
    }
   */
}
