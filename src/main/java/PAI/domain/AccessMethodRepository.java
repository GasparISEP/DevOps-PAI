package PAI.domain;

import java.util.ArrayList;

public class AccessMethodRepository {

    private ArrayList<AccessMethod> _accessMethodRepository;

    public AccessMethodRepository (){

        _accessMethodRepository = new ArrayList<>();
    }

    public boolean registerAccessMethod (String accessMethodName) throws Exception {
        AccessMethod accessMethod = new AccessMethod(accessMethodName);

        if (isAccessMethodRegistered(accessMethod))
            return false;

        _accessMethodRepository.add(accessMethod);
        return true;
    }

    public boolean isAccessMethodRegistered(AccessMethod accessMethod) {

        return _accessMethodRepository.contains(accessMethod);
    }
}
