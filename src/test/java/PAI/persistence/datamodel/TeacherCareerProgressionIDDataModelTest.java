package PAI.persistence.datamodel;

import PAI.VOs.TeacherCareerProgressionID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCareerProgressionIDDataModelTest {

    @Test
    void emptyConstructorShouldCreateTeacherCareerProgressionDataModel() {
        //arrange

        //act
        TeacherCareerProgressionIDDataModel tcpIDDataModel = new TeacherCareerProgressionIDDataModel();

        //assert
        assertNotNull(tcpIDDataModel);
    }

    @Test
    void getIDValueShouldReturnCorrectID() {
        //arrange
        UUID expected = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        TeacherCareerProgressionIDDataModel tcpIDDataModel = new TeacherCareerProgressionIDDataModel(expected);

        //act
        UUID result = tcpIDDataModel.getIDValue();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void equalsShouldReturnTrueWhenIDsAreEqual() {
        //arrange
        UUID id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        TeacherCareerProgressionIDDataModel model1 = new TeacherCareerProgressionIDDataModel(id);
        TeacherCareerProgressionIDDataModel model2 = new TeacherCareerProgressionIDDataModel(id);

        //act
        boolean isEqual = model1.equals(model2);

        //assert
        assertTrue(isEqual);
    }

    @Test
    void equalsShouldReturnFalseWhenIDsAreDifferent() {
        //arrange
        TeacherCareerProgressionIDDataModel model1 = new TeacherCareerProgressionIDDataModel(UUID.randomUUID());
        TeacherCareerProgressionIDDataModel model2 = new TeacherCareerProgressionIDDataModel(UUID.randomUUID());

        //act
        boolean isEqual = model1.equals(model2);

        //assert
        assertFalse(isEqual);
    }

    @Test
    void equalsShouldReturnFalseWhenComparedToNull() {
        //arrange
        TeacherCareerProgressionIDDataModel model = new TeacherCareerProgressionIDDataModel(UUID.randomUUID());

        //act
        boolean isEqual = model.equals(null);

        //assert
        assertFalse(isEqual);
    }

    @Test
    void equalsShouldReturnTrueWhenComparedWithItself() {
        //arrange
        TeacherCareerProgressionIDDataModel model = new TeacherCareerProgressionIDDataModel(UUID.randomUUID());

        //act
        boolean isEqual = model.equals(model);

        //assert
        assertTrue(isEqual);
    }

    @Test
    void hashCodeShouldBeEqualForEqualIDs() {
        //arrange
        UUID id = UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
        TeacherCareerProgressionIDDataModel model1 = new TeacherCareerProgressionIDDataModel(id);
        TeacherCareerProgressionIDDataModel model2 = new TeacherCareerProgressionIDDataModel(id);

        //act
        int hash1 = model1.hashCode();
        int hash2 = model2.hashCode();

        //assert
        assertEquals(hash1, hash2);
    }

    @Test
    void hashCodeShouldBeDifferentForDifferentIDs() {
        //arrange
        TeacherCareerProgressionIDDataModel model1 = new TeacherCareerProgressionIDDataModel(UUID.randomUUID());
        TeacherCareerProgressionIDDataModel model2 = new TeacherCareerProgressionIDDataModel(UUID.randomUUID());

        //act
        int hash1 = model1.hashCode();
        int hash2 = model2.hashCode();

        //assert
        assertNotEquals(hash1, hash2);
    }

}