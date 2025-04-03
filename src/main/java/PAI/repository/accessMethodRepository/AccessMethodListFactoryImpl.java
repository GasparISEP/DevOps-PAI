package PAI.repository.accessMethodRepository;

import PAI.domain.accessMethod.AccessMethod;

import java.util.ArrayList;
import java.util.List;

public class AccessMethodListFactoryImpl implements IAccessMethodListFactory {
    public List<AccessMethod> createAccessMethodList() {
        return new ArrayList<AccessMethod>();
    }
}
