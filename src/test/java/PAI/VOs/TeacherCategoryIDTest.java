package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryIDTest {

    @Test
    void shouldCreateUniqueIds() {
        TeacherCategoryID id1 = new TeacherCategoryID();
        TeacherCategoryID id2 = new TeacherCategoryID();

        assertNotNull(id1.getValue());
        assertNotNull(id2.getValue());
        assertNotEquals(id1, id2); // Randomly generated, should be different
    }

    @Test
    void shouldWrapExistingUUIDCorrectly() {
        UUID uuid = UUID.randomUUID();
        TeacherCategoryID id = new TeacherCategoryID(uuid);

        assertEquals(uuid, id.getValue());
    }

    @Test
    void shouldThrowExceptionIfUUIDIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new TeacherCategoryID(null));
    }

    @Test
    void shouldBeEqualIfUUIDsAreEqual() {
        UUID uuid = UUID.randomUUID();
        TeacherCategoryID id1 = new TeacherCategoryID(uuid);
        TeacherCategoryID id2 = new TeacherCategoryID(uuid);

        assertEquals(id1, id2);
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    void shouldReturnProperToString() {
        UUID uuid = UUID.randomUUID();
        TeacherCategoryID id = new TeacherCategoryID(uuid);

        assertEquals(uuid.toString(), id.toString());
    }
}