
package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class US23_IWantToKnowTheApprovalPercentageOfACourseEditionTest {

    @Test
    void gradeStudentInRepository() {
        //arrange
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        GradeStudentRepository gradeStudentRepository = new GradeStudentRepository(courseEditionRepository);

        //act
        US23_IWantToKnowTheApprovalPercentageOfACourseEdition approval1 = new US23_IWantToKnowTheApprovalPercentageOfACourseEdition(gradeStudentRepository);

        //assert
        assertNotNull(approval1);
    }


    @Test
    void approvalRateInACourseEdition () throws Exception {
        //arrange
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        GradeStudentRepository gradeStudentRepository = new GradeStudentRepository(courseEditionRepository);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository();


        //act
        US23_IWantToKnowTheApprovalPercentageOfACourseEdition controlador1 = new US23_IWantToKnowTheApprovalPercentageOfACourseEdition(gradeStudentRepository);

        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);
        Course c1 = new Course("Informatics", "INF", 6, 1);
        Course c2 = new Course("Science", "SCI", 6, 1);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Programme p2 = new Programme("Computer Science", "CES", 20, 6, master, CSE, teacher);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        SchoolYear sY2 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        ProgrammeEdition pE2 = new ProgrammeEdition(p2, sY2);
        LocalDate currentDate = LocalDate.now();

        CourseEdition courseEdition1 = new CourseEdition(c1, pE1);

        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        Student student2 = new Student(2, "João", "123456786", "963741258", "joao@gmail.com", address1);

        courseEditionRepository.createCourseEdition(c1, pE1);
        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1,currentDate);
        enrollmentRepository.enrollStudentInACourseEdition(student2, courseEdition1,currentDate);

        gradeStudentRepository.addGradeToStudent(8, "10/10/2025", student1, courseEdition1,enrollmentRepository);
        gradeStudentRepository.addGradeToStudent(20, "10/10/2025", student2, courseEdition1,enrollmentRepository);


        double optC1 = controlador1.IWantToKnowTheApprovalPercentageOfACourseEdition(courseEdition1);

        //assert
        assertEquals(50.0,optC1,0.01);
    }

}

