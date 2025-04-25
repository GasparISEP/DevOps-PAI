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
        assertEquals(nif, dataModel.getNifNumber());

    }

    @Test
    void getCountry () {

        //Arrange
        String nif = "123456789";
        String country = "Portugal";

        //Act
        NIFDataModel dataModel = new NIFDataModel(nif, country);

        //Assert
        assertEquals(country, dataModel.getNifCountry());

    }

    @Test
    void testEquals_sameObject() {
        NIFDataModel nif = new NIFDataModel("123456789", "PT");
        assertEquals(nif, nif); // reflexivity
    }

    @Test
    void testEquals_equalObjects() {
        NIFDataModel nif1 = new NIFDataModel("123456789", "PT");
        NIFDataModel nif2 = new NIFDataModel("123456789", "PT");

        assertEquals(nif1, nif2);
        assertEquals(nif2, nif1); // symmetry
    }

    @Test
    void testEquals_differentNIF() {
        NIFDataModel nif1 = new NIFDataModel("123456789", "PT");
        NIFDataModel nif2 = new NIFDataModel("987654321", "PT");

        assertNotEquals(nif1, nif2);
    }

    @Test
    void testEquals_differentCountry() {
        NIFDataModel nif1 = new NIFDataModel("123456789", "PT");
        NIFDataModel nif2 = new NIFDataModel("123456789", "ES");

        assertNotEquals(nif1, nif2);
    }

    @Test
    void testEquals_null() {
        NIFDataModel nif = new NIFDataModel("123456789", "PT");
        assertNotEquals(nif, null);
    }

    @Test
    void testEquals_differentClass() {
        NIFDataModel nif = new NIFDataModel("123456789", "PT");
        String other = "Not a NIF";
        assertNotEquals(nif, other);
    }

    @Test
    void testHashCode_sameValues() {
        NIFDataModel nif1 = new NIFDataModel("123456789", "PT");
        NIFDataModel nif2 = new NIFDataModel("123456789", "PT");

        assertEquals(nif1.hashCode(), nif2.hashCode());
    }

    @Test
    void testHashCode_differentValues() {
        NIFDataModel nif1 = new NIFDataModel("123456789", "PT");
        NIFDataModel nif2 = new NIFDataModel("987654321", "PT");

        assertNotEquals(nif1.hashCode(), nif2.hashCode());
    }
}

