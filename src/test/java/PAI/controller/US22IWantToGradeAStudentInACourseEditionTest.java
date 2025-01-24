package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class US22IWantToGradeAStudentInACourseEditionTest {
    @Test
    void gradeStudentInRepository () {
        //arrange
        CourseEditionRepository courseEditionRepository1 = new CourseEditionRepository();
        GradeStudentRepository gradeStudentRepository1 = new GradeStudentRepository(courseEditionRepository1);
        //act
        US22_IWantToGradeAStudentInACourseEdition G1 = new US22_IWantToGradeAStudentInACourseEdition(gradeStudentRepository1);
        //assert
        assertNotNull(G1);
    }

    @Test
    void iWantToGradeAStudentInACourseEdition () throws Exception {
        //arrange

        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        GradeStudentRepository gradeStudentRepository1 = new GradeStudentRepository(courseEditionRepository);
        //act
        US22_IWantToGradeAStudentInACourseEdition controller1 = new US22_IWantToGradeAStudentInACourseEdition(gradeStudentRepository1);

        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);
        Course c1 = new Course("Informatics", "INF", 6, 1);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1, pE1);
        Student student1 = new Student(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
        courseEditionRepository.createCourseEdition(c1, pE1);
        gradeStudentRepository1.addGradeToStudent(20.0,"12-02-2024",student1,courseEdition1);

        Optional<GradeStudent> optc1 = controller1.iWantToGradeAStudent(20,"20/11/2024",student1,courseEdition1);

        //assert

        assertTrue(optc1.isPresent());
    }

}