
package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US22_IWantToGradeAStudentInACourseEditionTest {
    @Test
    void gradeStudentInRepository () {
        //arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentRepository gradeStudentRepository1 = new GradeStudentRepository(gradeStudentFactory);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository1 = new CourseEditionEnrollmentRepository();
        //act
        US22_IWantToGradeAStudentInACourseEdition G1 = new US22_IWantToGradeAStudentInACourseEdition(gradeStudentRepository1,courseEditionEnrollmentRepository1);
        //assert
        assertNotNull(G1);
    }

    @Test
    void iWantToGradeAStudentInACourseEdition () throws Exception {
        //arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentRepository gradeStudentRepository1 = new GradeStudentRepository(gradeStudentFactory);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository();


        //act
        US22_IWantToGradeAStudentInACourseEdition controller1 = new US22_IWantToGradeAStudentInACourseEdition(gradeStudentRepository1,enrollmentRepository);

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
        LocalDate currentDate = LocalDate.now();

        GradeStudent gradeStudent1 = mock(GradeStudent.class);

        when(gradeStudentFactory.newGradeStudent(20, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);

        when(gradeStudent1.knowGrade()).thenReturn(20.0);

        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);

        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1,currentDate);

        Optional<GradeStudent> optc1 = controller1.iWantToGradeAStudent(20,"10-10-2025",student1,courseEdition1);

        //assert

        assertTrue(optc1.isPresent());
    }

    @Test
    void iWantToCheckIfStudentIsEnrolledInCourseEdition () throws Exception {
        //arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentRepository gradeStudentRepository1 = new GradeStudentRepository(gradeStudentFactory);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository();

        //act
        US22_IWantToGradeAStudentInACourseEdition controller1 = new US22_IWantToGradeAStudentInACourseEdition(gradeStudentRepository1,enrollmentRepository);

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
        LocalDate currentDate = LocalDate.now();

        GradeStudent gradeStudent1 = mock(GradeStudent.class);


        when(gradeStudentFactory.newGradeStudent(8, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);

        when(gradeStudent1.knowGrade()).thenReturn(8.0);

        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1,currentDate);

       Boolean result = controller1.isStudentEnrolledInCourseEdition(student1,courseEdition1);

        //assert

        assertTrue(result);
    }

    @Test
    void iWantToCheckIfStudentIsNotEnrolledInCourseEdition () throws Exception {
        //arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentRepository gradeStudentRepository1 = new GradeStudentRepository(gradeStudentFactory);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository();

        //act
        US22_IWantToGradeAStudentInACourseEdition controller1 = new US22_IWantToGradeAStudentInACourseEdition(gradeStudentRepository1,enrollmentRepository);

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
        Student student2 = new Student(2, "João", "123456788", "963741258", "joao@gmail.com", address1);

        LocalDate currentDate = LocalDate.now();

        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1,currentDate);

        GradeStudent gradeStudent1 = mock(GradeStudent.class);
        GradeStudent gradeStudent2 = mock(GradeStudent.class);


        when(gradeStudentFactory.newGradeStudent(8, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);
        when(gradeStudentFactory.newGradeStudent(20, "10-10-2025", student2, courseEdition1)).thenReturn(gradeStudent2);

        when(gradeStudent1.knowGrade()).thenReturn(8.0);
        when(gradeStudent2.knowGrade()).thenReturn(20.0);

        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(gradeStudent2.hasThisCourseEdition(courseEdition1)).thenReturn(true);

        Boolean result = controller1.isStudentEnrolledInCourseEdition(student2,courseEdition1);

        //assert

        assertFalse(result);
    }


    @Test
    void iWantoToGradeAStudentInCourseEditionNotEnrolledInCourseEdition () throws Exception {
        //arrange

        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentRepository gradeStudentRepository1 = new GradeStudentRepository(gradeStudentFactory);
        CourseEditionEnrollmentRepository enrollmentRepository = new CourseEditionEnrollmentRepository();

        //act
        US22_IWantToGradeAStudentInACourseEdition controller1 = new US22_IWantToGradeAStudentInACourseEdition(gradeStudentRepository1,enrollmentRepository);

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
        Student student2 = new Student(2, "João", "123456788", "963741258", "joao@gmail.com", address1);

        LocalDate currentDate = LocalDate.now();

        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1,currentDate);

        GradeStudent gradeStudent1 = mock(GradeStudent.class);


        when(gradeStudentFactory.newGradeStudent(20, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);


        when(gradeStudent1.knowGrade()).thenReturn(20.0);


        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);


        Optional <GradeStudent> opt1 = controller1.iWantToGradeAStudent(20,"20/11/2024",student2,courseEdition1);

        //assert

        assertTrue(opt1.isEmpty());
    }



    @Test
    void iWantToGradeAStudentInACourseEditionWithNullGradeStudentRepo () throws Exception {
        //arrange

        GradeStudentRepository gradeStudentRepository1 = null;
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository1 = new CourseEditionEnrollmentRepository();


        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new US22_IWantToGradeAStudentInACourseEdition(gradeStudentRepository1, courseEditionEnrollmentRepository1);
        });
        assertEquals("Cannot be null", exception.getMessage());

    }

    @Test
    void iWantToGradeAStudentInACourseEditionWithNullCourseEditionEnrollRepo () throws Exception {
        //arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentRepository gradeStudentRepository1 = new GradeStudentRepository(gradeStudentFactory);
        CourseEditionEnrollmentRepository courseEditionEnrollmentRepository1 = null;


        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new US22_IWantToGradeAStudentInACourseEdition(gradeStudentRepository1, courseEditionEnrollmentRepository1);
        });
        assertEquals("Cannot be null", exception.getMessage());

    }
}





