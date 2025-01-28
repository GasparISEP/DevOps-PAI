package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class US19CreateCourseEditionControllerTest {

    @Test
    void testCreateCourseEdition() throws Exception {
        // Arrange
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController();
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
        CourseEdition courseEdition = controller.createCourseEdition(course, programmeEdition);

        // Assert
        assertNotNull(courseEdition);
        assertEquals(course, courseEdition.getCourse());
        assertEquals(programmeEdition, courseEdition.getProgrammeEdition());
    }

    @Test
    void testGetAllCourseEditions() throws Exception {
        // Arrange
        US19_CreateCourseEditionController controller = new US19_CreateCourseEditionController();
        Course course1 = new Course("Informatics", "INF", 6, 1);
        ProgrammeEdition programmeEdition1 = new ProgrammeEdition(
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
        Course course2 = new Course("Maths", "MAT", 5, 1);
        ProgrammeEdition programmeEdition2 = new ProgrammeEdition(
                new Programme("Computer Science", "CC", 20, 6,
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
        controller.createCourseEdition(course1, programmeEdition1);
        controller.createCourseEdition(course2, programmeEdition2);

        // Assert
        assertEquals(2, controller.getAllCourseEditions().size());
    }
}