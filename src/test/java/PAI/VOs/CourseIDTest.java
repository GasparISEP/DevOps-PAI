package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseIDTest {

    @Test
    void shouldCreateCourseID() {
        //arrange
        Acronym acronymDouble = mock(Acronym.class);
        Name nameDouble = mock(Name.class);
        // act
        CourseID id = new CourseID(acronymDouble, nameDouble);
        //assert
        assertNotNull(id);
    }
    @Test
    void shouldThrowExceptionIfAcronymIsNull() {
        //Arrange
        Acronym acronymDouble = null;
        Name nameDouble = mock(Name.class);
        //Act+Assert
        assertThrows(NullPointerException.class, () -> {new CourseID(acronymDouble, nameDouble);});
    }

    @Test
    void shouldThrowExceptionIfNameIsNull() {
        //Arrange
        Acronym acronymDouble = mock(Acronym.class);
        Name nameDouble = null;
        //Act+Assert
        assertThrows(NullPointerException.class, () -> {new CourseID(acronymDouble, nameDouble);});
    }

    @Test
    void shouldReturnEqualsIfObjectsAreEqual() {
        //Arrange
        Acronym acronymDouble = mock(Acronym.class);
        Name nameDouble = mock(Name.class);
        CourseID courseID = new CourseID(acronymDouble, nameDouble);
        CourseID courseID2 = courseID;
        //Act+Assert
        assertEquals(courseID, courseID2);
    }

    @Test
    void shouldReturnNotEqualsIfComparingWithNull() {
        //Arrange
        Acronym acronymDouble = mock(Acronym.class);
        Name nameDouble = mock(Name.class);
        CourseID courseID = new CourseID(acronymDouble, nameDouble);
        CourseID courseID2 = null;
        //Act+Assert
        assertNotEquals(courseID, courseID2);
    }

    @Test
    void shouldReturnNotEqualsIfObjectsAreNotFromSameClass() {
        //Arrange
        Acronym acronymDouble = mock(Acronym.class);
        Name nameDouble = mock(Name.class);
        CourseID courseID = new CourseID(acronymDouble, nameDouble);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        //Act+Assert
        assertNotEquals(courseID, courseEditionID);
    }

    @Test
    void shouldReturnNotEqualsIfCourseIDsHaveDifferentAcronymsAndNames() {
        //Arrange
        Acronym acronymDouble = mock(Acronym.class);
        Name nameDouble = mock(Name.class);
        Acronym acronymDouble2 = mock(Acronym.class);
        Name nameDouble2 = mock(Name.class);
        CourseID courseID = new CourseID(acronymDouble, nameDouble);
        CourseID courseID2 = new CourseID(acronymDouble2, nameDouble2);
        //Act+Assert
        assertNotEquals(courseID, courseID2);
    }

    @Test
    void shouldReturnNotEqualsIfAcronymsAreEqualButNamesAreDifferent() {
        //Arrange
        Acronym acronymDouble = mock(Acronym.class);
        Name nameDouble = mock(Name.class);
        Name nameDouble2 = mock(Name.class);
        CourseID courseID = new CourseID(acronymDouble, nameDouble);
        CourseID courseID2 = new CourseID(acronymDouble, nameDouble2);
        //Act+Assert
        assertNotEquals(courseID, courseID2);
    }

    @Test
    void shouldReturnEqualsIfAcronymsAreDifferentButNamesAreEqual() {
        //Arrange
        Acronym acronymDouble = mock(Acronym.class);
        Acronym acronymDouble2 = mock(Acronym.class);
        Name nameDouble = mock(Name.class);
        CourseID courseID = new CourseID(acronymDouble, nameDouble);
        CourseID courseID2 = new CourseID(acronymDouble2, nameDouble);
        //Act+Assert
        assertNotEquals(courseID, courseID2);
    }

    @Test
    void shouldReturnCorrectIDToString() {
        //Arrange
        Acronym acronymDouble = mock(Acronym.class);
        Name nameDouble = mock(Name.class);
        CourseID courseID = new CourseID(acronymDouble, nameDouble);
        //Act
        String result = courseID.toString();
        //Assert
        assertEquals(courseID.toString(), result);
    }

    @Test
    void shouldReturnEqualsHashCode() {
        //Arrange
        Acronym acronymDouble = mock(Acronym.class);
        Name nameDouble = mock(Name.class);
        CourseID courseID = new CourseID(acronymDouble, nameDouble);
        //Act
        int hash1 = courseID.hashCode();
        int hash2 = courseID.hashCode();
        //Assert
        assertEquals(hash1,hash2);
    }

    @Test
    void shouldReturnNotEqualsHashCode() {
        //Arrange
        Acronym acronymDouble = mock(Acronym.class);
        Name nameDouble = mock(Name.class);
        Acronym acronymDouble2 = mock(Acronym.class);
        Name nameDouble2 = mock(Name.class);
        CourseID courseID = new CourseID(acronymDouble, nameDouble);
        CourseID courseID2 = new CourseID(acronymDouble2, nameDouble2);
        //Act
        int hash1 = courseID.hashCode();
        int hash2 = courseID2.hashCode();
        //Assert
        assertNotEquals(hash1,hash2);
    }

    @Test
    void shouldReturnAcronym() {
        //Arrange
        Acronym acronymDouble = mock(Acronym.class);
        Name nameDouble = mock(Name.class);
        CourseID courseID = new CourseID(acronymDouble, nameDouble);
        //Act
        Acronym result = courseID.getAcronym();
        //Assert
        assertEquals(acronymDouble, result);
    }

    @Test
    void shouldReturnName() {
        //Arrange
        Acronym acronymDouble = mock(Acronym.class);
        Name nameDouble = mock(Name.class);
        CourseID courseID = new CourseID(acronymDouble, nameDouble);
        //Act
        Name result = courseID.getName();
        //Assert
        assertEquals(nameDouble, result);
    }
}