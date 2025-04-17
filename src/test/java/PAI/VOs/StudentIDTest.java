package PAI.VOs;

import PAI.domain.Student;
import PAI.domain.Teacher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;

class StudentIDTest {


   @Test
    void validUniqueNumberCreatesStudentID () {

        // Arrange
        int uniqueNumber = 1234567;
        StudentID studentID = new StudentID(uniqueNumber);

        // Assert
        assertNotNull(studentID);
    }

    @Test
    void InvalidUniqueNumberDoesNotCreateStudentID() {

        // Arrange
        int uniqueNumber = 0;

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudentID(uniqueNumber);
        });

        //Assert
        assertEquals("Invalid unique number", exception.getMessage());
    }

    @Test
    void InvalidUniqueNumberDoesNotCreateStudentID2() {

        // Arrange
        int uniqueNumber = 200000000;

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudentID(uniqueNumber);
        });

        //Assert
        assertEquals("Invalid unique number", exception.getMessage());
    }

    @Test
    void InvalidUniqueNumberDoesNotCreateStudentID3() {

        // Arrange
        int uniqueNumber = 2134567;

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudentID(uniqueNumber);
        });

        //Assert
        assertEquals("Invalid unique number", exception.getMessage());
    }



    @Test
    void equalsMethodIsEqual () {
        int uniqueNumber = 1234567;

        // Arrange
        StudentID studentID1 = new StudentID(uniqueNumber);
        StudentID studentID2 = new StudentID(uniqueNumber);

        // Act
        boolean result = studentID1.equals(studentID2);

        // Assert
        assertTrue(result);
    }

    @Test
    void equalsMethodIsNotEqual () {
        // Arrange
        int uniqueNumber = 1234567;
        int uniqueNumber2 = 1234568;


        StudentID studentID1 = new StudentID(uniqueNumber);
        StudentID studentID2 = new StudentID(uniqueNumber2);

        // Act
        boolean result = studentID1.equals(studentID2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfObjectsAreTheSame() {
        //arrange
        int uniqueNumber = 1234567;
        StudentID id = new StudentID(uniqueNumber);

        //act
        boolean result = id.equals(id);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectToCompareIsNotAStudentID () {
        //arrange
        int uniqueNumber = 1234567;
        StudentID id = new StudentID(uniqueNumber);
        Object object = mock(Object.class);

        //act
        boolean result = id.equals(object);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldGetUniqueNumber () {
        // Arrange
        int uniqueNumber = 1234567;
        StudentID studentID = new StudentID(uniqueNumber);

        // Act
        int result = studentID.getUniqueNumber();

        //Assert
        assertEquals(1234567, result);
    }

    @Test
    void isEqualsMethodShouldReturnTrue () {

        // Arrange
        int uniqueNumber = 1234567;
        StudentID studentID1 = new StudentID(uniqueNumber);
        StudentID studentID2 = new StudentID(uniqueNumber);

        // Act
        boolean result = studentID1.isEquals(studentID2);

        // Assert
        assertTrue(result);
    }

    @Test
    void isEqualsMethodShouldReturnFalse () {

        // Arrange
        int uniqueNumber = 1234567;
        int uniqueNumber2 = 1234568;

        StudentID studentID1 = new StudentID(uniqueNumber);
        StudentID studentID2 = new StudentID(uniqueNumber2);

        // Act
        boolean result = studentID1.isEquals(studentID2);

        // Assert
        assertFalse(result);
    }
}


