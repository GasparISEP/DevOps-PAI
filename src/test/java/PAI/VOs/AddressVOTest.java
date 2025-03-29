package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AddressVOTest {

    // Method to create doubles for tests with isolation
    private Object[] createDoublesForTestsWithIsolation() {
        Street street = mock(Street.class);
        PostalCode postalCode = mock(PostalCode.class);
        Location location = mock(Location.class);
        Country country = mock(Country.class);
        return new Object[]{street, postalCode, location, country};
    }

    @Test
    void shouldConstructAddressIfValidString() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Street streetDouble = (Street) doubles[0];
        PostalCode postalCodeDouble = (PostalCode) doubles[1];
        Location locationDouble = (Location) doubles[2];
        Country countryDouble = (Country) doubles[3];

        //Act
        AddressVO address = new AddressVO(streetDouble, postalCodeDouble, locationDouble, countryDouble);
    }

    @Test
    void shouldNotConstructAddressIfNullStreet() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        PostalCode postalCodeDouble = (PostalCode) doubles[1];
        Location locationDouble = (Location) doubles[2];
        Country countryDouble = (Country) doubles[3];

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new AddressVO(null, postalCodeDouble, locationDouble, countryDouble));
    }

    @Test
    void shouldNotConstructAddressIfNullPostalCode() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Street streetDouble = (Street) doubles[0];
        Location locationDouble = (Location) doubles[2];
        Country countryDouble = (Country) doubles[3];

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new AddressVO(streetDouble, null, locationDouble, countryDouble));
    }

    @Test
    void shouldNotConstructAddressIfNullLocation() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Street streetDouble = (Street) doubles[0];
        PostalCode postalCodeDouble = (PostalCode) doubles[1];
        Country countryDouble = (Country) doubles[3];

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new AddressVO(streetDouble, postalCodeDouble, null, countryDouble));
    }

    @Test
    void shouldNotConstructAddressIfNullCountry() {
        //Arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Street streetDouble = (Street) doubles[0];
        PostalCode postalCodeDouble = (PostalCode) doubles[1];
        Location locationDouble = (Location) doubles[2];

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new AddressVO(streetDouble, postalCodeDouble, locationDouble, null));
    }
}