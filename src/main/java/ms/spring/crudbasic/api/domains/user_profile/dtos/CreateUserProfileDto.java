package ms.spring.crudbasic.api.domains.user_profile.dtos;

import jakarta.validation.constraints.NotNull;

public record CreateUserProfileDto(
        @NotNull
        Long userId,
        @NotNull
        Long profileId
) {
}
