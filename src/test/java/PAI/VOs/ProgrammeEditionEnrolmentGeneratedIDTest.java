package PAI.VOs;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionEnrolmentGeneratedIDTest {

    @Test
    void shouldGenerateRandomUUIDWhenUsingEmptyConstructor() {
        ProgrammeEditionEnrolmentGeneratedID id = new ProgrammeEditionEnrolmentGeneratedID();

        assertNotNull(id.toUUID());
        assertInstanceOf(UUID.class, id.toUUID());
    }

    @Test
    void shouldStoreProvidedUUID() {
        UUID expectedUUID = UUID.randomUUID();
        ProgrammeEditionEnrolmentGeneratedID id = new ProgrammeEditionEnrolmentGeneratedID(expectedUUID);

        assertEquals(expectedUUID, id.toUUID());
    }

    @Test
    void toStringShouldReturnUUIDAsString() {
        UUID expectedUUID = UUID.randomUUID();
        ProgrammeEditionEnrolmentGeneratedID id = new ProgrammeEditionEnrolmentGeneratedID(expectedUUID);

        assertEquals(expectedUUID.toString(), id.toString());
    }

    @Test
    void idsWithSameUUIDShouldBeEqualByUUID() {
        UUID uuid = UUID.randomUUID();
        ProgrammeEditionEnrolmentGeneratedID id1 = new ProgrammeEditionEnrolmentGeneratedID(uuid);
        ProgrammeEditionEnrolmentGeneratedID id2 = new ProgrammeEditionEnrolmentGeneratedID(uuid);

        assertEquals(id1.toUUID(), id2.toUUID());
    }

}