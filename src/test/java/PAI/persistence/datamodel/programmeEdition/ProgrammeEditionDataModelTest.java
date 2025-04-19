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
    void shouldReturnNullWhenTryToGetSchoolYearIDWhenProgrammeEditionDataModelIsCreatedWithEmptyConstructor() {
        // arrange
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel();
        // act
        UUID SchoolYearID = programmeEditionDataModel.getSchoolYearID();
        // assert
        assertNull(SchoolYearID);
    }

    @Test
    void shouldReturnSchoolYearIDWhenProgrammeEditionDataModelIsCreatedWithArguments() {
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

    @Test
    void shouldReturnTrueWhenCompareProgrammeEditionDataModelToItSelf() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearID );

        // Act
        boolean result = programmeEditionDataModel.equals(programmeEditionDataModel);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenCompareProgrammeEditionDataModelToNull() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearID );

        // Act
        boolean result = programmeEditionDataModel.equals(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenCompareProgrammeEditionDataModelToDifferentClass() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearID );

        // Act
        boolean result = programmeEditionDataModel.equals(programmeEditionIdDataModel);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenCompareProgrammeEditionDataModelToADifferentProgrammeEditionDataModelInstanceButWithSameAttributes() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        ProgrammeEditionDataModel programmeEditionDataModel1 = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearID );
        ProgrammeEditionDataModel programmeEditionDataModel2 = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearID );

        // Act
        boolean result = programmeEditionDataModel1.equals(programmeEditionDataModel2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenCompareProgrammeEditionDataModelToADifferentProgrammeEditionDataModelInstanceWithDifferentProgrammeEditionIDDataModel() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel1 = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel2 = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        ProgrammeEditionDataModel programmeEditionDataModel1 = new ProgrammeEditionDataModel(programmeEditionIdDataModel1, programmeIDDataModel, schoolYearID );
        ProgrammeEditionDataModel programmeEditionDataModel2 = new ProgrammeEditionDataModel(programmeEditionIdDataModel2, programmeIDDataModel, schoolYearID );

        // Act
        boolean result = programmeEditionDataModel1.equals(programmeEditionDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenCompareProgrammeEditionDataModelToADifferentProgrammeEditionDataModelInstanceWithDifferentProgrammeIDDataModel() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel1 = mock(ProgrammeIDDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel2 = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID = mock(UUID.class);
        ProgrammeEditionDataModel programmeEditionDataModel1 = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel1, schoolYearID );
        ProgrammeEditionDataModel programmeEditionDataModel2 = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel2, schoolYearID );

        // Act
        boolean result = programmeEditionDataModel1.equals(programmeEditionDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenCompareProgrammeEditionDataModelToADifferentProgrammeEditionDataModelInstanceWithDifferentSchoolYearID() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID1 = mock(UUID.class);
        UUID schoolYearID2 = mock(UUID.class);
        ProgrammeEditionDataModel programmeEditionDataModel1 = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearID1);
        ProgrammeEditionDataModel programmeEditionDataModel2 = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearID2);

        // Act
        boolean result = programmeEditionDataModel1.equals(programmeEditionDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenCompareProgrammeEditionDataModelToADifferentProgrammeEditionDataModelInstanceWithAllDifferentAttributes() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel1 = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel2 = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel1 = mock(ProgrammeIDDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel2 = mock(ProgrammeIDDataModel.class);
        UUID schoolYearID1 = mock(UUID.class);
        UUID schoolYearID2 = mock(UUID.class);
        ProgrammeEditionDataModel programmeEditionDataModel1 = new ProgrammeEditionDataModel(programmeEditionIdDataModel1, programmeIDDataModel1, schoolYearID1);
        ProgrammeEditionDataModel programmeEditionDataModel2 = new ProgrammeEditionDataModel(programmeEditionIdDataModel2, programmeIDDataModel2, schoolYearID2);

        // Act
        boolean result = programmeEditionDataModel1.equals(programmeEditionDataModel2);

        // Assert
        assertFalse(result);
    }
}