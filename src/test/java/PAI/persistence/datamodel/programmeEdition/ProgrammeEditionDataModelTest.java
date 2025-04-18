package PAI.persistence.datamodel.programmeEdition;

import PAI.persistence.datamodel.ProgrammeIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEditionDataModelTest {

    @Test
    void shouldCreateProgrammeEditionDataModelWithoutParameters() {
        // arrange
        // act
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel();
        // assert
        assertNotNull(programmeEditionDataModel);
    }

    @Test
    void shouldCreateProgrammeEditionDataModelWithParameters() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionId = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeID = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        // act
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionId, programmeID, schoolYearID );
        // assert
        assertNotNull(programmeEditionDataModel);
    }

}