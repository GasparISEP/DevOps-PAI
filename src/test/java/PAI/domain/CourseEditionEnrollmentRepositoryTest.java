package PAI.domain;

import PAI.controller.US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class CourseEditionEnrollmentRepositoryTest {

    @Test
    void shouldReturnTrueWithAValidCourseEditionEnrollment () {
        //arrange
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory);

        LocalDate currentDate = LocalDate.now();
        Student doubleSt1 = mock(Student.class);
        CourseEdition doubleCe1 = mock(CourseEdition.class);
        CourseEditionEnrollment doubleCee1 = mock(CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(doubleSt1,doubleCe1,currentDate)).thenReturn(doubleCee1);

        //act
        boolean result = repository.enrollStudentInACourseEdition(doubleSt1,doubleCe1,currentDate);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWithTwoValidCourseEditionEnrollments () {
        //arrange
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory);

        LocalDate currentDate = LocalDate.now();
        Student st1 = mock(Student.class);
        CourseEdition ce1 = mock(CourseEdition.class);
        CourseEditionEnrollment cee1 = mock(CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(st1,ce1,currentDate)).thenReturn(cee1);

        LocalDate currentDate1 = LocalDate.now();
        Student st2 = mock(Student.class);
        CourseEdition ce2 = mock(CourseEdition.class);
        CourseEditionEnrollment cee2 = mock(CourseEditionEnrollment.class);

        when(doubleCeeFactory.createCourseEditionEnrollment(st2,ce2,currentDate1)).thenReturn(cee2);

        //act
        boolean result1 = repository.enrollStudentInACourseEdition(st1,ce1,currentDate);
        boolean result2 = repository.enrollStudentInACourseEdition(st2,ce2,currentDate1);

        //assert
        assertEquals(true,result1);
        assertEquals(true,result2);
    }

    @Test
    void shouldReturnFalseWhenCourseEditionEnrollmentAlreadyExists() {
        //arrange
        CourseEditionEnrollmentFactory doubleCeeFactory= mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory);

        Student doubleSt1 = mock(Student.class);
        CourseEdition doubleCe1 = mock(CourseEdition.class);
        LocalDate currentDate = LocalDate.now();
        CourseEditionEnrollment doubleCee1 = mock(CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(doubleSt1,doubleCe1,currentDate)).thenReturn(doubleCee1);

        repository.enrollStudentInACourseEdition(doubleSt1,doubleCe1,currentDate);

        //act
        boolean result2 = repository.enrollStudentInACourseEdition(doubleSt1,doubleCe1,currentDate);

        //assert
        assertFalse(result2);
    }

    @Test
    void shouldConfirmStudentIsEnrollInACourseEdition () throws Exception {
        //arrange
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory);

        LocalDate currentDate = LocalDate.now();
        CourseEdition ce1 = mock(CourseEdition.class);
        Student student1 = mock(Student.class);
        CourseEditionEnrollment cee1 = mock(CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(student1,ce1,currentDate)).thenReturn(cee1);
        when(cee1.knowStudent()).thenReturn(student1);
        when(cee1.knowCourseEdition()).thenReturn(ce1);
        repository.enrollStudentInACourseEdition(student1, ce1, currentDate);

        //act & assert
        assertTrue(repository.isStudentEnrolledInCourseEdition(student1, ce1));
    }

    @Test
    void shouldConfirmStudentIsNotEnrollInACourseEdition () throws Exception {
        //arrange
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory);

        LocalDate currentDate = LocalDate.now();
        CourseEdition ce1 = mock(CourseEdition.class);
        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEditionEnrollment cee1 = mock(CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(student1,ce1,currentDate)).thenReturn(cee1);
        when(cee1.knowStudent()).thenReturn(student1);
        when(cee1.knowCourseEdition()).thenReturn(ce1);
        repository.enrollStudentInACourseEdition(student1, ce1, currentDate);

        //act
        //assert
        assertFalse(repository.isStudentEnrolledInCourseEdition(student2, ce1));
    }

    //US17
    @Test
    void shouldReturnCourseEditionEnrollmentWhenStudentIsEnrolled() throws Exception {
        // Arrange
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory);

        Student st1 = mock (Student.class);
        CourseEdition ce1 = mock (CourseEdition.class);
        LocalDate currentDate = LocalDate.now();
        CourseEditionEnrollment courseEEnrollments = mock(CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(st1,ce1,currentDate)).thenReturn(courseEEnrollments);
        when (courseEEnrollments.findStudentInCourseEditionEnrollment()).thenReturn(st1);
        when (courseEEnrollments.findCourseEditionInEnrollment()).thenReturn(ce1);

        repository.enrollStudentInACourseEdition(st1, ce1, currentDate);
        // Act
        Optional<CourseEditionEnrollment> result = repository.findByStudentAndEdition(st1, ce1);

        // Assert
        assertTrue(result.isPresent(), "The student was enrolled in the course edition successfully.");
        assertEquals(result.get().findStudentInCourseEditionEnrollment(), st1, "The student enrolled in the correct course edition.");
        assertEquals(result.get().findCourseEditionInEnrollment(), ce1, "The course edition enrolled is correct.");
    }

    //US17
    @Test
    void shouldReturnCourseEditionFromEnrollment() throws Exception {
        // Arrange
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory);

        Student st1 = mock (Student.class);
        CourseEdition ce1 = mock (CourseEdition.class);
        LocalDate currentDate = LocalDate.now();
        CourseEditionEnrollment courseEEnrollments = mock(CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(st1,ce1,currentDate)).thenReturn(courseEEnrollments);
        when (courseEEnrollments.findStudentInCourseEditionEnrollment()).thenReturn(st1);
        when (courseEEnrollments.findCourseEditionInEnrollment()).thenReturn(ce1);

        repository.enrollStudentInACourseEdition(st1, ce1, currentDate);

        // Act
        Optional<CourseEditionEnrollment> result = repository.findByStudentAndEdition(st1, ce1);

        // Assert
        assertTrue(result.isPresent(), "The student should be enrolled in the course edition.");
        assertEquals(ce1, result.get().findCourseEditionInEnrollment(), "The course edition returned should match the one in the enrollment.");
    }

    //US17
    @Test
    void shouldThrowExceptionWhenStudentOrCourseEditionIsNull() throws Exception {
        // Arrange
        CourseFactory courseFactory = mock(CourseFactory.class);
        CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (factoryDouble);
        CourseEdition ce1 = mock (CourseEdition.class);
        Student st1 = mock (Student.class);
        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.findByStudentAndEdition(null, ce1);
        });
        assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());

        thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.findByStudentAndEdition(st1, null);
        });
        assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());
    }
    //US17
    @Test
    void shouldReturnOptionalEmptyWhenNoEnrollmentFound() throws Exception {
        // Arrange
        CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (factoryDouble);
        Student st1 =mock(Student.class);
        Student st2 = mock(Student.class);
        CourseEdition ce1= mock(CourseEdition.class);

        // Act
        Optional<CourseEditionEnrollment> result = repository.findByStudentAndEdition(st2, ce1);

        // Assert
        assertFalse(result.isPresent(), "The result should be empty if the student is not enrolled in the course edition.");
    }

    //US17
    @Test
    void shouldReturnEmptyWhenStudentIsNotEnrolledInCourseEdition() throws Exception {
        // Arrange
        CourseFactory courseFactory = mock(CourseFactory.class);
        CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (factoryDouble);
        Student st1 = mock(Student.class);
        CourseEdition ce1 = mock(CourseEdition.class);

        // Act
        Optional<CourseEditionEnrollment> result = repository.findByStudentAndEdition(st1, ce1);

        // Assert
        assertFalse(result.isPresent(), "Expected no enrollment to be found");
    }


    // US24

    @Test
    void shouldReturnNumberOfStudentsEnrolledInCourseEdition() throws Exception {
        // Arrange
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository (doubleCeeFactory);

        // Create a course edition
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Create a student
        Student student1 = mock (Student.class);

        LocalDate currentDate = LocalDate.now();
        CourseEditionEnrollment cee1 = mock (CourseEditionEnrollment.class);
        when (doubleCeeFactory.createCourseEditionEnrollment(student1,courseEdition1,currentDate)).thenReturn(cee1);
        when (cee1.knowCourseEdition()).thenReturn(courseEdition1);
        repo.enrollStudentInACourseEdition(student1, courseEdition1, currentDate); // Add student to the repo

        // Act
        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(courseEdition1);

        // Assert
        assertEquals(1, studentsEnrolled);
    }

    @Test
    void ShouldReturnZeroWhenThereAreNoEnrolmentsInACourse() throws Exception {
        // Arrange
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository (doubleCeeFactory);

        // Create a course edition
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Create a second Course Edition with the same Professor, department, degree type, teacher category, and teacher
        CourseEdition courseEdition2 = mock (CourseEdition.class);

        // Create students
        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);

        CourseEditionEnrollment cee1 = mock(CourseEditionEnrollment.class);
        CourseEditionEnrollment cee2 = mock(CourseEditionEnrollment.class);

        LocalDate currentDate = LocalDate.now();
        when (doubleCeeFactory.createCourseEditionEnrollment(student1,courseEdition2,currentDate)).thenReturn(cee1);
        when (doubleCeeFactory.createCourseEditionEnrollment(student2,courseEdition2,currentDate)).thenReturn(cee2);

        when (cee1.knowCourseEdition()).thenReturn(courseEdition2);
        when (cee2.knowCourseEdition()).thenReturn(courseEdition2);

        repo.enrollStudentInACourseEdition(student1, courseEdition2, currentDate); // Add student to the repo
        repo.enrollStudentInACourseEdition(student2, courseEdition2, currentDate);

        // Act
        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(courseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }

    @Test
    void shouldReturnZeroWhenThereAreNoStudents() throws Exception {
        // Arrange
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository (doubleCeeFactory);

        // Create a course edition
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        // Act
        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(courseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }

    @Test
    void testNumberOfStudentsEnrolledInCourseEdition_NullCourseEdition_ShouldThrowException() {
        // Arrange
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            repository.numberOfStudentsEnrolledInCourseEdition(null);
        });

        // Assert
        assertEquals("Course edition cannot be null.", exception.getMessage());
    }

    //US28
    @Test
    public void removeExistingEnrollment() throws Exception {
        // arrange
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository (doubleCeeFactory);

        Student student = mock (Student.class);
        CourseEdition courseEdition = mock (CourseEdition.class);
        CourseEditionEnrollment cee1 = mock (CourseEditionEnrollment.class);

        // mock the factory method to simulate the creation of a CourseEditionEnrollment
        when (doubleCeeFactory.createCourseEditionEnrollment(student,courseEdition,LocalDate.now())).thenReturn(cee1);

        // mock CourseEditionEnrollment methods to return predefined values for testing
        when(cee1.findStudentInCourseEditionEnrollment()).thenReturn(student);
        when(cee1.findCourseEditionInEnrollment()).thenReturn(courseEdition);

        // act
        enrollmentRepository.enrollStudentInACourseEdition(student, courseEdition, LocalDate.now());
        boolean result = enrollmentRepository.removeEnrollment(student, courseEdition);

        // assert
        assertTrue(result, "Enrollment should be removed successfully.");
    }

    @Test
    public void removeNonExistingEnrollment() throws Exception {
        // arrange
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory);

        Student student = mock (Student.class);
        CourseEdition courseEdition = mock (CourseEdition.class);

        // act and assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.removeEnrollment(student, courseEdition);
        });

        // assert
        assertEquals("Enrollment does not exist.", exception.getMessage());
    }

    @Test
    void removeEnrollment_WithNullCourseEditionOrStudent_ShouldThrowException() throws Exception {
        // arrange
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory);

        Student st1 = mock (Student.class);
        CourseEdition ce1 = mock (CourseEdition.class);

        // act and assert
        // test for the case where Student is null
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.removeEnrollment(null, ce1);
        });
        assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());

        // test for the case where CourseEdition is null
        thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.removeEnrollment(st1, null);
        });
        assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());
    }

    @Test
    public void removeEnrollmentTwice_ShouldThrowExceptionOnSecondAttempt() throws Exception {
        // arrange
        CourseEditionEnrollmentFactory doubleCeeFactory = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory);

        Student student = mock (Student.class);
        CourseEdition courseEdition = mock (CourseEdition.class);
        CourseEditionEnrollment cee1 = mock (CourseEditionEnrollment.class);

        // mock the factory method to simulate the creation of a CourseEditionEnrollment
        when (doubleCeeFactory.createCourseEditionEnrollment(student,courseEdition,LocalDate.now())).thenReturn(cee1);

        // mock CourseEditionEnrollment methods to return predefined values for testing
        when(cee1.findStudentInCourseEditionEnrollment()).thenReturn(student);
        when(cee1.findCourseEditionInEnrollment()).thenReturn(courseEdition);

        repository.enrollStudentInACourseEdition(student, courseEdition, LocalDate.now());

        // act: remove enrollment the first time
        boolean firstRemoval = repository.removeEnrollment(student, courseEdition);

        // assert first removal
        assertTrue(firstRemoval, "The first removal should succeed.");

        // act and assert: try removing again and expect an exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            repository.removeEnrollment(student, courseEdition);
        });

        // assert second removal throws correct exception
        assertEquals("Enrollment does not exist.", exception.getMessage());
    }

    @Test
    void shouldEnrollStudentWhenNotAlreadyEnrolled() {
        // Arrange
        Student student = mock(Student.class);
        CourseEdition courseEdition2 = mock(CourseEdition.class);
        CourseEdition courseEdition3 = mock(CourseEdition.class);
        CourseEditionEnrollmentFactory doubleCeeFactory = mock(CourseEditionEnrollmentFactory.class);
        LocalDate currentDate = LocalDate.now();

        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository(doubleCeeFactory);

        CourseEditionEnrollmentRepository repoSpy = spy(repo);
        doReturn(Optional.empty()).when(repoSpy).findByStudentAndEdition(student, courseEdition2);
        doReturn(Optional.empty()).when(repoSpy).findByStudentAndEdition(student, courseEdition3);

        // Act
        repoSpy.enrollStudentInProgrammeCourseEditions(student, List.of(courseEdition2, courseEdition3));

        // Assert
        verify(repoSpy, times(1)).enrollStudentInACourseEdition(student, courseEdition2, currentDate);
        verify(repoSpy, times(1)).enrollStudentInACourseEdition(student, courseEdition3, currentDate);

    }

    @Test
    void shouldThrowExceptionWhenStudentAlreadyEnrolled() {
        // Arrange
        CourseEditionEnrollmentFactory factory = mock(CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository(factory);
        Student student = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        CourseEdition courseEdition2 = mock(CourseEdition.class);
        LocalDate currentDate = LocalDate.now();
        List<CourseEdition> courseEditions = List.of(courseEdition1, courseEdition2);

        CourseEditionEnrollment enrollment1 = mock (CourseEditionEnrollment.class);
        CourseEditionEnrollment enrollment2 = mock (CourseEditionEnrollment.class);
        when(factory.createCourseEditionEnrollment(student,courseEdition1,currentDate)).thenReturn(enrollment1);
        when(factory.createCourseEditionEnrollment(student,courseEdition2,currentDate)).thenReturn(enrollment2);
        when(enrollment1.findStudentInCourseEditionEnrollment()).thenReturn(student);
        when(enrollment2.findStudentInCourseEditionEnrollment()).thenReturn(student);
        when(enrollment1.findCourseEditionInEnrollment()).thenReturn(courseEdition1);
        when(enrollment2.findCourseEditionInEnrollment()).thenReturn(courseEdition2);
        //act

        repository.enrollStudentInProgrammeCourseEditions(student,courseEditions);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->{
                repository.enrollStudentInProgrammeCourseEditions(student,courseEditions);
        });
        //assert
        assertEquals("This course edition enrollment is already in the list.", exception.getMessage());
    }

    @Test
    void shouldReturnZeroWhenThereAreNoEnrollmentsInCourseEdition() throws Exception {
        // Arrange
        CourseEditionEnrollmentRepository repo = mock(CourseEditionEnrollmentRepository.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        when(repo.numberOfStudentsEnrolledInCourseEdition(courseEdition1)).thenReturn(0);

        // Act
        int studentsEnrolled = repo.numberOfStudentsEnrolledInCourseEdition(courseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }


}
