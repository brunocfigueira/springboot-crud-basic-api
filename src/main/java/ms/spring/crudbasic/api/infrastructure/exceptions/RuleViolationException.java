package ms.spring.crudbasic.api.infrastructure.exceptions;

public class RuleViolationException extends RuntimeException {

    private RuleViolationException(String message) {
        super(message);
    }

    private RuleViolationException(String message, Exception exception) {
        super(message, exception);
    }

    public static RuleViolationException emitMessage(String message) {
        return new RuleViolationException(message);
    }

    public static RuleViolationException emitMessageWithStack(String message, Exception exception) {
        return new RuleViolationException(message, exception);
    }
}
