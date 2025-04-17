package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEditionEnrolmentIDDataModelTest {

    @Test
    void emptyConstructorShouldCreateProgrammeEditionEnrolmentIDDataModel() {
        // arrange

        // act
        ProgrammeEditionEnrolmentIDDataModel id = new ProgrammeEditionEnrolmentIDDataModel();

        // assert
        assertNotNull(id);
    }

    @Test
    void constructorWithParametersShouldCreateProgrammeEditionEnrolmentIDDataModel() {
        // arrange

        // act
        ProgrammeEditionEnrolmentIDDataModel id = new ProgrammeEditionEnrolmentIDDataModel("student123", "edition456");

        // assert
        assertNotNull(id);
    }

    @Test
    void getStudentIdShouldReturnStudentId() {
        // arrange
        ProgrammeEditionEnrolmentIDDataModel id = new ProgrammeEditionEnrolmentIDDataModel("student123", "edition456");

        // act
        String result = id.getStudentId();

        // assert
        assertEquals("student123", result);
    }

    @Test
    void getProgrammeEditionIdShouldReturnProgrammeEditionId() {
        // arrange
        ProgrammeEditionEnrolmentIDDataModel id = new ProgrammeEditionEnrolmentIDDataModel("student123", "edition456");

        // act
        String result = id.getProgrammeEditionId();

        // assert
        assertEquals("edition456", result);
    }

    @Test
    void shouldThrowExceptionWhenStudentIdIsNull() {
        // arrange and act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ProgrammeEditionEnrolmentIDDataModel(null, "edition456")
        );

        // assert
        assertEquals("studentId cannot be null or blank", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionIdIsNull() {
        // arrange and act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ProgrammeEditionEnrolmentIDDataModel("student123", null)
        );

        // assert
        assertEquals("programmeEditionId cannot be null or blank", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStudentIdIsBlank() {
        // arrange and act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ProgrammeEditionEnrolmentIDDataModel("  ", "edition456")
        );

        // assert
        assertEquals("studentId cannot be null or blank", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionIdIsBlank() {
        // arrange and act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ProgrammeEditionEnrolmentIDDataModel("student123", " ")
        );

        // assert
        assertEquals("programmeEditionId cannot be null or blank", exception.getMessage());
    }

    // Equals Tests
    @Test
    void shouldReturnTrueIfObjectsAreTheSame() {
        // arrange
        ProgrammeEditionEnrolmentIDDataModel id = new ProgrammeEditionEnrolmentIDDataModel("student123", "edition456");

        // act
        boolean result = id.equals(id);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectToCompareIsNotAProgrammeEditionEnrolmentIDDataModel() {
        // arrange
        ProgrammeEditionEnrolmentIDDataModel id = new ProgrammeEditionEnrolmentIDDataModel("student123", "edition456");
        Object object = mock(Object.class);

        // act
        boolean result = id.equals(object);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfObjectsHaveTheSameParameters() {
        // arrange
        ProgrammeEditionEnrolmentIDDataModel id1 = new ProgrammeEditionEnrolmentIDDataModel("student123", "edition456");
        ProgrammeEditionEnrolmentIDDataModel id2 = new ProgrammeEditionEnrolmentIDDataModel("student123", "edition456");

        // act
        boolean result = id1.equals(id2);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfStudentIdsAreDifferent() {
        // arrange
        ProgrammeEditionEnrolmentIDDataModel id1 = new ProgrammeEditionEnrolmentIDDataModel("student123", "edition456");
        ProgrammeEditionEnrolmentIDDataModel id2 = new ProgrammeEditionEnrolmentIDDataModel("studentABC", "edition456");

        // act
        boolean result = id1.equals(id2);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfProgrammeEditionIdsAreDifferent() {
        // arrange
        ProgrammeEditionEnrolmentIDDataModel id1 = new ProgrammeEditionEnrolmentIDDataModel("student123", "edition456");
        ProgrammeEditionEnrolmentIDDataModel id2 = new ProgrammeEditionEnrolmentIDDataModel("student123", "editionXYZ");

        // act
        boolean result = id1.equals(id2);

        // assert
        assertFalse(result);
    }



    @Test
    void hashCodeShouldNotBeEqualForDifferentObjects() {
        // arrange
        ProgrammeEditionEnrolmentIDDataModel id1 = new ProgrammeEditionEnrolmentIDDataModel("student123", "edition456");
        ProgrammeEditionEnrolmentIDDataModel id2 = new ProgrammeEditionEnrolmentIDDataModel("student123", "editionXYZ");

        // act & assert
        assertNotEquals(id1.hashCode(), id2.hashCode());
    }
}
