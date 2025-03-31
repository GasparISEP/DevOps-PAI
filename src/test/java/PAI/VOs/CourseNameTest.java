package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseNameTest {

    @Test
    void shouldCreateCourseName() throws Exception{
        //act
        CourseName courseName = new CourseName("Mestrado em Engenharia Informática");
        //assert
        assertNotNull(courseName);
    }

    @Test
    void nullCourseNameThrowsException(){

        //act + assert
        assertThrows(Exception.class, () -> new CourseName(null));
    }

    @Test
    void emptyCourseNameThrowsException(){

        //act + assert
        assertThrows(Exception.class, () -> new CourseName(""));
    }

    @Test
    void shouldReturnsEqualsIfObjectsAreEqual() throws Exception {
        //Arrange
        CourseName courseName = new CourseName("Mestrado em Engenharia Informática");
        CourseName courseName2 = courseName;
        //Act+Assert
        assertEquals(courseName, courseName2);
    }

    @Test
    void shouldReturnNotEqualsIfComparingWithNull() throws Exception {
        //Arrange
        CourseName courseName = new CourseName("Mestrado em Engenharia Informática");
        CourseName courseName2 = null;
        //Act+Assert
        assertNotEquals(courseName, courseName2);
    }

    @Test
    void shouldReturnNotEqualsIfObjectsAreNotFromSameClass() throws Exception {
        //Arrange
        CourseName courseName = new CourseName("Mestrado em Engenharia Informática");
        CourseEditionID courseEditionID = new CourseEditionID();
        //Act+Assert
        assertNotEquals(courseName, courseEditionID);
    }

    @Test
    void shouldReturnNotEqualsIfCourseNamesHaveDifferentNames() throws Exception {
        //Arrange
        CourseName courseName = new CourseName("Mestrado em Engenharia Informática");
        CourseName courseName2 = new CourseName("Mestrado em Engenharia Mecanica");
        //Act+Assert
        assertNotEquals(courseName, courseName2);
    }

    @Test
    void shouldReturnEqualsHashCode() throws Exception {
        //Arrange
        CourseName courseName = new CourseName("Mestrado em Engenharia Informática");
        //Act
        int hash1 = courseName.hashCode();
        int hash2 = courseName.hashCode();
        //Assert
        assertEquals(hash1,hash2);
    }
}