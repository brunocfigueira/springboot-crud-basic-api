package ms.spring.crudbasic.api.domains.profiles.dtos;

import ms.spring.crudbasic.api.domains.profiles.ProfileEntity;

import java.util.Date;

public record DetailProfileDto(
        Long id,
        String name,
        String description,
        Boolean active,
        Date createdAt,
        Date updatedAt
) {
    public DetailProfileDto(ProfileEntity profile) {
        this(
                profile.getId(),
                profile.getName(),
                profile.getDescription(),
                profile.getActive(),
                profile.getCreatedAt(),
                profile.getUpdatedAt()
        );
    }
}
