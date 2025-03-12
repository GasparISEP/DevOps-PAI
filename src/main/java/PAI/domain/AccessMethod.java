package PAI.domain;

public class AccessMethod {

    private final String _accessMethodName;

    public AccessMethod(String accessMethodName) throws InstantiationException {

        if (!isAccessMethodNameValid (accessMethodName))
            throw new InstantiationException("Access method name cannot be empty nor blank");
        this._accessMethodName = accessMethodName;
    }

    private boolean isAccessMethodNameValid (String accessMethodName) {
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

        if (_accessMethodName.equals(testMethod._accessMethodName)) {
            return true;
        }
        return false;
    }

    public boolean hasThisAccessMethodName(String name) {return _accessMethodName.equals(name);}
}
