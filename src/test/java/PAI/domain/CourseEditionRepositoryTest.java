package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

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
    @Test
    void shouldReturnTrueIfCourseEditionContainsRuc() throws Exception {
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
        courseEditionRepository1.createCourseEdition(c1,pE1);
        CourseEdition ce1 = new CourseEdition(c1, pE1);
        Teacher ruc = new Teacher("AAA", "Joao Costa", "aaa@isep.ipp.pt", "123456780", "A106", "Doutoramento em Artes Circenses, 2004, ISEP", "Rua São Porto", "4249-015", "Porto", "Portugal", "24-03-2010", assistantProfessor, 80, CSE);

        //act
        boolean result = courseEditionRepository1.setRucInACourseEdition(ce1, ruc);
        //assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseIfCourseEditionDoesNotContainsRuc() throws Exception {
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
        courseEditionRepository1.createCourseEdition(c1,pE1);
        CourseEdition ce1 = new CourseEdition(c1, pE1);

        //act
        boolean result = courseEditionRepository1.setRucInACourseEdition(ce1, null);
        //assert
        assertFalse(result);
    }

    //US17
    @Test
    void shouldReturnCourseEditionsWithSameProgrammeEdition() throws Exception {
        // Arrange
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        Course c1 = new Course("Informática", "INF", 6, 1);
        Course c2 = new Course("Matemática", "MAT", 6, 1);
        SchoolYear sy1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);

        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        courseEditionRepository.createCourseEdition(c1, pe1);
        courseEditionRepository.createCourseEdition(c2, pe1);

        // Act
        List<CourseEdition> result = courseEditionRepository.findCourseEditionsByProgrammeEdition(pe1);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(new CourseEdition(c1, pe1)));
        assertTrue(result.contains(new CourseEdition(c2, pe1)));
        assertTrue(result.contains(new CourseEdition(c1,pe1)));
        assertTrue(result.contains(new CourseEdition(c2,pe1)));
    }


    @Test
    void shouldReturnProgrammeEditionWhenCourseEditionExists() throws Exception {
        // Arrange
        CourseEditionRepository repository = new CourseEditionRepository();
        Address address = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Department department = new Department("DCE", "Department of Computer Engineering");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher director = new Teacher("JOE", "Joe Doe", "joe@isep.pt", "123456789", "B106","Phd","Rua das Flores","4433-445","Porto","Portugal","14-10-2024",assistantProfessor,100,department);

        SchoolYear schoolYear = new SchoolYear("2024/2025", "14-10-2024", "30-06-2025");
        DegreeType degreeType = new DegreeType("Master", 30);
        Programme programme = new Programme("SWITCH DEV", "SDV", 30, 1, degreeType, department, director);
        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);

        Course course = new Course("Development", "DEV", 5, 1);
        CourseEdition courseEdition = new CourseEdition(course, programmeEdition);

        repository.createCourseEdition(course, programmeEdition);

        // Act
        ProgrammeEdition result = repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEdition);

        // Assert
        assertNotNull(result, "The returned ProgrammeEdition should not be null.");
        assertEquals(programmeEdition, result, "The returned ProgrammeEdition should match the one associated with the CourseEdition.");
    }


    @Test
    void shouldThrowExceptionWhenCourseEditionDoesNotExist() throws Exception {
        // Arrange
        CourseEditionRepository repository = new CourseEditionRepository();
        Address address = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Department department = new Department("DCE", "Department of Computer Engineering");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher director = new Teacher("JOE", "Joe Doe", "joe@isep.pt", "123456789", "B106","Phd","Rua das Flores","4433-445","Porto","Portugal","14-10-2024",assistantProfessor,100,department);
        SchoolYear schoolYear = new SchoolYear("2024/2025", "14-10-2024", "30-06-2025");
        DegreeType degreeType = new DegreeType("Master", 30);
        Programme programme = new Programme("SWITCH DEV", "SDV", 30, 1, degreeType, department, director);
        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        Course course = new Course("Development", "DEV", 5, 1);
        CourseEdition courseEdition = new CourseEdition(course, programmeEdition);

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            repository.findWhichProgrammeEditionBelongsToACourseEdition(courseEdition);
        });

        assertEquals("The course edition does not belong to the course Edition Repository", exception.getMessage(),
                "The exception message should match the expected message.");
    }
}