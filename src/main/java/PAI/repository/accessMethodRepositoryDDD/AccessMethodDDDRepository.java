package PAI.repository.accessMethodRepositoryDDD;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethodDDD.AccessMethodDDD;
import PAI.domain.accessMethodDDD.IAccessMethodDDDFactory;

import java.util.List;
import java.util.Optional;

public class AccessMethodDDDRepository {
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

            _accessMethods.add(accessMethod);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    private boolean isAccessMethodRegistered (AccessMethodDDD accessMethod){
        return _accessMethods.contains(accessMethod);
    }

    public Optional<AccessMethodDDD> getAccessMethodByName (NameWithNumbersAndSpecialChars accessMethodNameToSearch) {
        for ( AccessMethodDDD accessMethod : _accessMethods) {
            if ( accessMethod.hasThisAccessMethodName(accessMethodNameToSearch)){
                return Optional.of(accessMethod);
            }
        }
        return Optional.empty();
    }
}
