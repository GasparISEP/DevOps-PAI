package PAI.domain;

public class AccessMethod {

    private String _name;

    public AccessMethod(String acessMethodName) throws Exception {

        if (!isAccessMethodNameValid(acessMethodName))
            throw new Exception("Access method name cannot be null");
        this._name = acessMethodName;
    }

    private boolean isAccessMethodNameValid(String accessMethodName) {
        if (accessMethodName == null || accessMethodName.isBlank()) {
            return false;
        }
        return true;
    }
}
