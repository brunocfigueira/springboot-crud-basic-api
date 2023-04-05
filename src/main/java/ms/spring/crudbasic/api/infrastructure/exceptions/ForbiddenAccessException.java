package ms.spring.crudbasic.api.infrastructure.exceptions;

public class ForbiddenAccessException extends RuntimeException {
    private ForbiddenAccessException(String message) {
        super(message);
    }

    private ForbiddenAccessException(String message, Exception exception) {
        super(message, exception);
    }

    public static ForbiddenAccessException emitMessage(String message) {
        return new ForbiddenAccessException(message);
    }

    public static ForbiddenAccessException emitMessageWithStack(String message, Exception exception) {
        return new ForbiddenAccessException(message, exception);
    }
}
