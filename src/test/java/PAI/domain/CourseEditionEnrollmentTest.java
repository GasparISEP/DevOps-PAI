package PAI.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;

class CourseEditionEnrollmentTest {

    @Test
    void should_return_a_valid_course_edition_enrollment() throws Exception {
        //arrange
        CourseEditionEnrollmentFactory factory = new CourseEditionEnrollmentFactory();
        Student student = mock(Student.class);
        CourseEdition courseEdition = mock(CourseEdition.class);
        LocalDate enrollmentDate = LocalDate.now();

        //act + assert
        CourseEditionEnrollment enrollment = factory.createCourseEditionEnrollment(student, courseEdition, enrollmentDate);
    }

    //testing invalid enrollment date
    public static Stream<Arguments> provideInvalidEnrollmentDate() {
        return Stream.of(
                arguments(null, "Enrollment date cannot be null!"),
                arguments(LocalDate.of(2024, 12, 29), "Enrollment date must be the current day!"),
                arguments(LocalDate.of(2026, 1, 23), "Enrollment date must be the current day!")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidEnrollmentDate")
    void testInvalidEnrollmentDate(LocalDate enrollmentDate, String expectedMessage) throws Exception {
        // Arrange
        CourseEditionEnrollmentFactory factory = new CourseEditionEnrollmentFactory();
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);

        // Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            factory.createCourseEditionEnrollment(studentDouble, courseEditionDouble, enrollmentDate);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testCourseEditionEnrollmentWhenStudentIsNull() throws Exception {
        // Arrange
        CourseEditionEnrollmentFactory factory = new CourseEditionEnrollmentFactory();
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        LocalDate enrollmentDate = LocalDate.now();

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createCourseEditionEnrollment(null, courseEditionDouble, enrollmentDate);
        });
        assertEquals("Student cannot be null!", exception.getMessage());
    }

    @Test
    void testCourseEditionEnrollmentWhenCourseEditionIsNull() throws Exception {
        // Arrange
        CourseEditionEnrollmentFactory factory = new CourseEditionEnrollmentFactory();
        Student studentDouble = mock(Student.class);
        LocalDate enrollmentDate = LocalDate.now();

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createCourseEditionEnrollment(studentDouble, null, enrollmentDate);
        });
        assertEquals("Course edition cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsNull_EqualsMethod() throws Exception {
        //arrange
        CourseEditionEnrollmentFactory factory = new CourseEditionEnrollmentFactory();
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        LocalDate enrollmentDate = LocalDate.now();

        //act
        CourseEditionEnrollment enrollment = factory.createCourseEditionEnrollment(studentDouble, courseEditionDouble, enrollmentDate);

        //assert
        assertFalse(enrollment.equals(null));
    }

    @Test
    void shouldReturnTrueIfCourseEditionIsEqualThis_EqualsMethod() throws Exception {
        //arrange
        CourseEditionEnrollmentFactory factory = new CourseEditionEnrollmentFactory();
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        LocalDate enrollmentDate = LocalDate.now();

        //act
        CourseEditionEnrollment enrollment = factory.createCourseEditionEnrollment(studentDouble, courseEditionDouble, enrollmentDate);

        //assert
        assertTrue(enrollment.equals(enrollment));
    }

    //US17
    @Test
    void shouldReturnCourseEditionFromEnrollment() throws Exception {
        // Arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Course course = new Course("Informatica", "INF", 6, 1);
        SchoolYear schoolYear = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        CourseEdition courseEdition = new CourseEdition(course, programmeEdition);
        LocalDate currentDate = LocalDate.now();

        Student st1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        CourseEditionEnrollment enrollment = new CourseEditionEnrollment(st1, courseEdition,currentDate);

        // Act
        CourseEdition result = enrollment.findCourseEditionInEnrollment();

        // Assert
        assertEquals(courseEdition, result);
    }

    @Test
    void shouldReturnStudentInCourseEditionEnrollment() throws Exception {
        // Arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        Course c1 = new Course("c1", "CC", 30, 2);
        SchoolYear sy1 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        CourseEdition ce1 = new CourseEdition(c1, pe1);
        LocalDate currentDate = LocalDate.now();
        CourseEditionEnrollment enrollment1 = new CourseEditionEnrollment(st1, ce1, currentDate);

        // Act
        Student result = enrollment1.findStudentInCourseEditionEnrollment();

        // Assert
        assertEquals(st1, result);
    }

    @Test
    void should_return_a_valid_Student() throws Exception {
        //arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        Course c1 = new Course("c1", "CC", 30, 2);
        SchoolYear sy1 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("JSM", "John Smith", "jsm@isep.ipp.pt", "123456789", "B180","PhD","Rua do Caminho","4554-565","Porto","Portugal","10-10-2024",assistantProfessor,50,CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        CourseEdition ce1 = new CourseEdition(c1, pe1);
        LocalDate currentDate = LocalDate.now();

        //act
        CourseEditionEnrollment cee1 = new CourseEditionEnrollment(st1, ce1, currentDate);

        Object returnedStudent = cee1.knowStudent();

        //assert
        assertEquals(st1, returnedStudent);

    }

    @Test
    void should_return_a_valid_course_edition() throws Exception {
        //arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        Course c1 = new Course("c1", "CC", 30, 2);
        SchoolYear sy1 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("JSM", "John Smith", "jsm@isep.ipp.pt", "123456789", "B180","PhD","Rua do Caminho","4554-565","Porto","Portugal","10-10-2024",assistantProfessor,50,CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        CourseEdition ce1 = new CourseEdition(c1, pe1);
        LocalDate currentDate = LocalDate.now();

        //act
        CourseEditionEnrollment cee1 = new CourseEditionEnrollment(st1, ce1, currentDate);

        Object returnedCourseEdition = cee1.knowCourseEdition();
        //assert
        assertEquals(ce1, returnedCourseEdition);
    }

    //US17
    @Test
    void shouldReturnStudentInCourseEdition() throws Exception {
        // Arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        Course c1 = new Course("c1", "CC", 30, 2);
        SchoolYear sy1 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        CourseEdition ce1 = new CourseEdition(c1, pe1);
        LocalDate currentDate = LocalDate.now();
        CourseEditionEnrollment enrollment1 = new CourseEditionEnrollment(st1, ce1, currentDate);

        // Act
        Student result = enrollment1.findStudentInCourseEditionEnrollment();

        // Assert
        assertEquals(st1, result);
    }

    @Test
    void shouldReturnTrueIfAllFieldsAreEqual_EqualsMethod() throws Exception {
        // Arrange
        CourseEditionEnrollmentFactory factory = new CourseEditionEnrollmentFactory();
        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        LocalDate enrollmentDate = LocalDate.now();

        // Act
        CourseEditionEnrollment enrollment1 = factory.createCourseEditionEnrollment(studentDouble, courseEditionDouble, enrollmentDate);
        CourseEditionEnrollment enrollment2 = factory.createCourseEditionEnrollment(studentDouble, courseEditionDouble, enrollmentDate);

        // Assert
        assertTrue(enrollment1.equals(enrollment2));
    }
}