package ms.spring.crudbasic.api.domains.users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
        @NotNull
        Long profileId,
        @NotBlank
        @Size(min = 3, max = 100)
        String username,
        @NotBlank
        @Size(min = 3, max = 100)
        String login,
        @NotBlank
        @Size(min = 3, max = 255)
        String password,
        @Email
        @Size(min = 3, max = 255)
        String email

) {
}
