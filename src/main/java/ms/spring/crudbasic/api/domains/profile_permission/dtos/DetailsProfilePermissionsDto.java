package ms.spring.crudbasic.api.domains.profile_permission.dtos;

import ms.spring.crudbasic.api.domains.profile_permission.ProfilePermissionEntity;

public record DetailsProfilePermissionsDto(Long profileId, Long permissionId) {

    public DetailsProfilePermissionsDto(ProfilePermissionEntity entity) {
        this(entity.getProfileId(), entity.getPermissionId());
    }
}
