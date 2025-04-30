package PAI.persistence.datamodel.programmeEdition;

import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionIdDataModelTest {

    @Test
    void shouldCreateProgrammeEditionIdDataModelEmptyConstructor() {
        //arrange
        //act
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = new ProgrammeEditionIdDataModel();
        //assert
        assertNotNull(programmeEditionIdDataModel);
    }

    @Test
    void shouldCreateProgrammeEditionIdDataModelWithParametersIsolationTest() {
        // arrange
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        // act
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = new ProgrammeEditionIdDataModel(programmeIDDataModel, schoolYearIDDataModel);

        // assert
        assertNotNull(programmeEditionIdDataModel);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIDDataModelIsNull() {
        //arrange
        ProgrammeIDDataModel programmeIDDataModel = null;
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {new ProgrammeEditionIdDataModel(null, schoolYearIDDataModel);});
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearIDDataModelIsNull() {
        //arrange
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {new ProgrammeEditionIdDataModel(programmeIDDataModel, null);});
    }

    @Test
    void shouldReturnProgrammeIDDataModel() {
        //arrange
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = new ProgrammeEditionIdDataModel(programmeIDDataModel, schoolYearIDDataModel);
        //act
        ProgrammeIDDataModel result = programmeEditionIdDataModel.getProgrammeIdDataModel();
        //assert
        assertEquals(programmeIDDataModel, result);
    }

    @Test
    void shouldReturnSchoolYearIDDataModel() {
        //arrange
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        SchoolYearIDDataModel schoolYearIDDataModel = mock(SchoolYearIDDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionIdDataModel = new ProgrammeEditionIdDataModel(programmeIDDataModel, schoolYearIDDataModel);
        //act
        SchoolYearIDDataModel result = programmeEditionIdDataModel.get_schoolYearIDDataModel();
        //assert
        assertEquals(schoolYearIDDataModel, result);
    }

    @Test
    void shouldReturnTrueIfProgrammeEditionIdDataModelIsComparedToItself() {
        //arrange
        ProgrammeIDDataModel programmeIDDataModel = mock(ProgrammeIDDataModel.class);
        //act
        boolean result = programmeIDDataModel.equals(programmeIDDataModel);
        //assert
        assertTrue(result);
    }
}