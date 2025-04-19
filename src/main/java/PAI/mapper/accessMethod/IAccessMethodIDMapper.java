package PAI.mapper.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.persistence.datamodel.accessMethod.AccessMethodIDDataModel;

import java.util.Optional;

public interface IAccessMethodIDMapper {
    Optional<AccessMethodID> toVO(AccessMethodIDDataModel accessMethodIDDataModel);
    Optional<AccessMethodIDDataModel> toDataModel(AccessMethodID accessMethodID);
}
