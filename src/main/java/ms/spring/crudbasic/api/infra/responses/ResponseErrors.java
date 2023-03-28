package ms.spring.crudbasic.api.infra.responses;

import java.util.List;

public record ResponseErrors(Boolean error, String message, List content) {
    public static ResponseErrors argumentNotValid(List content) {
        return new ResponseErrors(true, "Detecção de um ou mais erros de validação", content);
    }

    public static ResponseErrors ruleViolation(String message) {
        return new ResponseErrors(true, message, null);
    }

    public static ResponseErrors unexpected() {
        return new ResponseErrors(true, "Ocoreu um erro inesperado durante a execução de processo", null);
    }
}
