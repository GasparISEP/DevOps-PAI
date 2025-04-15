package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AddressDataModelTest {

    @Test
    void testEmptyConstructor() {
        // Arrange + Act
        AddressDataModel address = new AddressDataModel();

        // Assert
        assertNotNull(address);
    }

    @Test
    void testConstructor() {
        // Arrange
        String street = "Rua de São Tomé";
        String postalCode = "4200-485";
        String location = "Porto";
        String country = "Portugal";

        // Act
        AddressDataModel addressDataModel = new AddressDataModel(street, postalCode, location, country);

        // Assert
        assertNotNull(addressDataModel);
    }

    @Test
    void testGetStreet () {
        // Arrange
        String street = "Rua de São Tomé";
        String postalCode = "4200-485";
        String location = "Porto";
        String country = "Portugal";

        AddressDataModel addressDataModel = new AddressDataModel(street, postalCode, location, country);

        // Act
        String result = addressDataModel.getStreet();

        // Assert
        assertEquals("Rua de São Tomé", result);
    }

    @Test
    void testGetPostalCode () {
        // Arrange
        String street = "Rua de São Tomé";
        String postalCode = "4200-485";
        String location = "Porto";
        String country = "Portugal";

        AddressDataModel addressDataModel = new AddressDataModel(street, postalCode, location, country);

        // Act
        String result = addressDataModel.getPostalCode();

        // Assert
        assertEquals("4200-485", result);
    }

    @Test
    void testGetLocation () {
        // Arrange
        String street = "Rua de São Tomé";
        String postalCode = "4200-485";
        String location = "Porto";
        String country = "Portugal";

        AddressDataModel addressDataModel = new AddressDataModel(street, postalCode, location, country);

        // Act
        String result = addressDataModel.getLocation();

        // Assert
        assertEquals("Porto", result);
    }

    @Test
    void testGetCountry () {
        // Arrange
        String street = "Rua de São Tomé";
        String postalCode = "4200-485";
        String location = "Porto";
        String country = "Portugal";

        AddressDataModel addressDataModel = new AddressDataModel(street, postalCode, location, country);

        // Act
        String result = addressDataModel.getCountry();

        // Assert
        assertEquals("Portugal", result);
    }
}