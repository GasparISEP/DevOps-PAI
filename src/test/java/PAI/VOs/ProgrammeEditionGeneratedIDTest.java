package PAI.VOs;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionGeneratedIDTest {

    @Test
    void shouldReturnTestConstructorWithValidUUID() {

        // arrange
        UUID uuid = java.util.UUID.randomUUID();

        // act
        ProgrammeEditionGeneratedID id = new ProgrammeEditionGeneratedID(uuid);

        // assert
        assertNotNull(uuid);
    }

    @Test
    void shouldReturnTestConstructorWithNullUUID() {

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionGeneratedID(null));
    }

    @Test
    void shouldTestToString() {

        // arrange
        UUID uuid = java.util.UUID.randomUUID();
        ProgrammeEditionGeneratedID id = new ProgrammeEditionGeneratedID(uuid);

        // act
        String idString = id.toString();

        // assert
        assertNotNull(uuid.toString(),idString);
    }

    @Test
    void shouldReturnTrue_WhenUUIDAreEquals() {

        // arrange
        UUID uuid = UUID.randomUUID();
        ProgrammeEditionGeneratedID id1 = new ProgrammeEditionGeneratedID(uuid);
        ProgrammeEditionGeneratedID id2 = new ProgrammeEditionGeneratedID(uuid);

        // act & assert
        assertEquals(id1, id2);
    }

    @Test
    void shouldReturnFalse_WhenUUIDAreDifferent() {

        // arrange
        ProgrammeEditionGeneratedID id1 = new ProgrammeEditionGeneratedID();
        ProgrammeEditionGeneratedID id2 = new ProgrammeEditionGeneratedID();

        // act & assert
        assertNotEquals(id1, id2);
    }
}