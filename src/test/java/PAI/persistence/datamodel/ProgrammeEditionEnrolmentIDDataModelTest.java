package PAI.persistence.datamodel;

import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEditionEnrolmentIDDataModelTest {

    @Test
    void shouldCreateAValidProgrammeEditionEnrolmentIDDataModel_WhenUsingDefaultConstructor() {
        // arrange & act
        ProgrammeEditionEnrolmentIDDataModel model = new ProgrammeEditionEnrolmentIDDataModel();

        // assert
        assertNotNull(model);
    }

    @Test
    void shouldThrowException_WhenStudentIdIsNull() {
        // arrange
        String studentId = null;
        ProgrammeEditionIdDataModel programmeEditionId = mock(ProgrammeEditionIdDataModel.class);

        // act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentIDDataModel(studentId, programmeEditionId);
        });
        assertEquals("studentId cannot be null or blank", exception.getMessage());
    }

    @Test
    void shouldThrowException_WhenStudentIdIsBlank() {
        // arrange
        String studentId = "  ";
        ProgrammeEditionIdDataModel programmeEditionId = mock(ProgrammeEditionIdDataModel.class);

        // act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentIDDataModel(studentId, programmeEditionId);
        });
        assertEquals("studentId cannot be null or blank", exception.getMessage());
    }

    @Test
    void shouldThrowException_WhenProgrammeEditionIdIsNull() {
        // arrange
        String studentId = "1000000";

        // act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeEditionEnrolmentIDDataModel(studentId, null);
        });
        assertEquals("programmeEditionId cannot be null or blank", exception.getMessage());
    }

    @Test
    void shouldCreateAValidProgrammeEditionEnrolmentIDDataModel_WhenParametersAreValid() {
        // arrange
        String studentId = "1999999";
        ProgrammeEditionIdDataModel programmeEditionId = mock(ProgrammeEditionIdDataModel.class);

        // act
        ProgrammeEditionEnrolmentIDDataModel model = new ProgrammeEditionEnrolmentIDDataModel(studentId, programmeEditionId);

        // assert
        assertNotNull(model);
        assertEquals(studentId, model.getStudentIdDataModel());
        assertEquals(programmeEditionId, model.getProgrammeEditionIdDataModel());
    }

    // Testing equals method

    @Test
    void shouldReturnTrue_WhenTwoObjectsHaveSameReference() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionId = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionEnrolmentIDDataModel model1 = new ProgrammeEditionEnrolmentIDDataModel("1234567", programmeEditionId);
        ProgrammeEditionEnrolmentIDDataModel model2 = model1;

        // act
        boolean result = model1.equals(model2);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrue_WhenTwoObjectsHaveSameValues() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionId = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionEnrolmentIDDataModel model1 = new ProgrammeEditionEnrolmentIDDataModel("1234567", programmeEditionId);
        ProgrammeEditionEnrolmentIDDataModel model2 = new ProgrammeEditionEnrolmentIDDataModel("1234567", programmeEditionId);

        // act
        boolean result = model1.equals(model2);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalse_WhenComparedWithNull() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionId = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionEnrolmentIDDataModel model = new ProgrammeEditionEnrolmentIDDataModel("1234567", programmeEditionId);

        // act
        boolean result = model.equals(null);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalse_WhenObjectsAreOfDifferentClasses() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionId = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionEnrolmentIDDataModel model = new ProgrammeEditionEnrolmentIDDataModel("1234567", programmeEditionId);
        Object differentObject = new Object();

        // act
        boolean result = model.equals(differentObject);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalse_WhenTwoObjectsHaveDifferentValues() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionId1 = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionId2 = mock(ProgrammeEditionIdDataModel.class);

        ProgrammeEditionEnrolmentIDDataModel model1 = new ProgrammeEditionEnrolmentIDDataModel("1234567", programmeEditionId1);
        ProgrammeEditionEnrolmentIDDataModel model2 = new ProgrammeEditionEnrolmentIDDataModel("7654321", programmeEditionId2);

        // act
        boolean result = model1.equals(model2);

        // assert
        assertFalse(result);
    }

    // Testing hashCode

    @Test
    void shouldReturnSameHashCode_WhenTwoObjectsHaveSameValues() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionId = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionEnrolmentIDDataModel model1 = new ProgrammeEditionEnrolmentIDDataModel("1234567", programmeEditionId);
        ProgrammeEditionEnrolmentIDDataModel model2 = new ProgrammeEditionEnrolmentIDDataModel("1234567", programmeEditionId);

        // act & assert
        assertEquals(model1.hashCode(), model2.hashCode());
    }

    @Test
    void shouldReturnDifferentHashCodes_WhenObjectsHaveDifferentValues() {
        // arrange
        ProgrammeEditionIdDataModel programmeEditionId1 = mock(ProgrammeEditionIdDataModel.class);
        ProgrammeEditionIdDataModel programmeEditionId2 = mock(ProgrammeEditionIdDataModel.class);

        ProgrammeEditionEnrolmentIDDataModel model1 = new ProgrammeEditionEnrolmentIDDataModel("1234567", programmeEditionId1);
        ProgrammeEditionEnrolmentIDDataModel model2 = new ProgrammeEditionEnrolmentIDDataModel("7654321", programmeEditionId2);

        // act & assert
        assertNotEquals(model1.hashCode(), model2.hashCode());
    }
}
