package ms.spring.crudbasic.api.infrastructure.auth;

import java.util.Date;

public record AuthResponseTokenDto(
        Date generatedAt,
        String token
) {

    public AuthResponseTokenDto(String tokenJWT) {
        this(new Date(), tokenJWT);
    }
}
