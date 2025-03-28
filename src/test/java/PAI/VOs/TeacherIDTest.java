package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TeacherIDTest {

    @Test
    void shouldCreateNewTeacherIDWithValidUUID() {
        // Act
        TeacherID teacherID = TeacherID.createNew();

        // Assert
        assertNotNull(teacherID);
    }

    @Test
    void shouldReturnA_UUID_WhenCallingIdentity() {
        // Act
        TeacherID firstTeacherID = TeacherID.createNew();

        // Assert
        assertNotNull(firstTeacherID.identity());
    }

    @Test
    void shouldGenerateUniqueTeacherIDsOnEachCall() {
        // Act
        TeacherID firstTeacherID = TeacherID.createNew();
        TeacherID secondTeacherID = TeacherID.createNew();

        // Assert
        assertNotEquals(firstTeacherID.identity(), secondTeacherID.identity());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenIdIsNull() {

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            try {
                // Access private constructor
                var constructor = TeacherID.class.getDeclaredConstructor(UUID.class);
                constructor.setAccessible(true);
                constructor.newInstance((Object) null); // Try to instance the constructor with a null value as input
            } catch (InvocationTargetException e) {
                throw e.getCause(); // Re-throw the original IllegalArgumentException
            }
        });
    }

    @Test
    void shouldReturnTrueWhenObjectsAreTheSame(){
        // Arrange
        TeacherID teacherID = TeacherID.createNew();

        // Act
        boolean result = teacherID.sameAs(teacherID);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenUUIDIsTheSame() {
        // Arrange
        TeacherID teacherID = TeacherID.createNew();
        UUID id = teacherID.identity();
        TeacherID teacherID2;

        try {
            var constructor = TeacherID.class.getDeclaredConstructor(UUID.class);
            constructor.setAccessible(true);
            teacherID2 = constructor.newInstance(id);
        } catch (Exception e) {
            teacherID2 = null;
        }

        // Act
        boolean result = teacherID.sameAs(teacherID2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenObjectAndTeacherIDAreNotTheSame(){
        // Arrange
        TeacherID teacherID = TeacherID.createNew();
        Object otherObject = new Object();

        // Act
        boolean result = teacherID.sameAs(otherObject);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenUUIDsAreNotTheSame() {
        // Arrange
        TeacherID teacherID = TeacherID.createNew();
        TeacherID teacherID2 = TeacherID.createNew();

        // Act
        boolean result = teacherID.sameAs(teacherID2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenTeacherIDIsComparedWithNullObject() {
        // Arrange
        TeacherID teacherID = TeacherID.createNew();
        TeacherID teacherID2 = null;

        // Act
        boolean result = teacherID.sameAs(teacherID2);

        // Assert
        assertFalse(result);
    }
}