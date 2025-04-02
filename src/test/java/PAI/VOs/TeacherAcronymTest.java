package PAI.VOs;

import org.junit.jupiter.api.Test;

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

    @Test
    void shouldNotReturnAcronymIfNull() {
        //Act+Assert
        assertThrows(IllegalArgumentException.class, () -> new TeacherAcronym(null));
    }

    @Test
    void shouldNotReturnAcronymIfBlank()  {
        assertThrows(IllegalArgumentException.class, () -> new TeacherAcronym(""));
    }

    @Test
    void shouldNotReturnAcronymIfContainsNumbers()  {
        assertThrows(IllegalArgumentException.class, () -> new TeacherAcronym("AB1"));
    }

    @Test
    void shouldNotReturnAcronymIfContainsLowerCases() {
        assertThrows(IllegalArgumentException.class, () -> new TeacherAcronym("ABc"));
    }

    @Test
    void shouldNotReturnAcronymIfContainsSpecialChars() {
        assertThrows(IllegalArgumentException.class, () -> new TeacherAcronym("AB@"));
    }

    @Test
    void shouldNotReturnAcronymIfMoreThan3Letters() {
        assertThrows(IllegalArgumentException.class, () -> new TeacherAcronym("ABCD"));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenTeacherAcronymIsNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new TeacherAcronym(null);
        });
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
}