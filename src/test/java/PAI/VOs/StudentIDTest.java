package PAI.VOs;

import PAI.domain.Student;
import PAI.domain.Teacher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.mock;

class StudentIDTest {

    public static Stream<Arguments> validUniqueNumber() {
        return Stream.of(
                arguments(1000001),
                arguments(1999999)
        );
    }

    @ParameterizedTest
    @MethodSource("validUniqueNumber")
    void validUniqueNumberCreatesStudentID (int uniqueNumber) {
        // Arrange
        StudentID studentID = new StudentID(uniqueNumber);

        // Assert
        assertNotNull(studentID);
    }

    public static Stream<Arguments> invalidUniqueNumber() {
        return Stream.of(
                arguments(1000000, "Student's unique number is invalid!"),
                arguments(2000000, "Student's unique number is invalid!")
        );
    }

    @ParameterizedTest
    @MethodSource ("invalidUniqueNumber")
    void invalidUniqueNumberDoesNotCreateStudentID(int uniqueNumber, String expectedException) {
        // Arrange + Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudentID(uniqueNumber);
        });

        //Assert
        assertEquals(expectedException, exception.getMessage());
    }

    @Test
    void equalsMethodIsEqual () {
        // Arrange
        StudentID studentID1 = new StudentID(1000001);
        StudentID studentID2 = new StudentID(1000001);

        // Act
        boolean result = studentID1.equals(studentID2);

        // Assert
        assertTrue(result);
    }

    @Test
    void equalsMethodIsNotEqual () {
        // Arrange
        StudentID studentID1 = new StudentID(1000001);
        StudentID studentID2 = new StudentID(1000002);

        // Act
        boolean result = studentID1.equals(studentID2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfObjectsAreTheSame() {
        //arrange
        StudentID id = new StudentID(1000001);

        //act
        boolean result = id.equals(id);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectToCompareIsNotAStudentID () {
        //arrange
        StudentID id = new StudentID(1000001);
        Teacher teacher = mock(Teacher.class);

        //act
        boolean result = id.equals(teacher);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldGetUniqueNumber () {
        // Arrange
        StudentID studentID = new StudentID(1500000);

        // Act
        int result = studentID.getUniqueNumber();

        //Assert
        assertEquals(1500000, result);
    }

    @Test
    void isEqualsMethodShouldReturnTrue () {

        // Arrange
        StudentID studentID1 = new StudentID(1234567);
        StudentID studentID2 = new StudentID(1234567);

        // Act
        boolean result = studentID1.isEquals(studentID2);

        // Assert
        assertTrue(result);
    }

    @Test
    void isEqualsMethodShouldReturnFalse () {

        // Arrange
        StudentID studentID1 = new StudentID(1234567);
        StudentID studentID2 = new StudentID(1326546);

        // Act
        boolean result = studentID1.isEquals(studentID2);

        // Assert
        assertFalse(result);
    }
}