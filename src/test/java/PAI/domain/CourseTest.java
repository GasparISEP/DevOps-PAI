package PAI.domain;

import PAI.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void shouldCreateValidCourse() throws Exception {
        // Arrange
        // Act
        Course course1 = new Course("Informatics", "INF", 6, 1);
        // Assert
        assertNotNull(course1);
    }

    @Test
    void shouldCreateValidCourseWithHalfOfCreditsIfDurationCourseInSemesterIsTwo() throws Exception {
        // Arrange
        // Act
        Course course1 = new Course("Informatics", "INF", 6, 2);
        // Assert
        assertEquals(3, course1.getQuantityCreditsEcts());
    }
    @Test
    void shouldReturnExceptionIfCourseNameIsInvalid() throws Exception {
        //arrange
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("", "INF", 6, 1));
    }

    @Test
    void shouldReturnExceptionIfCourseNameIsNull() throws Exception {
        //arrange
        //act
        //assert
        assertThrows(Exception.class, () -> new Course(null, "INF", 6, 1));
    }

    @Test
    void shouldReturnExceptionIfAcronymIsNull() throws Exception {
        //arrange
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", null, 6, 1));
    }

    @Test
    void shouldReturnExceptionIfAcronymIsBlank() throws Exception {
        //arrange
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", "", 6, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"^AZ", "A^Z", "AZ^", "áBD", "BsD", "BDá", "A ZD", "AZ D", " AZD", "AZ!", "A!Z", "A!Z", "asd"})
    void shouldReturnExceptionIfAcronymIsInvalid(String invalidAcronym) throws Exception {
        //arrange
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", invalidAcronym, 6, 1));
    }

    @Test
    void shouldReturnExceptionIfEctsIsLessThanOne() throws Exception{
        //arrange
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", "INF", 0, 1));
    }

    @Test
    void shouldReturnExceptionIfEctsIsHigherThanSixty() throws Exception{
        //arrange
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", "INF", 61, 1));
    }

    @Test
    void shouldReturnExceptionIfDurationOfCourseInSemesterIsLessThanOne() throws Exception{
        //arrange
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", "INF", 60, 0));
    }

    @Test
    void shouldReturnExceptionIfDurationOfCourseInSemesterHasMoreThanOneDecimalDigit() throws Exception{
        //arrange
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", "INF", 25.55, 0));
    }

    @Test
    void shouldReturnExceptionIfDurationOfCourseInSemesterIsHigherThanTwo() throws Exception{
        //arrange
        //act
        //assert
        assertThrows(Exception.class, () -> new Course("Informatics", "INF", 60, 3));
    }

    //Equals Method Test
    @Test
    void shouldReturnTrueIfObjectComparedIsTheSameAsThisCourse() throws Exception{
        //arrange
        Course course1 = new Course("Informatics", "INF", 10, 1);
        Object objectToCompare = course1;
        //act
        boolean result = course1.equals(objectToCompare);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectComparedIsNotAnInstanceOfCourse() throws Exception{
        //arrange
        TeacherCategory teacherCategory = new TeacherCategory("diretor");
        Department department = new Department("EIA", "Departamento EI");
        Address address = new Address("Rua da Alegria", "4222-232", "Porto", "Portugal");
        Teacher teacher = new Teacher("ASD", "Artur Silva Dias", "asd@gmail.com",
                "238310710", "A123", address, teacherCategory, department);
        Course course1 = new Course("Informatics", "INF", 10, 1);
        Object objectToCompare = teacher;
        //act
        boolean result = course1.equals(objectToCompare);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfObjectComparedHasTheSameAcronymAsOtherCourse() throws Exception{
        //arrange
        Course course1 = new Course("Informatics", "INF", 10, 1);
        Course course2 = new Course("Maths", "INF", 5, 1);
        //act
        boolean result = course1.equals(course2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfObjectComparedHasTheSameNameAsOtherCourse() throws Exception{
        //arrange
        Course course1 = new Course("Informatics", "INF", 10, 1);
        Course course2 = new Course("Informatics", "NIF", 5, 1);
        //act
        boolean result = course1.equals(course2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectComparedHasNeitherTheSameNameOrAcronymAsOtherCourse() throws Exception{
        //arrange
        Course course1 = new Course("Informatics", "INF", 10, 1);
        Course course2 = new Course("Maths", "MAT", 5, 1);
        //act
        boolean result = course1.equals(course2);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTheDurationCourseInSemester() throws Exception{
        //arrange
        Course course1 = new Course("Informatics", "INF", 10, 1);
        //act
        int result = course1.getDurationInSemester();
        //assert
        assertEquals(1, result);
    }
}