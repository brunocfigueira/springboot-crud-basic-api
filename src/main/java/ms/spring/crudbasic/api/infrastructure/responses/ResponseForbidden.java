package ms.spring.crudbasic.api.infrastructure.responses;

public record ResponseForbidden(String status, String message) {

    public static String rootProfileChange = "Não é permitido nenhuma alteração para este perfil.";
    public static String rootUserChange = "Não é permitido nenhuma alteração para este usuário.";
    public static String rootPermissionChange = "Não é permitido nenhuma alteração para esta permissão.";

    public static ResponseForbidden emitMessage(String message) {
        return new ResponseForbidden("403_Forbidden", message);
    }
}
