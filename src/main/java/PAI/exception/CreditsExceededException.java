package PAI.exception;

/**
 * Exception thrown when a study plan has already reached the ECTS credit limit.
 */
public class CreditsExceededException extends RuntimeException {
    public CreditsExceededException(String message) {
        super(message);
    }
}
