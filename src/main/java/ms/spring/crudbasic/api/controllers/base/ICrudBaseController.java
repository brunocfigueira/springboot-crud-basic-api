package ms.spring.crudbasic.api.controllers.base;

import jakarta.validation.Valid;
import ms.spring.crudbasic.api.infrastructure.configurations.swagger.ApiPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

public interface ICrudBaseController<CreateRecord extends Record, UpdateRecord extends Record, DetailsRecord extends Record> {
    @PostMapping
    ResponseEntity create(@RequestBody @Valid CreateRecord request, UriComponentsBuilder uriBuilder);

    @PutMapping("/{id}")
    ResponseEntity update(@PathVariable Long id, @RequestBody @Valid UpdateRecord request);

    @ApiPageable
    @GetMapping("/search")
    ResponseEntity<Page<DetailsRecord>> search(@PageableDefault(size = 10) Pageable request);

    @GetMapping("/{id}")
    ResponseEntity show(@PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity remove(@PathVariable Long id);
}
