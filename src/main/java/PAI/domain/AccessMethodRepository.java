package PAI.domain;

import java.util.ArrayList;
import java.util.Optional;

public class AccessMethodRepository {

    private final AccessMethodFactory _accessMethodFactory;
    private ArrayList<AccessMethod> _accessMethodRepository = new ArrayList<>();

    public AccessMethodRepository (AccessMethodFactory accessMethodFactory) {
        _accessMethodFactory = accessMethodFactory;
    }


    public Optional <AccessMethod> createAccessMethod (String name){
        try{
            AccessMethod accessMethod = new AccessMethod(name);
            if(!isAccessMethodRegistered(accessMethod)){
                _accessMethodRepository.add(accessMethod);
                return Optional.of(accessMethod);
            }

            return Optional.empty();

        } catch (InstantiationException e) {
            return Optional.empty();
        }
    }

    public boolean registerAccessMethod (String accessMethodName) throws Exception {

        AccessMethod accessMethod = _accessMethodFactory.createAccessMethod(accessMethodName);

        if (isAccessMethodRegistered(accessMethod))
            return false;

        _accessMethodRepository.add(accessMethod);
        return true;
    }

    public boolean isAccessMethodRegistered(AccessMethod accessMethod) {

        return _accessMethodRepository.contains(accessMethod);
    }

    public Optional <AccessMethod> getAccessMethodByName(String name) {
        for ( AccessMethod accessMethod : _accessMethodRepository) {
            if ( accessMethod.hasThisAccessMethodName(name)){
                return Optional.of(accessMethod);
            }
        }
        return Optional.empty();
    }
}
