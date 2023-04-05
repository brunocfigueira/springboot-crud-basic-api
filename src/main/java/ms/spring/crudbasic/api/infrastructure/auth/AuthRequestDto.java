package ms.spring.crudbasic.api.infrastructure.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDto(@NotBlank String login, @NotBlank String password) {
}
