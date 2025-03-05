package PAI.factory;

import PAI.domain.AccessMethod;

public interface IAccessMethodFactory {
    public AccessMethod createAccessMethod(String accessMethodName);
}
