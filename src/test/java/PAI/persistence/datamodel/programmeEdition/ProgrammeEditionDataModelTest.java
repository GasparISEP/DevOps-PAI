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
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        // act
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearID );
        // assert
        assertNotNull(programmeEditionDataModel);
    }

    @Test
    void shouldNotCreateProgrammeEditionDataModelIfProgrammeEditionIdNull() {
        // arrange
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionDataModel(null, programmeIDDataModel, schoolYearID )
        );
    }

    @Test
    void shouldNotCreateProgrammeEditionDataModelIfProgrammeIdNull() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionDataModel(programmeEditionIdDataModel, null, schoolYearID )
        );
    }

    @Test
    void shouldNotCreateProgrammeEditionDataModelIfSchoolYearIdNull() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, null)
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
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearID);
        // act
        ProgrammeEditionIdDataModel pEIDDataModel = programmeEditionDataModel.getProgrammeEditionIDDataModel();
        // assert
        assertNotNull(pEIDDataModel);
        assertEquals(programmeEditionIdDataModel, pEIDDataModel);
    }

    @Test
    void shouldReturnNullWhenTryToGetProgrammeIDDataModelWhenProgrammeEditionDataModelIsCreatedWithEmptyConstructor() {
        // arrange
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel();
        // act
        ProgrammeIDDataModel pIDDataModel = programmeEditionDataModel.getProgrammeIDDataModel();
        // assert
        assertNull(pIDDataModel);
    }

    @Test
    void shouldReturnProgrammeIDDataModelWhenProgrammeEditionDataModelIsCreatedWithArguments() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearID);
        // act
        ProgrammeIDDataModel pIDDataModel = programmeEditionDataModel.getProgrammeIDDataModel();
        // assert
        assertNotNull(pIDDataModel);
        assertEquals(programmeIDDataModel, pIDDataModel);
    }

    @Test
    void shouldReturnProgrammeEditionDataModelSchoolYearId() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearID );
        // act
        UUID result = programmeEditionDataModel.getSchoolYearID();
        // assert
        assertEquals(schoolYearID, result);
    }


}