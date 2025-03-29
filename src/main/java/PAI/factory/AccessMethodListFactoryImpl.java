package PAI.factory;

import PAI.domain.AccessMethod;

import java.util.ArrayList;
import java.util.List;

public class AccessMethodListFactoryImpl implements IAccessMethodListFactory{
    public List<AccessMethod> createAccessMethodList(){
        return new ArrayList<>();
    }
}
