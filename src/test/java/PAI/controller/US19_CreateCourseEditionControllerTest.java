package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class US19_CreateCourseEditionControllerTest {


    @Test
    void shouldReturnTrueIfCourseEditionIsCreated() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepository, courseEditionRepository, programmeList);
        Course course = new Course("Informatics", "INF", 6, 1);
        ProgrammeEdition programmeEdition = new ProgrammeEdition(
                new Programme("Computer Engineering", "CE", 20, 6,
                        new DegreeType("Master", 240),
                        new Department("CSE", "Computer Science Engineer"),
                        new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                                "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                                new Department("CSE", "Computer Science Engineer")
                        )
                ),
                new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025")
        );

        // Act
        boolean result = controller.createCourseEdition(course, programmeEdition);

        // Assert
        assertEquals(result, true);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsNotCreatedBecauseCourseIsNull() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepository, courseEditionRepository, programmeList);
        ProgrammeEdition programmeEdition = new ProgrammeEdition(
                new Programme("Computer Engineering", "CE", 20, 6,
                        new DegreeType("Master", 240),
                        new Department("CSE", "Computer Science Engineer"),
                        new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                                "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                                new Department("CSE", "Computer Science Engineer")
                        )
                ),
                new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025")
        );

        // Act
        boolean result = controller.createCourseEdition(null, programmeEdition);

        // Assert
        assertEquals(false, result);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsNotCreatedBecauseProgrammeEditionIsNull() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepository, courseEditionRepository, programmeList);
        Course course = new Course("Informatics", "INF", 6, 1);

        // Act
        boolean result = controller.createCourseEdition(course, null);

        // Assert
        assertEquals(false, result);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsNotCreatedBecauseProgrammeEditionAndCourseAreNull() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepository, courseEditionRepository, programmeList);

        // Act
        boolean result = controller.createCourseEdition(null, null);

        // Assert
        assertEquals(false, result);
    }

    @Test
    void shouldReturnNotNullEvenIfListOfProgrammeEditionsIsEmpty() throws Exception {
        //Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepository, courseEditionRepository, programmeList);
        List<ProgrammeEdition> allEditions = controller.getAllProgrammeEditions();
        ProgrammeEdition programmeEdition = new ProgrammeEdition(
                new Programme("Computer Engineering", "CE", 20, 6,
                        new DegreeType("Master", 240),
                        new Department("CSE", "Computer Science Engineer"),
                        new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                                "Doutoramento em Engenharia Informatica, 2005, ISEP",
                                "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                                "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                                new Department("CSE", "Computer Science Engineer")
                        )
                ),
                new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025")
        );
        controller.getAllProgrammeEditions();
        //Assert
        assertNotNull(allEditions);
    }

    @Test
    void ShouldReturnSizeOfListOfProgrammeEditionsForMethodGetAllProgrammeEditions() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepository, courseEditionRepository, programmeList);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6,
                new DegreeType("Master", 240),
                new Department("CSE", "Computer Science Engineer"),
                new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                        "Doutoramento em Engenharia Informatica, 2005, ISEP",
                        "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                        "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                        new Department("CSE", "Computer Science Engineer")
                )
        );
        Programme programme1 = new Programme("Computer Science", "CC", 20, 6,
                new DegreeType("Master", 240),
                new Department("CSE", "Computer Science Engineer"),
                new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                        "Doutoramento em Engenharia Informatica, 2005, ISEP",
                        "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                        "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                        new Department("CSE", "Computer Science Engineer")
                )
        );
        SchoolYear schoolYear = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");

        programmeEditionRepository.createProgrammeEdition(programme,schoolYear);
        programmeEditionRepository.createProgrammeEdition(programme1,schoolYear);

        // Act
        controller.getAllProgrammeEditions();

        // Assert
        assertEquals(2, controller.getAllProgrammeEditions().size());
    }


    @Test
    void shouldReturnTrueIfListOfProgrammeEditionsContainsProgrammeEdition() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepository, courseEditionRepository, programmeList);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6,
                new DegreeType("Master", 240),
                new Department("CSE", "Computer Science Engineer"),
                new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                        "Doutoramento em Engenharia Informatica, 2005, ISEP",
                        "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                        "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                        new Department("CSE", "Computer Science Engineer")
                )
        );
        Programme programme1 = new Programme("Computer Science", "CC", 20, 6,
                new DegreeType("Master", 240),
                new Department("CSE", "Computer Science Engineer"),
                new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                        "Doutoramento em Engenharia Informatica, 2005, ISEP",
                        "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                        "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                        new Department("CSE", "Computer Science Engineer")
                )
        );
        SchoolYear schoolYear = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition programmeEdition1 = new ProgrammeEdition(programme,schoolYear);
        programmeEditionRepository.createProgrammeEdition(programme,schoolYear);

        // Act
        List<ProgrammeEdition> allEditions = controller.getAllProgrammeEditions();
        // Assert
        assertEquals(true, allEditions.contains (programmeEdition1));
    }

    @Test
    void shouldReturnFalseIfListOfProgrammeEditionsNotContainsProgrammeEdition() throws Exception {
        // Arrange
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepository, courseEditionRepository, programmeList);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6,
                new DegreeType("Master", 240),
                new Department("CSE", "Computer Science Engineer"),
                new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                        "Doutoramento em Engenharia Informatica, 2005, ISEP",
                        "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                        "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                        new Department("CSE", "Computer Science Engineer")
                )
        );
        Programme programme1 = new Programme("Computer Science", "CC", 20, 6,
                new DegreeType("Master", 240),
                new Department("CSE", "Computer Science Engineer"),
                new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                        "Doutoramento em Engenharia Informatica, 2005, ISEP",
                        "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                        "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                        new Department("CSE", "Computer Science Engineer")
                )
        );
        SchoolYear schoolYear = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition programmeEdition2 = new ProgrammeEdition(programme1,schoolYear);
        programmeEditionRepository.createProgrammeEdition(programme,schoolYear);

        // Act
        List<ProgrammeEdition> allEditions = controller.getAllProgrammeEditions();
        // Assert
        assertEquals(false, allEditions.contains (programmeEdition2));
    }


    @Test
    void shouldReturnSizeOfCourseListInProgrammeForGetCoursesInProgrammeMethod() throws Exception {
        // Arrange
        CourseFactory courseFactory = mock(CourseFactory.class);
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepository, courseEditionRepository, programmeList);
        CourseRepository courseRepository = new CourseRepository(courseFactory);
        courseRepository.registerCourse("Informatica", "INF", 6, 1);
        courseRepository.registerCourse("Matemática", "MAT", 4, 1);
        Course c1 = new Course ("Informatica", "INF", 6, 1);
        Course c2 = new Course("Matemática", "MAT", 4, 1);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6,
                new DegreeType("Master", 240),
                new Department("CSE", "Computer Science Engineer"),
                new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                        "Doutoramento em Engenharia Informatica, 2005, ISEP",
                        "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                        "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                        new Department("CSE", "Computer Science Engineer")
                )
        );
        programmeList.registerProgramme("Computer Engineering", "CE", 20, 6,
                new DegreeType("Master", 240),
                new Department("CSE", "Computer Science Engineer"),
                new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                        "Doutoramento em Engenharia Informatica, 2005, ISEP",
                        "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                        "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                        new Department("CSE", "Computer Science Engineer")
                )
        );
        programme.addCourseToAProgramme(c1);
        programme.addCourseToAProgramme(c2);
        SchoolYear schoolYear = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition programmeEdition1 = new ProgrammeEdition(programme,schoolYear);
        programmeEditionRepository.createProgrammeEdition(programme,schoolYear);


        // Act
        controller.getCoursesInProgramme(programmeEdition1);

        // Assert
        assertEquals(2, controller.getCoursesInProgramme(programmeEdition1).size());
    }

    @Test
    void shouldReturnNotNullEvenIfCourseListIsEmptyInProgrammeForGetCoursesInProgrammeMethod() throws Exception {
        // Arrange
        CourseFactory courseFactory = mock(CourseFactory.class);
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepository, courseEditionRepository, programmeList);
        CourseRepository courseRepository = new CourseRepository(courseFactory);
        courseRepository.registerCourse("Informatica", "INF", 6, 1);
        courseRepository.registerCourse("Matemática", "MAT", 4, 1);

        Programme programme = new Programme("Computer Engineering", "CE", 20, 6,
                new DegreeType("Master", 240),
                new Department("CSE", "Computer Science Engineer"),
                new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                        "Doutoramento em Engenharia Informatica, 2005, ISEP",
                        "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                        "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                        new Department("CSE", "Computer Science Engineer")
                )
        );
        programmeList.registerProgramme("Computer Engineering", "CE", 20, 6,
                new DegreeType("Master", 240),
                new Department("CSE", "Computer Science Engineer"),
                new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                        "Doutoramento em Engenharia Informatica, 2005, ISEP",
                        "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                        "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                        new Department("CSE", "Computer Science Engineer")
                )
        );

        SchoolYear schoolYear = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition programmeEdition1 = new ProgrammeEdition(programme,schoolYear);
        programmeEditionRepository.createProgrammeEdition(programme,schoolYear);


        // Act
        // Assert
        assertNotNull(controller.getCoursesInProgramme(programmeEdition1));
    }

    @Test
    void shouldReturnTrueIfCourseListHasCourseInProgrammeForGetCoursesInProgrammeMethod() throws Exception {
        // Arrange
        CourseFactory courseFactory = mock(CourseFactory.class);
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepository, courseEditionRepository, programmeList);

        CourseRepository courseRepository = new CourseRepository(courseFactory);
        courseRepository.registerCourse("Informatica", "INF", 6, 1);
        courseRepository.registerCourse("Matemática", "MAT", 4, 1);
        Course c1 = new Course ("Informatica", "INF", 6, 1);
        Course c2 = new Course("Matemática", "MAT", 4, 1);

        Programme programme = new Programme("Computer Engineering", "CE", 20, 6,
                new DegreeType("Master", 240),
                new Department("CSE", "Computer Science Engineer"),
                new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                        "Doutoramento em Engenharia Informatica, 2005, ISEP",
                        "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                        "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                        new Department("CSE", "Computer Science Engineer")
                )
        );
        programmeList.registerProgramme("Computer Engineering", "CE", 20, 6,
                new DegreeType("Master", 240),
                new Department("CSE", "Computer Science Engineer"),
                new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                        "Doutoramento em Engenharia Informatica, 2005, ISEP",
                        "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                        "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                        new Department("CSE", "Computer Science Engineer")
                )
        );
        programme.addCourseToAProgramme(c1);
        programme.addCourseToAProgramme(c2);

        SchoolYear schoolYear = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition programmeEdition1 = new ProgrammeEdition(programme,schoolYear);
        programmeEditionRepository.createProgrammeEdition(programme,schoolYear);


        // Act
        // Assert
        assertEquals(true, controller.getCoursesInProgramme(programmeEdition1).contains(c1));
    }

    @Test
    void shouldReturnFalseIfCourseListNotHaveCourseInProgrammeForGetCoursesInProgrammeMethod() throws Exception {
        // Arrange
        CourseFactory courseFactory = mock(CourseFactory.class);
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();
        CourseEditionRepository courseEditionRepository = new CourseEditionRepository();
        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
        ProgrammeList programmeList = new ProgrammeList(programmeFactory);
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepository, courseEditionRepository, programmeList);

        CourseRepository courseRepository = new CourseRepository(courseFactory);
        courseRepository.registerCourse("Informatica", "INF", 6, 1);
        courseRepository.registerCourse("Matemática", "MAT", 4, 1);
        Course c1 = new Course ("Informatica", "INF", 6, 1);
        Course c2 = new Course("Matemática", "MAT", 4, 1);

        Programme programme = new Programme("Computer Engineering", "CE", 20, 6,
                new DegreeType("Master", 240),
                new Department("CSE", "Computer Science Engineer"),
                new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                        "Doutoramento em Engenharia Informatica, 2005, ISEP",
                        "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                        "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                        new Department("CSE", "Computer Science Engineer")
                )
        );
        programmeList.registerProgramme("Computer Engineering", "CE", 20, 6,
                new DegreeType("Master", 240),
                new Department("CSE", "Computer Science Engineer"),
                new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                        "Doutoramento em Engenharia Informatica, 2005, ISEP",
                        "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                        "20-12-2010", new TeacherCategory("Assistant Professor"), 100,
                        new Department("CSE", "Computer Science Engineer")
                )
        );

        programme.addCourseToAProgramme(c1);

        SchoolYear schoolYear = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition programmeEdition1 = new ProgrammeEdition(programme,schoolYear);
        programmeEditionRepository.createProgrammeEdition(programme,schoolYear);


        // Act
        controller.getCoursesInProgramme(programmeEdition1);
        // Assert
        assertEquals(false, controller.getCoursesInProgramme(programmeEdition1).contains(c2));
    }

}