package ms.spring.crudbasic.api.controllers.base;

import jakarta.validation.Valid;
import ms.spring.crudbasic.api.infrastructure.configurations.swagger.ApiPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

public interface ICrudBaseController<
        C extends Record, // CreateDto
        U extends Record, // UpdateDto
        R extends Record> // ReadDto
{
    @PostMapping
    ResponseEntity create(@RequestBody @Valid C request, UriComponentsBuilder uriBuilder);

    @PutMapping("/{id}")
    ResponseEntity update(@PathVariable Long id, @RequestBody @Valid U request);

    @ApiPageable
    @GetMapping("/search")
    ResponseEntity<Page<R>> search(@PageableDefault(size = 10) Pageable request);

    @GetMapping("/{id}")
    ResponseEntity show(@PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity remove(@PathVariable Long id);
}
