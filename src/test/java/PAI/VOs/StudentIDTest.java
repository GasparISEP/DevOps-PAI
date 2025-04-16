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
        UniqueNumber uniqueNumberDouble= mock(UniqueNumber.class);
        NIF nifDouble = mock(NIF.class);
        StudentID studentID = new StudentID(uniqueNumberDouble, nifDouble);

        // Assert
        assertNotNull(studentID);
    }

    @Test
    void nullUniqueNumberDoesNotCreateStudentID() {

        // Arrange
        UniqueNumber uniqueNumberDouble = null;
        NIF nifDouble = mock(NIF.class);

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudentID(uniqueNumberDouble, nifDouble);
        });

        //Assert
        assertEquals("Student's unique number cannot be null!", exception.getMessage());
    }

    @Test
    void nullNIFDoesNotCreateStudentID() {

        // Arrange
        UniqueNumber uniqueNumberDouble = mock(UniqueNumber.class);
        NIF nifDouble = null;

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudentID(uniqueNumberDouble, nifDouble);
        });

        //Assert
        assertEquals("NIF cannot be null!", exception.getMessage());
    }

    @Test
    void equalsMethodIsEqual () {
       UniqueNumber uniqueNumberDouble = mock(UniqueNumber.class);
       NIF nifDouble = mock(NIF.class);
        // Arrange
        StudentID studentID1 = new StudentID(uniqueNumberDouble, nifDouble);
        StudentID studentID2 = new StudentID(uniqueNumberDouble, nifDouble);

        // Act
        boolean result = studentID1.equals(studentID2);

        // Assert
        assertTrue(result);
    }

    @Test
    void equalsMethodIsNotEqual () {
        // Arrange
       UniqueNumber uniqueNumberDouble1 = mock(UniqueNumber.class);
       UniqueNumber uniqueNumberDouble2 = mock(UniqueNumber.class);
       NIF nifDouble1 = mock(NIF.class);
       NIF nifDouble2 = mock(NIF.class);

        StudentID studentID1 = new StudentID(uniqueNumberDouble1, nifDouble1);
        StudentID studentID2 = new StudentID(uniqueNumberDouble2, nifDouble2);

        // Act
        boolean result = studentID1.equals(studentID2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfObjectsAreTheSame() {
        //arrange
        UniqueNumber uniqueNumberDouble = mock(UniqueNumber.class);
        NIF nifDouble = mock(NIF.class);
        StudentID id = new StudentID(uniqueNumberDouble, nifDouble);

        //act
        boolean result = id.equals(id);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectToCompareIsNotAStudentID () {
        //arrange
        UniqueNumber uniqueNumberDouble = mock(UniqueNumber.class);
        NIF nifDouble = mock(NIF.class);
        StudentID id = new StudentID(uniqueNumberDouble, nifDouble);
        Object object = mock(Object.class);

        //act
        boolean result = id.equals(object);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldGetUniqueNumber () {
        // Arrange
        UniqueNumber uniqueNumberDouble = mock(UniqueNumber.class);
        NIF nifDouble = mock(NIF.class);
        StudentID studentID = new StudentID(uniqueNumberDouble, nifDouble);

        // Act
        UniqueNumber result = studentID.getUniqueNumber();

        //Assert
        assertEquals(uniqueNumberDouble, result);
    }

    @Test
    void isEqualsMethodShouldReturnTrue () {

        // Arrange
        UniqueNumber uniqueNumberDouble = mock(UniqueNumber.class);
        NIF nifDouble = mock(NIF.class);
        StudentID studentID1 = new StudentID(uniqueNumberDouble, nifDouble);
        StudentID studentID2 = new StudentID(uniqueNumberDouble, nifDouble);

        // Act
        boolean result = studentID1.isEquals(studentID2);

        // Assert
        assertTrue(result);
    }

    @Test
    void isEqualsMethodShouldReturnFalse () {

        // Arrange
        UniqueNumber uniqueNumberDouble1 = mock(UniqueNumber.class);
        UniqueNumber uniqueNumberDouble2 = mock(UniqueNumber.class);
        NIF nifDouble1 = mock(NIF.class);
        NIF nifDouble2 = mock(NIF.class);

        StudentID studentID1 = new StudentID(uniqueNumberDouble1, nifDouble1);
        StudentID studentID2 = new StudentID(uniqueNumberDouble2, nifDouble2);

        // Act
        boolean result = studentID1.isEquals(studentID2);

        // Assert
        assertFalse(result);
    }

    @Test
    void getNIF() {

       // Arrange
        UniqueNumber uniqueNumberDouble = mock(UniqueNumber.class);
        NIF nifDouble = mock(NIF.class);
        StudentID studentID = new StudentID(uniqueNumberDouble, nifDouble);

        // Act
        NIF result = studentID.getNIF();

        //Assert
        assertEquals(nifDouble, result);
        }
    }
