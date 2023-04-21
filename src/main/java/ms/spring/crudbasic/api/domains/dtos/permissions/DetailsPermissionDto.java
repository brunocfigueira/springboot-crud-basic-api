package ms.spring.crudbasic.api.domains.dtos.permissions;

import ms.spring.crudbasic.api.domains.entities.PermissionEntity;

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
