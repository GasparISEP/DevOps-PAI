package PAI.persistence.datamodel;

import PAI.VOs.ProgrammeEditionID;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEditionEnrolmentDataModelTest {

    // Test constructor

    @Test
    void shouldCreateEnrolmentDataModelWithCorrectIdAndEnrolmentDate() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionID = mock(ProgrammeEditionIdDataModel.class);
        StudentIDDataModel studentIdDataModel = mock(StudentIDDataModel.class);
        ProgrammeEditionEnrolmentIDDataModel id = new ProgrammeEditionEnrolmentIDDataModel(studentIdDataModel, programmeEditionID);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 21);
        // Act
        ProgrammeEditionEnrolmentDataModel model = new ProgrammeEditionEnrolmentDataModel(id,enrolmentDate);

        // Assert
        assertNotNull(model);
    }

    //test empty constructor

    @Test
    void defaultConstructorShouldCreateObject() {
        // Arrange
        // Act
        ProgrammeEditionEnrolmentDataModel model = new ProgrammeEditionEnrolmentDataModel();

        // Assert
        assertNotNull(model);
    }

    // test getters

    @Test
    void getProgrammeEditionEnrolmentIDDataModel_ShouldReturnCorrectId() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel expectedId =  mock(ProgrammeEditionEnrolmentIDDataModel.class);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 21);
        ProgrammeEditionEnrolmentDataModel model = new ProgrammeEditionEnrolmentDataModel(expectedId, enrolmentDate);

        // Act
        ProgrammeEditionEnrolmentIDDataModel actualId = model.getProgrammeEditionEnrolmentIDDataModel();

        // Assert
        assertEquals(expectedId, actualId);
    }

    @Test
    void getEnrolmentDate_ShouldReturnCorrectDate() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id =  mock(ProgrammeEditionEnrolmentIDDataModel.class);
        LocalDate expectedDate = LocalDate.of(2025, 5, 1);
        ProgrammeEditionEnrolmentDataModel model = new ProgrammeEditionEnrolmentDataModel(id, expectedDate);

        // Act
        LocalDate actualDate = model.getEnrolmentDate();

        // Assert
        assertEquals(expectedDate, actualDate);
    }

    // test equals

    @Test
    void equalsShouldReturnTrueForSameObject() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id =
                mock(ProgrammeEditionEnrolmentIDDataModel.class);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 21);
        ProgrammeEditionEnrolmentDataModel enrolment =
                new ProgrammeEditionEnrolmentDataModel(id, enrolmentDate);

        // Act & Assert
        assertEquals(enrolment, enrolment); // mesmo objeto
    }

    @Test
    void equalsShouldReturnFalseForDifferentObjects() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id1 =
                mock(ProgrammeEditionEnrolmentIDDataModel.class);
        ProgrammeEditionEnrolmentIDDataModel id2 =
                mock(ProgrammeEditionEnrolmentIDDataModel.class);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 21);

        ProgrammeEditionEnrolmentDataModel e1 = new ProgrammeEditionEnrolmentDataModel(id1, enrolmentDate);
        ProgrammeEditionEnrolmentDataModel e2 = new ProgrammeEditionEnrolmentDataModel(id2, enrolmentDate);

        // Act & Assert
        assertNotEquals(e1, e2);
    }


    @Test
    void equalsShouldReturnFalseForNull() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id =
                mock(ProgrammeEditionEnrolmentIDDataModel.class);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 21);

        ProgrammeEditionEnrolmentDataModel enrolment =
                new ProgrammeEditionEnrolmentDataModel(id,enrolmentDate);

        // Act & Assert
        assertNotEquals(enrolment, null);
    }

    @Test
    void equalsShouldReturnFalseForDifferentClass() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id =
                mock(ProgrammeEditionEnrolmentIDDataModel.class);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 21);

        ProgrammeEditionEnrolmentDataModel enrolment =
                new ProgrammeEditionEnrolmentDataModel(id,enrolmentDate);

        // Act & Assert
        assertNotEquals(enrolment, "string");
    }

    //test hashcode

    @Test
    void hashCodeShouldBeSameForSameId() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id =  mock(ProgrammeEditionEnrolmentIDDataModel.class);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 21);

        ProgrammeEditionEnrolmentDataModel model1 = new ProgrammeEditionEnrolmentDataModel(id,enrolmentDate);
        ProgrammeEditionEnrolmentDataModel model2 = new ProgrammeEditionEnrolmentDataModel(id,enrolmentDate);

        // Act
        int hash1 = model1.hashCode();
        int hash2 = model2.hashCode();

        // Assert
        assertEquals(hash1, hash2);
    }

    @Test
    void hashCodeShouldMatchExpectedValue() {
        // arrange
        ProgrammeEditionEnrolmentIDDataModel id = mock(ProgrammeEditionEnrolmentIDDataModel.class);

        LocalDate enrolmentDate = LocalDate.of(2025, 4, 21);
        ProgrammeEditionEnrolmentDataModel model = new ProgrammeEditionEnrolmentDataModel(id,enrolmentDate);

        // act
        int hash = model.hashCode();

        // assert
        assertEquals(Objects.hash(id), hash);
    }

    @Test
    void hashCodesShouldDifferForDifferentObjects() {
        // arrange
        ProgrammeEditionEnrolmentIDDataModel id1 = mock(ProgrammeEditionEnrolmentIDDataModel.class);
        ProgrammeEditionEnrolmentIDDataModel id2 = mock(ProgrammeEditionEnrolmentIDDataModel.class);

        LocalDate enrolmentDate = LocalDate.of(2025, 4, 21);
        ProgrammeEditionEnrolmentDataModel obj1 = new ProgrammeEditionEnrolmentDataModel(id1,enrolmentDate);
        ProgrammeEditionEnrolmentDataModel obj2 = new ProgrammeEditionEnrolmentDataModel(id2,enrolmentDate);

        // act & assert
        assertNotEquals(obj1.hashCode(), obj2.hashCode());
    }

    @Test
    void hashCodeShouldBeDifferentForSimilarButNotEqualIds() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id1 = mock(ProgrammeEditionEnrolmentIDDataModel.class);
        ProgrammeEditionEnrolmentIDDataModel id2 = mock(ProgrammeEditionEnrolmentIDDataModel.class);

        LocalDate enrolmentDate = LocalDate.of(2025, 4, 21);
        ProgrammeEditionEnrolmentDataModel model1 = new ProgrammeEditionEnrolmentDataModel(id1,enrolmentDate);
        ProgrammeEditionEnrolmentDataModel model2 = new ProgrammeEditionEnrolmentDataModel(id2,enrolmentDate);

        // Act
        int hash1 = model1.hashCode();
        int hash2 = model2.hashCode();

        // Assert
        assertNotEquals(hash1, hash2);
    }

}
