package PAI.mapper.programmeEdition;

import PAI.VOs.ProgrammeEditionGeneratedID;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionGeneratedIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionGeneratedIDMapperImplTest {

    @Test
    void toDomain_ShouldMapCorrectly() throws Exception {
        // arrange
        ProgrammeEditionGeneratedIDMapperImpl mapper = new ProgrammeEditionGeneratedIDMapperImpl();
        UUID generatedId = java.util.UUID.randomUUID();
        ProgrammeEditionGeneratedIDDataModel dataModel = new ProgrammeEditionGeneratedIDDataModel(generatedId);

        // act
        ProgrammeEditionGeneratedID domain = mapper.toDomain(dataModel);

        // assert
        assertNotNull(domain);
        assertEquals(generatedId, domain.getProgrammeEditionGID());
    }

    @Test
    void toDomain_ShouldThrowException_WhenDataModelIsNull() {
        // arrange
        ProgrammeEditionGeneratedIDMapperImpl mapper = new ProgrammeEditionGeneratedIDMapperImpl();

        // act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> mapper.toDomain(null));
        assertEquals("ProgrammeEditionGeneratedIDDataModel cannot be null", exception.getMessage());
    }

    @Test
    void toDataModel_ShouldMapCorrectly() throws Exception {
        // arrange
        ProgrammeEditionGeneratedIDMapperImpl mapper = new ProgrammeEditionGeneratedIDMapperImpl();
        UUID generatedId = java.util.UUID.randomUUID();
        ProgrammeEditionGeneratedID domain = new ProgrammeEditionGeneratedID(generatedId);

        // act
        ProgrammeEditionGeneratedIDDataModel dataModel = mapper.toDataModel(domain);

        // assert
        assertNotNull(dataModel);
        assertEquals(generatedId, dataModel.getGeneratedId());
    }

    @Test
    void toDataModel_ShouldThrowException_WhenDomainIsNull() {
        // arrange
        ProgrammeEditionGeneratedIDMapperImpl mapper = new ProgrammeEditionGeneratedIDMapperImpl();

        // act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> mapper.toDataModel(null));
        assertEquals("ProgrammeEditionGeneratedID cannot be null", exception.getMessage());
    }

}