package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseEditionRepositoryTest {

    @Test
    void shouldReturnTrueIfCourseEditionHasBeenCreated() throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal","20-12-2010",assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEditionRepository courseEditionRepository1 = new CourseEditionRepository();
        //act
        boolean result = courseEditionRepository1.createCourseEdition(c1,pE1);
        //assert
        assertTrue(result);

    }

    @Test
    void shouldReturnTrueIfTheAddedCourseEditionHasDifferentCourseButTheSameProgrammeEdition() throws Exception {
        //Arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal","20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Course c1 = new Course ("Informatica", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);

        Course c2 = new Course ("Matemática", "MAT", 6, 1);
        CourseEditionRepository courseEditionRepository1 = new CourseEditionRepository();
        courseEditionRepository1.createCourseEdition(c1,pE1);
        //act
        boolean result = courseEditionRepository1.createCourseEdition(c2,pE1);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfTheAddedCourseEditionHasSameCourseButDifferentProgrammeEdition() throws Exception {
        //Arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal","20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY2 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2023", "09-12-2024");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        ProgrammeEdition pE2 = new ProgrammeEdition (p1, sY2);

        CourseEditionRepository courseEditionRepository1 = new CourseEditionRepository();
        courseEditionRepository1.createCourseEdition(c1,pE1);
        //act
        boolean result = courseEditionRepository1.createCourseEdition(c1,pE2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTheCourseEditionIsAlreadyRegistered() throws Exception {
        //arrange
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal","20-12-2010", assistantProfessor,100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEditionRepository courseEditionRepository1 = new CourseEditionRepository();
        courseEditionRepository1.createCourseEdition(c1,pE1);
        //act
        boolean result = courseEditionRepository1.createCourseEdition(c1,pE1);
        //assert
        assertFalse(result);

    }


    @Test
    void shouldReturnFalseIfCourseIsNull() throws Exception {
        //Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal","20-12-2010", assistantProfessor,100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        CourseEditionRepository courseEditionRepository1 = new CourseEditionRepository();
        //Act
        boolean result = courseEditionRepository1.createCourseEdition(null, pE1);
        //assert
        assertFalse(result);

    }

    @Test
    void shouldReturnFalseIfProgrammeEditionIsNull() throws Exception {
        //Arrange
        Course c1 = new Course("Informatics", "INF", 6, 1);
        CourseEditionRepository courseEditionRepository1 = new CourseEditionRepository();
        //Act
        boolean result = courseEditionRepository1.createCourseEdition(c1, null);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseAndProgrammeEditionAreNull() throws Exception {
        //Arrange
        CourseEditionRepository courseEditionRepository1 = new CourseEditionRepository();
        //Act
        boolean result = courseEditionRepository1.createCourseEdition(null, null);
        //assert
        assertFalse(result);
    }




}