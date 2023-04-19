package ms.spring.crudbasic.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import ms.spring.crudbasic.api.controllers.base.ICrudBaseController;
import ms.spring.crudbasic.api.domains.permissions.PermissionService;
import ms.spring.crudbasic.api.domains.permissions.dtos.CreatePermissionDto;
import ms.spring.crudbasic.api.domains.permissions.dtos.DetailsPermissionDto;
import ms.spring.crudbasic.api.domains.permissions.dtos.UpdatePermissionDto;
import ms.spring.crudbasic.api.infrastructure.configurations.swagger.ApiPageable;
import ms.spring.crudbasic.api.infrastructure.responses.ResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@SecurityRequirement(name = "bearer-key") // this parameter is equals defined in OpenApiConfiguration
@RestController
@RequestMapping("/permissions")
public class PermissionController implements ICrudBaseController<CreatePermissionDto, UpdatePermissionDto, DetailsPermissionDto> {
    @Autowired
    private PermissionService service;

    @Override
    @Operation(summary = "Create permission", description = "Execute operation of create permission")
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CreatePermissionDto request, UriComponentsBuilder uriBuilder) {
        var reference = service.create(request);
        var uri = uriBuilder.path("permissions/{id}").buildAndExpand(reference.getId()).toUri();

        return ResponseEntity.created(uri).body(ResponseSuccess.createdSuccess());
    }

    @Override
    @Operation(summary = "Update permission", description = "Execute operation of update permission")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid UpdatePermissionDto request) {
        service.update(id, request);
        return ResponseEntity.ok(ResponseSuccess.updatedSuccess());
    }

    @Override
    @Operation(summary = "Search permission", description = "Execute operation of search permission")
    @ApiPageable
    @GetMapping("/search")
    public ResponseEntity<Page<DetailsPermissionDto>> search(@PageableDefault(size = 10) Pageable request) {
        var response = service.search(request);
        return ResponseEntity.ok(response);
    }

    @Override
    @Operation(summary = "Show details permission", description = "Execute operation of show details permission")
    @GetMapping("/{id}")
    public ResponseEntity show(@PathVariable Long id) {
        var reference = service.showDetails(id);
        return ResponseEntity.ok(new DetailsPermissionDto(reference));
    }

    @Override
    @Operation(summary = "Remove permission", description = "Execute operation of remove permission")
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        return service.remove(id) ? ResponseEntity.ok(ResponseSuccess.removedSuccess()) : ResponseEntity.notFound().build();
    }
}
