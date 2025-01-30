package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionEnrollmentControllerTest {


    @Test
    public void shouldReturnFalseIfStudentIsNotInProgrammeEditionThatHasCourseEdition() throws Exception {

        //arrange
        CourseEditionEnrollmentRepository ceeRepository = new CourseEditionEnrollmentRepository();
        ProgrammeEditionEnrollmentRepo peeRepository = new ProgrammeEditionEnrollmentRepo();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();

        CourseEditionEnrollmentController controller = new CourseEditionEnrollmentController(
                ceeRepository, peeRepository, courseEditionRepository);

        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Student st2 = new Student(124,"John","223445667","222333444","124@gmail.com",add1);
        Department d1 = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType dt1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d1);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("SWITCH DEV","SDV",30,1,dt1,d1,teacher1, courseRepository);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);
        LocalDate enrollmentDate = LocalDate.now();
        LocalDate currentDate = LocalDate.now();
        peeRepository.enrollStudentInProgrammeEdition(st1,pe1,currentDate);
        courseEditionRepository.createCourseEdition(c1,pe1);

        //act
        boolean result = controller.enrollStudentInCourseEdition(st2, ce1, enrollmentDate);

        //assert
        assertFalse(result);
    }


    @Test
    void shouldReturnTrueIfIsAValidCourseEditionEnrollment () throws Exception {

        //arrange
        CourseEditionEnrollmentRepository ceeRepository = new CourseEditionEnrollmentRepository();
        ProgrammeEditionEnrollmentRepo peeRepository = new ProgrammeEditionEnrollmentRepo();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();

        CourseEditionEnrollmentController controller = new CourseEditionEnrollmentController(
                ceeRepository, peeRepository, courseEditionRepository);


        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(123,"John","223445667","222333444","123@gmail.com",add1);
        Student st2 = new Student(124,"Marie","223445679","221133444","124@gmail.com",add1);
        Department d1 = new Department("DCE","Department of Computer Engineering");
        SchoolYear sy1 = new SchoolYear("2024/2025","14-10-2024","30-06-2025");
        DegreeType dt1 = new DegreeType("Master",30);
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d1);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("SWITCH DEV","SDV",30,1,dt1,d1,teacher1, courseRepository);
        Course c1 = new Course("Development","DEV",5,1);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1,sy1);
        CourseEdition ce1 = new CourseEdition(c1,pe1);
        LocalDate currentDate = LocalDate.now();
        LocalDate enrollmentDate = LocalDate.now();
        peeRepository.enrollStudentInProgrammeEdition(st1,pe1,currentDate);
        courseEditionRepository.createCourseEdition(c1,pe1);
        ceeRepository.enrollStudentInACourseEdition(st2,ce1, enrollmentDate);


        //act
        boolean result = controller.enrollStudentInCourseEdition(st1,ce1,currentDate);

        //assert
        assertTrue(result);
    }
}