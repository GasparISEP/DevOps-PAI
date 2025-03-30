package PAI.repository.accessMethodRepositoryDDD;

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
}
