package ms.spring.crudbasic.api.infrastructure.responses;

public record ResponseSuccess(Boolean success, String message) {

    public final static String createdSuccess = "Registro criado com sucesso.";
    public final static String updatedSuccess = "Registro atualizado com sucesso.";
    public final static String removedSuccess = "Registro excluído com sucesso.";

    public static ResponseSuccess createdSuccess() {
        return new ResponseSuccess(true, createdSuccess);
    }

    public static ResponseSuccess updatedSuccess() {
        return new ResponseSuccess(true, updatedSuccess);
    }

    public static ResponseSuccess removedSuccess() {
        return new ResponseSuccess(true, removedSuccess);
    }
}
