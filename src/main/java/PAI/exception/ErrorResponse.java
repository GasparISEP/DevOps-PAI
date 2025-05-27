package PAI.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String _code;
    private String _message;
    private LocalDateTime _timestamp;

    public ErrorResponse(String code, String message) {
        this._code = code;
        this._message = message;
        this._timestamp = LocalDateTime.now();
    }

    public String getCode() {
        return _code;
    }

    public String getMessage() {
        return _message;
    }

    public LocalDateTime getTimestamp() {
        return _timestamp;
    }
}