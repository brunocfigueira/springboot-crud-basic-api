package ms.spring.crudbasic.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import ms.spring.crudbasic.api.controllers.base.ICrudBaseController;
import ms.spring.crudbasic.api.domains.users.UserService;
import ms.spring.crudbasic.api.domains.users.dtos.CreateUserDto;
import ms.spring.crudbasic.api.domains.users.dtos.DetailsUserDto;
import ms.spring.crudbasic.api.domains.users.dtos.PasswordChangeDto;
import ms.spring.crudbasic.api.domains.users.dtos.UpdateUserDto;
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
@RequestMapping("/users")
public class UserController implements ICrudBaseController<CreateUserDto, UpdateUserDto, DetailsUserDto> {

    @Autowired
    private UserService service;

    @Operation(summary = "Create user", description = "Execute operation of create user")
    @Override
    public ResponseEntity create(@RequestBody @Valid CreateUserDto request, UriComponentsBuilder uriBuilder) {
        var reference = service.create(request);
        var uri = uriBuilder.path("users/{id}").buildAndExpand(reference.getId()).toUri();

        return ResponseEntity.created(uri).body(ResponseSuccess.createdSuccess());
    }

    @Operation(summary = "Show details user", description = "Execute operation of show details user")
    @Override
    public ResponseEntity show(@PathVariable Long id) {
        var reference = service.showDetails(id);
        return ResponseEntity.ok(new DetailsUserDto(reference));
    }

    @Operation(summary = "Update user", description = "Execute operation of update user")
    @Override
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid UpdateUserDto request) {
        service.update(id, request);
        return ResponseEntity.ok(ResponseSuccess.updatedSuccess());
    }

    @Operation(summary = "Change Password user", description = "Execute operation of change password user")
    @PatchMapping("/ChangePassword/{id}")
    public ResponseEntity changePassword(@PathVariable Long id, @RequestBody @Valid PasswordChangeDto request) {
        service.changePassword(id, request);
        return ResponseEntity.ok(ResponseSuccess.updatedSuccess());
    }

    @Operation(summary = "Search user", description = "Execute operation of search user")
    @Override
    public ResponseEntity<Page<DetailsUserDto>> search(@PageableDefault(size = 10) Pageable request) {
        var response = service.search(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Remove user", description = "Execute operation of remove user")
    @Override
    public ResponseEntity remove(@PathVariable Long id) {
        return service.remove(id) ? ResponseEntity.ok(ResponseSuccess.removedSuccess()) : ResponseEntity.notFound().build();
    }
}
