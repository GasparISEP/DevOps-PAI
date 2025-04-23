package PAI.persistence.datamodel;

import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
class DepartmentIDDataModelTest {
    static Stream<Arguments> InvalidDepartmentIDDatamodelConstructorTests(){
        return Stream.of(
        Arguments.of(""),
        Arguments.of(" "),
        Arguments.of("AB")
        );
    }
    @ParameterizedTest
    @MethodSource("InvalidDepartmentIDDatamodelConstructorTests")
    void ShouldThrowAnExceptionForInvalidDepartmentID(String departmentID) {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DepartmentIDDataModel(departmentID));
        assertTrue(exception.getMessage().matches("Department ID cannot be an empty string.|Department ID must contain at least 3 characters."));
    }

    @Test
    void ShouldCreateAValidDepartmentIDDataModel(){
        //arrange
        String departmentID = "ABC";
        // Act
        DepartmentIDDataModel model = new DepartmentIDDataModel(departmentID);
        //assert
        assertNotNull(model);
    }
    @Test
    void ShouldCreateAValidDepartmentIDDataModelWithNoParameters(){
        //arrange + Act
        DepartmentIDDataModel model = new DepartmentIDDataModel();
        //assert
        assertNotNull(model);
    }
    @Test
    void ShouldThrowAnExceptionWhenTheDepartmentIDIsNull(){
        //arrange
        String departmentID = null;
        //act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DepartmentIDDataModel(departmentID));
        assertEquals("Department ID cannot be an empty string.", exception.getMessage());
    }

    @Test
    void ShouldReturnFalseWhenComparedWithObjectOfDifferentClass() {
        DepartmentIDDataModel model = new DepartmentIDDataModel("ABC");
        Object other = new Object();
        assertFalse(model.equals(other));
    }

    @Test
    void ShouldReturnFalseWhenComparedWithInstanceOfDifferentClass() {
        DepartmentIDDataModel model = new DepartmentIDDataModel("ABC");
        String differentType = "ABC"; // Different class entirely
        assertFalse(model.equals(differentType));
    }

    @Test
    void ShouldReturnFalseIfDepartmentIDModelIsNull() {
        DepartmentIDDataModel model = new DepartmentIDDataModel("ABC");
        assertFalse(model.equals(null));
    }

    @Test
    void ShouldReturnTrueWhenComparedWithItself() {
        DepartmentIDDataModel model = new DepartmentIDDataModel("ABC");
        assertTrue(model.equals(model));
    }

    @Test
    void ShouldReturnTrueIfDepartmentIDModelsHaveSameDepartmentID() {
        DepartmentIDDataModel model1 = new DepartmentIDDataModel("ABC");
        DepartmentIDDataModel model2 = new DepartmentIDDataModel("ABC");
        assertTrue(model1.equals(model2));
    }

    @Test
    void ShouldReturnFalseIfDepartmentIDModelsHaveDifferentDepartmentID() {
        DepartmentIDDataModel model1 = new DepartmentIDDataModel("ABC");
        DepartmentIDDataModel model2 = new DepartmentIDDataModel("XYZ");
        assertFalse(model1.equals(model2));
    }
    @Test
    void GetDepartmentIDShouldReturnTheDepartmentID() {
        //arrange
        String departmentID = "ABC";
        DepartmentIDDataModel departmentIDDataModel = new DepartmentIDDataModel("ABC");
        //act + assert
        assertEquals(departmentID, departmentIDDataModel.getDepartmentID());
    }
    @Test
    void GetDepartmentIDShouldReturnNotEqual(){
        //arrange
        String departmentID = "ABC";
        String departmentIDFake = "ACB";
        DepartmentIDDataModel model = new DepartmentIDDataModel(departmentID);
        //act + assert
        assertNotEquals(departmentIDFake, model.getDepartmentID());
    }
    @Test
    void ShouldReturnEqualsIfHasCodesAreTheSame(){
        //arrange
        String departmentID = "ABC";
        DepartmentIDDataModel model1 = new DepartmentIDDataModel(departmentID);
        DepartmentIDDataModel model2 = new DepartmentIDDataModel(departmentID);
        //act + assert
        assertEquals(model1.hashCode(),model2.hashCode());
    }
    @Test
    void ShouldReturnNotEqualsIfHasCodesAreDifferent(){
        //arrange
        String departmentID = "ABC";
        String departmentIDFake = "ACB";
        DepartmentIDDataModel model1 = new DepartmentIDDataModel(departmentID);
        DepartmentIDDataModel model2 = new DepartmentIDDataModel(departmentIDFake);
        //act + assert
        assertNotEquals(model1.hashCode(),model2.hashCode());
    }
}