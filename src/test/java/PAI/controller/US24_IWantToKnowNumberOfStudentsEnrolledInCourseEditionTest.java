package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionTest {

    @Test
    void checksIfControllerInitializes() {

        //arrange
        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository();

        //act
        new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition(repo);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEdition() throws Exception {

        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository();
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition(repo);

        //create a course edition
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master, CSE, teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1,pE1);

        //create a student
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        //create an enrollment
        LocalDate currentDate = LocalDate.now();
        repo.enrollStudentInACourseEdition(student1, courseEdition1, currentDate); //add student to the repo

        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEdition1);

        assertEquals(1, studentsEnrolled);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEditionForMoreThan1Student() throws Exception {

        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository();
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition(repo);

        //create a course edition
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6, master, CSE, teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1,pE1);

        //create a student
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = new Address("Avenida Principal, nº10", "1000-001", "Lisboa", "Portugal");
        Student student2 = new Student(12345, "Paula", "222333444", "910000000", "paula@gmail.com", address2);

        //create an enrollment
        LocalDate currentDate = LocalDate.now();
        repo.enrollStudentInACourseEdition(student1, courseEdition1, currentDate); //add student to the repo
        repo.enrollStudentInACourseEdition(student2, courseEdition1, currentDate);

        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEdition1);

        assertEquals(2, studentsEnrolled);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEditionWhenCourseEditionMoreThanOneAndStudentMoreThanOne() throws Exception {

        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository();
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition(repo);

        //create a course edition
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6, master, CSE, teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1,pE1);

        //create a second Course Edition with the same Professor, department, degreetype, teachercategory and teacher
        Programme p2 = new Programme("Computer Science", "CS", 20,6, master, CSE, teacher);
        Course c2 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2024");
        ProgrammeEdition pE2 = new ProgrammeEdition (p2, sY2);
        CourseEdition courseEdition2 = new CourseEdition(c2,pE2);

        //create a student
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = new Address("Avenida Principal, nº10", "1000-001", "Lisboa", "Portugal");
        Student student2 = new Student(12345, "Paula", "222333444", "910000000", "paula@gmail.com", address2);

        //create an enrollment
        LocalDate currentDate = LocalDate.now();
        repo.enrollStudentInACourseEdition(student1, courseEdition1, currentDate); //add student to the repo
        repo.enrollStudentInACourseEdition(student2, courseEdition2, currentDate);

        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEdition1);

        assertEquals(1, studentsEnrolled);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEditionWhenCourseEditionMoreThanOneAndStudentsOnlyEnrolledInOneCourseEdition() throws Exception {

        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository();
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition(repo);

        //create a course edition
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6, master, CSE, teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1,pE1);

        //create a second Course Edition with the same Professor, department, degreetype, teachercategory and teacher
        Programme p2 = new Programme("Computer Science", "CS", 20,6, master, CSE, teacher);
        Course c2 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2024");
        ProgrammeEdition pE2 = new ProgrammeEdition (p2, sY2);
        CourseEdition courseEdition2 = new CourseEdition(c2,pE2);

        //create a student
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = new Address("Avenida Principal, nº10", "1000-001", "Lisboa", "Portugal");
        Student student2 = new Student(12345, "Paula", "222333444", "910000000", "paula@gmail.com", address2);

        //create an enrollment
        LocalDate currentDate = LocalDate.now();
        repo.enrollStudentInACourseEdition(student1, courseEdition2, currentDate); //add student to the repo
        repo.enrollStudentInACourseEdition(student2, courseEdition2, currentDate);

        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEdition2);

        assertEquals(2, studentsEnrolled);
    }

    @Test
    void IWantToKnowNumberOfStudentsEnrolledInCourseEditionWhenCourseEditionDoesNotHaveStudentsEnrolled() throws Exception {

        CourseEditionEnrollmentRepository repo = new CourseEditionEnrollmentRepository();
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition(repo);

        //create a course edition
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6, master, CSE, teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1,pE1);

        //create a second Course Edition with the same Professor, department, degreetype, teachercategory and teacher
        Programme p2 = new Programme("Computer Science", "CS", 20,6, master, CSE, teacher);
        Course c2 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2024");
        ProgrammeEdition pE2 = new ProgrammeEdition (p2, sY2);
        CourseEdition courseEdition2 = new CourseEdition(c2,pE2);

        //create a student
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = new Address("Avenida Principal, nº10", "1000-001", "Lisboa", "Portugal");
        Student student2 = new Student(12345, "Paula", "222333444", "910000000", "paula@gmail.com", address2);

        //create an enrollment
        LocalDate currentDate = LocalDate.now();
        repo.enrollStudentInACourseEdition(student1, courseEdition2, currentDate); //add student to the repo
        repo.enrollStudentInACourseEdition(student2, courseEdition2, currentDate);

        int studentsEnrolled = controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(courseEdition1);

        assertEquals(0, studentsEnrolled);
    }

    @Test
    void testIWantToKnowNumberOfStudentsEnrolledInCourseEdition_NullCourseEdition_ShouldThrowException() {
        // Arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition controller = new US24_IWantToKnowNumberOfStudentsEnrolledInCourseEdition(repository);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.IWantToKnowNumberOfStudentsEnrolledInCourseEdition(null);
        });

        assertEquals("CourseEdition cannot be null", exception.getMessage());
    }
}