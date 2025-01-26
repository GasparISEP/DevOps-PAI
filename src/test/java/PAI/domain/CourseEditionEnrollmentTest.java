package PAI.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CourseEditionEnrollmentTest {

    @Test
    void should_return_a_valid_course_edition_enrollment() throws Exception {
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

        //act + assert
        CourseEditionEnrollment cee1 = new CourseEditionEnrollment(st1, ce1, currentDate);
    }

    @Test
    void everythingNullGenerateException() throws Exception {
        //arrange
        //act + assert
        assertThrows(Exception.class, () -> new CourseEditionEnrollment(null, null, null));
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
        // Act + Assert
        Exception exception = assertThrows(Exception.class, () -> {
            new CourseEditionEnrollment(st1, ce1, enrollmentDate);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testCourseEditionEnrollmentWhenStudentIsNull() throws Exception {
        // Arrange
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Course c1 = new Course("c1", "CC", 30, 2);
        SchoolYear sy1 = new SchoolYear("ola", "20-01-2024", "23-02-2024");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("JSM", "John Smith", "jsm@isep.ipp.pt", "123456789", "B180","PhD","Rua do Caminho","4554-565","Porto","Portugal","10-10-2024",assistantProfessor,50,CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        CourseEdition ce1 = new CourseEdition(c1, pe1);
        LocalDate enrollmentDate = LocalDate.now();

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrollment(null, ce1, enrollmentDate);
        });
        assertEquals("Student cannot be null!", exception.getMessage());
    }

    @Test
    void testCourseEditionEnrollmentWhenCourseEditionIsNull() throws Exception {
        // Arrange
        Student student = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", null);
        LocalDate enrollmentDate = LocalDate.now();

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrollment(student, null, enrollmentDate);
        });
        assertEquals("Course edition cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsNull_EqualsMethod() throws Exception {
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
        CourseEditionEnrollment enrollment1 = new CourseEditionEnrollment(st1, ce1, currentDate);

        //assert
        assertFalse(enrollment1.equals(null));
    }

    @Test
    void shouldReturnTrueIfCourseEditionIsEqualThis_EqualsMethod() throws Exception {
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
        CourseEditionEnrollment enrollment1 = new CourseEditionEnrollment(st1, ce1, currentDate);

        //assert
        assertTrue(enrollment1.equals(enrollment1));
    }
}