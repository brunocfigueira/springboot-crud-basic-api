package ms.spring.crudbasic.api.domains.permissions.dtos;

import ms.spring.crudbasic.api.domains.permissions.PermissionEntity;
import ms.spring.crudbasic.api.domains.profiles.ProfileEntity;

import java.util.Date;

public record DetailsPermissionDto(
        Long id,
        String name,
        String description,
        Date createdAt,
        Date updatedAt

) {
    public DetailsPermissionDto(PermissionEntity entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
