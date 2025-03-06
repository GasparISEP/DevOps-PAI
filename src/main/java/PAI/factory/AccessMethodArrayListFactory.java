package PAI.factory;

import PAI.domain.AccessMethod;

import java.util.ArrayList;
import java.util.List;

public class AccessMethodArrayListFactory implements IAccessMethodListFactory{
    public List<AccessMethod> createAccessMethodArrayList(){
        return new ArrayList<>();
    }
}
