package PAI.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionEnrollmentRepositoryTest {


    @Test
    void shouldReturnAValidCourseEditionEnrollment () throws Exception {
        //arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department CSE = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType master = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("JSM", "John Smith", "jsm@isep.ipp.pt", "123456789", "B180","PhD","Rua do Caminho","4554-565","Porto","Portugal","10-10-2024",assistantProfessor,50,CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);
        LocalDate currentDate = LocalDate.now();

        //act
        Optional<CourseEditionEnrollment> result = repository.enrollStudentInACourseEdition(st1,ce1,currentDate);

        //assert
        assertTrue(result.isPresent(), "The student was enrolled in a course edition sucessfully.");
    }

    @Test
    void shouldReturnATwoValidCourseEditionEnrollments () throws Exception {
        //arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department CSE = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType master = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("JSM", "John Smith", "jsm@isep.ipp.pt", "123456789", "B180","PhD","Rua do Caminho","4554-565","Porto","Portugal","10-10-2024",assistantProfessor,50,CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);
        LocalDate currentDate = LocalDate.now();

        Address add2 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st2 = new Student(123,"John","223445667","222333444","123@gmail.com",add2);
        Department CSE1 = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy2 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType master1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor1 = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("JSM", "John Smith", "jsm@isep.ipp.pt", "123456789", "B180","PhD","Rua do Caminho","4554-565","Porto","Portugal","10-10-2024",assistantProfessor1,50,CSE1);
        Programme p2 = new Programme("Computer Engineering", "CE", 20, 6, master1, CSE1, teacher1);
        Course c2 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe2 = new ProgrammeEdition(p2,sy2);
        CourseEdition ce2 = new CourseEdition(c2,pe2);
        LocalDate currentDate1 = LocalDate.now();

        //act
        Optional<CourseEditionEnrollment> result1 = repository.enrollStudentInACourseEdition(st1,ce1,currentDate);
        Optional<CourseEditionEnrollment> result2 = repository.enrollStudentInACourseEdition(st2,ce2,currentDate1);


        //assert
        assertTrue(result1.isPresent(), "The student was enrolled in a course edition sucessfully.");
        assertTrue(result2.isPresent(), "The student was enrolled in a course edition sucessfully.");

    }

    @Test
    void shouldReturnAnExceptionWhenCourseEditionEnrollmentAlreadyExists() throws Exception {
        //arrange
        CourseEditionEnrollmentRepository repository = new CourseEditionEnrollmentRepository();
        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Department CSE = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType master = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("JSM", "John Smith", "jsm@isep.ipp.pt", "123456789", "B180","PhD","Rua do Caminho","4554-565","Porto","Portugal","10-10-2024",assistantProfessor,50,CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);
        LocalDate currentDate = LocalDate.now();

        //act
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            repository.enrollStudentInACourseEdition(st1, ce1, currentDate);
            repository.enrollStudentInACourseEdition(st1, ce1, currentDate);
        });

        //assert
        assertEquals("This course edition enrollment is already in the list.", thrown.getMessage());
    }

}