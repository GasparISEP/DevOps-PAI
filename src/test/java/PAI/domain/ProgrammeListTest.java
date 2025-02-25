package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeListTest {

    @Test
    void shouldRegisterValidProgramme() throws Exception {
        // Arrange
        ProgrammeList list = new ProgrammeList();
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        CourseRepository courseRepository = new CourseRepository();
        //act + assert
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);


        // Act
        boolean result = list.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);

        // Asssert
        assertTrue(result);
    }


    @Test
    void duplicatedShouldNotRegisterValidProgramme() throws Exception {
        // Arrange
        ProgrammeList list = new ProgrammeList();
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        CourseRepository courseRepository = new CourseRepository();

        // Act
        list.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        boolean result = list.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);

            // Asssert
        assertFalse(result);
    }

    @Test
    void changeProgrammedDirectorOfValidProgramme() throws Exception {
        // Arrange
        ProgrammeList list = new ProgrammeList();
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor, 100,CSE);
        Teacher teacher1 = new Teacher("ABC", "John Travis", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        CourseRepository courseRepository = new CourseRepository();
        //act + assert
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);

        // Act
        boolean result = list.changeProgrammeDirector(CE,teacher1);

        // Asssert
        assertTrue(result);
    }

    @Test
    void dontChangeProgrammedDirectorOfValidProgramme() throws Exception {
        // Arrange
        ProgrammeList list = new ProgrammeList();
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor, 100,CSE);
        Teacher teacher1 = new Teacher("ABC", "John Travis", "abc@isep.ipp.pt", "123456789", "B106","Doutoramento em Engenharia Informatica, 2005, ISEP","Rua São Tomé Porto","4249-015","Porto", "Portugal", "20-12-2010", assistantProfessor,100, CSE);
        CourseRepository courseRepository = new CourseRepository();
        //act + assert
        Programme CE = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);

        // Act
        boolean result = list.changeProgrammeDirector(CE,null);

        // Asssert
        assertFalse(result);
    }

    @Test
    void shouldReturnCourseList() throws Exception {
        //arrange
        ProgrammeList programmeList = new ProgrammeList();
        CourseRepository courseRepository = new CourseRepository();
        Course course1 = new Course("Programming", "PROG", 25, 1);
        Course course2 = new Course("Mathematics", "MATH", 25, 2);
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);
        Programme programme = new Programme("Computer Engineering", "CE", 30, 6, master, cse, teacher);

        // act
        courseRepository.registerCourse("Programming", "PROG", 25, 1);
        courseRepository.registerCourse("Mathematics", "MATH", 25, 2);

        programme.addCourseToAProgramme(course1);
        programme.addCourseToAProgramme(course2);

        List<Course> courseList = programmeList.getCourseList(programme);

        //assert
        assertEquals(2, courseList.size(), "O número de cursos deve ser 2");
        assertTrue(courseList.contains(course1), "A lista deve conter o curso Programming");
        assertTrue(courseList.contains(course2), "A lista deve conter o curso Mathematics");

    }

    @Test
    void shouldReturnProgrammedWithTheRequiredName() throws Exception  {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);

        ProgrammeList repository = new ProgrammeList();

        repository.registerProgramme("Computer Engineering", "CE", 20, 6, master, cse, teacher);

        // Act
        Optional<Programme> result = repository.getProgrammeByName("Computer Engineering");

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    void shouldReturnNullIfProgrammeWithTheRequiredNameDoesNotExist() throws Exception  {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department cse = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informática, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, cse);

        ProgrammeList repository = new ProgrammeList();

        repository.registerProgramme("Computer Engineering", "CE", 20, 6, master, cse, teacher);

        // Act
        Optional<Programme> result = repository.getProgrammeByName("Space Engineering");


        // Assert
        assertTrue(result.isEmpty());
    }
}