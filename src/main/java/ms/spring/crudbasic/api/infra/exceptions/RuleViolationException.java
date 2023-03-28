package ms.spring.crudbasic.api.infra.exceptions;

public class RuleViolationException extends RuntimeException {

    private RuleViolationException(String message) {
        super(message);
    }

    public static RuleViolationException emitMessage(String message) {
        return new RuleViolationException(message);
    }
}
