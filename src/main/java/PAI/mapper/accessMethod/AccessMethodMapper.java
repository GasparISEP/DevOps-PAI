package PAI.mapper.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.persistence.datamodel.AccessMethodDataModel;

import java.util.Optional;
import java.util.UUID;

public class AccessMethodMapper implements  IAccessMethodMapper{

    private final IAccessMethodFactory _accessMethodFactory;

    public AccessMethodMapper(IAccessMethodFactory accessMethodFactory) {
        if (accessMethodFactory == null) {
            throw new IllegalArgumentException("AccessMethodFactory can't be null");
        }
        this._accessMethodFactory = accessMethodFactory;
    }

    @Override
    public Optional<AccessMethodDataModel> toDataModel(AccessMethod accessMethod) {
        if(accessMethod == null) {
            return Optional.empty();
        }
        UUID uuid = accessMethod.identity().getAccessMethodID();
        NameWithNumbersAndSpecialChars name = accessMethod.getAccessMethodName();
        String nameToString = name.toString();
        AccessMethodDataModel accessMethodDataModel = new AccessMethodDataModel(uuid, nameToString);
        return Optional.of(accessMethodDataModel);
    }

    @Override
    public Optional<AccessMethod> toDomain(AccessMethodDataModel accessMethodDataModel) {
        if(accessMethodDataModel == null) {
            return Optional.empty();
        }
        UUID uuid = accessMethodDataModel.getAccessMethodID();
        AccessMethodID accessMethodID = new AccessMethodID(uuid);
        String nameDatabase = accessMethodDataModel.getName();
        NameWithNumbersAndSpecialChars nameToDomain = new NameWithNumbersAndSpecialChars(nameDatabase);
        AccessMethod accessMethod = _accessMethodFactory.createAccessMethod(accessMethodID, nameToDomain);
        return Optional.of(accessMethod);
    }
}
