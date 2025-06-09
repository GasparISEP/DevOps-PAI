package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class StudentGradeGeneratedIDTest {

    @Test
    void shouldCreateStudentGradeGeneratedIDWithUUID() {
        UUID uuid = UUID.randomUUID();
        StudentGradeGeneratedID studentGradeId = new StudentGradeGeneratedID(uuid);
        assertNotNull(studentGradeId);
        assertEquals(uuid, studentGradeId.getStudentGradeGeneratedID());
    }

    @Test
    void shouldCreateStudentGradeGeneratedIDWithRandomUUID() {
        StudentGradeGeneratedID studentGradeId = new StudentGradeGeneratedID();
        assertNotNull(studentGradeId.getStudentGradeGeneratedID());
    }

    @Test
    void shouldThrowExceptionWhenCreatingStudentGradeGeneratedIDWithNullUUID() {
        assertThrows(IllegalArgumentException.class, () -> {
            new StudentGradeGeneratedID(null);
        });
    }

    @Test
    void shouldReturnTrueWhenComparingSameStudentGradeGeneratedID() {
        UUID uuid = UUID.randomUUID();
        StudentGradeGeneratedID studentGradeId = new StudentGradeGeneratedID(uuid);
        assertTrue(studentGradeId.equals(studentGradeId));
    }

    @Test
    void shouldReturnTrueWhenComparingEqualStudentGradeGeneratedIDs() {
        UUID uuid = UUID.randomUUID();
        StudentGradeGeneratedID studentGradeId1 = new StudentGradeGeneratedID(uuid);
        StudentGradeGeneratedID studentGradeId2 = new StudentGradeGeneratedID(uuid);
        assertTrue(studentGradeId1.equals(studentGradeId2));
    }

    @Test
    void shouldReturnFalseWhenComparingDifferentStudentGradeGeneratedIDs() {
        StudentGradeGeneratedID studentGradeId1 = new StudentGradeGeneratedID(UUID.randomUUID());
        StudentGradeGeneratedID studentGradeId2 = new StudentGradeGeneratedID(UUID.randomUUID());
        assertFalse(studentGradeId1.equals(studentGradeId2));
    }

    @Test
    void shouldReturnFalseWhenComparingStudentGradeGeneratedIDWithNull() {
        StudentGradeGeneratedID studentGradeId = new StudentGradeGeneratedID(UUID.randomUUID());
        assertFalse(studentGradeId.equals(null));
    }

    @Test
    void shouldReturnFalseWhenComparingStudentGradeGeneratedIDWithDifferentClass() {
        StudentGradeGeneratedID studentGradeId = new StudentGradeGeneratedID(UUID.randomUUID());
        Name name = new Name("John Doe");
        assertFalse(studentGradeId.equals(name));
    }

    @Test
    void shouldReturnSameHashCodeForEqualStudentGradeGeneratedIDs() {
        UUID uuid = UUID.randomUUID();
        StudentGradeGeneratedID studentGradeId1 = new StudentGradeGeneratedID(uuid);
        StudentGradeGeneratedID studentGradeId2 = new StudentGradeGeneratedID(uuid);
        assertEquals(studentGradeId1.hashCode(), studentGradeId2.hashCode());
    }

    @Test
    void shouldReturnDifferentHashCodeForDifferentStudentGradeGeneratedIDs() {
        StudentGradeGeneratedID studentGradeId1 = new StudentGradeGeneratedID(UUID.randomUUID());
        StudentGradeGeneratedID studentGradeId2 = new StudentGradeGeneratedID(UUID.randomUUID());
        assertNotEquals(studentGradeId1.hashCode(), studentGradeId2.hashCode());
    }

    @Test
    void shouldReturnCorrectStringRepresentationOfStudentGradeGeneratedID() {
        UUID uuid = UUID.randomUUID();
        StudentGradeGeneratedID studentGradeId = new StudentGradeGeneratedID(uuid);
        assertEquals(uuid.toString(), studentGradeId.toString());
    }

    @Test
    void shouldReturnFalseHashCodeForStudentGradeGeneratedID() {
        UUID uuid = UUID.randomUUID();
        StudentGradeGeneratedID studentGradeId = new StudentGradeGeneratedID();
        assertNotEquals(uuid.hashCode(), studentGradeId.hashCode());
    }

    @Test
    void shouldReturnCorrectStudentGradeGeneratedID() {
        UUID uuid = UUID.randomUUID();
        StudentGradeGeneratedID studentGradeId = new StudentGradeGeneratedID(uuid);
        assertEquals(uuid, studentGradeId.getStudentGradeGeneratedID());
    }

}