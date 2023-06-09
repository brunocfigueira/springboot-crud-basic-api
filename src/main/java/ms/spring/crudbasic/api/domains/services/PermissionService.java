package ms.spring.crudbasic.api.domains.services;

import ms.spring.crudbasic.api.domains.entities.PermissionEntity;
import ms.spring.crudbasic.api.domains.repositories.IPermissionRepository;
import ms.spring.crudbasic.api.domains.dtos.permissions.CreatePermissionDto;
import ms.spring.crudbasic.api.domains.dtos.permissions.DetailsPermissionDto;
import ms.spring.crudbasic.api.domains.dtos.permissions.UpdatePermissionDto;
import ms.spring.crudbasic.api.infrastructure.services.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PermissionService extends CrudService<IPermissionRepository, PermissionEntity, CreatePermissionDto, UpdatePermissionDto> {
    public PermissionService(IPermissionRepository repository) {
        super(repository);
    }

    private void applyRuleValidationDelete(Long permissionId) {
        checkRootPermissionId(permissionId);
        checkExistsReferenceId(permissionId);
    }


    @Override
    public PermissionEntity create(CreatePermissionDto dto) {

        var reference = PermissionEntity.createNewPermission(dto);
        save(reference);
        return reference;
    }

    @Override
    public PermissionEntity update(Long id, UpdatePermissionDto dto) {
        checkRootPermissionId(id);
        var reference = getReference(id);
        reference.setUpdatedAt(new Date());
        reference.setName(dto.name().toUpperCase());
        reference.setDescription(dto.description());
        save(reference);
        return reference;
    }

    @Override
    public PermissionEntity showDetails(Long id) {
        return getReference(id);
    }

    @Override
    public Page<DetailsPermissionDto> search(Pageable pageable) {
        return repository.findAll(pageable).map(entity -> new DetailsPermissionDto(entity));
    }

    @Override
    public boolean remove(Long id) {
        applyRuleValidationDelete(id);
        delete(id);
        return true;
    }

    public boolean existsPermission(Long id) {
        return exists(id);
    }
}
