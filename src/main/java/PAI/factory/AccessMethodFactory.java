package PAI.factory;

import PAI.domain.AccessMethod;

public class AccessMethodFactory {

    public AccessMethod createAccessMethod(String accessMethodName) throws InstantiationException {
        return new AccessMethod(accessMethodName);
    }
}
