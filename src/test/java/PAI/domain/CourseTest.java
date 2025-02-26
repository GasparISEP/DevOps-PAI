package PAI.domain;

import PAI.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseTest {

    @Test
    void shouldCreateValidCourse() throws Exception {
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        //act
        Course course = courseFactory.createCourse("Informatics", "INF", 6, 1);
        // Assert
        assertNotNull(course);
    }

    @Test
    void shouldReturnExceptionIfCourseNameIsInvalid()  {
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        //act
        //assert
        assertThrows(Exception.class, () -> courseFactory.createCourse("", "INF", 6, 1));
    }

    @Test
    void shouldReturnExceptionIfCourseNameIsNull()  {
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        //act
        //assert
        assertThrows(Exception.class, () -> courseFactory.createCourse(null, "INF", 6, 1));
    }

    @Test
    void shouldReturnExceptionIfAcronymIsNull() {
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        //act
        //assert
        assertThrows(Exception.class, () -> courseFactory.createCourse("Informatics", null, 6, 1));
    }

    @Test
    void shouldReturnExceptionIfAcronymIsBlank(){
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        //act
        //assert
        assertThrows(Exception.class, () -> courseFactory.createCourse("Informatics", "", 6, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"^AZ", "A^Z", "AZ^", "áBD", "BsD", "BDá", "A ZD", "AZ D", " AZD", "AZ!", "A!Z", "A!Z", "asd"})
    void shouldReturnExceptionIfAcronymIsInvalid(String invalidAcronym)  {
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        //act
        //assert
        assertThrows(Exception.class, () -> courseFactory.createCourse("Informatics", invalidAcronym, 6, 1));
    }

    @Test
    void shouldReturnExceptionIfEctsIsLessThanOne(){
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        //act
        //assert
        assertThrows(Exception.class, () -> courseFactory.createCourse("Informatics", "INF", 0, 1));
    }

    @Test
    void shouldReturnExceptionIfEctsIsHigherThanSixty(){
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        //act
        //assert
        assertThrows(Exception.class, () -> courseFactory.createCourse("Informatics", "INF", 61, 1));
    }

    @Test
    void shouldReturnExceptionIfDurationOfCourseInSemesterIsLessThanOne(){
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        //act
        //assert
        assertThrows(Exception.class, () -> courseFactory.createCourse("Informatics", "INF", 60, 0));
    }

    @Test
    void shouldReturnExceptionIfDurationOfCourseInSemesterHasMoreThanOneDecimalDigit(){
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        //act
        //assert
        assertThrows(Exception.class, () -> courseFactory.createCourse("Informatics", "INF", 25.55, 0));
    }

    @Test
    void shouldReturnExceptionIfDurationOfCourseInSemesterIsHigherThanTwo(){
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        //act
        //assert
        assertThrows(Exception.class, () -> courseFactory.createCourse("Informatics", "INF", 60, 3));
    }

    //Equals Method Test
    @Test
    void shouldReturnTrueIfObjectComparedIsTheSameAsThisCourse() throws Exception{
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        Course course1 = courseFactory.createCourse("Informatics", "INF", 10, 1);
        Object objectToCompare = course1;
        //act
        boolean result = course1.equals(objectToCompare);
        //assert
        assertTrue(result);
        assertSame(course1,objectToCompare);
    }

    @Test
    void shouldReturnFalseIfObjectComparedIsNotAnInstanceOfCourse() throws Exception{
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        Course course1 = courseFactory.createCourse("Informatics", "INF", 10, 1);

        AccessMethod accessMethodMock = mock(AccessMethod.class);
        Object objectToCompare = accessMethodMock;
        //act
        boolean result = course1.equals(objectToCompare);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfObjectComparedHasTheSameAcronymAsOtherCourse() throws Exception{
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        Course course1 = courseFactory.createCourse("Informatics", "INF", 10, 1);
        Course course2 = courseFactory.createCourse("Maths", "INF", 5, 1);
        //act
        boolean result = course1.equals(course2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfObjectComparedHasTheSameNameAsOtherCourse() throws Exception{
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        Course course1 = courseFactory.createCourse("Informatics", "INF", 10, 1);
        Course course2 = courseFactory.createCourse("Informatics", "NIF", 5, 1);
        //act
        boolean result = course1.equals(course2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectComparedHasNeitherTheSameNameOrAcronymAsOtherCourse() throws Exception{
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        Course course1 = courseFactory.createCourse("Informatics", "INF", 10, 1);
        Course course2 = courseFactory.createCourse("Maths", "MAT", 5, 1);
        //act
        boolean result = course1.equals(course2);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTheDurationCourseInSemester() throws Exception{
        //arrange
        CourseFactory courseFactory = new CourseFactory();
        Course course1 = courseFactory.createCourse("Informatics", "INF", 10, 1);
        //act
        int result = course1.getDurationInSemester();
        //assert
        assertEquals(1, result);
    }

    @Test
    void shouldReturnQuantityCreditsEcts() throws Exception {
        // Arrange
        CourseFactory courseFactory = new CourseFactory();
        Course course1 = courseFactory.createCourse("Informatics", "INF", 6, 1);
        // Act
        double resultado = course1.getQuantityCreditsEcts();
        // Assert
        assertEquals(6, resultado);
    }

    @Test
    void shouldReturnCourseName() throws Exception {
        // Arrange
        CourseFactory courseFactory = new CourseFactory();
        Course course1 = courseFactory.createCourse("Informatics", "INF", 6, 1);
        // Act
        String resultado = course1.getName();
        // Assert
        assertEquals("Informatics", resultado);
    }

    @Test
    void shouldReturnCourseAcronym() throws Exception {
        // Arrange
        CourseFactory courseFactory = new CourseFactory();
        Course course1 = courseFactory.createCourse("Informatics", "INF", 6, 1);
        // Act
        String resultado = course1.getAcronym();
        // Assert
        assertEquals("INF", resultado);
    }
}