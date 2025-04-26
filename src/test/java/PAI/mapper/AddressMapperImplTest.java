package PAI.mapper;

import PAI.VOs.*;
import PAI.persistence.datamodel.AddressDataModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddressMapperImplTest {

    private AddressMapperImpl _addressMapper;

    @BeforeEach
    void setUp () {
        // Arrange
        _addressMapper = new AddressMapperImpl();
    }

    @Test
    void toDomainShouldReturnNullWhenAddressDataModelIsNull () {
        // Act
        Address result = _addressMapper.toDomain(null);

        // Assert
        assertNull(result);
    }

    @Test
    void toDomainShouldReturnAddress () {
        // Arrange
        String street = "Rua Da Alegria";
        String postalCode = "3440-307";
        String location = "Viseu";
        String country = "Portugal";

        AddressDataModel addressDataModel = mock(AddressDataModel.class);

        // Act
        when(addressDataModel.getStreet()).thenReturn(street);
        when(addressDataModel.getPostalCode()).thenReturn(postalCode);
        when(addressDataModel.getLocation()).thenReturn(location);
        when(addressDataModel.getCountry()).thenReturn(country);

        Address result = _addressMapper.toDomain(addressDataModel);

        // Assert
        assertTrue(
                street.equals(result.getStreet().getStreet()) &&
                postalCode.equals(result.getPostalCode().getPostalCode()) &&
                location.equals(result.getLocation().getLocation()) &&
                country.equals(result.getCountry().getCountryName())
        );
    }

    @Test
    void toDataModelShouldReturnNullWhenAddressIsNull () {

        AddressDataModel result = _addressMapper.toDataModel(null);

        assertNull(result);
    }

    @Test
    void toDataModelShouldReturnAddressDataModel () {
        // Arrange
        String street = "Rua Da Alegria";
        String postalCode = "3440-307";
        String location = "Viseu";
        String country = "Portugal";

        Street streetVO = mock(Street.class);
        PostalCode postalCodeVO = mock(PostalCode.class);
        Location locationVO = mock(Location.class);
        Country countryVO = mock(Country.class);

        Address address = mock(Address.class);

        when(address.getStreet()).thenReturn(streetVO);
        when(streetVO.getStreet()).thenReturn(street);

        when(address.getPostalCode()).thenReturn(postalCodeVO);
        when(postalCodeVO.getPostalCode()).thenReturn(postalCode);

        when(address.getLocation()).thenReturn(locationVO);
        when(locationVO.getLocation()).thenReturn(location);

        when(address.getCountry()).thenReturn(countryVO);
        when(countryVO.getCountryName()).thenReturn(country);

        // Act
        AddressDataModel result = _addressMapper.toDataModel(address);

        // Assert
        assertTrue(
                street.equals(result.getStreet()) &&
                postalCode.equals(result.getPostalCode()) &&
                location.equals(result.getLocation()) &&
                country.equals(result.getCountry())
        );
    }
}