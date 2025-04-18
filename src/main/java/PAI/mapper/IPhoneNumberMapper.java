package PAI.mapper;

import PAI.VOs.PhoneNumber;
import PAI.persistence.datamodel.PhoneNumberDataModel;

public interface IPhoneNumberMapper {

    PhoneNumberDataModel domainToDataModel(PhoneNumber phoneNumber);
    PhoneNumber dataModelToDomain(PhoneNumberDataModel phoneNumberDataModel) throws Exception;
}
