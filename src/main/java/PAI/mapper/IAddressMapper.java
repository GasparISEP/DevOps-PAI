package PAI.mapper;

import PAI.VOs.Address;
import PAI.persistence.datamodel.AddressDataModel;

public interface IAddressMapper {

    Address toDomain (AddressDataModel addressDataModel);
}
