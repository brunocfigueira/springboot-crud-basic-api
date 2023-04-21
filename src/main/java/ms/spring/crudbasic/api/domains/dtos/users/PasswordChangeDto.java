package ms.spring.crudbasic.api.domains.dtos.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ms.spring.crudbasic.api.infrastructure.validations.ConfirmedEqualsFields;

/**
 * Password rule explained
 *
 * Min 1 or more char TINY (?=(.*[a-z]){1,})
 * Min 1 or more char CAPITAL (?=(.*[A-Z]){1,})
 * Min 1 or more char DIGIT (.*[0-9]){1,})
 * Min 1 or more char ESPECIAL (?=(.*[!@#$%^&*()\-__+.])
 * Min 5 and Max 15 total characters {5,15}
 */
@ConfirmedEqualsFields(originalField = "newPassword", confirmationField = "newPasswordConfirmation")
public record PasswordChangeDto(
        @NotBlank
        @Size(min = 5, max = 15)
        @Pattern(regexp = "^(?=(.*[a-z]){1,})(?=(.*[A-Z]){1,})(?=(.*[0-9]){1,})(?=(.*[!@#$%^&*()\\-__+.]){1,}).{5,15}$",
                message = "A senha não está de acordo com as políticas de segurança")
        String newPassword,
        @NotBlank
        @Size(min = 5, max = 15)
        String newPasswordConfirmation

) {
}
