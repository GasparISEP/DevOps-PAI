package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void testValidCourseCreation() throws Exception {
        // Arrange
        Semester semester = new Semester(1);

        // Act
        Course course = new Course("Informatics", "INF", 6, semester);

        // Assert
        assertNotNull(course);
    }

    @Test
    void testCourseCreation_TestingInvalidName() throws Exception {
        //arrange
        Semester semester1 = new Semester(1);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("", "PT", 30, semester1));
    }

    @Test
    void testCourseCreation_TestingNullName() throws Exception {
        //arrange
        Semester semester1 = new Semester(1);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course(null, "PT", 30, semester1));
    }

    @Test
    void testCourseCreation_TestingNullAcronym() throws Exception {
        //arrange
        Semester semester1 = new Semester(1);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Portuguese", null, 30, semester1));
    }

    @Test
    void testCourseCreation_TestingInvalidAcronym() throws Exception {
        //arrange
        Semester semester1 = new Semester(1);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Portuguese", "", 30, semester1));
    }

    @Test
    void testCourseCreation_TestingInvalidQuantityOfEctsLower() throws Exception{
        //arrange
        Semester semester1 = new Semester(1);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Portuguese", "PT", 0, semester1));
    }

    @Test
    void testCourseCreation_TestingInvalidQuantityOfEctsHigher() throws Exception{
        //arrange
        Semester semester1 = new Semester(1);
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Portuguese", "PT", 181, semester1));
    }
}