package PAI.persistence.datamodel;

import PAI.VOs.ProgrammeEditionID;
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
        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);

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
        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);

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
        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);

        // act
        ProgrammeEditionEnrolmentIDDataModel model = new ProgrammeEditionEnrolmentIDDataModel(studentId, programmeEditionId);

        // assert
        assertNotNull(model);
        assertEquals(studentId, model.getStudentId());
        assertEquals(programmeEditionId, model.getProgrammeEditionId());
    }

    // Testing equals method

    @Test
    void shouldReturnTrue_WhenTwoObjectsHaveSameReference() {
        // arrange
        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);
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
        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);
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
        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentIDDataModel model = new ProgrammeEditionEnrolmentIDDataModel("1234567", programmeEditionId);

        // act
        boolean result = model.equals(null);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalse_WhenObjectsAreOfDifferentClasses() {
        // arrange
        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);
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
        ProgrammeEditionID programmeEditionId1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionId2 = mock(ProgrammeEditionID.class);

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
        ProgrammeEditionID programmeEditionId = mock(ProgrammeEditionID.class);
        ProgrammeEditionEnrolmentIDDataModel model1 = new ProgrammeEditionEnrolmentIDDataModel("1234567", programmeEditionId);
        ProgrammeEditionEnrolmentIDDataModel model2 = new ProgrammeEditionEnrolmentIDDataModel("1234567", programmeEditionId);

        // act & assert
        assertEquals(model1.hashCode(), model2.hashCode());
    }

    @Test
    void shouldReturnDifferentHashCodes_WhenObjectsHaveDifferentValues() {
        // arrange
        ProgrammeEditionID programmeEditionId1 = mock(ProgrammeEditionID.class);
        ProgrammeEditionID programmeEditionId2 = mock(ProgrammeEditionID.class);

        ProgrammeEditionEnrolmentIDDataModel model1 = new ProgrammeEditionEnrolmentIDDataModel("1234567", programmeEditionId1);
        ProgrammeEditionEnrolmentIDDataModel model2 = new ProgrammeEditionEnrolmentIDDataModel("7654321", programmeEditionId2);

        // act & assert
        assertNotEquals(model1.hashCode(), model2.hashCode());
    }
}
