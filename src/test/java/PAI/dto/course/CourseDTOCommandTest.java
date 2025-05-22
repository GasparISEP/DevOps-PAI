package PAI.dto.course;

import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseDTOCommandTest {

    @Test
    void testConstructorNotNull () {
        // Arrange
        CourseID courseIDDouble = mock(CourseID.class);
        Name nameDouble = mock(Name.class);
        Acronym acronymDouble = mock(Acronym.class);

        // Act
        CourseDTOCommand courseDTOCommand = new CourseDTOCommand(courseIDDouble, nameDouble, acronymDouble);

        // Assert
        assertNotNull(courseDTOCommand);
    }

    @Test
    void testGetCourseIDShouldReturnCourseID () {
        // Arrange
        CourseID courseIDDouble = mock(CourseID.class);
        Name nameDouble = mock(Name.class);
        Acronym acronymDouble = mock(Acronym.class);

        CourseDTOCommand courseDTOCommand = new CourseDTOCommand(courseIDDouble, nameDouble, acronymDouble);

        // Act
        CourseID result = courseDTOCommand.getCourseId();

        // Assert
        assertEquals(courseIDDouble, result);
    }

    @Test
    void testGetNameShouldReturnName () {
        // Arrange
        CourseID courseIDDouble = mock(CourseID.class);
        Name nameDouble = mock(Name.class);
        Acronym acronymDouble = mock(Acronym.class);

        CourseDTOCommand courseDTOCommand = new CourseDTOCommand(courseIDDouble, nameDouble, acronymDouble);

        // Act
        Name result = courseDTOCommand.getName();

        // Assert
        assertEquals(nameDouble, result);
    }

    @Test
    void testGetAcronymShouldReturnAcronym () {
        // Arrange
        CourseID courseIDDouble = mock(CourseID.class);
        Name nameDouble = mock(Name.class);
        Acronym acronymDouble = mock(Acronym.class);

        CourseDTOCommand courseDTOCommand = new CourseDTOCommand(courseIDDouble, nameDouble, acronymDouble);

        // Act
        Acronym result = courseDTOCommand.getAcronym();

        // Assert
        assertEquals(acronymDouble, result);
    }
}