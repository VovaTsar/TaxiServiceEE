package my.project.model.exception;

public class AlreadyRegisteredRuntimeException extends RuntimeException {
    public AlreadyRegisteredRuntimeException() {
    }

    public AlreadyRegisteredRuntimeException(String s) {
        super(s);
    }

    public AlreadyRegisteredRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
