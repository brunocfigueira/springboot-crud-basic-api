package ms.spring.crudbasic.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import ms.spring.crudbasic.api.controllers.base.ICrudBaseController;
import ms.spring.crudbasic.api.domains.user_profile.UserProfileService;
import ms.spring.crudbasic.api.domains.user_profile.dtos.DetailsUserProfileDto;
import ms.spring.crudbasic.api.domains.user_profile.dtos.CreateUserProfileDto;
import ms.spring.crudbasic.api.domains.user_profile.dtos.UpdateUserProfileDto;
import ms.spring.crudbasic.api.infra.responses.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@SecurityRequirement(name = "bearer-key") // this parameter is equals defined in OpenApiConfiguration
@RestController
@RequestMapping("/user-profile")
public class UserProfileController implements ICrudBaseController<CreateUserProfileDto, UpdateUserProfileDto, DetailsUserProfileDto> {
    @Autowired
    private UserProfileService service;

    @Override
    public ResponseEntity create(@RequestBody @Valid CreateUserProfileDto request, UriComponentsBuilder uriBuilder) {
        var reference = service.create(request);
        var uri = uriBuilder.path("user-profile/{id}").buildAndExpand(reference.getId()).toUri();

        return ResponseEntity.created(uri).body(ResponseMessage.createdSuccess());
    }

    @Override
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid UpdateUserProfileDto request) {
        service.update(id, request);
        return ResponseEntity.ok(ResponseMessage.updateSuccess());
    }

    @Override
    public ResponseEntity<Page<DetailsUserProfileDto>> search(@PageableDefault(size = 10) Pageable request) {
        var responsePage = service.search(request);
        return ResponseEntity.ok(responsePage);
    }

    @Override
    public ResponseEntity show(@PathVariable Long id) {
        var reference = service.showDetails(id);
        return ResponseEntity.ok(new DetailsUserProfileDto(reference));
    }

    @Override
    public ResponseEntity remove(@PathVariable Long id) {
        return service.remove(id) ? ResponseEntity.ok(ResponseMessage.removeSuccess()) : ResponseEntity.notFound().build();
    }
}
