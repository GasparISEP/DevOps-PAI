package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NIFDataModelTest {

    @Test
    void testConstructorWithArguments() {
        // Arrange
        String nif = "123456789";
        String country = "Portugal";

        // Act
        NIFDataModel dataModel = new NIFDataModel(nif, country);

        //Assert

        assertNotNull(dataModel);

    }

    @Test
    void testEmptyConstructor() {

        // Act
        NIFDataModel dataModel = new NIFDataModel();

        //Assert
        assertNotNull(dataModel);
    }

    @Test
    void getNif () {

        //Arrange
        String nif = "123456789";
        String country = "Portugal";

        //Act
        NIFDataModel dataModel = new NIFDataModel(nif, country);

        //Assert
        assertEquals(nif, dataModel.getNIF());

    }

    @Test
    void getCountry () {

        //Arrange
        String nif = "123456789";
        String country = "Portugal";

        //Act
        NIFDataModel dataModel = new NIFDataModel(nif, country);

        //Assert
        assertEquals(country, dataModel.getCountry());

    }

}