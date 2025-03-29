package PAI.repository.accessMethodRepositoryDDD;

import PAI.domain.accessMethodDDD.AccessMethodDDD;

import java.util.ArrayList;
import java.util.List;

public class AccessMethodDDDListFactoryImpl implements IAccessMethodDDDListFactory{
    public List<AccessMethodDDD> createAccessMethodList() {
        return new ArrayList<AccessMethodDDD>();
    }
}
