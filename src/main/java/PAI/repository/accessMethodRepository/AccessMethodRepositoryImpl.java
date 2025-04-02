package PAI.repository.accessMethodRepository;
import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.IAccessMethodFactory;

import java.util.List;
import java.util.Optional;

public class AccessMethodRepositoryImpl implements IRepositoryAccessMethod {
    private final IAccessMethodFactory _accessMethodFactory;
    private final List<AccessMethod> _accessMethods;

    public AccessMethodRepositoryImpl(IAccessMethodFactory accessMethodFactory, IAccessMethodListFactory accessMethodListFactory){
        _accessMethodFactory = accessMethodFactory;
        _accessMethods = accessMethodListFactory.createAccessMethodList();
    }

    public boolean registerAccessMethod (NameWithNumbersAndSpecialChars accessMethodName){
        try {
            AccessMethod accessMethod = _accessMethodFactory.createAccessMethod(accessMethodName);

            if (isAccessMethodRegistered(accessMethod)) return false;

            save(accessMethod);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    private boolean isAccessMethodRegistered (AccessMethod accessMethod){
        for(AccessMethod accessMethodDDD : _accessMethods){
            return accessMethodDDD.equals(accessMethod) || accessMethodDDD.sameAs(accessMethodDDD);
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
