package PAI.persistence.datamodel.programmeEdition;

import org.junit.jupiter.api.Test;


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
        // act
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel);
        // assert
        assertNotNull(programmeEditionDataModel);
    }

    @Test
    void shouldNotCreateProgrammeEditionDataModelIfProgrammeEditionIdNull() {
        // arrange

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionDataModel(null)
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

        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel);
        // act
        ProgrammeEditionIdDataModel pEIDDataModel = programmeEditionDataModel.getProgrammeEditionIDDataModel();
        // assert
        assertNotNull(pEIDDataModel);
        assertEquals(programmeEditionIdDataModel, pEIDDataModel);
    }

    @Test
    void shouldReturnTrueWhenCompareProgrammeEditionDataModelToItSelf() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);

        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel);

        // Act
        boolean result = programmeEditionDataModel.equals(programmeEditionDataModel);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenCompareProgrammeEditionDataModelToNull() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);

        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel);

        // Act
        boolean result = programmeEditionDataModel.equals(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenCompareProgrammeEditionDataModelToDifferentClass() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);

        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel);

        // Act
        boolean result = programmeEditionDataModel.equals(programmeEditionIdDataModel);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenCompareProgrammeEditionDataModelToADifferentProgrammeEditionDataModelInstanceButWithSameAttributes() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);

        ProgrammeEditionDataModel programmeEditionDataModel1 = new ProgrammeEditionDataModel(programmeEditionIdDataModel);
        ProgrammeEditionDataModel programmeEditionDataModel2 = new ProgrammeEditionDataModel(programmeEditionIdDataModel);

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

        ProgrammeEditionDataModel programmeEditionDataModel1 = new ProgrammeEditionDataModel(programmeEditionIdDataModel1);
        ProgrammeEditionDataModel programmeEditionDataModel2 = new ProgrammeEditionDataModel(programmeEditionIdDataModel2);

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

        ProgrammeEditionDataModel programmeEditionDataModel1 = new ProgrammeEditionDataModel(programmeEditionIdDataModel1);
        ProgrammeEditionDataModel programmeEditionDataModel2 = new ProgrammeEditionDataModel(programmeEditionIdDataModel2);

        // Act
        boolean result = programmeEditionDataModel1.equals(programmeEditionDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnValidHashCodeMethod() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);

        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel);

        // Act
        int result = programmeEditionDataModel.hashCode();

        // Assert
        assertEquals(result, programmeEditionIdDataModel.hashCode());
    }
}