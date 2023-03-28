package ms.spring.crudbasic.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import ms.spring.crudbasic.api.domains.profiles.ProfileService;
import ms.spring.crudbasic.api.domains.profiles.dtos.DetailProfileDto;
import ms.spring.crudbasic.api.domains.profiles.dtos.NewProfileDto;
import ms.spring.crudbasic.api.domains.profiles.dtos.UpdateProfileDto;
import ms.spring.crudbasic.api.infra.configurations.swagger.ApiPageable;
import ms.spring.crudbasic.api.infra.responses.ResponseMessage;
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
public class ProfileController {

    @Autowired
    private ProfileService service;

    @Operation(summary = "Create profile", description = "Execute operation of create profile")
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid NewProfileDto request, UriComponentsBuilder uriBuilder) {
        var reference = service.create(request);
        var uri = uriBuilder.path("profiles/{id}").buildAndExpand(reference.getId()).toUri();
        return ResponseEntity.created(uri).body(ResponseMessage.createdSuccess());
    }

    @Operation(summary = "Show details profile", description = "Execute operation of show details profile")
    @GetMapping("/{id}")
    public ResponseEntity show(@PathVariable Long id) {
        var reference = service.showDetails(id);
        return ResponseEntity.ok(new DetailProfileDto(reference));
    }

    @Operation(summary = "Update profile", description = "Execute operation of update profile")
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid UpdateProfileDto request) {
        service.update(id, request);
        return ResponseEntity.ok(ResponseMessage.updateSuccess());
    }

    @Operation(summary = "Search profile", description = "Execute operation of search profile")
    @ApiPageable
    @GetMapping("/search")
    public ResponseEntity<Page<DetailProfileDto>> search(@PageableDefault(size = 10) Pageable request) {
        var responsePage = service.search(request);
        return ResponseEntity.ok(responsePage);
    }

    @Operation(summary = "Remove profile", description = "Execute operation of remove profile")
    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        return service.remove(id) ? ResponseEntity.ok(ResponseMessage.removeSuccess()) : ResponseEntity.notFound().build();
    }
}
