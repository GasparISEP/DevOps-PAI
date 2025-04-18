package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeacherIDTest {

    @Test
    void shouldCreateNewTeacherIDWithValidAcronym() {
        //Arrange
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);

        // Act
        TeacherID teacherID = new TeacherID(teacherAcronym);

        // Assert
        assertNotNull(teacherID);
    }

    @Test
    void shouldReturnATeacherAcronymWhenCallingIdentity() {
        //Arrange
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        TeacherID firstTeacherID = new TeacherID(teacherAcronym);

        // Act
        TeacherAcronym result = firstTeacherID.getTeacherAcronym();

        // Assert
        assertEquals(result, teacherAcronym);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenTeacherAcronymIsNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new TeacherID(null);
        });
    }

    @Test
    void shouldReturnTrueWhenObjectsAreTheSame(){
        // Arrange
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        TeacherID teacherID = new TeacherID(teacherAcronym);

        // Act
        boolean result = teacherID.equals(teacherID);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenTeacherAcronymIsTheSame() {
        // Arrange
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        TeacherID teacherID = new TeacherID(teacherAcronym);
        TeacherID teacherID2 = new TeacherID(teacherAcronym);

        // Act
        boolean result = teacherID.equals(teacherID2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenOtherObjectAndTeacherIDAreNotTheSame(){
        // Arrange
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        TeacherID teacherID = new TeacherID(teacherAcronym);
        Object otherObject = new Object();

        // Act
        boolean result = teacherID.equals(otherObject);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenTeacherAcronymsAreNotTheSame() {
        // Arrange
        TeacherAcronym teacherAcronym1 = mock(TeacherAcronym.class);
        TeacherAcronym teacherAcronym2 = mock(TeacherAcronym.class);
        TeacherID teacherID1 = new TeacherID(teacherAcronym1);
        TeacherID teacherID2 = new TeacherID(teacherAcronym2);

        // Act
        boolean result = teacherID1.equals(teacherID2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenTeacherIDIsComparedWithNull() {
        // Arrange
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        TeacherID teacherID = new TeacherID(teacherAcronym);
        TeacherID teacherID2 = null;

        // Act
        boolean result = teacherID.equals(teacherID2);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnEqualWhenTwoObjectsHaveTheSameHashCode() {
        //Arrange
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        TeacherID teacherId1 = new TeacherID(teacherAcronym);
        TeacherID teacherId2 = new TeacherID(teacherAcronym);
        //Act & Assert
        assertEquals(teacherId1.hashCode(), teacherId2.hashCode());
    }

    @Test
    public void shouldReturnNotEqualWhenTwoObjectsHaveDifferentHashCodes() {
        //Arrange
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        TeacherAcronym teacherAcronym2 = mock(TeacherAcronym.class);
        TeacherID teacherId1 = new TeacherID(teacherAcronym);
        TeacherID teacherId2 = new TeacherID(teacherAcronym2);
        //Act & Assert
        assertNotEquals(teacherId1.hashCode(), teacherId2.hashCode());
    }
}