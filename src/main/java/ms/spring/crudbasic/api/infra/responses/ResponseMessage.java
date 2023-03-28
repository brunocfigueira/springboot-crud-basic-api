package ms.spring.crudbasic.api.infra.responses;

public record ResponseMessage(
        Boolean success,
        String message

) {
    public static ResponseMessage createdSuccess() {
        return new ResponseMessage(true, "Registro criado com sucesso.");
    }
    public static ResponseMessage updateSuccess() {
        return new ResponseMessage(true, "Registro atualizado com sucesso.");
    }
    public static ResponseMessage removeSuccess() {
        return new ResponseMessage(true, "Registro excluído com sucesso.");
    }
}
