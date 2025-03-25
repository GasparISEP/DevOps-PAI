package PAI.factory;

import PAI.domain.AccessMethod;

public class AccessMethodFactoryImpl implements IAccessMethodFactory{

    public AccessMethod createAccessMethod(String accessMethodName) throws InstantiationException {
        return new AccessMethod(accessMethodName);
    }
}
