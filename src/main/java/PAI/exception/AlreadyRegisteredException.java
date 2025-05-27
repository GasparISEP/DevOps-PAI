package PAI.exception;

public class AlreadyRegisteredException extends RuntimeException {

    public AlreadyRegisteredException(String entityName) {
        super(entityName + " is already registered.");
    }
}