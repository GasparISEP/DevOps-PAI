package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudentIDDataModelTest {

    @Test
    void emptyConstructorShouldCreateStudentIDDataModel() {
        //arrange

        //act
        StudentIDDataModel studentIDDataModel = new StudentIDDataModel();

        //assert
        assertNotNull(studentIDDataModel);
    }

    @Test
    void constructorWhitParametersShouldCreateStudentIDDataModel() {
        //arrange

        //act
        StudentIDDataModel studentIDDataModel = new StudentIDDataModel(1000001);

        //assert
        assertNotNull(studentIDDataModel);
    }

    @Test
    void getUniqueNumberShouldReturnUniqueNumber() {
        //arrange
        StudentIDDataModel studentIDDataModel = new StudentIDDataModel(1000001);

        //act
        int result = studentIDDataModel.getUniqueNumber();

        //assert
        assertEquals(1000001, result);
    }

    //Equals
    @Test
    void shouldReturnTrueIfObjectsAreTheSame() {
        //arrange
        StudentIDDataModel studentIDDataModel = new StudentIDDataModel(1000001);

        //act
        boolean result = studentIDDataModel.equals(studentIDDataModel);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfObjectToCompareIsNotAStudentIDDataModel() {
        //arrange
        StudentIDDataModel studentIDDataModel = new StudentIDDataModel(1000001);
        Object object = mock(Object.class);

        //act
        boolean result = studentIDDataModel.equals(object);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfObjectsHaveTheSameParameters() {
        //arrange
        StudentIDDataModel studentIDDataModel1 = new StudentIDDataModel(1000001);
        StudentIDDataModel studentIDDataModel2 = new StudentIDDataModel(1000001);


        //act
        boolean result = studentIDDataModel1.equals(studentIDDataModel2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfUniqueNumbersAreDifferent() {
        //arrange
        StudentIDDataModel studentIDDataModel1 = new StudentIDDataModel(1000001);
        StudentIDDataModel studentIDDataModel2 = new StudentIDDataModel(1000002);


        //act
        boolean result = studentIDDataModel1.equals(studentIDDataModel2);

        //assert
        assertFalse(result);
    }

    @Test
    void hashCodeShouldBeEqualForEqualObjects() {
        StudentIDDataModel id1 = new StudentIDDataModel(1000001);
        StudentIDDataModel id2 = new StudentIDDataModel(1000001);

        assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    void hashCodeShouldNotBeEqualForDifferentObjects() {
        StudentIDDataModel id1 = new StudentIDDataModel(1000001);
        StudentIDDataModel id2 = new StudentIDDataModel(1000002);

        assertNotEquals(id1.hashCode(), id2.hashCode());
    }

}