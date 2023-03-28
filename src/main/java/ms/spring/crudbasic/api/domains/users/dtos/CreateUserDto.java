package ms.spring.crudbasic.api.domains.users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(
        @NotBlank
        String username,
        @NotBlank
        String login,
        @NotBlank
        String password,
        @Email
        String email

) {
}
