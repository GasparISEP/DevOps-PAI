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

    @Test
    void shouldNotCreateProgrammeEditionDataModelIfProgrammeEditionIdNull() {
        // arrange
        ProgrammeIDDataModel programmeID = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionDataModel(null, programmeID, schoolYearID )
        );
    }

    @Test
    void shouldNotCreateProgrammeEditionDataModelIfProgrammeIdNull() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionId = mock(ProgrammeEditionIdDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionDataModel(programmeEditionId, null, schoolYearID )
        );
    }

    @Test
    void shouldNotCreateProgrammeEditionDataModelIfSchoolYearIdNull() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionId = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeID = mock(ProgrammeIDDataModel.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionDataModel(programmeEditionId, programmeID, null)
        );
    }

    @Test
    void shouldReturnNullWhenTryToGetProgrammeEditionIDDataModelWhenProgrammeEditionDataModelIsCreatedWithEmptyConstructor() {
        // arrange
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel();
        // act
        ProgrammeEditionIdDataModel pEIDDataModel = programmeEditionDataModel.getProgrammeEditionIDDataModel();
        // assert
        assertNull(pEIDDataModel);
    }

    @Test
    void shouldReturnProgrammeEditionIDDataModelWhenProgrammeEditionDataModelIsCreatedWithArguments() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionId = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeID = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionId, programmeID, schoolYearID );
        // act
        ProgrammeEditionIdDataModel pEIDDataModel = programmeEditionDataModel.getProgrammeEditionIDDataModel();
        // assert
        assertNotNull(pEIDDataModel);
        assertEquals(programmeEditionId, pEIDDataModel);
    }

    @Test
    void shouldReturnProgrammeEditionDataModelSchoolYearId() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionId = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeID = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionId, programmeID, schoolYearID );
        // act
        UUID result = programmeEditionDataModel.getSchoolYearID();
        // assert
        assertEquals(schoolYearID, result);
    }
}