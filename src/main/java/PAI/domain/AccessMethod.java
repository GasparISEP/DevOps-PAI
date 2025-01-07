package PAI.domain;

public class AccessMethod {

    private String _name;

    public AccessMethod(String acessMethodName) throws Exception {

        if (acessMethodName == null)
            throw new Exception("Access method name cannot be null");
        this._name = acessMethodName;
    }
}
