package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UniqueNumberTest {

    @Test
    void validUniqueNumberCreatesStudentID () {

        // Arrange
        StudentID studentID = mock(StudentID.class);

        // Assert
        assertNotNull(studentID);
    }

    @Test
    void nullUniqueNumberDoesNotCreateStudentID() {

        // Arrange
        NIF nifDouble = mock(NIF.class);

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StudentID(null, nifDouble);
        });

        //Assert
        assertEquals("Student's unique number cannot be null!", exception.getMessage());
    }

    @Test
    void shouldGetUniqueNumber () {
        // Arrange
        StudentID studentID = mock(StudentID.class);
        UniqueNumber uniqueNumberDouble = mock(UniqueNumber.class);
        when(studentID.getUniqueNumber()).thenReturn(uniqueNumberDouble);

        // Act
        UniqueNumber result = studentID.getUniqueNumber();

        //Assert
        assertEquals(uniqueNumberDouble, result);
    }

    @Test
    void shouldThrowExceptionForInvalidLowUniqueNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new UniqueNumber(1000000);
        });
    }

    @Test
    void shouldThrowExceptionForInvalidHighUniqueNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new UniqueNumber(2000000);
        });
    }

    @Test
    void shouldBeEqualIfUniqueNumbersAreSame() {
        UniqueNumber u1 = new UniqueNumber(1500001);
        UniqueNumber u2 = new UniqueNumber(1500001);
        assertEquals(u1, u2);
    }

    @Test
    void shouldNotBeEqualIfUniqueNumbersAreDifferent() {
        UniqueNumber u1 = new UniqueNumber(1500001);
        UniqueNumber u2 = new UniqueNumber(1500002);
        assertNotEquals(u1, u2);
    }

    @Test
    void shouldBeEqualToItself() {
        UniqueNumber u = new UniqueNumber(1500001);
        assertEquals(u, u);
    }

    @Test
    void shouldNotBeEqualToNull() {
        UniqueNumber u = new UniqueNumber(1500001);
        assertNotEquals(u, null);
    }

    @Test
    void shouldNotBeEqualToDifferentClassObject() {
        UniqueNumber u = new UniqueNumber(1500001);
        String other = "1500001";
        assertNotEquals(u, other);
    }
}
