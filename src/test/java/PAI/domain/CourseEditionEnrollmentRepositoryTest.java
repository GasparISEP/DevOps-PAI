package PAI.domain;

import PAI.controller.US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionEnrollmentRepositoryTest {

    @Test
    void shouldReturnTrueWithAValidCourseEditionEnrollment () throws Exception {
        //arrange
        CourseEditionEnrollmentFactory ceeFactoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (ceeFactoryDouble);

        LocalDate currentDate = LocalDate.now();
        Student st1 = mock(Student.class);
        CourseEdition ce1 = mock(CourseEdition.class);
        CourseEditionEnrollment cee1 = mock(CourseEditionEnrollment.class);

        when (ceeFactoryDouble.createCourseEditionEnrollment(st1,ce1,currentDate)).thenReturn(cee1);

        //act
        boolean result = repository.enrollStudentInACourseEdition(st1,ce1,currentDate);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWithTwoValidCourseEditionEnrollments () throws Exception {
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
    void shouldReturnFalseWhenCourseEditionEnrollmentAlreadyExists() throws Exception {
        //arrange
        CourseEditionEnrollmentFactory doubleCeeFactory= mock (CourseEditionEnrollmentFactory.class);

        Student st1 = mock(Student.class);
        CourseEdition ce1 = mock(CourseEdition.class);
        LocalDate currentDate = LocalDate.now();
        CourseEditionEnrollment cee1 = mock(CourseEditionEnrollment.class);

        when (doubleCeeFactory.createCourseEditionEnrollment(st1,ce1,currentDate)).thenReturn(cee1);

        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (doubleCeeFactory);
        repository.enrollStudentInACourseEdition(st1,ce1,currentDate);

        //act
        boolean result2 = repository.enrollStudentInACourseEdition(st1,ce1,currentDate);

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

        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department d1 = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType dt1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher pd1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d1);
        CourseRepository courseRepository = new CourseRepository(courseFactory);
        Programme p1 = new Programme("SWITCH DEV","SDV",30,1,dt1,d1,pd1);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);

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
        CourseFactory courseFactory = mock(CourseFactory.class);
        CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository (factoryDouble);

        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department d1 = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType dt1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher pd1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d1);
        CourseRepository courseRepository = new CourseRepository(courseFactory);
        Programme p1 = new Programme("SWITCH DEV","SDV",30,1,dt1,d1,pd1);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);
        Student st2 = new Student(124,"Maria","223445679","256333444","124@gmail.com",add1);

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

        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department d1 = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType dt1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher pd1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d1);
        CourseRepository courseRepository = new CourseRepository(courseFactory);
        Programme p1 = new Programme("SWITCH DEV","SDV",30,1,dt1,d1,pd1);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);

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
}