package ms.spring.crudbasic.api.domains.profile_permission.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateProfilePermissionsDto(
        @NotNull
        Long profileId,
        @NotNull
        List<Long> permissionIds
) {
}
