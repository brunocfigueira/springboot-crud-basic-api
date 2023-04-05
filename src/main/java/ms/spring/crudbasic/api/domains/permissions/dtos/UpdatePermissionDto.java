package ms.spring.crudbasic.api.domains.permissions.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePermissionDto(
        @NotBlank
        @Size(min = 3, max = 100)
        String name,
        @NotBlank
        @Size(min = 3, max = 255)
        String description
) {
}
