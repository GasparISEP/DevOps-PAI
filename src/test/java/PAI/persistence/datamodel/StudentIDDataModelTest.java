package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        StudentIDDataModel studentIDDataModel = new StudentIDDataModel(1000001, "123456789", "Portugal");

        //assert
        assertNotNull(studentIDDataModel);
    }

    @Test
    void getUniqueNumberShouldReturnUniqueNumber() {
        //arrange
        StudentIDDataModel studentIDDataModel = new StudentIDDataModel(1000001, "123456789", "Portugal");

        //act
        int result = studentIDDataModel.getUniqueNumber();

        //assert
        assertEquals(1000001, result);
    }

    @Test
    void getNIFShouldReturnNIF() {
        //arrange
        StudentIDDataModel studentIDDataModel = new StudentIDDataModel(1000001, "123456789", "Portugal");

        //act
        String result = studentIDDataModel.getNIF();

        //assert
        assertEquals("123456789", result);
    }

    @Test
    void getCountryShouldReturnCountry() {
        //arrange
        StudentIDDataModel studentIDDataModel = new StudentIDDataModel(1000001, "123456789", "Portugal");

        //act
        String result = studentIDDataModel.getCountry();

        //assert
        assertEquals("Portugal", result);
    }
}