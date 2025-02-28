package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US19_CreateCourseEditionControllerTest {


    @Test
    void shouldReturnTrueIfCourseEditionIsCreated() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeList programmeListDouble = mock (ProgrammeList.class);
        Course courseDouble = mock (Course.class);
        ProgrammeEdition programmeEditionDouble = mock (ProgrammeEdition.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (courseEditionRepositoryDouble.createAndSaveCourseEdition(courseDouble, programmeEditionDouble)).thenReturn(true);

        // Act
        boolean result = controller.createCourseEdition(courseDouble, programmeEditionDouble);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseEditionIsNotCreated() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeList programmeListDouble = mock (ProgrammeList.class);
        ProgrammeEdition programmeEditionDouble = mock (ProgrammeEdition.class);
        Course course = mock (Course.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (courseEditionRepositoryDouble.createAndSaveCourseEdition(course, programmeEditionDouble)).thenReturn(false);

        // Act
        boolean result = controller.createCourseEdition(course, programmeEditionDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnNotNullEvenIfListOfProgrammeEditionsIsEmpty() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeList programmeListDouble = mock (ProgrammeList.class);
        List<ProgrammeEdition> allEditionsDouble = mock (List.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //instructions
        when (programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(allEditionsDouble);

        //Act
        controller.getAllProgrammeEditions();
        //Assert
        assertNotNull(allEditionsDouble);
    }


    @Test
    void ShouldReturnSizeOfListOfProgrammeEditionsForMethodGetAllProgrammeEditions() {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeList programmeListDouble = mock (ProgrammeList.class);
        List<ProgrammeEdition> allEditionsDouble = mock (List.class);

        //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

        //instructions
        when (programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(allEditionsDouble);

        // Act
        controller.getAllProgrammeEditions();

        // Assert
        assertEquals(0, controller.getAllProgrammeEditions().size());
    }


    @Test
    void shouldReturnTrueIfListOfProgrammeEditionsContainsProgrammeEdition() throws Exception {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
            //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeList programmeListDouble = mock (ProgrammeList.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);

            //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

            //auxiliary methods
        List<ProgrammeEdition> allEditions = new ArrayList<>();
        allEditions.add(programmeEditionDouble);

            //instructions
        when (programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(allEditions);

        // Act
        controller.getAllProgrammeEditions();

        // Assert
        assertTrue(allEditions.contains(programmeEditionDouble));
    }


    @Test
    void shouldReturnFalseIfListOfProgrammeEditionsNotContainsProgrammeEdition() throws Exception {
        //SUT = CreateCourseEditionController -> all else as Double
        // Arrange
        //Doubles' instantiation
        ProgrammeEditionRepository programmeEditionRepositoryDouble = mock(ProgrammeEditionRepository.class);
        CourseEditionRepository courseEditionRepositoryDouble = mock (CourseEditionRepository.class);
        ProgrammeList programmeListDouble = mock (ProgrammeList.class);
        ProgrammeEdition programmeEditionDouble = mock(ProgrammeEdition.class);
        List <ProgrammeEdition> allEditionsDouble = mock (List.class);

        //SUT
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController(programmeEditionRepositoryDouble, courseEditionRepositoryDouble, programmeListDouble);

        //instructions
        when (programmeEditionRepositoryDouble.getAllProgrammeEditions()).thenReturn(allEditionsDouble);

        // Act
        controller.getAllProgrammeEditions();

        // Assert
        assertFalse(allEditionsDouble.contains(programmeEditionDouble));
    }


    @Test
    void shouldReturnSizeOfCourseListInProgrammeForGetCoursesInProgrammeMethod() throws Exception {
        // Arrange
        CourseFactory courseFactory = mock(CourseFactory.class);
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory);
        CourseEditionRepository courseEditionRepository = mock (CourseEditionRepository.class);
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
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory);
        CourseEditionRepository courseEditionRepository = mock (CourseEditionRepository.class);
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
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory);
        CourseEditionRepository courseEditionRepository = mock (CourseEditionRepository.class);
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
        ProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactory();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository(programmeEditionFactory);
        CourseEditionRepository courseEditionRepository = mock (CourseEditionRepository.class);
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