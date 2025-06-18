package PAI.exception;

/**
 * Exception thrown when an entity already exists and a duplicate operation is attempted.
 */
public class AlreadyExistsException extends RuntimeException {
  public AlreadyExistsException(String message) {
    super(message);
  }
}
