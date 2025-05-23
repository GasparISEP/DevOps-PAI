package PAI.dto.course;
import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CourseDTOCommandTest {

    @Test
    void shouldCreateValidCourseDTOCommandWithValidVOs() {
        // Arrange
        Name name = new Name("Desenvolvimento de Software");
        Acronym acronym = new Acronym("DSOFT");
        CourseID courseId = new CourseID(acronym, name);

        // Act
        CourseDTOCommand command = new CourseDTOCommand(courseId, name, acronym);
        CourseDTOCommand expected = new CourseDTOCommand(courseId, name, acronym);

        // Assert
        assertEquals(expected, command);
    }
}