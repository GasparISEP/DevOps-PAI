package PAI.factory;

import PAI.domain.AccessMethod;

import java.util.ArrayList;

public class AccessMethodArrayListFactory implements IAccessMethodListFactory{
    public ArrayList<AccessMethod> createAccessMethodArrayList(){
        return new ArrayList<>();
    }
}
