package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseQuantityCreditsEctsTest {

    @Test
    void shouldCreateCourseQuantityCreditsEcts() throws Exception{
        //act
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(6);
        //assert
        assertNotNull(courseQuantityCreditsEcts);
    }

    @Test
    void shouldThrowExceptionWhenCourseQuantityCreditsEctsIsLower() throws Exception{
        //act + assert
        assertThrows(Exception.class, () -> new CourseQuantityCreditsEcts(0));
    }

    @Test
    void shouldThrowExceptionWhenCourseQuantityCreditsEctsIsHigher() throws Exception{
        //act + assert
        assertThrows(Exception.class, () -> new CourseQuantityCreditsEcts(61));
    }

    @Test
    void shouldThrowExceptionWhenCourseQuantityCreditsEctsHas2DecimalPlace() throws Exception{
        //act + assert
        assertThrows(Exception.class, () -> new CourseQuantityCreditsEcts(5.22));
    }

    @Test
    void shouldCreateCourseQuantityCreditsEctsWithZeroDecimalPlace() throws Exception{
        //act
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        //assert
        assertNotNull(courseQuantityCreditsEcts);
    }

    @Test
    void shouldCreateCourseQuantityCreditsEctsWithOneDecimalPlace() throws Exception{
        //act
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5.0);
        //assert
        assertNotNull(courseQuantityCreditsEcts);
    }

    @Test
    void shouldCreateCourseQuantityCreditsEctsWithTwoZeroDecimalPlace() throws Exception{
        //act
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5.00);
        //assert
        assertNotNull(courseQuantityCreditsEcts);
    }

    @Test
    void shouldReturnsEqualsIfObjectsAreEqual() throws Exception{
        //Arrange
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        CourseQuantityCreditsEcts courseID2 = courseQuantityCreditsEcts;
        //Act+Assert
        assertEquals(courseQuantityCreditsEcts, courseID2);
    }

    @Test
    void shouldReturnNotEqualsIfComparingWithNull() throws Exception {
        //Arrange
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        CourseQuantityCreditsEcts courseID2 = null;
        //Act+Assert
        assertNotEquals(courseQuantityCreditsEcts, courseID2);
    }

    @Test
    void shouldReturnNotEqualsIfObjectsAreNotFromSameClass() throws Exception {
        //Arrange
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        CourseEditionID courseEditionID = new CourseEditionID();
        //Act+Assert
        assertNotEquals(courseQuantityCreditsEcts, courseEditionID);
    }

    @Test
    void shouldReturnNotEqualsIfCourseQuantityCreditsEctsHaveDifferentQuantityCreditsEcts() throws Exception {
        //Arrange
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts2 = new CourseQuantityCreditsEcts(6);
        //Act+Assert
        assertNotEquals(courseQuantityCreditsEcts, courseQuantityCreditsEcts2);
    }

    @Test
    void shouldReturnEqualsHashCode() throws Exception {
        //Arrange
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        //Act
        int hash1 = courseQuantityCreditsEcts.hashCode();
        int hash2 = courseQuantityCreditsEcts.hashCode();
        //Assert
        assertEquals(hash1,hash2);
    }

}