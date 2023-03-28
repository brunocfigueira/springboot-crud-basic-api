package ms.spring.crudbasic.api.domains.user_profile.dtos;

import jakarta.validation.constraints.NotNull;

public record UpdateUserProfileDto(@NotNull Long profileId) {
}
