package PAI.persistence.mem.accessMethod;

import PAI.domain.accessMethod.AccessMethod;

import java.util.List;

public interface IAccessMethodListFactory {
    List<AccessMethod> createAccessMethodList();
}
