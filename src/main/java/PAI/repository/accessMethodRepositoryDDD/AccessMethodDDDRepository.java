package PAI.repository.accessMethodRepositoryDDD;
import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethodDDD.AccessMethodDDD;
import PAI.domain.accessMethodDDD.IAccessMethodDDDFactory;

import java.util.List;
import java.util.Optional;

public class AccessMethodDDDRepository implements IRepositoryAccessMethodDDD{
    private final IAccessMethodDDDFactory _accessMethodFactory;
    private final List<AccessMethodDDD> _accessMethods;

    public AccessMethodDDDRepository (IAccessMethodDDDFactory accessMethodFactory, IAccessMethodDDDListFactory accessMethodListFactory){
        _accessMethodFactory = accessMethodFactory;
        _accessMethods = accessMethodListFactory.createAccessMethodList();
    }

    public boolean registerAccessMethod (NameWithNumbersAndSpecialChars accessMethodName){
        try {
            AccessMethodDDD accessMethod = _accessMethodFactory.createAccessMethod(accessMethodName);

            if (isAccessMethodRegistered(accessMethod)) return false;

            save(accessMethod);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    private boolean isAccessMethodRegistered (AccessMethodDDD accessMethod){
        for(AccessMethodDDD accessMethodDDD : _accessMethods){
            return accessMethodDDD.equals(accessMethod) || accessMethodDDD.sameAs(accessMethodDDD);
        }
        return false;
    }

    @Override
    public AccessMethodDDD save (AccessMethodDDD accessMethod) {
        _accessMethods.add(accessMethod);
        return accessMethod;
    }

    @Override
    public Iterable<AccessMethodDDD> findAll () {

        return _accessMethods;
    }

    public Optional<AccessMethodDDD> getAccessMethodByName (NameWithNumbersAndSpecialChars accessMethodNameToSearch) {
        for ( AccessMethodDDD accessMethod : _accessMethods) {
            if ( accessMethod.hasThisAccessMethodName(accessMethodNameToSearch)){
                return Optional.of(accessMethod);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<AccessMethodDDD> ofIdentity (AccessMethodID id) {

        for (AccessMethodDDD accessMethod : _accessMethods) {
            if (accessMethod.identity().equals(id)){
                return Optional.of(accessMethod);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity (AccessMethodID id) {
        for (AccessMethodDDD accessMethod : _accessMethods) {
            if (accessMethod.identity().equals(id)){
                return true;
            }
        }
        return false;
    }
}
