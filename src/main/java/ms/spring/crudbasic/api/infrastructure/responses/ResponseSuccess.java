package ms.spring.crudbasic.api.infrastructure.responses;

public record ResponseSuccess(Boolean success, String message) {

    public static String createdSuccess = "Registro criado com sucesso.";
    public static String updatedSuccess = "Registro atualizado com sucesso.";
    public static String removeSuccess = "Registro excluído com sucesso.";

    public static ResponseSuccess createdSuccess() {
        return new ResponseSuccess(true, createdSuccess);
    }

    public static ResponseSuccess updateSuccess() {
        return new ResponseSuccess(true, updatedSuccess);
    }

    public static ResponseSuccess removeSuccess() {
        return new ResponseSuccess(true, removeSuccess);
    }
}
