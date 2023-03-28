package ms.spring.crudbasic.api.domains.user_profile.dtos;

import jakarta.validation.constraints.NotNull;
import ms.spring.crudbasic.api.domains.user_profile.UserProfileEntity;

import java.util.Date;

public record DetailsUserProfileDto(
        Long id,
        Long userId,
        Long profileId,
        Date createdAt,
        Date updatedAt
) {
    public DetailsUserProfileDto(UserProfileEntity entity) {
        this(
                entity.getId(),
                entity.getUserId(),
                entity.getProfileId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}