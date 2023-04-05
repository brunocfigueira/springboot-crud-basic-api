package ms.spring.crudbasic.api.domains.users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDto(
        @NotNull
        Long profileId,
        @NotBlank
        String username,
        @NotBlank
        String login,
        @Email
        String email,
        @NotNull
        Boolean active
) {
}
