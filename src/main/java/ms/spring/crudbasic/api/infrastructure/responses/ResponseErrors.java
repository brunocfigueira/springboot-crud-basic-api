package ms.spring.crudbasic.api.infrastructure.responses;

import java.util.List;

public record ResponseErrors(Boolean error, String message, List content) {
    public final static String hasProfilePermissions = "Este perfil já possui permissões vinculadas. Tente atualizar o cadastro.";
    public final static String violationPermissionId = "Não é permitido vinculuar permissão inexistente para este perfil.";
    public final static String violationProfileIdPermission = "Não é permitido o criar, alterar ou excluir permissão de perfil inválido.";
    public final static String violationUserProfileId = "Não é permitido vincular perfil inativo ou inexistente para este usuário.";
    public final static String violationParamRequest = "Violação de parâmetro na requeisição";
    public final static String argumentNotValid = "Detecção de um ou mais erros de validação";
    public final static String unexpected = "Ocoreu um erro inesperado durante a execução de processo";

    public static ResponseErrors argumentNotValid(List content) {
        return new ResponseErrors(true, argumentNotValid, content);
    }

    public static ResponseErrors ruleViolation(String message) {
        return new ResponseErrors(true, message, null);
    }

    public static ResponseErrors unexpected() {
        return new ResponseErrors(true, unexpected, null);
    }
}
