package PAI.repository;

import PAI.domain.AccessMethod;
import PAI.factory.AccessMethodListFactory;
import PAI.factory.AccessMethodFactory;

import java.util.List;
import java.util.Optional;

public class AccessMethodRepository {

    private final AccessMethodFactory _accessMethodFactory;
    private final List<AccessMethod> _accessMethodRepository;

    public AccessMethodRepository (AccessMethodFactory accessMethodFactory, AccessMethodListFactory accessMethodListFactory) {
        _accessMethodFactory = accessMethodFactory;
        _accessMethodRepository = accessMethodListFactory.createAccessMethodArrayList();
    }


    public Optional <AccessMethod> createAccessMethod (String accessMethodName){
        try{
            AccessMethod accessMethod = _accessMethodFactory.createAccessMethod(accessMethodName);
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
