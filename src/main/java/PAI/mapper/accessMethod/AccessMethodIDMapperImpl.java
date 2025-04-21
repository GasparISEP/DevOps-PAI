package PAI.mapper.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.persistence.datamodel.accessMethod.AccessMethodIDDataModel;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AccessMethodIDMapperImpl implements IAccessMethodIDMapper{

    @Override
    public Optional<AccessMethodID> toVO(AccessMethodIDDataModel accessMethodIDDataModel) {
        if(accessMethodIDDataModel == null) {
            return Optional.empty();
        }
        UUID id = accessMethodIDDataModel.getId();
        AccessMethodID accessMethodID = new AccessMethodID(id);
        return Optional.of(accessMethodID);
    }

    @Override
    public Optional<AccessMethodIDDataModel> toDataModel(AccessMethodID accessMethodID) {
        if(accessMethodID == null) {
            return Optional.empty();
        }
        UUID id = accessMethodID.getAccessMethodID();
        AccessMethodIDDataModel accessMethodIDDataModel = new AccessMethodIDDataModel(id);
        return Optional.of(accessMethodIDDataModel);
    }
}
