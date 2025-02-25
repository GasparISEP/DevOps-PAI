package PAI.domain;

public class AccessMethodFactory {

    public AccessMethod createAccessMethod(String accessMethodName) throws InstantiationException {
        return new AccessMethod(accessMethodName);
    }
}
