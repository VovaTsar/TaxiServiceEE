package my.project.model.exception;

public class RegistrationRuntimeException extends RuntimeException {

    public RegistrationRuntimeException() { }

    public RegistrationRuntimeException(String s) {
        super(s);
    }

    public RegistrationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
