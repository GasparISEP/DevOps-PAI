package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionTest {

    @Test
    void shouldCreateCourseEdition() throws Exception {
        //Arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        //act
        CourseEdition courseEdition1 = new CourseEdition(c1,pE1);
        //Assert
        assertNotNull(courseEdition1);

    }

    @Test
    void shouldNotCreateCourseEditionIfCourseIsNull() throws Exception {
        //Arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        //Assert
        assertThrows(Exception.class, () -> {new CourseEdition(null, pE1);});

    }

    @Test
    void shouldNotCreateCourseEditionIfProgrammeEditionIsNull() throws Exception {
        //Arrange
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        //Assert
        assertThrows(Exception.class, () -> {new CourseEdition(c1, null);});

    }

    @Test
    void shouldReturnTrueIfCourseEditionEqualsObject()throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor,100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEdition cE1 = new CourseEdition(c1,pE1);
        Object cE2 = cE1;
        //act
        boolean result = cE1.equals(cE2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectIsNotCourseEdition() throws Exception{
        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEdition cE1 = new CourseEdition(c1,pE1);
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        //act
        boolean result = cE1.equals(tc1);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfCourseAndProgrammeEditionOfBothObjectsAreEqual() throws Exception{
        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor,100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEdition cE1 = new CourseEdition(c1,pE1);
        CourseEdition cE2 = new CourseEdition(c1,pE1);

        //act
        boolean result = cE1.equals(cE2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseAreNotEqualButProgrammeEditionsAreEqual() throws Exception{
        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEdition cE1 = new CourseEdition(c1,pE1);

        Course c2 = new Course ("Matemática", "MAT", 6, 1);
        CourseEdition cE2 = new CourseEdition(c2,pE1);

        //act
        boolean result = cE1.equals(cE2);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCoursesAreEqualButProgrammeEditionsAreNotEqual() throws Exception{
        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 100,CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY2 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2024");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        ProgrammeEdition pE2 = new ProgrammeEdition (p1, sY2);
        CourseEdition cE1 = new CourseEdition(c1,pE1);

        CourseEdition cE2 = new CourseEdition(c1,pE2);

        //act
        boolean result = cE1.equals(cE2);
        //assert
        assertFalse(result);
    }


}