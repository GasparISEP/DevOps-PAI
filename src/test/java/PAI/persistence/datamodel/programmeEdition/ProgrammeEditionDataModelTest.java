package PAI.persistence.datamodel.programmeEdition;

import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
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
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        // act
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearIDDataModel);
        // assert
        assertNotNull(programmeEditionDataModel);
    }

    @Test
    void shouldNotCreateProgrammeEditionDataModelIfProgrammeEditionIdNull() {
        // arrange
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionDataModel(null, programmeIDDataModel, schoolYearIDDataModel)
        );
    }

    @Test
    void shouldNotCreateProgrammeEditionDataModelIfProgrammeIdNull() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEditionDataModel(programmeEditionIdDataModel, null, schoolYearIDDataModel)
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
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearIDDataModel);
        // act
        ProgrammeEditionIdDataModel pEIDDataModel = programmeEditionDataModel.getProgrammeEditionIDDataModel();
        // assert
        assertNotNull(pEIDDataModel);
        assertEquals(programmeEditionIdDataModel, pEIDDataModel);
    }

    @Test
    void shouldReturnNullWhenTryToGetSchoolYearIDDataModelWhenProgrammeEditionDataModelIsCreatedWithEmptyConstructor() {
        // arrange
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel();
        // act
        SchoolYearIDDataModel SchoolYearIDDataModel = programmeEditionDataModel.getSchoolYearIDDataModel();
        // assert
        assertNull(SchoolYearIDDataModel);
    }

    @Test
    void shouldReturnSchoolYearIDDataModelWhenProgrammeEditionDataModelIsCreatedWithArguments() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearIDDataModel);
        // act
        SchoolYearIDDataModel result = programmeEditionDataModel.getSchoolYearIDDataModel();
        // assert
        assertEquals(schoolYearIDDataModel, result);
    }

    @Test
    void shouldReturnTrueWhenCompareProgrammeEditionDataModelToItSelf() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearIDDataModel);

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
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearIDDataModel);

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
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearIDDataModel);

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
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        ProgrammeEditionDataModel programmeEditionDataModel1 = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearIDDataModel);
        ProgrammeEditionDataModel programmeEditionDataModel2 = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearIDDataModel);

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
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        ProgrammeEditionDataModel programmeEditionDataModel1 = new ProgrammeEditionDataModel(programmeEditionIdDataModel1, programmeIDDataModel, schoolYearIDDataModel);
        ProgrammeEditionDataModel programmeEditionDataModel2 = new ProgrammeEditionDataModel(programmeEditionIdDataModel2, programmeIDDataModel, schoolYearIDDataModel);

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
        SchoolYearIDDataModel schoolYearIDDataModel1 = mock(SchoolYearIDDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel2 = mock(SchoolYearIDDataModel.class);
        ProgrammeEditionDataModel programmeEditionDataModel1 = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearIDDataModel1);
        ProgrammeEditionDataModel programmeEditionDataModel2 = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearIDDataModel2);

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
        SchoolYearIDDataModel schoolYearIDDataModel1 = mock(SchoolYearIDDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel2 = mock(SchoolYearIDDataModel.class);
        ProgrammeEditionDataModel programmeEditionDataModel1 = new ProgrammeEditionDataModel(programmeEditionIdDataModel1, programmeIDDataModel1, schoolYearIDDataModel1);
        ProgrammeEditionDataModel programmeEditionDataModel2 = new ProgrammeEditionDataModel(programmeEditionIdDataModel2, programmeIDDataModel2, schoolYearIDDataModel2);

        // Act
        boolean result = programmeEditionDataModel1.equals(programmeEditionDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnValidHashCodeMethod() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        ProgrammeEditionDataModel programmeEditionDataModel = new ProgrammeEditionDataModel(programmeEditionIdDataModel, programmeIDDataModel, schoolYearIDDataModel);

        // Act
        int result = programmeEditionDataModel.hashCode();

        // Assert
        assertEquals(result, programmeEditionIdDataModel.hashCode());
    }
}