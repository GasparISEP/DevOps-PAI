package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US20_DefineRucForCourseEditionControllerTest {

    @Test
    void shouldDefineRucForCourseEdition() throws Exception {

        //Arrange
        CourseEditionRepository repo1 = new CourseEditionRepository();
        TeacherRepository repo2 = new TeacherRepository();
        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);

        // Arrange Teacher Ruc
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");
        Teacher t1 =  new Teacher( "BBB", "Mariana Antunes", "BBB@isep.ipp.pt", "123456780",
                "B106","Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005",
                category, 70, department);

        //Arrange CourseEdition
        DegreeType master = new DegreeType("Master",240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20,6,master,CSE,teacher);
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition (p1, sY1);
        CourseEdition cE1 = new CourseEdition(c1, pE1);

        //Act
        boolean result = ctrl1.defineRucForCourseEdition(cE1, t1);

        //Asser
        assertTrue(result, "RUC definition should succeed");
    }
    @Test
    void shouldReturnAllTeachersWithSameSize() throws Exception {
        //ARRANGE

        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");
        CourseEditionRepository repo1 = new CourseEditionRepository();
        TeacherRepository repo2 = new TeacherRepository();
        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);

        repo2.registerTeacher( "AAA", "Joao Costa", "AAA@isep.ipp.pt", "123456789",
                "A106", "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005",
                category, 70, department);

        repo2.registerTeacher( "BBB", "Mariana Antunes", "BBB@isep.ipp.pt", "123456780",
                "B106","Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005",
                category, 70, department);

        //ACT
        List<Teacher> result = ctrl1.getTeachers();

        //
        assertEquals(2, result.size());


    }
    @Test
    void shouldReturnFalseIfRucIsNotSet() throws Exception {
        //ARRANGE

        //TeacherCategory category = new TeacherCategory("Professor Adjunto");

        //Department department = new Department("MAT", "Mathematics");
        CourseEditionRepository repo1 = new CourseEditionRepository();
        TeacherRepository repo2 = new TeacherRepository();
        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);


        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);


        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);


        Course c1 = new Course ("Informatics", "INF", 6, 1);
        repo1.createCourseEdition(c1, pE1);


        Course c2 = new Course ("Cidadania", "CID", 6, 1);
        repo1.createCourseEdition(c2, pE1);

        List<CourseEdition> result = ctrl1.getCourseEditions();

        //Assert
        assertEquals(2, result.size());
    }
    @Test
    void shouldThrowExceptionWhenCourseEditionIsNull() throws Exception {
        CourseEditionRepository repo1 = new CourseEditionRepository();
        TeacherRepository repo2 = new TeacherRepository();
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);
        Teacher t1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);

        assertThrows(NullPointerException.class, () -> ctrl1.defineRucForCourseEdition(null, t1));
    }


    //



}