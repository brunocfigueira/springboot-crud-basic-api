package ms.spring.crudbasic.api.domains.users.dtos;
import jakarta.validation.constraints.*;
public record UpdateUserDto(
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
