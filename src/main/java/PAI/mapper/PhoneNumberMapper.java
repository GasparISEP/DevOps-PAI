package PAI.mapper;

import PAI.VOs.PhoneNumber;
import PAI.persistence.datamodel.PhoneNumberDataModel;

public class PhoneNumberMapper implements IPhoneNumberMapper{

    public PhoneNumberDataModel domainToDataModel(PhoneNumber phoneNumber) {

        String countryCode = phoneNumber.getCountryCode();
        String number = phoneNumber.getNumber();

        return new PhoneNumberDataModel(countryCode, number);
    }

    public PhoneNumber dataModelToDomain(PhoneNumberDataModel phoneNumberDataModel) throws Exception {

        String countryCode = phoneNumberDataModel.getCountryCode();
        String number = phoneNumberDataModel.getNumber();

        return new PhoneNumber(countryCode, number);
    }
}
