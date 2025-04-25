package PAI.VOs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeacherAcronymTest {

    @Test
    void shouldCreateAcronym() throws Exception {
        //Arrange
        TeacherAcronym teacherAcronym1 = new TeacherAcronym("ABC");

        //Act+Assert
        assertNotNull(teacherAcronym1);
    }

    @Test
    void shouldReturnCorrectAcronym() throws Exception {
        //Arrange
        String acronym1 = "ABC";
        TeacherAcronym teacherAcronym2 = new TeacherAcronym(acronym1);

        //Act
        String acronym2String = teacherAcronym2.getAcronym();

        //Assert
        assertEquals(acronym1, acronym2String);
    }

    static Stream<Arguments> testInvalidTeacherAcronym(){
        return Stream.of(
                Arguments.of("", "Acronym must be a 3 capital letter non-empty String."),
                Arguments.of(" ", "Acronym must be a 3 capital letter non-empty String."),
                Arguments.of(null, "Acronym must be a 3 capital letter non-empty String."),
                Arguments.of("AB", "Acronym must contain only three capital letters."),
                Arguments.of("ABCD", "Acronym must contain only three capital letters."),
                Arguments.of("abc", "Acronym must contain only three capital letters."),
                Arguments.of("A 99", "Acronym must contain only three capital letters."),
                Arguments.of("9", "Acronym must contain only three capital letters."),
                Arguments.of("Ç99", "Acronym must contain only three capital letters."),
                Arguments.of("B-12", "Acronym must contain only three capital letters."),
                Arguments.of("Z1SD2", "Acronym must contain only three capital letters.")
        );
    }
    @ParameterizedTest
    @MethodSource("testInvalidTeacherAcronym")
    void shouldReturnExceptionWhenCreatingTeacherAcronymWithInvalidInputs(String teacherAcronym, String expectedMessage){
        //Arrange (provided by @MethodSource)
        //Act
        Executable action = () -> new TeacherAcronym(teacherAcronym);
        //Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, action);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldReturnTrueWhenObjectsAreTheSame(){
        // Arrange
        TeacherAcronym teacherAcronym = new TeacherAcronym("ABC");

        // Act
        boolean result = teacherAcronym.equals(teacherAcronym);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenTeacherAcronymIsTheSame() {
        // Arrange
        TeacherAcronym teacherAcronym1 = new TeacherAcronym("ABC");
        TeacherAcronym teacherAcronym2 = new TeacherAcronym("ABC");

        // Act
        boolean result = teacherAcronym1.equals(teacherAcronym2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenOtherObjectAndTeacherIDAreNotTheSame(){
        // Arrange
        TeacherAcronym teacherAcronym = new TeacherAcronym("ABC");
        Object otherObject = new Object();

        // Act
        boolean result = teacherAcronym.equals(otherObject);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenTeacherAcronymsAreNotTheSame() {
        // Arrange
        TeacherAcronym teacherAcronym1 = new TeacherAcronym("ABC");
        TeacherAcronym teacherAcronym2 = new TeacherAcronym("ABB");

        // Act
        boolean result = teacherAcronym1.equals(teacherAcronym2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenTeacherIDIsComparedWithNull() {
        // Arrange
        TeacherAcronym teacherAcronym1 = new TeacherAcronym("ABC");
        TeacherAcronym teacherAcronym2 = null;

        // Act
        boolean result = teacherAcronym1.equals(teacherAcronym2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnEqualWhenTwoObjectsHaveTheSameHashCode() {
        //Arrange
        TeacherAcronym teacherAcronym1 = new TeacherAcronym("ABC");
        TeacherAcronym teacherAcronym2 = new TeacherAcronym("ABC");
        //Act & Assert
        assertEquals(teacherAcronym1.hashCode(), teacherAcronym2.hashCode());
    }

    @Test
    public void shouldReturnNotEqualWhenTwoObjectsHaveDifferentHashCodes() {
        //Arrange
        TeacherAcronym teacherAcronym1 = new TeacherAcronym("ABC");
        TeacherAcronym teacherAcronym2 = new TeacherAcronym("ZXC");
        //Act & Assert
        assertNotEquals(teacherAcronym1.hashCode(), teacherAcronym2.hashCode());
    }

    @Test
    void toStringMethodShouldReturnRespectiveString () {
        // Arrange
        TeacherAcronym teacherAcronym1 = new TeacherAcronym("ABC");

        // Act
        String result = teacherAcronym1.toString();

        // Assert
        assertEquals("ABC", result);
    }
}