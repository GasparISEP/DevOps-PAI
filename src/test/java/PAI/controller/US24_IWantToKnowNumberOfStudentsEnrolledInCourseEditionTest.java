package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionTest {

    @Test
    void checksIfControllerInitializes() {
        // Arrange
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository();

        // Act
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(repo);

        // Assert
        // Check if the controller is properly initialized
        assertNotNull(controller);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEdition() throws Exception {
        // Arrange
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository();
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(repo);

        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6, master, CSE, teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1, pE1);

        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        // Act
        LocalDate currentDate = LocalDate.now();
        repo.enrollStudentInACourseEdition(student1, courseEdition1, currentDate); //add student to the repo

        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEdition1);

        // Assert
        assertEquals(1, studentsEnrolled);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEditionForMoreThan1Student() throws Exception {
        // Arrange
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository();
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(repo);

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6, master, CSE, teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1,pE1);

        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = new Address("Avenida Principal, nº10", "1000-001", "Lisboa", "Portugal");
        Student student2 = new Student(12345, "Paula", "222333444", "910000000", "paula@gmail.com", address2);

        // Act
        LocalDate currentDate = LocalDate.now();
        repo.enrollStudentInACourseEdition(student1, courseEdition1, currentDate); //add student to the repo
        repo.enrollStudentInACourseEdition(student2, courseEdition1, currentDate);

        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEdition1);

        // Assert
        assertEquals(2, studentsEnrolled);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEditionWhenCourseEditionMoreThanOneAndStudentMoreThanOne() throws Exception {
        // Arrange
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository();
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(repo);

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6, master, CSE, teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1,pE1);

        Programme p2 = new Programme("Computer Science", "CS", 20,6, master, CSE, teacher);
        Course c2 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2024");
        ProgrammeEdition pE2 = new ProgrammeEdition (p2, sY2);
        CourseEdition courseEdition2 = new CourseEdition(c2,pE2);

        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = new Address("Avenida Principal, nº10", "1000-001", "Lisboa", "Portugal");
        Student student2 = new Student(12345, "Paula", "222333444", "910000000", "paula@gmail.com", address2);

        // Act
        LocalDate currentDate = LocalDate.now();
        repo.enrollStudentInACourseEdition(student1, courseEdition1, currentDate); //add student to the repo
        repo.enrollStudentInACourseEdition(student2, courseEdition2, currentDate);

        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEdition1);

        // Assert
        assertEquals(1, studentsEnrolled);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEditionWhenCourseEditionMoreThanOneAndStudentsOnlyEnrolledInOneCourseEdition() throws Exception {
        // Arrange
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository();
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(repo);

        // Create Course Edition 1
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Course c1 = new Course("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1, pE1);

        // Create Course Edition 2 (same teacher, department, etc.)
        Programme p2 = new Programme("Computer Science", "CS", 20, 6, master, CSE, teacher);
        Course c2 = new Course("Informatics", "INF", 6, 1);
        SchoolYear sY2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2024");
        ProgrammeEdition pE2 = new ProgrammeEdition(p2, sY2);
        CourseEdition courseEdition2 = new CourseEdition(c2, pE2);

        // Create students
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = new Address("Avenida Principal, nº10", "1000-001", "Lisboa", "Portugal");
        Student student2 = new Student(12345, "Paula", "222333444", "910000000", "paula@gmail.com", address2);

        // Enroll students in Course Edition 2
        LocalDate currentDate = LocalDate.now();
        repo.enrollStudentInACourseEdition(student1, courseEdition2, currentDate); // Enroll student 1
        repo.enrollStudentInACourseEdition(student2, courseEdition2, currentDate); // Enroll student 2

        // Act
        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEdition2);

        // Assert
        assertEquals(2, studentsEnrolled);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEditionWhenCourseEditionDoesNotHaveStudentsEnrolled() throws Exception {
        // Arrange
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository();
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(repo);

        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6, master, CSE, teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1,pE1);

        Programme p2 = new Programme("Computer Science", "CS", 20,6, master, CSE, teacher);
        Course c2 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2024");
        ProgrammeEdition pE2 = new ProgrammeEdition (p2, sY2);
        CourseEdition courseEdition2 = new CourseEdition(c2,pE2);

        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = new Address("Avenida Principal, nº10", "1000-001", "Lisboa", "Portugal");
        Student student2 = new Student(12345, "Paula", "222333444", "910000000", "paula@gmail.com", address2);

        // Act
        LocalDate currentDate = LocalDate.now();
        repo.enrollStudentInACourseEdition(student1, courseEdition2, currentDate); //add student to the repo
        repo.enrollStudentInACourseEdition(student2, courseEdition2, currentDate);

        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEdition1);

        // Assert
        assertEquals(0, studentsEnrolled);
    }

    @Test
    void testIWantToKnowNumberOfStudentsEnrolledInCourseEdition_NullCourseEdition_ShouldThrowException() {
        // Arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(repository);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(null);
        });

        // Assert
        assertEquals("Course edition cannot be null.", exception.getMessage());
    }
}