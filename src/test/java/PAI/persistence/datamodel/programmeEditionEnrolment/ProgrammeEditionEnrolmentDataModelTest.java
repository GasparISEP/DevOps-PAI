package PAI.persistence.datamodel.programmeEditionEnrolment;

import PAI.VOs.EnrolmentStatus;
import PAI.VOs.ProgrammeEditionEnrolmentGeneratedID;
import PAI.persistence.datamodel.student.StudentIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEditionEnrolmentDataModelTest {

    // Test constructor

    @Test
    void shouldCreateEnrolmentDataModelWithCorrectIdAndEnrolmentDate() {
        // Arrange
        ProgrammeEditionIdDataModel programmeEditionID = mock(ProgrammeEditionIdDataModel.class);
        StudentIDDataModel studentIdDataModel = mock(StudentIDDataModel.class);
        ProgrammeEditionEnrolmentIDDataModel id = new ProgrammeEditionEnrolmentIDDataModel(studentIdDataModel, programmeEditionID);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 21);
        EnrolmentStatus enrolmentStatus = mock(EnrolmentStatus.class);
        UUID programmeEditionEnrolmentGeneratedID = mock(UUID.class);
        // Act
        ProgrammeEditionEnrolmentDataModel model = new ProgrammeEditionEnrolmentDataModel(id,enrolmentDate, enrolmentStatus.isEnrolmentActive(), programmeEditionEnrolmentGeneratedID);

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
        EnrolmentStatus enrolmentStatus = mock(EnrolmentStatus.class);
        UUID programmeEditionEnrolmentGeneratedID = mock(UUID.class);

        ProgrammeEditionEnrolmentDataModel model = new ProgrammeEditionEnrolmentDataModel(expectedId, enrolmentDate,enrolmentStatus.isEnrolmentActive(), programmeEditionEnrolmentGeneratedID);

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
        EnrolmentStatus enrolmentStatus = mock(EnrolmentStatus.class);
        UUID programmeEditionEnrolmentGeneratedID = mock(UUID.class);

        ProgrammeEditionEnrolmentDataModel model = new ProgrammeEditionEnrolmentDataModel(id, expectedDate,enrolmentStatus.isEnrolmentActive(), programmeEditionEnrolmentGeneratedID);

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
        EnrolmentStatus enrolmentStatus = mock(EnrolmentStatus.class);
        UUID programmeEditionEnrolmentGeneratedID = mock(UUID.class);

        ProgrammeEditionEnrolmentDataModel enrolment =
                new ProgrammeEditionEnrolmentDataModel(id, enrolmentDate,enrolmentStatus.isEnrolmentActive(), programmeEditionEnrolmentGeneratedID);

        // Act & Assert
        assertEquals(enrolment, enrolment);
    }

    @Test
    void equalsShouldReturnFalseForDifferentObjects() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id1 =
                mock(ProgrammeEditionEnrolmentIDDataModel.class);
        ProgrammeEditionEnrolmentIDDataModel id2 =
                mock(ProgrammeEditionEnrolmentIDDataModel.class);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 21);
        EnrolmentStatus enrolmentStatus = mock(EnrolmentStatus.class);
        UUID programmeEditionEnrolmentGeneratedID1 = mock(UUID.class);
        UUID programmeEditionEnrolmentGeneratedID2 = mock(UUID.class);


        ProgrammeEditionEnrolmentDataModel e1 = new ProgrammeEditionEnrolmentDataModel(id1, enrolmentDate,enrolmentStatus.isEnrolmentActive(), programmeEditionEnrolmentGeneratedID1);
        ProgrammeEditionEnrolmentDataModel e2 = new ProgrammeEditionEnrolmentDataModel(id2, enrolmentDate,enrolmentStatus.isEnrolmentActive(), programmeEditionEnrolmentGeneratedID2);

        // Act & Assert
        assertNotEquals(e1, e2);
    }


    @Test
    void equalsShouldReturnFalseForNull() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id =
                mock(ProgrammeEditionEnrolmentIDDataModel.class);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 21);
        EnrolmentStatus enrolmentStatus = mock(EnrolmentStatus.class);
        UUID programmeEditionEnrolmentGeneratedID1 = mock(UUID.class);

        ProgrammeEditionEnrolmentDataModel enrolment =
                new ProgrammeEditionEnrolmentDataModel(id,enrolmentDate,enrolmentStatus.isEnrolmentActive(), programmeEditionEnrolmentGeneratedID1);

        // Act & Assert
        assertNotEquals(enrolment, null);
    }

    @Test
    void equalsShouldReturnFalseForDifferentClass() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id =
                mock(ProgrammeEditionEnrolmentIDDataModel.class);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 21);
        EnrolmentStatus enrolmentStatus = mock(EnrolmentStatus.class);
        UUID programmeEditionEnrolmentGeneratedID1 = mock(UUID.class);

        ProgrammeEditionEnrolmentDataModel enrolment =
                new ProgrammeEditionEnrolmentDataModel(id,enrolmentDate,enrolmentStatus.isEnrolmentActive(), programmeEditionEnrolmentGeneratedID1);

        // Act & Assert
        assertNotEquals(enrolment, "string");
    }

    //test hashcode

    @Test
    void hashCodeShouldBeSameForSameId() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id =  mock(ProgrammeEditionEnrolmentIDDataModel.class);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 21);
        EnrolmentStatus enrolmentStatus = mock(EnrolmentStatus.class);
        UUID programmeEditionEnrolmentGeneratedID1 = mock(UUID.class);
        UUID programmeEditionEnrolmentGeneratedID2= mock(UUID.class);

        ProgrammeEditionEnrolmentDataModel model1 = new ProgrammeEditionEnrolmentDataModel(id,enrolmentDate,enrolmentStatus.isEnrolmentActive(), programmeEditionEnrolmentGeneratedID1);
        ProgrammeEditionEnrolmentDataModel model2 = new ProgrammeEditionEnrolmentDataModel(id,enrolmentDate,enrolmentStatus.isEnrolmentActive(), programmeEditionEnrolmentGeneratedID2);

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
        EnrolmentStatus enrolmentStatus = mock(EnrolmentStatus.class);
        UUID programmeEditionEnrolmentGeneratedID1 = mock(UUID.class);

        ProgrammeEditionEnrolmentDataModel model = new ProgrammeEditionEnrolmentDataModel(id,enrolmentDate,enrolmentStatus.isEnrolmentActive(), programmeEditionEnrolmentGeneratedID1);

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
        EnrolmentStatus enrolmentStatus = mock(EnrolmentStatus.class);
        UUID programmeEditionEnrolmentGeneratedID1 = mock(UUID.class);
        UUID programmeEditionEnrolmentGeneratedID2 = mock(UUID.class);

        ProgrammeEditionEnrolmentDataModel obj1 = new ProgrammeEditionEnrolmentDataModel(id1,enrolmentDate,enrolmentStatus.isEnrolmentActive(), programmeEditionEnrolmentGeneratedID1);
        ProgrammeEditionEnrolmentDataModel obj2 = new ProgrammeEditionEnrolmentDataModel(id2,enrolmentDate,enrolmentStatus.isEnrolmentActive(), programmeEditionEnrolmentGeneratedID2);

        // act & assert
        assertNotEquals(obj1.hashCode(), obj2.hashCode());
    }

    @Test
    void hashCodeShouldBeDifferentForSimilarButNotEqualIds() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id1 = mock(ProgrammeEditionEnrolmentIDDataModel.class);
        ProgrammeEditionEnrolmentIDDataModel id2 = mock(ProgrammeEditionEnrolmentIDDataModel.class);

        LocalDate enrolmentDate = LocalDate.of(2025, 4, 21);
        EnrolmentStatus enrolmentStatus = mock(EnrolmentStatus.class);
        UUID programmeEditionEnrolmentGeneratedID1 = mock(UUID.class);
        UUID programmeEditionEnrolmentGeneratedID2 = mock(UUID.class);

        ProgrammeEditionEnrolmentDataModel model1 = new ProgrammeEditionEnrolmentDataModel(id1,enrolmentDate,enrolmentStatus.isEnrolmentActive(), programmeEditionEnrolmentGeneratedID1);
        ProgrammeEditionEnrolmentDataModel model2 = new ProgrammeEditionEnrolmentDataModel(id2,enrolmentDate,enrolmentStatus.isEnrolmentActive(), programmeEditionEnrolmentGeneratedID2);

        // Act
        int hash1 = model1.hashCode();
        int hash2 = model2.hashCode();

        // Assert
        assertNotEquals(hash1, hash2);
    }

    @Test
    void shouldReturnCorrectGeneratedID() {
        UUID expectedUUID = UUID.randomUUID();

        ProgrammeEditionEnrolmentIDDataModel id = mock(ProgrammeEditionEnrolmentIDDataModel.class);
        LocalDate date = LocalDate.now();
        boolean isActive = true;

        ProgrammeEditionEnrolmentDataModel dataModel = new ProgrammeEditionEnrolmentDataModel(id, date, isActive, expectedUUID);

        assertEquals(expectedUUID, dataModel.getGeneratedID());
    }

}
