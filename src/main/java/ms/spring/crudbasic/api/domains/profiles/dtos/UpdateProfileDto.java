package ms.spring.crudbasic.api.domains.profiles.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProfileDto(
        @NotBlank
        @Size(min = 3, max = 100)
        String name,
        @NotBlank
        @Size(min = 3, max = 255)
        String description,
        @NotBlank
        @Size(min = 3, max = 50)
        String acronym,
        @NotNull
        Boolean active
) {
}
