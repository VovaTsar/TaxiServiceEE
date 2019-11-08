package my.project.model.exception;

public class EntityCreationRuntimeException extends RuntimeException {
    public EntityCreationRuntimeException() {
    }

    public EntityCreationRuntimeException(String message) {
        super(message);
    }

    public EntityCreationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
