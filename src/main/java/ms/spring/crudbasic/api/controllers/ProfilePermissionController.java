package ms.spring.crudbasic.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import ms.spring.crudbasic.api.controllers.base.ICrudBaseController;
import ms.spring.crudbasic.api.domains.profile_permission.ProfilePermissionService;
import ms.spring.crudbasic.api.domains.profile_permission.dtos.CreateProfilePermissionsDto;
import ms.spring.crudbasic.api.domains.profile_permission.dtos.DetailsProfilePermissionsDto;
import ms.spring.crudbasic.api.domains.profile_permission.dtos.UpdateProfilePermissionsDto;
import ms.spring.crudbasic.api.infrastructure.configurations.swagger.ApiPageable;
import ms.spring.crudbasic.api.infrastructure.responses.ResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Collectors;

@SecurityRequirement(name = "bearer-key") // this parameter is equals defined in OpenApiConfiguration
@RestController
@RequestMapping("/profile-permission")
public class ProfilePermissionController implements ICrudBaseController<CreateProfilePermissionsDto, UpdateProfilePermissionsDto, DetailsProfilePermissionsDto> {
    @Autowired
    private ProfilePermissionService service;

    @Override
    @Operation(summary = "Create profile permission", description = "Execute operation of create profile permission")
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CreateProfilePermissionsDto request, UriComponentsBuilder uriBuilder) {
        var reference = service.create(request);
        var uri = uriBuilder.path("profile-permission/{profileId}").buildAndExpand(reference.getProfileId()).toUri();
        return ResponseEntity.created(uri).body(ResponseSuccess.createdSuccess());
    }

    @Override
    @Operation(summary = "Update profile permission", description = "Execute operation of update profile permission")
    @PutMapping("/{profileId}")
    public ResponseEntity update(@PathVariable Long profileId, @RequestBody @Valid UpdateProfilePermissionsDto request) {
        service.update(profileId, request);
        return ResponseEntity.ok(ResponseSuccess.updatedSuccess());
    }

    @Override
    @Operation(summary = "Search profile permission", description = "Execute operation of search profile permission")
    @ApiPageable
    @GetMapping("/search")
    public ResponseEntity<Page<DetailsProfilePermissionsDto>> search(@PageableDefault(size = 10) Pageable request) {
        var response = service.search(request);
        return ResponseEntity.ok(response);
    }

    @Override
    @Operation(summary = "Show details profile permission", description = "Execute operation of show details profile permission")
    @GetMapping("/{profileId}")
    public ResponseEntity show(@PathVariable Long profileId) {
        var reference = service.showDetailsProfilePermission(profileId);
        var details = reference.stream()
                .map(ref -> new DetailsProfilePermissionsDto(ref.getProfileId(), ref.getPermissionId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(details);
    }

    @Override
    @Operation(summary = "Remove profile permission", description = "Execute operation of remove profile permission")
    @DeleteMapping("/{profileId}")
    public ResponseEntity remove(@PathVariable Long profileId) {
        return service.remove(profileId) ? ResponseEntity.ok(ResponseSuccess.removedSuccess()) : ResponseEntity.notFound().build();
    }
}
