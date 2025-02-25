package PAI.domain;

public class AccessMethodFactory {

    public AccessMethod createAccessMethod(String accessMethodName) throws InstantiationException {
        AccessMethod accessMethod = new AccessMethod(accessMethodName);
        return accessMethod;
    }
}
