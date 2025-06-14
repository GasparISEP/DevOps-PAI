package PAI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Already registered entity in the persistence
    @ExceptionHandler(AlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyRegistered(AlreadyRegisteredException ex) {
        ErrorResponse error = new ErrorResponse(
                "ALREADY_REGISTERED",
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    //Generic invalid argument
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "ARGUMENT_INVALID",
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //Business rule violated
    @ExceptionHandler(BusinessRuleViolationException.class)
    public ResponseEntity<ErrorResponse> handleBusinessRuleViolation(BusinessRuleViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "BUSINESS_RULE_VIOLATED",
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //Generic exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                "INTERNAL_ERROR",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //RestController's parameters invalid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        String message = ex.getFieldError() != null
                ? ex.getFieldError().getDefaultMessage()
                : "Invalid input";

        ErrorResponse error = new ErrorResponse("ARGUMENT_INVALID", message);
        return ResponseEntity.badRequest().body(error);
    }

    //Entity not found in the persistence
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                "RESOURCE_NOT_FOUND",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //Input missing parameters from user interface
    @ExceptionHandler(org.springframework.web.bind.MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParameter(org.springframework.web.bind.MissingServletRequestParameterException ex) {
        String message = String.format("The required parameter '%s' is missing.", ex.getParameterName());

        ErrorResponse error = new ErrorResponse(
                "MISSING_PARAMETER",
                message
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handles invalid or missing HTTP request body and returns a 400 Bad Request
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException messageNotReadableException) {
        ErrorResponse error = new ErrorResponse(
                "INVALID_REQUEST_BODY",
                "Request body is missing or malformed"
        );
        return ResponseEntity.badRequest().body(error);
    }

    // Handles MethodArgumentTypeMismatchException and throws the proper BadRequest(400) instead of InternalServerError(500)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = String.format("Invalid parameter '%s': %s", ex.getName(), ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(message, "BAD_REQUEST");
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(CourseEditionCreationException.class)
    public ResponseEntity<ErrorResponse> handleCourseEditionCreationException(CourseEditionCreationException ex) {
        ErrorResponse error = new ErrorResponse(
                "CREATION_FAILED",
                ex.getMessage()
        );
        return ResponseEntity.badRequest().body(error);
    }

}
