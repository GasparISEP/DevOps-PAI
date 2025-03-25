package PAI.factory;

import PAI.domain.AccessMethod;

import java.util.ArrayList;

public class AccessMethodArrayListFactoryImpl implements IAccessMethodListFactory{
    public ArrayList<AccessMethod> createAccessMethodArrayList(){
        return new ArrayList<>();
    }
}
