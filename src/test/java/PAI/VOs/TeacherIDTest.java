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
    void shouldReturnA_UUID_WhenGettingID() {
        // Act
        TeacherID firstTeacherID = TeacherID.createNew();

        // Assert
        assertNotNull(firstTeacherID.getID());
    }

    @Test
    void shouldGenerateUniqueTeacherIDsOnEachCall() {
        // Act
        TeacherID firstTeacherID = TeacherID.createNew();
        TeacherID secondTeacherID = TeacherID.createNew();

        // Assert
        assertNotEquals(firstTeacherID.getID(), secondTeacherID.getID());
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
            } catch (InvocationTargetException e) { // Catches InvocationTargetException
                throw e.getCause(); // Re-throw the original IllegalArgumentException
            }
        });
    }
}