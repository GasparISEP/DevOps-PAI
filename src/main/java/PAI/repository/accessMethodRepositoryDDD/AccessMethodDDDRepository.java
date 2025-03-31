package PAI.repository.accessMethodRepositoryDDD;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethodDDD.AccessMethodDDD;
import PAI.domain.accessMethodDDD.IAccessMethodDDDFactory;

import java.util.List;

public class AccessMethodDDDRepository {
    private IAccessMethodDDDFactory _accessMethodFactory;
    private List<AccessMethodDDD> _accessMethods;

    public AccessMethodDDDRepository (IAccessMethodDDDFactory accessMethodFactory, IAccessMethodDDDListFactory accessMethodListFactory){
        _accessMethodFactory = accessMethodFactory;
        _accessMethods = accessMethodListFactory.createAccessMethodList();
    }

    public boolean registerAccessMethod (NameWithNumbersAndSpecialChars accessMethodName){
        try {
            AccessMethodDDD accessMethod = _accessMethodFactory.createAccessMethod(accessMethodName);

            if (_accessMethods.contains(accessMethod)) return false;

            _accessMethods.add(accessMethod);
            return true;

        }catch (Exception e){
            return false;
        }
    }
}
