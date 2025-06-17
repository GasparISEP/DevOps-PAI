package PAI.exception;

public class CreditsExceededException extends RuntimeException {
    public CreditsExceededException(String message) {
        super(message);
    }
}
