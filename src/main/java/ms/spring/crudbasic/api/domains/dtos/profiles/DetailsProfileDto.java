package ms.spring.crudbasic.api.domains.dtos.profiles;

import ms.spring.crudbasic.api.domains.entities.ProfileEntity;

import java.util.Date;

public record DetailsProfileDto(
        Long id,
        String name,
        String description,
        String acronym,
        Boolean active,
        Date createdAt,
        Date updatedAt
) {
    public DetailsProfileDto(ProfileEntity entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getAcronym().toUpperCase(),
                entity.getActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
