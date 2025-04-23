package PAI.mapper;

import PAI.VOs.*;
import PAI.persistence.datamodel.AddressDataModel;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements IAddressMapper {

    public Address toDomain (AddressDataModel addressDataModel) {

        if (addressDataModel == null)
            return null;

        Street street = new Street(addressDataModel.getStreet());
        PostalCode postalCode = new PostalCode(addressDataModel.getPostalCode());
        Location location = new Location(addressDataModel.getLocation());
        Country country = new Country(addressDataModel.getCountry());

        return new Address(street, postalCode, location, country);
    }

    public AddressDataModel toDataModel (Address address) {

        if (address == null)
            return null;

        String street = address.getStreet().getStreet();
        String postalCode = address.getPostalCode().getPostalCode();
        String location = address.getLocation().getLocation();
        String country = address.getCountry().getCountryName();

        return new AddressDataModel(street, postalCode, location, country);
    }
}
