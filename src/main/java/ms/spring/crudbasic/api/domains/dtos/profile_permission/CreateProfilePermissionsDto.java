package ms.spring.crudbasic.api.domains.dtos.profile_permission;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateProfilePermissionsDto(
        @NotNull
        Long profileId,
        @NotNull
        List<Long> permissionIds
) {
}
