package ms.spring.crudbasic.api.domains.dtos.profile_permission;

import ms.spring.crudbasic.api.domains.entities.ProfilePermissionEntity;

public record DetailsProfilePermissionsDto(Long profileId, Long permissionId) {

    public DetailsProfilePermissionsDto(ProfilePermissionEntity entity) {
        this(entity.getProfileId(), entity.getPermissionId());
    }
}
