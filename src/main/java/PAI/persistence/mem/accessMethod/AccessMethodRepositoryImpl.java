package PAI.persistence.mem.accessMethod;
import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.repository.accessMethodRepository.IRepositoryAccessMethod;

import java.util.List;
import java.util.Optional;

public class AccessMethodRepositoryImpl implements IRepositoryAccessMethod {
    private final List<AccessMethod> _accessMethods;

    public AccessMethodRepositoryImpl(IAccessMethodListFactory accessMethodListFactory){
        _accessMethods = accessMethodListFactory.createAccessMethodList();
    }

    @Override
    public Optional<AccessMethod> saveAccessMethod (AccessMethod accessMethod){
        if(accessMethod == null){
            return Optional.empty();
        }
        if (isAccessMethodRegistered(accessMethod)) {
            return Optional.empty();
        }
        save(accessMethod);
        return Optional.of(accessMethod);
    }

    private boolean isAccessMethodRegistered(AccessMethod accessMethod) {
        for(AccessMethod accessMethodDDD : _accessMethods) {
            if(accessMethodDDD.equals(accessMethod) || accessMethodDDD.sameAs(accessMethod)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public AccessMethod save (AccessMethod accessMethod) {
        _accessMethods.add(accessMethod);
        return accessMethod;
    }

    @Override
    public Iterable<AccessMethod> findAll () {

        return _accessMethods;
    }

    public Optional<AccessMethod> getAccessMethodByName (NameWithNumbersAndSpecialChars accessMethodNameToSearch) {
        for ( AccessMethod accessMethod : _accessMethods) {
            if ( accessMethod.hasThisAccessMethodName(accessMethodNameToSearch)){
                return Optional.of(accessMethod);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<AccessMethod> ofIdentity (AccessMethodID id) {

        for (AccessMethod accessMethod : _accessMethods) {
            if (accessMethod.identity().equals(id)){
                return Optional.of(accessMethod);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity (AccessMethodID id) {
        for (AccessMethod accessMethod : _accessMethods) {
            if (accessMethod.identity().equals(id)){
                return true;
            }
        }
        return false;
    }
}
