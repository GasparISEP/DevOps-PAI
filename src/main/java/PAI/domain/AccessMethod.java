package PAI.domain;

public class AccessMethod {

    private String _name;

    public AccessMethod(String acessMethodName) throws Exception {

        if (!isAccessMethodNameValid(acessMethodName))
            throw new Exception("Access method name cannot be empty nor blank");
        this._name = acessMethodName;
    }

    private boolean isAccessMethodNameValid(String accessMethodName) {
        if (accessMethodName == null || accessMethodName.isBlank()) {
            return false;
        }
        return true;
    }


    @Override
    public boolean equals (Object objectToCompare){

        if (this == objectToCompare) {
            return true;
        }
        if (!(objectToCompare instanceof AccessMethod)) {
            return false;
        }
        AccessMethod testMethod = (AccessMethod) objectToCompare;

        if (_name.equals(testMethod._name)) {
            return true;
        }
        return false;
    }

}
