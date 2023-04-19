package ms.spring.crudbasic.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import ms.spring.crudbasic.api.controllers.base.ICrudBaseController;
import ms.spring.crudbasic.api.domains.profiles.ProfileService;
import ms.spring.crudbasic.api.domains.profiles.dtos.CreateProfileDto;
import ms.spring.crudbasic.api.domains.profiles.dtos.DetailsProfileDto;
import ms.spring.crudbasic.api.domains.profiles.dtos.UpdateProfileDto;
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
@RequestMapping("/profiles")
public class ProfileController implements ICrudBaseController<CreateProfileDto, UpdateProfileDto, DetailsProfileDto> {

    @Autowired
    private ProfileService service;

    @Operation(summary = "Create profile", description = "Execute operation of create profile")
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CreateProfileDto request, UriComponentsBuilder uriBuilder) {
        var reference = service.create(request);
        var uri = uriBuilder.path("profiles/{id}").buildAndExpand(reference.getId()).toUri();
        return ResponseEntity.created(uri).body(ResponseSuccess.createdSuccess());
    }

    @Operation(summary = "Show details profile", description = "Execute operation of show details profile")
    @GetMapping("/{id}")
    public ResponseEntity show(@PathVariable Long id) {
        var reference = service.showDetails(id);
        return ResponseEntity.ok(new DetailsProfileDto(reference));
    }

    @Operation(summary = "Update profile", description = "Execute operation of update profile")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid UpdateProfileDto request) {
        service.update(id, request);
        return ResponseEntity.ok(ResponseSuccess.updatedSuccess());
    }

    @Operation(summary = "Search profile", description = "Execute operation of search profile")
    @ApiPageable
    @GetMapping("/search")
    public ResponseEntity<Page<DetailsProfileDto>> search(@PageableDefault(size = 10) Pageable request) {
        var response = service.search(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Remove profile", description = "Execute operation of remove profile")
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        return service.remove(id) ? ResponseEntity.ok(ResponseSuccess.removedSuccess()) : ResponseEntity.notFound().build();
    }
}
