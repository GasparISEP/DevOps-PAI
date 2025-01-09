package PAI.domain;

import java.util.ArrayList;

public class AccessMethodRepository {

    private ArrayList<AccessMethod> _AccessMethodRepository;

    public AccessMethodRepository (){

        _AccessMethodRepository = new ArrayList<>();
    }

    public boolean registerAccessMethod (String accessMethodName) throws Exception {
        AccessMethod accessMethod = new AccessMethod(accessMethodName);

        if (_AccessMethodRepository.contains(accessMethod))
            return false;

        _AccessMethodRepository.add(accessMethod);
        return true;
    }
}
