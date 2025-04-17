package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionEnrolmentDataModelTest {

    @Test
    void constructorShouldSetEmbeddedId() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id = new ProgrammeEditionEnrolmentIDDataModel("S001", "P001");

        // Act
        ProgrammeEditionEnrolmentDataModel model = new ProgrammeEditionEnrolmentDataModel(id);

        // Assert
        assertEquals(id, model.getProgrammeEditionEnrolmentIDDataModel());
    }

    @Test
    void defaultConstructorShouldCreateObject() {
        // Arrange
        // Act
        ProgrammeEditionEnrolmentDataModel model = new ProgrammeEditionEnrolmentDataModel();

        // Assert
        assertNotNull(model);
    }

    @Test
    void setterShouldUpdateEmbeddedId() {
        // Arrange
        ProgrammeEditionEnrolmentDataModel model = new ProgrammeEditionEnrolmentDataModel();
        ProgrammeEditionEnrolmentIDDataModel id = new ProgrammeEditionEnrolmentIDDataModel("S002", "P002");

        // Act
        model.setProgrammeEditionEnrolmentIDDataModel(id);

        // Assert
        assertEquals(id, model.getProgrammeEditionEnrolmentIDDataModel());
    }

    @Test
    void equalsShouldReturnTrueForSameId() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id = new ProgrammeEditionEnrolmentIDDataModel("S003", "P003");
        ProgrammeEditionEnrolmentDataModel model1 = new ProgrammeEditionEnrolmentDataModel(id);
        ProgrammeEditionEnrolmentDataModel model2 = new ProgrammeEditionEnrolmentDataModel(id);

        // Act
        boolean isEqual = model1.equals(model2);

        // Assert
        assertTrue(isEqual);
    }

    @Test
    void equalsShouldReturnFalseForDifferentId() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id1 = new ProgrammeEditionEnrolmentIDDataModel("S004", "P004");
        ProgrammeEditionEnrolmentIDDataModel id2 = new ProgrammeEditionEnrolmentIDDataModel("S005", "P005");
        ProgrammeEditionEnrolmentDataModel model1 = new ProgrammeEditionEnrolmentDataModel(id1);
        ProgrammeEditionEnrolmentDataModel model2 = new ProgrammeEditionEnrolmentDataModel(id2);

        // Act
        boolean isEqual = model1.equals(model2);

        // Assert
        assertFalse(isEqual);
    }

    @Test
    void hashCodeShouldBeSameForSameId() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id = new ProgrammeEditionEnrolmentIDDataModel("S006", "P006");
        ProgrammeEditionEnrolmentDataModel model1 = new ProgrammeEditionEnrolmentDataModel(id);
        ProgrammeEditionEnrolmentDataModel model2 = new ProgrammeEditionEnrolmentDataModel(id);

        // Act
        int hash1 = model1.hashCode();
        int hash2 = model2.hashCode();

        // Assert
        assertEquals(hash1, hash2);
    }

    @Test
    void equalsShouldReturnTrueForSameObject() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id =
                new ProgrammeEditionEnrolmentIDDataModel("S1", "P1");
        ProgrammeEditionEnrolmentDataModel enrolment =
                new ProgrammeEditionEnrolmentDataModel(id);

        // Act & Assert
        assertEquals(enrolment, enrolment); // mesmo objeto
    }

    @Test
    void equalsShouldReturnTrueForEqualObjects() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id1 =
                new ProgrammeEditionEnrolmentIDDataModel("S1", "P1");
        ProgrammeEditionEnrolmentIDDataModel id2 =
                new ProgrammeEditionEnrolmentIDDataModel("S1", "P1");

        ProgrammeEditionEnrolmentDataModel e1 = new ProgrammeEditionEnrolmentDataModel(id1);
        ProgrammeEditionEnrolmentDataModel e2 = new ProgrammeEditionEnrolmentDataModel(id2);

        // Act & Assert
        assertEquals(e1, e2);
    }

    @Test
    void equalsShouldReturnFalseForDifferentObjects() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id1 =
                new ProgrammeEditionEnrolmentIDDataModel("S1", "P1");
        ProgrammeEditionEnrolmentIDDataModel id2 =
                new ProgrammeEditionEnrolmentIDDataModel("S2", "P2");

        ProgrammeEditionEnrolmentDataModel e1 = new ProgrammeEditionEnrolmentDataModel(id1);
        ProgrammeEditionEnrolmentDataModel e2 = new ProgrammeEditionEnrolmentDataModel(id2);

        // Act & Assert
        assertNotEquals(e1, e2);
    }

    @Test
    void hashCodeShouldBeEqualForEqualObjects() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id1 =
                new ProgrammeEditionEnrolmentIDDataModel("S1", "P1");
        ProgrammeEditionEnrolmentIDDataModel id2 =
                new ProgrammeEditionEnrolmentIDDataModel("S1", "P1");

        ProgrammeEditionEnrolmentDataModel e1 = new ProgrammeEditionEnrolmentDataModel(id1);
        ProgrammeEditionEnrolmentDataModel e2 = new ProgrammeEditionEnrolmentDataModel(id2);

        // Act & Assert
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    void equalsShouldReturnFalseForNull() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id =
                new ProgrammeEditionEnrolmentIDDataModel("S1", "P1");
        ProgrammeEditionEnrolmentDataModel enrolment =
                new ProgrammeEditionEnrolmentDataModel(id);

        // Act & Assert
        assertNotEquals(enrolment, null);
    }

    @Test
    void equalsShouldReturnFalseForDifferentClass() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id =
                new ProgrammeEditionEnrolmentIDDataModel("S1", "P1");
        ProgrammeEditionEnrolmentDataModel enrolment =
                new ProgrammeEditionEnrolmentDataModel(id);

        // Act & Assert
        assertNotEquals(enrolment, "string");
    }

    @Test
    void hashCodeShouldBeEqualForEqualIDs() {
        // Arrange
        ProgrammeEditionEnrolmentIDDataModel id1 = new ProgrammeEditionEnrolmentIDDataModel("S1", "P1");
        ProgrammeEditionEnrolmentIDDataModel id2 = new ProgrammeEditionEnrolmentIDDataModel("S1", "P1");

        ProgrammeEditionEnrolmentDataModel e1 = new ProgrammeEditionEnrolmentDataModel(id1);
        ProgrammeEditionEnrolmentDataModel e2 = new ProgrammeEditionEnrolmentDataModel(id2);

        // Act & Assert
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    void hashCodeShouldMatchExpectedValue() {
        // arrange
        ProgrammeEditionEnrolmentIDDataModel id = new ProgrammeEditionEnrolmentIDDataModel("student123", "edition456");
        ProgrammeEditionEnrolmentDataModel model = new ProgrammeEditionEnrolmentDataModel(id);

        // act
        int hash = model.hashCode();

        // assert
        assertEquals(Objects.hash(id), hash);
    }

    @Test
    void hashCodesShouldDifferForDifferentObjects() {
        // arrange
        ProgrammeEditionEnrolmentIDDataModel id1 = new ProgrammeEditionEnrolmentIDDataModel("stu1", "prog1");
        ProgrammeEditionEnrolmentIDDataModel id2 = new ProgrammeEditionEnrolmentIDDataModel("stu2", "prog2");

        ProgrammeEditionEnrolmentDataModel obj1 = new ProgrammeEditionEnrolmentDataModel(id1);
        ProgrammeEditionEnrolmentDataModel obj2 = new ProgrammeEditionEnrolmentDataModel(id2);

        // act & assert
        assertNotEquals(obj1.hashCode(), obj2.hashCode());
    }
}
