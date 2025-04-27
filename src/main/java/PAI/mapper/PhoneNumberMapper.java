package PAI.mapper;

import PAI.VOs.PhoneNumber;
import PAI.persistence.datamodel.PhoneNumberDataModel;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberMapper implements IPhoneNumberMapper{

    public PhoneNumberDataModel domainToDataModel(PhoneNumber phoneNumber) {

        String countryCode = phoneNumber.getCountryCode();
        String number = phoneNumber.getNumber();

        return new PhoneNumberDataModel(countryCode, number);
    }

    public PhoneNumber dataModelToDomain(PhoneNumberDataModel phoneNumberDataModel) {

        String countryCode = phoneNumberDataModel.getCountryCode();
        String number = phoneNumberDataModel.getNumber();

        return new PhoneNumber(countryCode, number);
    }
}
