package PAI.mapper.accessMethod;

import PAI.domain.accessMethod.AccessMethod;
import PAI.persistence.datamodel.AccessMethodDataModel;

import java.util.Optional;

public interface IAccessMethodMapper {
    Optional<AccessMethodDataModel> toDataModel(AccessMethod accessMethod);
    Optional<AccessMethod> toDomain(AccessMethodDataModel accessMethodDataModel);
}
