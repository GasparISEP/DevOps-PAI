package PAI.persistence.datamodel.programmeEdition;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionGeneratedIDDataModelTest {

    @Test
    void shouldCreateWithValidUUID() {

        // arrange
        UUID uuid = UUID.randomUUID();

        // act
        ProgrammeEditionGeneratedIDDataModel idModel = new ProgrammeEditionGeneratedIDDataModel(uuid);

        // assert
        assertNotNull(idModel);
        assertEquals(uuid, idModel.getGeneratedId());
    }

    @Test
    void shouldThrowExceptionWhenUUIDIsNull() {

        // act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionGeneratedIDDataModel(null));
    }

    @Test
    void shouldSetAndGetGeneratedId() {

        // arrange
        UUID uuid = UUID.randomUUID();
        ProgrammeEditionGeneratedIDDataModel idModel = new ProgrammeEditionGeneratedIDDataModel();

        // act
        idModel.setGeneratedId(uuid);

        // assert
        assertEquals(uuid, idModel.getGeneratedId());
    }
}