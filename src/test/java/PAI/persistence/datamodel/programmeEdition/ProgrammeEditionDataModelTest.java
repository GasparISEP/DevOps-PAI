package PAI.persistence.datamodel.programmeEdition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionDataModelTest {

    @Test
    void shouldCreateProgrammeEditionDataModelWithoutParameters() {
        // arrange
        // act
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel();
        // assert
        assertNotNull(programmeEditionDataModel);
    }

}